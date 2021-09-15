package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.*;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service(DeliveryService.NAME)
public class DeliveryServiceBean implements DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceBean.class);

    @Inject
    private Persistence persistence;

    //1 Расчет стоимости доставки, стоимость доставки перевозчика (cost) умноженное на расстояние и веса товара,
    // актуально только для промышленных товаров в доставке.
    @Override
    public BigDecimal getCostOfDelivery(Delivery delivery) {

        BigDecimal resultCostOfDelivery = new BigDecimal(0.0);

        View viewWight = new View(Delivery.class)
                .addProperty("goods")
                ;

        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId").setView(viewWight);
            query.setParameter("deliveryId", delivery.getId());
            Delivery delivery1 = (Delivery) query.getSingleResult();

            BigDecimal carrierShippingCost = delivery1.getCarrier().getCost();

            int transportationDistance = delivery1.getDistance();

            transaction.commit();

            List<IndustrialProducts> industrialProducts = new ArrayList<>();
            for (int i = 0; i < delivery1.getGoods().size(); i++) {
                if (delivery1.getGoods().get(i).getClass().toString().endsWith("Products")){
                    industrialProducts.add((IndustrialProducts) delivery1.getGoods().get(i));
                }
            }
            double weightOfAllShippingItems = 0.0;
            for (IndustrialProducts industrialProduct : industrialProducts) {
                weightOfAllShippingItems = weightOfAllShippingItems + industrialProduct.getWeight();
            }

            resultCostOfDelivery = new BigDecimal(String.valueOf(carrierShippingCost.multiply(new BigDecimal(transportationDistance * weightOfAllShippingItems))));
            log.debug("Стоимости доставки: {} ", resultCostOfDelivery);
        }
        return resultCostOfDelivery;
    }

    // 2. Сервис проверки, что дата доставки не превышает срок годности товара, актуально только для продуктов, не используется запрос в базу.
    @Override
    public List<FoodStuffs> checkExpirationDate (Delivery delivery) {

        List<FoodStuffs> foodProductsWithAnExpirationDateExceedingTheDeliveryDate = new ArrayList<>();

        View viewWight = new View(Delivery.class)
                .addProperty("goods");
        try (final Transaction transaction = persistence.createTransaction()) {
//            final EntityManager entityManager = persistence.getEntityManager();
//            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId").setView(viewWight);
//            query.setParameter("deliveryId", delivery.getId());
//            Delivery delivery1 = (Delivery) query.getSingleResult();
//            transaction.commit();



            List<FoodStuffs> foodStuffs  = new ArrayList<>();
            List<UUID> uuidList = new ArrayList<>();
            for (int i = 0; i < delivery.getGoods().size(); i++) {
               if (delivery.getGoods().get(i) instanceof FoodStuffs) {
                    foodStuffs.add((FoodStuffs)delivery.getGoods().get(i));
                    uuidList.add(delivery.getId());
                }
            }

            if (CollectionUtils.isEmpty(foodStuffs)){
                return foodProductsWithAnExpirationDateExceedingTheDeliveryDate;
            }

//            if (uuidList.size() != 0) {
//                log.info("convertNewDeliveriesToCanceled() uuidList {} ", uuidList);
//                final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId");
//                for (UUID item : uuidList) {
//                    query.setParameter("deliveryId", item);
//                }

//            нужно получить ExpirationDate у foodStuffs запросом из базы
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_FoodStuffs d where d.id = :deliveryId");
            for (FoodStuffs item : foodStuffs) {
                query.setParameter("deliveryId", item.getId());
            }

            foodStuffs = query.getResultList();
//            for (Delivery item : deliveryList) {
//                item.setStatus(StatusDelivery.CANCEL);
//            }
            transaction.commit();


            for (int i = 0; i < foodStuffs.size(); i++) {
                if (foodStuffs.get(i).getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(delivery.getDate()) < 0) {
                    log.info("Дата доставки: {} превышает срок годности товара {} :", delivery, foodStuffs);
                    foodProductsWithAnExpirationDateExceedingTheDeliveryDate.add(foodStuffs.get(i));
                }
           }
            log.info("Дата доставки не превышает срок годности товара в доставке");
            return foodProductsWithAnExpirationDateExceedingTheDeliveryDate;
        }
    }

//  3. Список доставок осуществленных за последние 7 дней перевозчиком.
    @Override
    public List<Delivery> getDeliveryInTheLast7Days (Carrier carrier) {
        log.info("777 {}", carrier.getName());
        View viewWight1 = new View(Delivery.class)
                .addProperty("carrier",new View(Carrier.class)
                        .addProperty("name")
                )
                .addProperty("goods", new View(Goods.class)
                        .addProperty("name")
                );
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Delivery d where @between(d.date, now, now+1, day) and d.carrier = :carrierId");
            query.setParameter("carrierId", carrier);
            query.setView(viewWight1);
            List<Delivery> deliveryList = query.getResultList();
            transaction.commit();
            log.info("Доставки поставщика {} за последение 7 дней: получены  ", carrier.getName());
            return deliveryList;
        }
    }



//        try (final Transaction transaction = persistence.createTransaction()) {
//            final EntityManager entityManager = persistence.getEntityManager();
////            final Query query = entityManager.createQuery("select d.carrier.cost from carrier_Delivery d where d.delivery = :deliveryId");
////            final Query query = entityManager.createQuery("select d from carrier_Delivery d");
////            final Query query = entityManager.createQuery("select d.goods from carrier_Delivery d");
////            final Query query = entityManager.createQuery("select SUM(d.goods.weight) from carrier_Delivery d where ");
////            final Query query = entityManager.createQuery("select g from carrier_Delivery d where ");
////            final Query query = entityManager.createQuery("select g from carrier_Goods g join g.deliveries d where type(g) = :subClass and d.number = :number");
////            final Query query = entityManager.createQuery("select g from carrier_Goods g left join g.deliveries d where type(g) = :subClass and d.number = :number").setView(viewWight);
//            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId").setView(viewWight);
//            query.setParameter("deliveryId", delivery.getId());
////            query.setParameter("subClass", IndustrialProducts.class);
////            query.setParameter("number", "1");
////            final Query query = entityManager.createQuery("select d.carrier.cost from carrier_Delivery d TYPE(d.goods) = :IndustrialProducts");
////            //query.setParameter("deliveryId", delivery.getId());
////            resultCostOfDelivery = (BigDecimal) query.getFirstResult();
//
////            List<Object> resultCostOfDelivery1 =  query.getResultList();
//

}
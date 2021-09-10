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

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service(DeliveryService.NAME)
public class DeliveryServiceBean implements DeliveryService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceBean.class);

    @Inject
    private Persistence persistence;


    //Расчет стоимости доставки, стоимость доставки перевозчика (cost) умноженное на расстояние и веса товара,
    // актуально только для промышленных товаров в доставке.
    @Override
    public BigDecimal getCostOfDelivery(Delivery delivery) {

        BigDecimal resultCostOfDelivery = new BigDecimal(0.0);

        View viewWight = new View(Delivery.class)
                .addProperty("goods");

        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId").setView(viewWight);
            query.setParameter("deliveryId", delivery.getId());
            Delivery delivery1 = (Delivery) query.getSingleResult();

            BigDecimal carrierShippingCost = delivery1.getCarrier().getCost();

            int transportationDistance = Integer.parseInt(delivery1.getDistance());

            transaction.commit();

            List<IndustrialProducts> industrialProducts = new ArrayList<>();
            for (int i = 0; i < delivery1.getGoods().size(); i++) {
                if (delivery1.getGoods().get(i).getClass().toString().endsWith("Products")){
                    industrialProducts.add((IndustrialProducts) delivery1.getGoods().get(i));
                }
            }
            double weightOfAllShippingItems = 0.0;
            for (int i = 0; i < industrialProducts.size(); i++) {
                weightOfAllShippingItems = weightOfAllShippingItems + industrialProducts.get(i).getWeight();
            }

            resultCostOfDelivery = new BigDecimal(String.valueOf(carrierShippingCost.multiply(new BigDecimal(transportationDistance * weightOfAllShippingItems))));
            log.info("Стоимости доставки: {} ", resultCostOfDelivery);
        }
        return resultCostOfDelivery;
    }

    // 2. Сервис проверки, что дата доставки не превышает срок годности товара, актуально только для продуктов, не используется запрос в базу.
    // Если дата доставки превышает срок годнгости вернет true
    @Override
    public Boolean checkExpirationDate (Delivery delivery) {

        View viewWight = new View(Delivery.class)
                .addProperty("goods");

        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId").setView(viewWight);
            query.setParameter("deliveryId", delivery.getId());
            Delivery delivery1 = (Delivery) query.getSingleResult();
            transaction.commit();

            boolean result = false;
            List<FoodStuffs> foodStuffs = new ArrayList<>();
            for (int i = 0; i < delivery.getGoods().size(); i++) {
//            System.out.println(delivery.getGoods().get(i).getClass().toString().endsWith("Stuffs"));
                if (delivery.getGoods().get(i) instanceof FoodStuffs) {
                    foodStuffs.add((FoodStuffs) delivery.getGoods().get(i));
                }
            }
//            System.out.println(foodStuffs);
//            System.out.println("111" + foodStuffs.get(0).getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(delivery.getDate()));
            for (int i = 0; i < foodStuffs.size(); i++) {
                if (foodStuffs.get(i).getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(delivery.getDate()) < 0) {
//                    System.out.println(foodStuffs.get(i).getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(delivery.getDate()));
//                    result = true;
                    log.info("Дата доставки превышает срок годности товара в доставке {} :", foodStuffs);
                return result ;
                }
//        log.info(String.valueOf(foodStuffs));
            }
            log.info("Дата доставки не превышает срок годности товара в доставке");
            return result;
        }
    }


//  3. Список доставок осуществленных за последние 7 дней перевозчиком.
    @Override
    public List<Delivery> getDeliveryInTheLast7Days (Delivery delivery) {
        log.info("delivery{} ", delivery);
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();

            final Query query = entityManager.createQuery("select d from carrier_Delivery d where @between(d.date, now-5, now, day) and d.carrier = :carrierId");
            query.setParameter("carrierId", delivery.getCarrier());
            List<Delivery> deliveryList =  query.getResultList();
            transaction.commit();
            log.info("Доставки поставщика {} за последение 7 дней: {} ", delivery.getCarrier().getName(), deliveryList.get(0).getDate());
//            goodsServiceBean.convertNewDeliveriesStatusToCanceled(delivery.getCarrier().getName());
            return deliveryList;
        }
    }


    public List<GoodsM> getAllFoodStuffs() {
        List<GoodsM> result;
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Goods d where type(d) = :subClass");
            query.setParameter("subClass", FoodStuffs.class);
            result =  query.getResultList();
            System.out.println("getAllFoodStuffs() " + result);
            transaction.commit();
        }
        return result;
    }


    public List<GoodsM> getAllIndustrialProducts() {
        List<GoodsM> result;
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Goods d where type(d) = :subClass");
            query.setParameter("subClass", IndustrialProducts.class);
            result =  query.getResultList();
            System.out.println("getAllIndustrialProducts() " + result);
            transaction.commit();
        }
        return result;
    }



//    //Посчитать стоимость всех order какого customer
//    public BigDecimal getCostOfDelivery(Customer customer) {
//        BigDecimal result;
//        try (final Transaction transaction = persistence.createTransaction()) {
//            final EntityManager entityManager = persistence.getEntityManager();
//            final Query query = entityManager.createQuery("select sum(o.amount) from sales_Order o where o.customer = customerId");
//            query.setParameter("customerId", customer.getId() );
//            result = (BigDecimal) query.getFirstResult();
//            transaction.commit();
//
//        }
//        return result != null? result : BigDecimal.ZERO;
//    }

//    @Override
//    public BigDecimal getCostOfDelivery(Delivery delivery) {
//        BigDecimal resultCostOfDelivery = new BigDecimal(0.0);
//
//        View viewWight = new View(Delivery.class)
//                .addProperty("goods")
//                ;
//
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
//
////            getAllIndustrialProducts();
////            getAllFoodStuffs();
//            Delivery delivery1 = (Delivery) query.getSingleResult();
//
//
//            List<IndustrialProducts> industrialProducts = new ArrayList<>();
//            for (int i = 0; i < delivery1.getGoods().size(); i++) {
//                if (delivery1.getGoods().get(i).getClass().toString().endsWith("Products")){
//                    industrialProducts.add((IndustrialProducts) delivery1.getGoods().get(i));
//                }
//            }
//            for (int i = 0; i < industrialProducts.size(); i++) {
//                resultCostOfDelivery = resultCostOfDelivery.add(new BigDecimal(industrialProducts.get(i).getWeight()));
//            }
//            System.out.println(resultCostOfDelivery);
//            System.out.println("_");
////            System.out.println("1 " + deliveries) ;
////            List<Delivery> industrialProducts
////            System.out.println("1 " + deliveries);
////            for (int i = 0; i < deliveries.size(); i++) {
////                System.out.println("3353 " + deliveries.get(i).getGoods().get(0).getClass().toString().endsWith("IndustrialProducts"));
////
////                if (deliveries.get(i).getGoods().get(0).getClass().toString().endsWith("IndustrialProducts")){
////                    System.out.println((deliveries.get(i).getGoods().get(0).getCost()));
////                }
////            }
////            System.out.println("_");
//
///            System.out.println("2 " + industrialProducts.get(0).getGoods());
//
////                       dataManager.load(Goods.class)
//////                        .query("select p from library_BookPublication p where p.book.id = :bookId")
////                        .query("select g from carrier_Goods g left join g.deliveries d where type(g) = :subClass and d.number = :number")
////                        .parameter("bookId", viewWight)
////                        .view("bookPublication.full")
////                        .list();
//
//
//
////            transaction.commit();
////            transaction.close();
//
////            SELECT e FROM app_Employee e WHERE TYPE(e) IN (:empType1, :empType2)
////
////            SELECT e FROM Employee e, IN(e.projects) p WHERE p.budget > 1000000
////
////            SELECT e FROM Employee e WHERE e.projects IS EMPTY
//
//        }
//        return resultCostOfDelivery;
//    }
}
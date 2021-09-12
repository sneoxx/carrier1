package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.*;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service(GoodsService.NAME)
public class GoodsServiceBean implements GoodsService {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceBean.class);

    @Inject
    private Persistence persistence;

    @Inject
    private DeliveryBean deliveryBean;

    @Inject
    private GoodsMBean goodsMBean;



    //    Создайте GoodsService и реализуйте метод для перевода всех доставок определенного перевозчика в статусе NEW в состояние CANCEL.
    //    Метод принимает имя перевозчика.
    //    Создайте отдельный Spring Component для получения всех ID доставок в различных состояниях определенных перевозчиков и используйте его
    //    в основном сервисе.
    @Override
    public void convertNewDeliveriesStatusToCanceled(String carrierName) {
        log.info("convertNewDeliveriesToCanceled() carrierName: {} ", carrierName);
        Map<UUID, StatusDelivery> mapAllCarrierDeliveries = deliveryBean.getAllCarrierDeliveries(carrierName);
        List<UUID> uuidList = new ArrayList<>();
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            for (Map.Entry<UUID, StatusDelivery> item : mapAllCarrierDeliveries.entrySet()) {
                if (item.getValue().toString().equals("NEW")) {
                    uuidList.add(item.getKey());
                }
            }
            if (uuidList.size() != 0) {
                log.info("convertNewDeliveriesToCanceled() uuidList {} ", uuidList);
                final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.id = :deliveryId");
                for (UUID item : uuidList) {
                    query.setParameter("deliveryId", item);
                }

                List<Delivery> deliveryList = query.getResultList();
                for (Delivery item : deliveryList) {
                    item.setStatus(StatusDelivery.CANCEL);
                }
                transaction.commit();
                log.info("Доставки: {} NEW поставщика: {} установлены в состояние CANCEL", deliveryList, carrierName);
            } else {
                log.info("У поставщика {} нет доставок в состоянии NEW", carrierName);
            }
        }
        }

    @Override
    public void removeExpiredFoodStuffs(){
        goodsMBean.removeExpiredFoodStuffs();
    }

}
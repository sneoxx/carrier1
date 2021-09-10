package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.carrier.entity.IndustrialProducts;
import com.haulmont.carrier.entity.StatusDelivery;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

@Component(DeliveryBean.NAME)
public class DeliveryBean {

    public static final String NAME = "carrier_DeliveryBean";

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceBean.class);

    @Inject
    private Persistence persistence;

//    Создайте отдельный Spring Component для получения всех ID доставок в различных состояниях определенных перевозчиков и используйте его в основном сервисе.
    public Map<UUID, StatusDelivery> getAllCarrierDeliveries(String carrierName) {

//        BigDecimal resultCostOfDelivery = new BigDecimal(0.0);
//
//        View viewWight = new View(Delivery.class)
//                .addProperty("goods");
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Delivery d where d.carrier.name = :carrierName");
            query.setParameter("carrierName", carrierName);
            List<Delivery> deliveryList =  query.getResultList();
            log.info("У перевозчика {} получены все доставки {} ",carrierName, deliveryList );
            transaction.commit();
            Map<UUID, StatusDelivery> allCarrierDeliveries  = new HashMap<>();
            for (int i = 0; i < deliveryList.size(); i++) {
                allCarrierDeliveries.put(deliveryList.get(i).getId(), deliveryList.get(i).getStatus());
            }
            log.info("У перевозчика {} получены все доставки {} ",carrierName, allCarrierDeliveries );
            return allCarrierDeliveries;
        }

    }

}
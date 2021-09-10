package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.FoodStuffs;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component("carrier_GoodsM")
public class GoodsM implements GoodsMBean {

    private static final Logger log = LoggerFactory.getLogger(DeliveryServiceBean.class);

    @Inject
    private Persistence persistence;


    // Создайте JMX Bean для удаление всех продуктов, у которых закончился срок годности на текущий день,
    // также данная задача должна запускается каждый день в 23:59. Выводите информацию о удаленных продуктов в LOG на уровне INFO.
    @Override
    public void removeExpiredFoodStuffs() {

        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select d from carrier_Goods d where type(d) = :subClass");
            query.setParameter("subClass", FoodStuffs.class);
            List<FoodStuffs> allFoodStuffs =  (List<FoodStuffs>) query.getResultList();
            for (FoodStuffs foodStuffs: allFoodStuffs) {
                if (foodStuffs.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(LocalDate.now()) <= 0) {
                    entityManager.remove(foodStuffs);
                    log.info("removeExpiredProducts() Будет удален просроченный продукт {}", foodStuffs);
                }
            }
            transaction.commit();
        }
    }
}
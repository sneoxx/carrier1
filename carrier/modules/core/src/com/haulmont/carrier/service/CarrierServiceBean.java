package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(CarrierService.NAME)
public class CarrierServiceBean implements CarrierService {

    @Inject
    private Persistence persistence;

    @Override
    public List<Carrier> getAllCarrier() {
        List<Carrier> result;
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select c from carrier_Carrier c");
            result =  query.getResultList();
            System.out.println("getAllCarrier" + result);
            transaction.commit();
        }
        return result;
    };

//    @Override
//    public List<Carrier> getAllCarrier() {
//        List<Carrier> result;
//        try (final Transaction transaction = persistence.createTransaction()) {
//            final EntityManager entityManager = persistence.getEntityManager();
//            final Query query = entityManager.createQuery("select c from carrier_Carrier c");
//            result =  query.getResultList();
//            System.out.println("getAllCarrier" + result);
//            transaction.commit();
//        }
//        return result;
//    };




//    Carrier carrier = dataManager.loadValue(
//            "select с from carrier_Carrier с where с.extUser = :user1", Carrier.class)
////                .store("legacy_db")
//            .parameter("user1", user)
//            .one();

}
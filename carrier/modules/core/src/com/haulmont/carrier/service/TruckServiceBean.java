package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Query;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(TruckService.NAME)
public class TruckServiceBean implements TruckService {

    @Inject
    private Persistence persistence;

    @Override
    public List<Delivery> getAllTruck (){

        List<Delivery> result;
        try (final Transaction transaction = persistence.createTransaction()) {
            final EntityManager entityManager = persistence.getEntityManager();
            final Query query = entityManager.createQuery("select t from carrier_Truck t");
            result =  query.getResultList();
            transaction.commit();
        }
        return result;
    }
}
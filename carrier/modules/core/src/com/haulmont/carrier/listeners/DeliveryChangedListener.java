package com.haulmont.carrier.listeners;

import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("carrier_DeliveryChangedListener")
public class DeliveryChangedListener {

    @Inject
    private UniqueNumbersAPI uniqueNumbers;

    @Inject
    private TransactionalDataManager txDm;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Delivery, UUID> event) {
        Delivery delivery;
        delivery = txDm.load(event.getEntityId()).one();
        Long uniqueNumber = uniqueNumbers.getNextNumber("deliveryNumber");
        delivery.setNumber(uniqueNumber.toString());
        txDm.save(delivery);
    }
}

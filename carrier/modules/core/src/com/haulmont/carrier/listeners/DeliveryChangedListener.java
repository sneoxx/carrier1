package com.haulmont.carrier.listeners;

import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.UniqueNumbersAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.PersistenceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("carrier_DeliveryChangedListener")
public class DeliveryChangedListener {

    private static final Logger log = LoggerFactory.getLogger(DeliveryChangedListener.class);

    @Inject
    private UniqueNumbersAPI uniqueNumbers;

    @Inject
    private TransactionalDataManager txDm;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Delivery, UUID> event) {
        Delivery delivery = txDm.load(event.getEntityId()).one();
        if (event.getType() == EntityChangedEvent.Type.CREATED) {
            long uniqueNumber = uniqueNumbers.getNextNumber("deliveryNumber");
            delivery.setNumber(Long.toString(uniqueNumber));
            log.info("Для новой доставки {} сгенерирован номер {}:",delivery, uniqueNumber);
            txDm.save(delivery);
        }
    }
}


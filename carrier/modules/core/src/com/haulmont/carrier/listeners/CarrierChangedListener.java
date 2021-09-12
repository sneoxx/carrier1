package com.haulmont.carrier.listeners;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.carrier.entity.HistoryCost;
import com.haulmont.cuba.core.TransactionalDataManager;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;

@Component("carrier_CarrierChangedListener")
public class CarrierChangedListener {

    private static final Logger log = LoggerFactory.getLogger(DeliveryChangedListener.class);

    @Inject
    private TransactionalDataManager txDm;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<Carrier, UUID> event) {
        HistoryCost historyCost = new HistoryCost();
        Carrier carrier = txDm.load(event.getEntityId()).one();
        AttributeChanges changes = event.getChanges();
        if (changes.isChanged("cost")) {
            historyCost.setCost(carrier.getCost());
            historyCost.setCarrier(carrier);
            historyCost.setChangeDate(new Date());
            log.info("У перевозчика: {} новая стоимость доставки: {}",carrier.getName(), carrier.getCost());
            txDm.save(historyCost);
        }
    }
}
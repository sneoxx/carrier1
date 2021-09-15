package com.haulmont.carrier.web.screens.delivery;

import com.haulmont.carrier.service.DeliveryService;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Set;

@UiController("carrier_Delivery.browse")
@UiDescriptor("delivery-browse.xml")
@LookupComponent("deliveriesTable")
@LoadDataBeforeShow
public class DeliveryBrowse extends StandardLookup<Delivery> {

    @Inject
    DeliveryService deliveryService;

    @Inject
    UiComponents uiComponents;

    @Install(to = "deliveriesTable.costOfDelivery", subject = "columnGenerator")
    private Component deliveriesTableCostoOfDeliveryColumnGenerator(Delivery delivery) {
        Label<BigDecimal> label = uiComponents.create(Label.TYPE_BIGDECIMAL);
        label.setValue(deliveryService.getCostOfDelivery(delivery));
        return label;
    }

}
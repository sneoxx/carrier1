package com.haulmont.carrier.web.screens.delivery;

import com.haulmont.carrier.entity.StatusDelivery;
import com.haulmont.carrier.web.screens.truck.TruckBrowse;
import com.haulmont.carrier.web.screens.truck.TruckEdit;
import com.haulmont.cuba.gui.actions.list.AddAction;
import com.haulmont.cuba.gui.components.Collapsable;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Delivery;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@UiController("carrier_Delivery.edit")
@UiDescriptor("delivery-edit.xml")
@EditedEntityContainer("deliveryDc")
@LoadDataBeforeShow
public class DeliveryEdit extends StandardEditor<Delivery> {

    @Inject
    private InstanceContainer<Delivery> deliveryDc;

    @PostConstruct
    public void postConstruct() {
        deliveryDc.getItem().setStatus(StatusDelivery.NEW);

    }

//    @Install(to = "truckTable.add", subject = "screenConfigurer")
//    private void trucksTableAddScreenConfigurer(Screen screen) {
//        ((TruckBrowse) screen);
//    }

}
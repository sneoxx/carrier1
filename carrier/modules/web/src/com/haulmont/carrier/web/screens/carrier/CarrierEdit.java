package com.haulmont.carrier.web.screens.carrier;

import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Carrier;

import javax.inject.Inject;

@UiController("carrier_Carrier.edit")
@UiDescriptor("carrier-edit.xml")
@EditedEntityContainer("carrierDc")
@LoadDataBeforeShow
public class CarrierEdit extends StandardEditor<Carrier> {
//    @Inject
//    private CollectionLoader<Delivery> deliveriesDl;
//
//    @Subscribe
//    public void onBeforeShow(BeforeShowEvent event) {
//        deliveriesDl.setParameter("carrier", getEditedEntity());
////        deliveriesDl.setParameter("status", "1");
//        getScreenData().loadAll();
//    }

}


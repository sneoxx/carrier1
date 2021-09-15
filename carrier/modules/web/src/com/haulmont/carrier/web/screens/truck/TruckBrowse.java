package com.haulmont.carrier.web.screens.truck;

import com.haulmont.carrier.web.screens.delivery.DeliveryEdit;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Truck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@UiController("carrier_Truck.browse")
@UiDescriptor("truck-browse.xml")
@LookupComponent("trucksTable")
public class TruckBrowse extends StandardLookup<Truck> {

    private static final Logger log = LoggerFactory.getLogger(DeliveryEdit.class);

    @Inject
    private CollectionLoader<Truck> trucksDl;

    @Subscribe
    private void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof MapScreenOptions ) {
            Object passedParameter  = ((MapScreenOptions) options).getParams().get("selectedСarrier");
            trucksDl.setParameter("carrier", passedParameter);
            log.info("В экран TruckBrowse передан параметр: {} ", passedParameter);
         }
        }
    }
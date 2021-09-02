package com.haulmont.carrier.web.screens.truck;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Truck;

import javax.inject.Inject;

@UiController("carrier_Truck.browse")
@UiDescriptor("truck-browse.xml")
@LookupComponent("trucksTable")
public class TruckBrowse extends StandardLookup<Truck> {

    @Inject
    private CollectionLoader<Truck> trucksDl;

    @Subscribe
    private void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof MapScreenOptions) {
            Object message = ((MapScreenOptions) options).getParams().get("selectedСarrier");
            trucksDl.setParameter("carrier", message);
        }
    }
}
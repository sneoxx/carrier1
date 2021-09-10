package com.haulmont.carrier.web.screens.truck;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.service.CarrierService;
import com.haulmont.carrier.service.TruckService;
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

    @Inject
    private CarrierService carrierService;

    @Subscribe
    private void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();

//        result != null? result : BigDecimal.ZERO;
        System.out.println("2 "+  ((MapScreenOptions) options).getParams().get("selectedСarrier"));
        System.out.println(((MapScreenOptions) options).getParams().get("selectedСarrier") != null);
        if(((MapScreenOptions) options).getParams().get("selectedСarrier") != null){
        if ( options instanceof MapScreenOptions ) {
            Object message = ((MapScreenOptions) options).getParams().get("selectedСarrier");

            System.out.println("11 " + message);
            trucksDl.setParameter("carrier", message);

        }
        } else {
            trucksDl.setParameter("carrier", carrierService.getAllCarrier());
//           System.out.println(carrierService.getAllCarrier().get(0));

        }
    }
}
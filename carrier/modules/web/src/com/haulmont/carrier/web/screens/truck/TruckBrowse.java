package com.haulmont.carrier.web.screens.truck;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Truck;

@UiController("carrier_Truck.browse")
@UiDescriptor("truck-browse.xml")
@LookupComponent("trucksTable")
@LoadDataBeforeShow
public class TruckBrowse extends StandardLookup<Truck> {
}
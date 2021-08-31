package com.haulmont.carrier.web.screens.trucktype;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.TruckType;

@UiController("carrier_TruckType.browse")
@UiDescriptor("truck-type-browse.xml")
@LookupComponent("table")
@LoadDataBeforeShow
public class TruckTypeBrowse extends MasterDetailScreen<TruckType> {
}
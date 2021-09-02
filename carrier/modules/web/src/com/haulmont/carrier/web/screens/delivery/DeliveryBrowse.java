package com.haulmont.carrier.web.screens.delivery;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Delivery;

@UiController("carrier_Delivery.browse")
@UiDescriptor("delivery-browse.xml")
@LookupComponent("deliveriesTable")
@LoadDataBeforeShow
public class DeliveryBrowse extends StandardLookup<Delivery> {

}
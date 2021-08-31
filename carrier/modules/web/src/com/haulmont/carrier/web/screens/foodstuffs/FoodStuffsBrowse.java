package com.haulmont.carrier.web.screens.foodstuffs;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.FoodStuffs;

@UiController("carrier_FoodStuffs.browse")
@UiDescriptor("food-stuffs-browse.xml")
@LookupComponent("foodStuffsesTable")
@LoadDataBeforeShow
public class FoodStuffsBrowse extends StandardLookup<FoodStuffs> {
}
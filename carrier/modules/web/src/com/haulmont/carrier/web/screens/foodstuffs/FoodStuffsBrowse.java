package com.haulmont.carrier.web.screens.foodstuffs;

import com.haulmont.carrier.entity.IndustrialProducts;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.FoodStuffs;

import javax.inject.Inject;

@UiController("carrier_FoodStuffs.browse")
@UiDescriptor("food-stuffs-browse.xml")
@LookupComponent("foodStuffsesTable")
@LoadDataBeforeShow
public class FoodStuffsBrowse extends StandardLookup<FoodStuffs> {

}
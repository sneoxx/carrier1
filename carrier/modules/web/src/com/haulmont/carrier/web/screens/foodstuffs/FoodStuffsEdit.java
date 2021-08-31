package com.haulmont.carrier.web.screens.foodstuffs;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.FoodStuffs;

@UiController("carrier_FoodStuffs.edit")
@UiDescriptor("food-stuffs-edit.xml")
@EditedEntityContainer("foodStuffsDc")
@LoadDataBeforeShow
public class FoodStuffsEdit extends StandardEditor<FoodStuffs> {
}
package com.haulmont.carrier.web.screens.industrialproducts;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.IndustrialProducts;

@UiController("carrier_IndustrialProducts.edit")
@UiDescriptor("industrial-products-edit.xml")
@EditedEntityContainer("industrialProductsDc")
@LoadDataBeforeShow
public class IndustrialProductsEdit extends StandardEditor<IndustrialProducts> {
}
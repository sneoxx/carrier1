package com.haulmont.carrier.web.screens.industrialproducts;

import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.IndustrialProducts;

@UiController("carrier_IndustrialProducts.browse")
@UiDescriptor("industrial-products-browse.xml")
@LookupComponent("industrialProductsesTable")
@LoadDataBeforeShow
public class IndustrialProductsBrowse extends StandardLookup<IndustrialProducts> {
}
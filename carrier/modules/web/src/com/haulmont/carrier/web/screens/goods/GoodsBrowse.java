package com.haulmont.carrier.web.screens.goods;

import com.haulmont.carrier.entity.IndustrialProducts;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.LookupComponent;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;

@UiController("carrier_Goods.browse")
@UiDescriptor("goods-browse.xml")
@com.haulmont.cuba.gui.screen.LookupComponent("tabSheetGoods")
public class GoodsBrowse extends StandardLookup<Entity> {

    @Inject
    private GroupTable industrialProductsesTable;

    @Inject
    private GroupTable foodStuffsesTable;

    @Inject
    protected TabSheet tabSheetGoods;
    @Inject
    private HBoxLayout lookupActions;

    @Subscribe("industrialProductsesTable")
    public void onIndustrialProductsesTableSelection(Table.SelectionEvent<IndustrialProducts> event) {
        lookupActions.setEnabled(true);
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        getScreenData().loadAll();
    }

    @Override
    protected LookupComponent<Entity> getLookupComponent() {
        if ("foodStuffsTab".equals(tabSheetGoods.getSelectedTab().getName())) {

            return foodStuffsesTable;
        }
        if ("industrialProductsTab".equals(tabSheetGoods.getSelectedTab().getName())) {
            return industrialProductsesTable;
        }
        return foodStuffsesTable;
    }
}
package com.haulmont.carrier.web.screens.goods;

import com.haulmont.carrier.entity.FoodStuffs;
import com.haulmont.carrier.entity.IndustrialProducts;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.UiComponents;
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

    @Inject
    UiComponents uiComponents;


//    @Subscribe("industrialProductsesTable")
//    public void onIndustrialProductsesTableSelection(Table.SelectionEvent<IndustrialProducts> event) {
//        lookupActions.setEnabled(true);
//        lookupActions.setVisible(true);
//    }
//
//    @Subscribe("foodStuffsesTable")
//    public void onFoodStuffsesTableSelection(Table.SelectionEvent<FoodStuffs> event) {
//        lookupActions.setEnabled(true);
//        lookupActions.setVisible(true);
//    }

    @Subscribe
    protected void onInit(InitEvent event) {
        tabSheetGoods.addSelectedTabChangeListener(selectedTabChangeEvent -> {
            if ("foodStuffsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
                lookupActions.setEnabled(true);
                lookupActions.setVisible(true);
            } else if ("industrialProductsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
                lookupActions.setEnabled(true);
                lookupActions.setVisible(true);
            }
        });
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

//    @Install(to = "foodStuffsesTable.productType", subject = "columnGenerator")
//    private Component foodStuffsesTableProductTypeColumnGenerator(FoodStuffs foodStuffs) {
//        Label<String> label = uiComponents.create(Label.TYPE_STRING);
//        String productType = foodStuffs.getProductType();
//        label.setValue(productType.substring(productType.lastIndexOf('.') + 1));
//        return label;
//
//    }

    @Install(to = "industrialProductsesTable.productType", subject = "columnGenerator")
    private Component industrialProductsesTableProductTypeColumnGenerator(IndustrialProducts industrialProducts) {
        Label<String> label = uiComponents.create(Label.TYPE_STRING);
        String productType = industrialProducts.getProductType();
        label.setValue(productType.substring(productType.lastIndexOf('.') + 1));
        return label;
    }
}
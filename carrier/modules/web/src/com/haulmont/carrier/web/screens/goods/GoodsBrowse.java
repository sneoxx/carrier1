package com.haulmont.carrier.web.screens.goods;

import com.haulmont.carrier.entity.FoodStuffs;
import com.haulmont.carrier.entity.IndustrialProducts;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.LookupComponent;
import com.haulmont.cuba.gui.components.TabSheet;
import com.haulmont.cuba.gui.components.VBoxLayout;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import javax.inject.Named;

@UiController("carrier_Goods.browse")
@UiDescriptor("goods-browse.xml")
//@LookupComponent("tabSheetGoods")
//@LoadDataBeforeShow
public class GoodsBrowse extends StandardLookup<Entity> {

    @Inject
    private GroupTable<FoodStuffs> foodStuffsesTable;

    @Inject
    protected TabSheet tabSheetGoods;

    @Inject
    private GroupTable<IndustrialProducts> industrialProductsesTable;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        getScreenData().loadAll();
    }

//    @Subscribe
//    public void onInit(InitEvent event) {
//        tabSheetGoods.addSelectedTabChangeListener(selectedTabChangeEvent -> {
//
//            if ("foodStuffsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
////                tabSheetGoods.setSelectedTab((TabSheet.Tab) getLookupComponent());
////                getScreenData().loadAll();
////               getLookupComponent();
////                getScreenData().loadAll();
//            } else if ("industrialProductsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
////                getLookupComponent();
////                getScreenData().loadAll();
////                getLookupComponent();
////               getScreenData().loadAll();
//            }
//        });
//    }


    @Override
    protected LookupComponent<Entity> getLookupComponent() {
        return (com.haulmont.cuba.gui.components.LookupComponent) foodStuffsesTable;

        //                return (com.haulmont.cuba.gui.components.LookupComponent<Goods>) tabSheetGoods.getTabComponent(tabSheetGoods.getSelectedTab().getName());
//                return (com.haulmont.cuba.gui.components.LookupComponent<Goods>) getWindow().getComponent(tabSheetGoods.getSelectedTab().getName());
//            (com.haulmont.cuba.gui.components.LookupComponent) getWindow().getComponentNN(annotation.value());
//              return uiComponents.create("FoodStuffs");
    }

//        tabSheet.addSelectedTabChangeListener(selectedTabChangeEvent -> {
//        if ("detailsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
//            initDetails();
//        } else if ("historyTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
//            initHistory();
//        }
//    });

//    Для правильной работы экрана нужно в контроллере экрана возвращать правильную таблицу
//    в качестве LookupComponent в зависимости от активной Tab, а также активировать Action выбора.
//    Используйте слушатель SelectedTabChangeListener на событие переключение Tab
//    в компоненте TabSheet а также переопределите метод контроллера getLookupComponent() для возврата нужной таблицы.


    //    protected com.haulmont.cuba.gui.components.LookupComponent<T> getLookupComponent() {
//        com.haulmont.cuba.gui.screen.LookupComponent annotation =
//                getClass().getAnnotation(com.haulmont.cuba.gui.screen.LookupComponent.class);
//        if (annotation == null || Strings.isNullOrEmpty(annotation.value())) {
//            throw new IllegalStateException(
//                    String.format("StandardLookup %s does not declare @LookupComponent", getClass())
//            );
//        }
//        return (com.haulmont.cuba.gui.components.LookupComponent) getWindow().getComponentNN(annotation.value());
//    }
}
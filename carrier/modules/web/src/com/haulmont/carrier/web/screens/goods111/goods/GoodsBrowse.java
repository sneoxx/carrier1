//package com.haulmont.carrier.web.screens.goods111.goods;
//
//import com.haulmont.carrier.entity.Goods;
//import com.haulmont.cuba.gui.screen.*;
//
//@UiController("goods")
//@UiDescriptor("goods-browse.xml")
//@LookupComponent("goodsesTable")
//@LoadDataBeforeShow
//public class GoodsBrowse extends StandardLookup<Goods> {
////
////    @Inject
////    private GroupTable<FoodStuffs> foodStuffsesTable;
////    @Inject
////    protected UiComponents uiComponents;
////    @Inject
////    protected TabSheet tabSheetGoods;
////
////    @Named("tabSheetGoods.industrialProductsTab")
////    private VBoxLayout industrialProductsTab;
////    @Inject
////    private GroupTable<IndustrialProducts> industrialProductsesTable;
////    @Named("tabSheetGoods.foodStuffsTab")
////    private VBoxLayout foodStuffsTab;
//
////    @Subscribe
////    public void onInit(InitEvent event) {
////        tabSheetGoods.addSelectedTabChangeListener(selectedTabChangeEvent -> {
////            if ("foodStuffsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
//////                tabSheetGoods.setSelectedTab((TabSheet.Tab) getLookupComponent());
//////                getScreenData().loadAll();
//////                getLookupComponent();
////                getScreenData().loadAll();
////            } else if ("industrialProductsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
//////                getLookupComponent();
////                getScreenData().loadAll();
////
//////                getScreenData().loadAll();
////            }
////        });
////    }
//
//
////        @Override
////    protected com.haulmont.cuba.gui.components.LookupComponent<Goods> getLookupComponent() {
////
//////                return (com.haulmont.cuba.gui.components.LookupComponent<Goods>) tabSheetGoods.getTabComponent(tabSheetGoods.getSelectedTab().getName());
////                return (com.haulmont.cuba.gui.components.LookupComponent<Goods>) getWindow().getComponentNN(tabSheetGoods.getSelectedTab().getName());
//////              return uiComponents.create("FoodStuffs");
////
//////        });
////    }
//
////        tabSheet.addSelectedTabChangeListener(selectedTabChangeEvent -> {
////        if ("detailsTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
////            initDetails();
////        } else if ("historyTab".equals(selectedTabChangeEvent.getSelectedTab().getName())) {
////            initHistory();
////        }
////    });
//
////    Для правильной работы экрана нужно в контроллере экрана возвращать правильную таблицу
////    в качестве LookupComponent в зависимости от активной Tab, а также активировать Action выбора.
////    Используйте слушатель SelectedTabChangeListener на событие переключение Tab
////    в компоненте TabSheet а также переопределите метод контроллера getLookupComponent() для возврата нужной таблицы.
//
//
//    //    protected com.haulmont.cuba.gui.components.LookupComponent<T> getLookupComponent() {
////        com.haulmont.cuba.gui.screen.LookupComponent annotation =
////                getClass().getAnnotation(com.haulmont.cuba.gui.screen.LookupComponent.class);
////        if (annotation == null || Strings.isNullOrEmpty(annotation.value())) {
////            throw new IllegalStateException(
////                    String.format("StandardLookup %s does not declare @LookupComponent", getClass())
////            );
////        }
////        return (com.haulmont.cuba.gui.components.LookupComponent) getWindow().getComponentNN(annotation.value());
////    }
//}
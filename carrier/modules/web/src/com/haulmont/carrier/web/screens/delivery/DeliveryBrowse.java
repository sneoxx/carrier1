package com.haulmont.carrier.web.screens.delivery;

import com.haulmont.carrier.service.DeliveryService;
import com.haulmont.carrier.service.GoodsService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.cuba.gui.screen.LookupComponent;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Set;

@UiController("carrier_Delivery.browse")
@UiDescriptor("delivery-browse.xml")
@LookupComponent("deliveriesTable")
@LoadDataBeforeShow
public class DeliveryBrowse extends StandardLookup<Delivery> {

    @Inject
    DeliveryService deliveryService;

    @Inject
    UiComponents uiComponents;

    @Install(to = "deliveriesTable.costOfDelivery", subject = "columnGenerator")
    private Component deliveriesTableCostoOfDeliveryColumnGenerator(Delivery delivery) {
        Label<BigDecimal> label = uiComponents.create(Label.TYPE_BIGDECIMAL);
        label.setValue(deliveryService.getCostOfDelivery(delivery));
        return label;
    }


//    public Component generateCostoOfDeliveryColumn(Delivery delivery) {
//        Image image = uiComponents.create(Image.NAME);
//        image.setSource(FileDescriptorResource.class).setFileDescriptor(delivery);
//        return null;
//    }


//    @Install(to = "deliveriesTable.&#1089;ostOfDelivery", subject = "columnGenerator")
//    private Component deliveriesTable1089OstOfDeliveryColumnGenerator(Delivery delivery) {
//        return null;
//    }


//    @Inject
//    private GroupTable<Delivery> deliveriesTable;
//    @Inject
//    private Notifications notifications;




//    @Subscribe("buttonCostOfDelivery")
//    public void onButtonCostOfDeliveryClick(Button.ClickEvent event) {
//       final Customer singleSelected = deliveriesTable.getSingleSelected();
//       if(singleSelected != null){
//          final BigDecimal decimal = orderService.getCostOfDelivery(singleSelected);
//           notifications.create(Notifications.NotificationType.HUMANIZED).withCaption("Sum = " + decimal.toPlainString()).show();
//       } else {
//           notifications.create(Notifications.NotificationType.WARNING).withCaption("Select customer").show();
//       }
//    }

//   // Измените списочный экран доставки, добавьте генерируемую колонку в таблицу, которая показывает стоимость доставки каждой, расчет осуществляется с использованием сервиса.
//
//    @Subscribe
//    protected void onInit(InitEvent event) {
//        DataGrid.Column column = usersGrid.addGeneratedColumn("loginUpperCase", new DataGrid.ColumnGenerator<User, String>(){
//            @Override
//            public String getValue(DataGrid.ColumnGeneratorEvent<User> event){
//                return event.getItem().getLogin().toUpperCase();
//            }
//
//            @Override
//            public Class<String> getType(){
//                return String.class;
//            }
//        }, 1);
//        column.setCaption("Login Upper Case");
//    }
}
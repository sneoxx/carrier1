package com.haulmont.carrier.web.screens.carrier;

import com.haulmont.carrier.entity.Delivery;
import com.haulmont.carrier.service.DeliveryService;
import com.haulmont.carrier.service.GoodsService;
import com.haulmont.carrier.web.screens.delivery.DeliveryEdit;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Carrier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Set;

@UiController("carrier_Carrier.browse")
@UiDescriptor("carrier-browse.xml")
@LookupComponent("carriersTable")
@LoadDataBeforeShow
public class CarrierBrowse extends StandardLookup<Carrier> {

    private static final Logger log = LoggerFactory.getLogger(DeliveryEdit.class);

    @Inject
    private DeliveryService deliveryService;

    @Inject
    private GoodsService goodsService;

    @Inject
    private GroupTable<Carrier> carriersTable;

    @Inject
    Notifications notifications;


//    Измените списочный экран списка перевозчиков, добавьте на него кнопку, при нажатии на которую будут отменяться все заказы, в состоянии NEW выбранного перевозчика из таблице,
//    если нет активного перевозчика (выбранного) созданная кнопка блокируется, если выделено несколько перевозчиков, то сервис применяется для всех.
//    Так же данное действие доступно при нажатии правой кнопкой на строке. Используйте Action.
    @Subscribe("buttonConvertNewDeliveriesStatusToCanceled")
    public void onButtonCostOfDeliveryClick(Button.ClickEvent event) {
        final Set<Carrier> selectedCarriers = carriersTable.getSelected();
        int numberOfNewDeliveries = 0;
        for (Carrier itemCarrier : selectedCarriers) {
            for (Delivery itemDelivery : itemCarrier.getDeliveries()) {
                if (itemDelivery.getStatus().toString().equals("NEW")) {
                    numberOfNewDeliveries = numberOfNewDeliveries + 1;
                    goodsService.convertNewDeliveriesStatusToCanceled(itemDelivery.getCarrier().getName());
                    notifications.create(Notifications.NotificationType.HUMANIZED).withCaption("Deliveries are set to CANCEL status" ).show();
                }
            }
        }
        if ( numberOfNewDeliveries == 0) {
            log.info("У перевозчика(-ов) {} нет доставок статусе CANCEL", selectedCarriers);
            notifications.create(Notifications.NotificationType.WARNING).withCaption("No deliveries with NEW status").show();
        }
        carriersTable.repaint();
        getScreenData().loadAll();
    }
}
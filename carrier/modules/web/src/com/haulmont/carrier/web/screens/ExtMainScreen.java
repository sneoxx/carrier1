package com.haulmont.carrier.web.screens;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.carrier.entity.ExtUser;
import com.haulmont.carrier.service.DeliveryService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.core.global.View;
import com.haulmont.cuba.gui.components.Label;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.app.main.MainScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@UiController("extMainScreen")
@UiDescriptor("ext-main-screen.xml")
public class ExtMainScreen extends MainScreen {
    private static final Logger log = LoggerFactory.getLogger(ExtMainScreen.class);

    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private DeliveryService deliveryService;

    @Inject
    private DataManager dataManager;

//    @Inject
//    private Label dynamicLabel;
//
//    @Subscribe
//    protected void onInit(InitEvent event) {
//        ExtUser user = (ExtUser) userSessionSource.getUserSession().getUser();
//        View viewWight = new View(ExtUser.class)
//                .addProperty("carrier");
//        user = dataManager.reload(user, viewWight);
//        dynamicLabel.setValue(" Deliveries of the сarrier " + user.getCarrier().getName() +  " in the last 7 days ");
//    }

    @Install(to = "deliveriesDl", target = Target.DATA_LOADER)
    protected List<Delivery> customersDlLoadDelegate(LoadContext<Delivery> loadContext) {
        ExtUser user = (ExtUser) userSessionSource.getUserSession().getUser();
        log.debug("customersDlLoadDelegate() Вход пользователя {} :", user);
        View viewWight = new View(ExtUser.class)
                .addProperty("carrier");
        user = dataManager.reload(user, viewWight);
        Carrier carrier = new Carrier();
        List<Delivery> deliveryList = new ArrayList<>();
        if (user.getCarrier() != null) {
            carrier = user.getCarrier();
        } else {
            return deliveryList;
        }

        deliveryList = deliveryService.getDeliveryInTheLast7Days(carrier);
        return deliveryList;
    }
}
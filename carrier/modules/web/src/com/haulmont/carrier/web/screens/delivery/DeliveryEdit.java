package com.haulmont.carrier.web.screens.delivery;

import com.haulmont.carrier.entity.FoodStuffs;
import com.haulmont.carrier.entity.Goods;
import com.haulmont.carrier.service.DeliveryService;
import com.haulmont.carrier.service.GoodsService;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.Dialogs;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.carrier.entity.Delivery;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

@UiController("carrier_Delivery.edit")
@UiDescriptor("delivery-edit.xml")
@EditedEntityContainer("deliveryDc")
@LoadDataBeforeShow
public class DeliveryEdit extends StandardEditor<Delivery> {

    private static final Logger log = LoggerFactory.getLogger(DeliveryEdit.class);

    @Inject
    private Label costOfAllGoods;

    @Inject
    private Dialogs dialogs;

    @Inject
    private DeliveryService deliveryService;

    @Inject
    private GoodsService goodsService;


    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (!PersistenceHelper.isNew(getEditedEntity())) {
            List<Goods> changes = getEditedEntity().getGoods();
            Delivery delivery = getEditedEntity();
            BigDecimal costAllGoods = new BigDecimal("0.0");
            for (Goods goods : changes) {
                costAllGoods = costAllGoods.add(goods.getCost());

//            deliveryService.getCostOfDelivery(delivery);
//            deliveryService.checkExpirationDate(delivery);
//            deliveryService.getDeliveryInTheLast7Days(delivery);
//                goodsService.convertNewDeliveriesStatusToCanceled(delivery.getCarrier().getName());
//            goodsService.removeExpiredFoodStuffs();
            }
                 costOfAllGoods.setValue(costAllGoods);
        }
    }

    @Install(to = "truckTable.add", subject = "screenOptionsSupplier")
    private ScreenOptions truckTableAddScreenOptionsSupplier() {
        return new MapScreenOptions(ParamsMap.of("selectedСarrier", getEditedEntity().getCarrier()));
    }

//    Если нет не одного товара в доставке, то при сохранении такого заказа, показывать предупреждение пользователю с вопросом продолжить сохранение
//    и закрыть экран, или остаться на экране редактирования

//    Измените экран создания доставки, при сохранении требуется проверить, что все продукты в заказе не превышают срок доставки.
//    Если такие присутствуют, показываем диалоговое окно пользователю с предупреждением, о том, что данные продукты будут удалены из доставки.
//    После получения согласия от пользователя исключаем их и сохраняем.
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        List<Goods> changes = getEditedEntity().getGoods();
        if (CollectionUtils.isEmpty(changes)) {
            dialogs.createOptionDialog()
                    .withCaption("Confirmation")
                    .withMessage("Do you want to be left without goods in delivery?")
                    .withActions(
                            new DialogAction(DialogAction.Type.OK).withHandler(e -> {
                                event.resume();
                            }),
                            new DialogAction(DialogAction.Type.CANCEL)
                    )
                    .show();
            event.preventCommit();
        } else {
            List<FoodStuffs> foodProductsWithAnExpirationDateExceedingTheDeliveryDate = deliveryService.checkExpirationDate(getEditedEntity());
            if (foodProductsWithAnExpirationDateExceedingTheDeliveryDate.size() > 0) {
                dialogs.createOptionDialog()
                        .withCaption("Confirmation")
                        .withMessage("The delivery date exceeds the expiration date of the item in delivery. Such items will be removed from delivery.")
                        .withActions(
                                new DialogAction(DialogAction.Type.OK).withHandler(e -> {
                                    changes.removeAll(foodProductsWithAnExpirationDateExceedingTheDeliveryDate);
                                    getEditedEntity().setNumber("0");
                                    event.resume();
                                }),
                                new DialogAction(DialogAction.Type.CANCEL)
                        )
                        .show();
                event.preventCommit();
            }
        }
    }

    @Subscribe(id = "deliveryDc", target = Target.DATA_CONTAINER)
    private void onDeliveryDcItemPropertyChange(InstanceContainer.ItemPropertyChangeEvent<Delivery> event) {
        String changedProperty = event.getProperty();
        log.debug("Меняется событие: {} ", changedProperty);
        if ("carrier".equals(changedProperty)) {
            getEditedEntity().setTruck(null);
        }
        if ("date".equals(changedProperty)) {
            if (LocalDate.now().minusDays(1).compareTo(getEditedEntity().getDate()) > -1) {
                dialogs.createMessageDialog().withCaption("Error").withMessage("Delivery date cannot be less than the current one ").show();
                getEditedEntity().setDate(LocalDate.now());
            }
        }
        if ("goods".equals(changedProperty)) {
            List<Goods> changes = getEditedEntity().getGoods();
            BigDecimal costAllGoods = new BigDecimal("0.0");
            for (Goods goods : changes) {
                costAllGoods = costAllGoods.add(goods.getCost());
            }
            costOfAllGoods.setValue(costAllGoods);
        }
    }
}

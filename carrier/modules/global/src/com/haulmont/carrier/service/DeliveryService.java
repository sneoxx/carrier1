package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;
import com.haulmont.carrier.entity.FoodStuffs;
import com.haulmont.carrier.entity.Goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryService {
    String NAME = "carrier_DeliveryService";

    BigDecimal getCostOfDelivery(Delivery delivery);

    List<FoodStuffs> checkExpirationDate (Delivery delivery);

    List<Delivery> getDeliveryInTheLast7Days (Delivery delivery);

//    List<Goods> getAllFoodStuffs();
//
//    List<Goods> getAllIndustrialProducts();

}
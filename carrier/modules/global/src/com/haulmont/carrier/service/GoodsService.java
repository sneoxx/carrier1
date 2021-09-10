package com.haulmont.carrier.service;

public interface GoodsService {
    String NAME = "carrier_GoodsService";

   void convertNewDeliveriesStatusToCanceled(String carrierName);

    void removeExpiredFoodStuffs();
}
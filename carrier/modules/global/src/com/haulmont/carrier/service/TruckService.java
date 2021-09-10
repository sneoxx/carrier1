package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Delivery;

import java.util.List;

public interface TruckService {
    String NAME = "carrier_TruckService";

    List<Delivery> getAllTruck ();
}
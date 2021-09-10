package com.haulmont.carrier.service;

import com.haulmont.carrier.entity.Carrier;
import com.haulmont.carrier.entity.Delivery;

import java.util.List;

public interface CarrierService {
    String NAME = "carrier_CarrierService";

    List<Carrier> getAllCarrier();
}
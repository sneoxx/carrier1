package com.haulmont.carrier.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum StatusDelivery implements EnumClass<Integer> {

    NEW(10),
    IN_WORK(20),
    COMPLETED(30),
    CANCEL(40);

    private Integer id;

    StatusDelivery(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static StatusDelivery fromId(Integer id) {
        for (StatusDelivery at : StatusDelivery.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}
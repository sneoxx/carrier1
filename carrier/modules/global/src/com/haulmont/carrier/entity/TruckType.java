package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Table(name = "CARRIER_TRUCK_TYPE")
@Entity(name = "carrier_TruckType")
@NamePattern("%s|name")
public class TruckType extends StandardEntity {
    private static final long serialVersionUID = -2394264743429121325L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "CAPACITY", nullable = false)
    @Positive(message = "{msg://carrier_TruckType.capacity.validation.Positive}")
    private Integer capacity;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CARRIER_INDUSTRIAL_PRODUCTS")
@Entity(name = "carrier_IndustrialProducts")
@NamePattern("%s|name")
public class IndustrialProducts extends Goods {
    private static final long serialVersionUID = -7525297857028624440L;

    @Column(name = "WEIGHT")
    private Double weight;

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "IndustrialProducts{" +
                "weight=" + weight +
                '}';
    }
}
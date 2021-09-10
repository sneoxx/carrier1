package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.*;
import java.util.Date;

@Table(name = "CARRIER_FOOD_STUFFS")
@Entity(name = "carrier_FoodStuffs")
@NamePattern("%s|name")
public class FoodStuffs extends Goods {
    private static final long serialVersionUID = -8446296525579522275L;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "FoodStuffs{" +
                "expirationDate=" + expirationDate +
                '}';
    }
}
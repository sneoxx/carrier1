package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.CaseConversion;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "CARRIER_TRUCK")
@Entity(name = "carrier_Truck")
@NamePattern("%s|number")
public class Truck extends StandardEntity {
    private static final long serialVersionUID = -2879604437120381986L;

    @CaseConversion
    @NotNull
    @Column(name = "NUMBER_", nullable = false, unique = true)
    @Length(message = "{msg://carrier_Truck.number.validation.Length}", min = 9, max = 9)
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARRIER_ID")
    private Carrier carrier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRUCK_TYPE_ID")
    private TruckType truckType;
    @JoinTable(name = "CARRIER_DELIVERY_TRUCK_LINK",
            joinColumns = @JoinColumn(name = "TRUCK_ID"),
            inverseJoinColumns = @JoinColumn(name = "DELIVERY_ID"))
    @ManyToMany
    private List<Delivery> deliveries;

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public TruckType getTruckType() {
        return truckType;
    }

    public void setTruckType(TruckType truckType) {
        this.truckType = truckType;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
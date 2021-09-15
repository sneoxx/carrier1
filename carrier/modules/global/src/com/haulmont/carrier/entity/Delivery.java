package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "CARRIER_DELIVERY")
@Entity(name = "carrier_Delivery")
@NamePattern("%s|number")
public class Delivery extends StandardEntity {
    private static final long serialVersionUID = 1573604847669153165L;

    @Column(name = "NUMBER_", nullable = false)
    private String number;

    @NotNull
    @Column(name = "STATUS", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "DATE_", nullable = false)
    private LocalDate date;

    @Column(name = "DISTANCE", nullable = false)
    @NotNull
    @Positive(message = "{msg://carrier_Delivery.distance.validation.Positive}")
    private Integer distance;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CARRIER_ID")
    @NotNull
    private Carrier carrier;

    @JoinTable(name = "CARRIER_DELIVERY_TRUCK_LINK",
            joinColumns = @JoinColumn(name = "DELIVERY_ID"),
            inverseJoinColumns = @JoinColumn(name = "TRUCK_ID"))
    @ManyToMany
    private List<Truck> truck;

    @JoinTable(name = "CARRIER_DELIVERY_GOODS_LINK",
            joinColumns = @JoinColumn(name = "DELIVERY_ID"),
            inverseJoinColumns = @JoinColumn(name = "GOODS_ID"))
    @ManyToMany
    private List<Goods> goods;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "house", column = @Column(name = "ADDRESS_HOUSE"))
    })
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXT_USER_ID")
    private ExtUser extUser;

    public ExtUser getExtUser() {
        return extUser;
    }

    public void setExtUser(ExtUser extUser) {
        this.extUser = extUser;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public List<Truck> getTruck() {
        return truck;
    }

    public void setTruck(List<Truck> truck) {
        this.truck = truck;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StatusDelivery getStatus() {
        return status == null ? null : StatusDelivery.fromId(status);
    }

    public void setStatus(StatusDelivery status) {
        this.status = status == null ? null : status.getId();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @PostConstruct
    public void postConstruct() {
        setStatus(StatusDelivery.NEW);
    }

}
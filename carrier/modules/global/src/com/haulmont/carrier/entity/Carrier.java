package com.haulmont.carrier.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@PublishEntityChangedEvents
@Table(name = "CARRIER_CARRIER")
@Entity(name = "carrier_Carrier")
@NamePattern("%s|name")
public class Carrier extends StandardEntity {
    private static final long serialVersionUID = -5737611676534904189L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @Column(name = "EMAIL")
    @Email(message = "{msg://carrier_Carrier.email.validation.Email}")
    private String email;

    @OneToMany(mappedBy = "carrier")
    private List<Delivery> deliveries = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "carrier")
    private ExtUser extUser;

    public ExtUser getExtUser() {
        return extUser;
    }

    public void setExtUser(ExtUser extUser) {
        this.extUser = extUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }
}
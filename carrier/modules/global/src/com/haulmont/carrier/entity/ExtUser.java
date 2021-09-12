package com.haulmont.carrier.entity;

import com.haulmont.cuba.core.entity.annotation.Extends;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "carrier_ExtUser")
@Extends(User.class)
public class ExtUser extends User {
    private static final long serialVersionUID = -48888392032895495L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARRIER_ID")
    private Carrier carrier;

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }
}
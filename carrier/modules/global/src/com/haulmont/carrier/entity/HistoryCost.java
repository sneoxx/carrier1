package com.haulmont.carrier.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@PublishEntityChangedEvents
@Table(name = "CARRIER_HISTORY_COST")
@Entity(name = "carrier_HistoryCost")
public class HistoryCost extends StandardEntity {
    private static final long serialVersionUID = -7897359436479864514L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHANGE_DATE", nullable = false)
    @NotNull
    private Date changeDate;

    @NotNull
    @Column(name = "COST", nullable = false)
    private BigDecimal cost;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CARRIER_ID")
    private Carrier carrier;

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }
}
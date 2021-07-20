package com.github.legionarks.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RATE")
public class Rate implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "ORIGIN")
    private Currency origin;

    @Id
    @ManyToOne
    @JoinColumn(name = "TARGET")
    private Currency target;

    @Column(name = "EXCHANGE")
    private BigDecimal exchange;

    public Rate() {
    }

    public Rate(Currency origin, Currency target, BigDecimal exchange) {
        this.origin = origin;
        this.target = target;
        this.exchange = exchange;
    }

    public Currency getOrigin() {
        return origin;
    }

    public void setOrigin(Currency origin) {
        this.origin = origin;
    }

    public Currency getTarget() {
        return target;
    }

    public void setTarget(Currency target) {
        this.target = target;
    }

    public BigDecimal getExchange() {
        return exchange;
    }

    public void setExchange(BigDecimal exchange) {
        this.exchange = exchange;
    }

}

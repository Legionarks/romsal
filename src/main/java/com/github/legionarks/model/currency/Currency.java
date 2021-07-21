package com.github.legionarks.model.currency;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Short id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, unique = true)
    private CurrencyType type;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Rate> rates;

    public Currency() {
    }

    public Currency(CurrencyType type) {
        this.type = type;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public CurrencyType getType() {
        return type;
    }

    public void setType(CurrencyType type) {
        this.type = type;
    }

    public Set<Rate> getRates() {
        return rates;
    }

    public void setRates(Set<Rate> rates) {
        this.rates = rates;
    }
}

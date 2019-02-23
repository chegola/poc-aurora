package com.n2n.poc.marketdata.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marketvalue")
public class MarketValue {

    @Id
    @Column(name = "market_code", length = 20, nullable = false)
    private String marketCode;

    @Column(name = "market_value", length = 200, nullable = false)
    private String marketValue;

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }
}

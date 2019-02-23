package com.n2n.poc.marketdata.service;

import com.n2n.poc.marketdata.domain.MarketValue;

import java.util.Optional;

public interface MarketValueService {

    MarketValue createMarketValue(MarketValue marketValue);

    MarketValue updateMarketValue(String code, String value);

    Optional<MarketValue> getMarketValueByCode(String code);
}

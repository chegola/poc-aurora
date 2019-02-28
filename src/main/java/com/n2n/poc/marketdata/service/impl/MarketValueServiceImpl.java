package com.n2n.poc.marketdata.service.impl;

import com.n2n.poc.marketdata.domain.MarketValue;
import com.n2n.poc.marketdata.repository.MarketValueRepository;
import com.n2n.poc.marketdata.service.MarketValueService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class MarketValueServiceImpl implements MarketValueService {

    private Logger logger = LogManager.getLogger(MarketValueServiceImpl.class);

    @Autowired
    private MarketValueRepository marketValueRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<MarketValue> getMarketValueByCode(String code) {
        logger.info("Get Market Value Code:" + code);
        // rdsInstanceConfigurer.getDataSourceFactory().createDataSource()
        return  marketValueRepository.findById(code);
    }

    @Override
    public MarketValue createMarketValue(MarketValue marketValue) {
        logger.info("Created Market Value Code:" + marketValue.getMarketCode());
        marketValue.setMarketCode(this.convertMarketCode(marketValue.getMarketCode()));
        return marketValueRepository.save(marketValue);
    }

    @Override
    public MarketValue updateMarketValue(String code, String value) {
        return null;
    }

    @Override
    public String convertMarketCode(String marketCode) {
        if (marketCode.equals("BTC")) {
            return "BIT COIN1";
        } else {
            return marketCode;
        }
    }
}

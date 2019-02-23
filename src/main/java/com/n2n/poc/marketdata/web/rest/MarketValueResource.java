package com.n2n.poc.marketdata.web.rest;

import com.n2n.poc.marketdata.domain.MarketValue;
import com.n2n.poc.marketdata.service.MarketValueService;
import com.n2n.poc.marketdata.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class MarketValueResource {

    private final Logger log = LoggerFactory.getLogger(MarketValueResource.class);

    private MarketValueService marketValueService;

    public MarketValueResource(MarketValueService marketValueService) {
        this.marketValueService = marketValueService;
    }

    @PostMapping("/marketvalues")
    public ResponseEntity<MarketValue> createMarketValue(@Valid @RequestBody MarketValue marketValueData) throws URISyntaxException {
        MarketValue marketValue = marketValueService.createMarketValue(marketValueData);
        return ResponseEntity.created(new URI("/api/marketvalues/" + marketValue.getMarketCode()))
                .headers(HeaderUtil.createAlert("marketValue.created", marketValue.getMarketCode()))
                .body(marketValue);
    }
}

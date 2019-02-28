package com.n2n.poc.marketdata.service.impl;

import com.n2n.poc.marketdata.MarketdataApplication;
import com.n2n.poc.marketdata.service.MarketValueService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketdataApplication.class)
public class MarketValueServiceTest {

    @Autowired
    private MarketValueService underTest;

    @Test
    public void testConvertMarketValue() {

        String test = underTest.convertMarketCode("BTC");
        assertThat(test).isEqualTo("BIT COIN");

        test = underTest.convertMarketCode("KBANK");
        assertThat(test).isEqualTo("KBANK");


    }
}
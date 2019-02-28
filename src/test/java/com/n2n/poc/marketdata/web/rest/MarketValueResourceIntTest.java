package com.n2n.poc.marketdata.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n2n.poc.marketdata.MarketdataApplication;
import com.n2n.poc.marketdata.domain.MarketValue;
import com.n2n.poc.marketdata.repository.MarketValueRepository;
import com.n2n.poc.marketdata.service.MarketValueService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MarketdataApplication.class)
public class MarketValueResourceIntTest {

    private MockMvc restMarketValueMockMvc;

    @Autowired
    MarketValueRepository marketValueRepository;

    @Autowired
    MarketValueService marketValueService;

    @Before
    public void setup() {
        MarketValueResource marketValueResource = new MarketValueResource(marketValueService );
        this.restMarketValueMockMvc = MockMvcBuilders.standaloneSetup(marketValueResource).build();
    }

    @Test
    @Transactional
    public void createMarketValue() throws Exception {

        String EXPECT_CODE = "testCode";
        String EXPECT_VALUE =  "testValue";

        int databaseSizeBeforeCreate = marketValueRepository.findAll().size();

        // Create MarketValue
        MarketValue marketValue = new MarketValue();
        marketValue.setMarketCode(EXPECT_CODE);
        marketValue.setMarketValue(EXPECT_VALUE);

        restMarketValueMockMvc.perform(post("/api/marketvalues")
                .contentType(new MediaType(
                        MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8))
                .content( new ObjectMapper().writeValueAsBytes(marketValue))
        ).andExpect(status().isCreated());

        // Validate Database
        List<MarketValue> marketValues = marketValueRepository.findAll();
        assertThat(marketValues).hasSize(databaseSizeBeforeCreate + 1);


        MarketValue marketValueTest = marketValues.get(marketValues.size() - 1);

        assertThat(marketValueTest.getMarketCode()).isEqualTo(EXPECT_CODE);
        assertThat(marketValueTest.getMarketValue()).isEqualTo(EXPECT_VALUE);


    }
}

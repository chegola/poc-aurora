package com.n2n.poc.marketdata.repository;

import com.n2n.poc.marketdata.domain.MarketValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketValueRepository extends JpaRepository<MarketValue, String>, JpaSpecificationExecutor<MarketValue> {

}

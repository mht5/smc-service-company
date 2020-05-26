package com.smc.repository;

import com.smc.domain.CompanyStockExchange;
import com.smc.domain.SCEKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyStockExchangeRepository extends JpaRepository<CompanyStockExchange, SCEKey> {

    List<CompanyStockExchange> findByStockExchangeId(int id);

    List<CompanyStockExchange> findByStockCode(String stockCode);

    List<CompanyStockExchange>  findByCompanyId(int companyId);
}
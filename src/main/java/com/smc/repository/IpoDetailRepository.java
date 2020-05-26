package com.smc.repository;

import com.smc.domain.IpoDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IpoDetailRepository extends JpaRepository<IpoDetail, Integer> {

    @Query("FROM IpoDetail ipo WHERE ipo.openDateTime > :current ORDER BY ipo.openDateTime ASC")
    List<IpoDetail> viewPlannedIpo(@Param("current") Date date);

    List<IpoDetail> findByStockExchangeIdAndStockCode(int stockExchangeId, String stockCode);
}
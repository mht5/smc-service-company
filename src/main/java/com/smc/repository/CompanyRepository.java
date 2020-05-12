package com.smc.repository;

import com.smc.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company findById(int id);

    List<Company> findByName(String name);

    @Query("FROM Company c WHERE c.id IN :companyIds")
    List<Company> findByIds(@Param("companyIds") List<Integer> companyIds);
}
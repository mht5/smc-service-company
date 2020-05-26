package com.smc.repository;

import com.smc.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("FROM Company c WHERE c.deactivated = 'N'")
    List<Company> findAllActive();

    Company findById(int id);

    @Query("FROM Company c WHERE c.name = :name AND c.deactivated = 'N'")
    List<Company> findActiveByName(@Param("name") String name);

    @Query("FROM Company c WHERE c.id IN :companyIds AND c.deactivated = 'N'")
    List<Company> findActiveByIds(@Param("companyIds") List<Integer> companyIds);
}
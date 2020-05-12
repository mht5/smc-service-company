package com.smc.service;

import com.smc.domain.Company;
import com.smc.domain.IpoDetail;
import com.smc.pojo.AddCompanyRequest;

import java.util.List;

public interface CompanyService {

    boolean add(AddCompanyRequest request) throws Exception;

    List<Company> findAll();

    Company findCompanyById(int id) throws Exception;

    Company findCompanyByName(String name) throws Exception;

    boolean deactivateCompany(int id) throws Exception;

    boolean updateCompany(Company company) throws Exception;

    List<Company> findCompanyByIds(List<Integer> companyIds);
}

package com.smc.service;

import com.smc.domain.Company;
import com.smc.domain.CompanyStockExchange;
import com.smc.domain.IpoDetail;
import com.smc.domain.Sector;
import com.smc.pojo.AddCompanyRequest;
import com.smc.repository.CompanyRepository;
import com.smc.repository.CompanyStockExchangeRepository;
import com.smc.repository.IpoDetailRepository;
import com.smc.repository.SectorRepository;
import com.smc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private SectorRepository sectorRepo;

    @Autowired
    private IpoDetailRepository ipoDetailRepo;

    @Autowired
    private CompanyStockExchangeRepository companyStockExchangeRepo;

    private void validateCompany(Company company) throws Exception {
        if (company == null) {
            throw new Exception("Company can not be null.");
        }
        if (company.getName() == null || company.getName().length() < 1) {
            throw new Exception("Company name can not be null.");
        }
        if (company.getSectorId() == 0) {
            throw new Exception("Sector ID can not be null.");
        }
        Sector sector = sectorRepo.findById(company.getSectorId());
        if (sector == null) {
            throw new Exception("Sector with this sector ID does not exist.");
        }
        if (company.getTurnover() == null) {
            throw new Exception("Turnover can not be null.");
        }
        if (company.getCeo() == null || company.getCeo().length() < 1) {
            throw new Exception("Company CEO can not be null.");
        }
        if (company.getBoardOfDirectors() == null || company.getBoardOfDirectors().length() < 1) {
            throw new Exception("Company board of directors can not be null.");
        }
    }

    @Override
    @Transactional(rollbackOn = SQLException.class)
    public boolean add(AddCompanyRequest request) throws Exception {
        Company company = request.getCompany();
        IpoDetail ipoDetail = request.getIpoDetail();
        try {
            validateCompany(company);
            ValidationUtil.validateIpoDetail(ipoDetail, true);
        } catch (Exception e) {
            throw e;
        }
        List<Company> companyList = companyRepo.findActiveByName(company.getName());
        if (!companyList.isEmpty()) {
            throw new Exception("Company with the same name already exists.");
        }
        company.setListedInStockExchanges(true);
        company.setDeactivated(false);
        company = companyRepo.save(company);
        ipoDetailRepo.save(ipoDetail);
        CompanyStockExchange sec = new CompanyStockExchange(company.getId(), ipoDetail.getStockExchangeId(), ipoDetail.getStockCode());
        companyStockExchangeRepo.save(sec);
        return true;
    }

    @Override
    public List<Company> findAll() {
        return companyRepo.findAllActive();
    }

    @Override
    public Company findCompanyById(int id) throws Exception {
        if (id < 1) {
            throw new Exception("Company ID can not be null.");
        }
        return companyRepo.findById(id);
    }

    @Override
    public Company findCompanyByName(String name) throws Exception {
        if (name == null || name.length() < 1) {
            throw new Exception("Company name can not be null.");
        }
        List<Company> companyList = companyRepo.findActiveByName(name);
        if (!companyList.isEmpty()) {
            return companyList.get(0);
        }
        return null;
    }

    @Override
    public boolean deactivateCompany(int id) throws Exception {
        Company company = companyRepo.findById(id);
        if (company == null) {
            throw new Exception("Company with the given ID does not exist.");
        }
        company.setDeactivated(true);
        companyRepo.save(company);
        return true;
    }

    @Override
    public boolean updateCompany(Company company) throws Exception {
        Company c = companyRepo.findById(company.getId()).get();
        if (c == null) {
            throw new Exception("Company with the given ID does not exist.");
        }
        try {
            validateCompany(company);
        } catch (Exception e) {
            throw e;
        }
        c.setSectorId(company.getSectorId());
        c.setTurnover(company.getTurnover());
        c.setCeo(company.getCeo());
        c.setBoardOfDirectors(company.getBoardOfDirectors());
        c.setBriefWriteup(company.getBriefWriteup());
        companyRepo.save(c);
        return true;
    }

    @Override
    public List<Company> findCompanyByIds(List<Integer> companyIds) {
        return companyRepo.findActiveByIds(companyIds);
    }

}

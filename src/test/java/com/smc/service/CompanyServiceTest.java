package com.smc.service;

import com.smc.domain.Company;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    @Rollback
    public void findAllTest() {
        List<Company> companyList = companyService.findAll();
        Assert.assertThat(companyList.size(), greaterThan(0));
    }

    @Test
    @Rollback
    public void findCompanyByIdTest() throws Exception {
        int companyId = 1;
        Company company = companyService.findCompanyById(companyId);
        Assert.assertThat(company.getId(), is(companyId));
    }

    @Test
    @Rollback
    public void findCompanyByNameTest() throws Exception {
        String companyName = "company1";
        Company company = companyService.findCompanyByName(companyName);
        Assert.assertThat(company.getName(), is(companyName));
    }

    @Test
    @Rollback
    public void deactivateCompanyTest() throws Exception {
        int companyId = 1;
        boolean result = companyService.deactivateCompany(companyId);
        Assert.assertThat(result, is(true));
        Company company = companyService.findCompanyById(companyId);
        Assert.assertThat(company.getDeactivated(), is(true));
    }

    @Test
    @Rollback
    public void updateCompanyTest() throws Exception {
        int companyId = 1;
        int sectorId = 1;
        BigDecimal turnover = new BigDecimal(10.20);
        String text = "test";
        Company company = new Company();
        company.setId(companyId);
        company.setName(text);
        company.setSectorId(sectorId);
        company.setTurnover(turnover);
        company.setCeo(text);
        company.setBoardOfDirectors(text);
        company.setBriefWriteup(text);
        boolean result = companyService.updateCompany(company);
        Assert.assertThat(result, is(true));
        company = companyService.findCompanyById(companyId);
        Assert.assertThat(company.getSectorId(), is(sectorId));
        Assert.assertThat(company.getTurnover(), is(turnover));
        Assert.assertThat(company.getCeo(), is(text));
        Assert.assertThat(company.getBoardOfDirectors(), is(text));
        Assert.assertThat(company.getBoardOfDirectors(), is(text));
    }

}

package com.smc.controller;

import com.smc.domain.Company;
import com.smc.pojo.AddCompanyRequest;
import com.smc.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public boolean add(@RequestBody AddCompanyRequest request) throws Exception {
        return companyService.add(request);
    }

    @GetMapping("/list-all")
    public List<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/find-by-id")
    public Company findCompanyById(@RequestParam int id) throws Exception {
        return companyService.findCompanyById(id);
    }

    @GetMapping("/find-by-ids")
    public List<Company> findCompanyByIds(@RequestParam List<Integer> companyIds) {
        return companyService.findCompanyByIds(companyIds);
    }

    @GetMapping("/find-by-name")
    public Company findCompanyByName(@RequestParam String name) throws Exception {
        return companyService.findCompanyByName(name);
    }

    @PostMapping("/deactivate")
    public boolean deactivateCompany(@RequestParam int id) throws Exception {
        return companyService.deactivateCompany(id);
    }

    @PostMapping("/update")
    public boolean updateCompany(@RequestBody Company company) throws Exception {
        return companyService.updateCompany(company);
    }
}

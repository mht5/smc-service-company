package com.smc.pojo;

import com.smc.domain.Company;
import com.smc.domain.IpoDetail;

public class AddCompanyRequest {
    private Company company;
    private IpoDetail ipoDetail;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public IpoDetail getIpoDetail() {
        return ipoDetail;
    }

    public void setIpoDetail(IpoDetail ipoDetail) {
        this.ipoDetail = ipoDetail;
    }
}

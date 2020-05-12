package com.smc.service;

import com.smc.domain.CompanyStockExchange;
import com.smc.domain.IpoDetail;
import com.smc.domain.SCEKey;
import com.smc.repository.CompanyRepository;
import com.smc.repository.CompanyStockExchangeRepository;
import com.smc.repository.IpoDetailRepository;
import com.smc.repository.SectorRepository;
import com.smc.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class IpoServiceImpl implements IpoService {

    @Autowired
    private CompanyRepository companyRepo;

    @Autowired
    private SectorRepository sectorRepo;

    @Autowired
    private IpoDetailRepository ipoDetailRepo;

    @Autowired
    private CompanyStockExchangeRepository companyStockExchangeRepo;

    @Override
    @Transactional(rollbackOn = SQLException.class)
    public boolean updateIpo(IpoDetail ipoDetail) throws Exception {
        IpoDetail ipo = ipoDetailRepo.findById(ipoDetail.getId()).get();
        int stockExchangeId = ipo.getStockExchangeId();
        if (ipo == null) {
            throw new Exception("IPO info with the given ID does not exist.");
        }
        try {
            ValidationUtil.validateIpoDetail(ipoDetail, false);
        } catch (Exception e) {
            throw e;
        }
//        ipo.setStockExchangeId(ipoDetail.getStockExchangeId());
        ipo.setStockCode(ipoDetail.getStockCode());
        ipo.setPricePerShare(ipoDetail.getPricePerShare());
        ipo.setNumberOfShares(ipoDetail.getNumberOfShares());
        ipo.setOpenDateTime(ipoDetail.getOpenDateTime());
        ipoDetailRepo.save(ipo);

        CompanyStockExchange cse = companyStockExchangeRepo.findById(new SCEKey(ipoDetail.getCompanyId(), stockExchangeId)).get();
//        cse.setStockExchangeId(ipoDetail.getStockExchangeId());
        if (!cse.getStockCode().equals(ipoDetail.getStockCode())) {
            cse.setStockCode(ipoDetail.getStockCode());
            companyStockExchangeRepo.save(cse);
        }
        return true;
    }

    @Override
    public List<IpoDetail> viewPlannedIpo() {
        Date date = new Date();
        return ipoDetailRepo.viewPlannedIpo(date);
    }
}

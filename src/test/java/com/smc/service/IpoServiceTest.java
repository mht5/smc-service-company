package com.smc.service;

import com.smc.domain.IpoDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class IpoServiceTest {

    @Autowired
    private IpoService ipoService;

    @Test
    @Rollback
    public void updateIpoTest() throws Exception {
        IpoDetail ipoDetail = new IpoDetail();
        ipoDetail.setId(1);
        ipoDetail.setCompanyId(11);
        ipoDetail.setStockCode("comp11-1");
        ipoDetail.setPricePerShare(new BigDecimal(100.00));
        ipoDetail.setNumberOfShares(100);
        ipoDetail.setOpenDateTime(new Date());
        boolean result = ipoService.updateIpo(ipoDetail);
        Assert.assertThat(result, is(true));
    }

    @Test
    @Rollback
    public void viewPlannedIpoTest() {
        List<IpoDetail> ipoList = ipoService.viewPlannedIpo();
        Assert.assertThat(ipoList.size(), greaterThan(0));
        if (ipoList.size() > 1) {
            for (int i = 0; i < ipoList.size() - 1; i++) {
                Assert.assertThat(ipoList.get(i).getOpenDateTime().before(ipoList.get(i + 1).getOpenDateTime()), is(true));
            }
        }
    }
}

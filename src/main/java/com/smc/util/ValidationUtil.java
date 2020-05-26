package com.smc.util;

import com.smc.domain.IpoDetail;

public class ValidationUtil {

    public static void validateIpoDetail(IpoDetail ipoDetail, boolean checkStockExchangeAndStockCode) throws Exception {
        if (ipoDetail == null) {
            throw new Exception("IPO can not be null.");
        }
        if (checkStockExchangeAndStockCode && ipoDetail.getStockExchangeId() == 0) {
            throw new Exception("Stock exchange ID can not be null.");
        }
        if (checkStockExchangeAndStockCode && (ipoDetail.getStockCode() == null || ipoDetail.getStockCode().length() < 1)) {
            throw new Exception("Stock code can not be null.");
        }
        if (ipoDetail.getPricePerShare() == null) {
            throw new Exception("Price per share can not be null.");
        }
        if (ipoDetail.getNumberOfShares() == 0) {
            throw new Exception("Number of shares can not be null.");
        }
        if (ipoDetail.getOpenDateTime() == null) {
            throw new Exception("Open date time can not be null.");
        }
    }
}

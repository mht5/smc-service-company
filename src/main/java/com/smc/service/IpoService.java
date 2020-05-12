package com.smc.service;

import com.smc.domain.IpoDetail;

import java.util.List;

public interface IpoService {

    boolean updateIpo(IpoDetail ipoDetail) throws Exception;

    List<IpoDetail> viewPlannedIpo();
}

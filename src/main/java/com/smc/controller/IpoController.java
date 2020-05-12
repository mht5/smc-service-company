package com.smc.controller;

import com.smc.domain.IpoDetail;
import com.smc.service.IpoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IpoController {

    @Autowired
    private IpoService ipoService;

    @PostMapping("/update-ipo")
    public boolean updateIpo(@RequestBody IpoDetail ipoDetail) throws Exception {
        return ipoService.updateIpo(ipoDetail);
    }

    @GetMapping("/view-planned-ipo")
    public List<IpoDetail> viewPlannedIpo() throws Exception {
        return ipoService.viewPlannedIpo();
    }
}

package com.smc.controller;

import com.smc.domain.Sector;
import com.smc.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("/list-sectors")
    public List<Sector> findAll() {
        return sectorService.findAll();
    }
}

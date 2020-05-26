package com.smc.service;

import com.smc.domain.Sector;
import com.smc.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    private SectorRepository sectorRepo;

    @Override
    public List<Sector> findAll() {
        return sectorRepo.findAll();
    }
}

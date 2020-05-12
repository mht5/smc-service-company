package com.smc.repository;

import com.smc.domain.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Sector findById(int id);

}
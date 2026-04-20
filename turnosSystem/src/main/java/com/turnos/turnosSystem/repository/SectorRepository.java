package com.turnos.turnosSystem.repository;

import com.turnos.turnosSystem.model.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SectorRepository extends JpaRepository<SectorModel, Long>
{
    List<SectorModel> findByActivoTrue();
}
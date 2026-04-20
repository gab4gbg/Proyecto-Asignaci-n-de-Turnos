package com.turnos.turnosSystem.repository;

import com.turnos.turnosSystem.model.CampoFormularioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CampoFormularioRepository extends JpaRepository<CampoFormularioModel, Long>
{
    List<CampoFormularioModel> findByActivoTrueOrderByOrdenAsc();
}
package com.turnos.turnosSystem.repository;

import com.turnos.turnosSystem.enums.TurnosEstado;
import com.turnos.turnosSystem.model.TurnosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnosRepository extends JpaRepository<TurnosModel, Long>
{

    // Todos los turnos de hoy en un sector
    List<TurnosModel> findBySectorIdAndFechaCreacionBetween
    (
            Long sectorId,
            LocalDateTime inicio,
            LocalDateTime fin
    );

    // Cola visible: turnos en espera y llamados
    List<TurnosModel> findByEstadoInOrderByPrioridadDescFechaCreacionAsc
    (
            List<TurnosEstado> estados
    );
}
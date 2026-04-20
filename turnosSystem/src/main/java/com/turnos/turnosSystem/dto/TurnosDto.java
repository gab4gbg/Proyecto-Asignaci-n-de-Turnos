package com.turnos.turnosSystem.dto;

import com.turnos.turnosSystem.enums.TurnosPrioridad;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TurnosDto
{

    private String nombreUsuario;
    private String cedulaUsuario;
    private TurnosPrioridad prioridad;

    // Para cuando el admin asigna sector
    private Long sectorId;

    // Para cuando el admin cambia estado
    private String nuevoEstado;
}
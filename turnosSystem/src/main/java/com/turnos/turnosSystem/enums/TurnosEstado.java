package com.turnos.turnosSystem.enums;

public enum TurnosEstado
{
    PENDIENTE,    // Turno recién creado
    EN_COLA,      // Esperando ser llamado
    LLAMADO,      // Siendo atendido ahora
    ATENDIDO,     // Finalizado correctamente
    CANCELADO     // Eliminado/anulado
}
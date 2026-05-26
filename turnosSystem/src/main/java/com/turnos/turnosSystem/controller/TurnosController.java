package com.turnos.turnosSystem.controller;

import com.turnos.turnosSystem.dto.TurnosDto;
import com.turnos.turnosSystem.enums.TurnosEstado;
import com.turnos.turnosSystem.model.TurnosModel;
import com.turnos.turnosSystem.services.TurnosServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnosController
{

    private final TurnosServices turnosServices;

    public TurnosController(TurnosServices turnosServices)
    {
        this.turnosServices = turnosServices;
    }

    // POST Usuario pide un turno — ahora incluye sectorId
    @PostMapping
    public ResponseEntity<TurnosModel> crearTurno(@RequestBody TurnosDto dto)
    {
        TurnosModel turno = turnosServices.crearTurno(
                dto.getNombreUsuario(),
                dto.getCedulaUsuario(),
                dto.getPrioridad(),
                dto.getSectorId()
        );
        return ResponseEntity.ok(turno);
    }

    // GET Ver cola actual
    @GetMapping("/cola")
    public ResponseEntity<List<TurnosModel>> verCola()
    {
        return ResponseEntity.ok(turnosServices.obtenerCola());
    }

    // PUT Admin asigna sector a un turno
    @PutMapping("/{id}/sector")
    public ResponseEntity<TurnosModel> asignarSector(
            @PathVariable Long id,
            @RequestBody TurnosDto dto)
    {
        return ResponseEntity.ok(turnosServices.asignarSector(id, dto.getSectorId()));
    }

    // PUT Admin cambia estado del turno
    @PutMapping("/{id}/estado")
    public ResponseEntity<TurnosModel> cambiarEstado(
            @PathVariable Long id,
            @RequestBody TurnosDto dto)
    {
        TurnosEstado estado = TurnosEstado.valueOf(dto.getNuevoEstado().toUpperCase());
        return ResponseEntity.ok(turnosServices.cambiarEstado(id, estado));
    }
}
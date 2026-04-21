package com.turnos.turnosSystem.controller;

import com.turnos.turnosSystem.model.SectorModel;
import com.turnos.turnosSystem.services.SectorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sectores")
public class SectorController
{

    private final SectorService sectorService;

    public SectorController(SectorService sectorService)
    {
        this.sectorService = sectorService;
    }

    // GET /api/sectores - publico
    @GetMapping
    public ResponseEntity<List<SectorModel>> listarActivos()
    {
        return ResponseEntity.ok(sectorService.listarActivos());
    }

    // GET /api/sectores/todos - admins
    @GetMapping("/todos")
    public ResponseEntity<List<SectorModel>> listarTodos()
    {
        return ResponseEntity.ok(sectorService.listarTodos());
    }

    // POST /api/sectores — admin crea sector
    @PostMapping
    public ResponseEntity<SectorModel> crearSector(@RequestBody Map<String, String> body)
    {
        return ResponseEntity.ok(sectorService.crearSector(body.get("nombre")));
    }

    // PUT /api/sectores/{id}/estado — admin activa o desactiva
    @PutMapping("/{id}/estado")
    public ResponseEntity<SectorModel> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body)
    {
        return ResponseEntity.ok(sectorService.cambiarEstado(id, body.get("activo")));
    }

    // PUT /api/sectores/{id}/nombre — admin renombra
    @PutMapping("/{id}/nombre")
    public ResponseEntity<SectorModel> renombrar(
            @PathVariable Long id,
            @RequestBody Map<String, String> body)
    {
        return ResponseEntity.ok(sectorService.renombrar(id, body.get("nombre")));
    }
}
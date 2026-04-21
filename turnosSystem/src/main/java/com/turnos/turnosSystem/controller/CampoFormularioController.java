package com.turnos.turnosSystem.controller;

import com.turnos.turnosSystem.model.CampoFormularioModel;
import com.turnos.turnosSystem.services.CampoFormularioServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campos")
public class CampoFormularioController
{

    private final CampoFormularioServices campoService;

    public CampoFormularioController(CampoFormularioServices campoService)
    {
        this.campoService = campoService;
    }

    // GET /api/campos — Público
    @GetMapping
    public ResponseEntity<List<CampoFormularioModel>> listarActivos()
    {
        return ResponseEntity.ok(campoService.listarActivos());
    }

    // GET /api/campos/todos — Admin
    @GetMapping("/todos")
    public ResponseEntity<List<CampoFormularioModel>> listarTodos()
    {
        return ResponseEntity.ok(campoService.listarTodos());
    }

    // POST /api/campos — Admin crea campo
    @PostMapping
    public ResponseEntity<CampoFormularioModel> crearCampo(
            @RequestBody Map<String, Object> body) {
        return ResponseEntity.ok(campoService.crearCampo(
                (String)  body.get("etiqueta"),
                (String)  body.get("tipo"),
                (Boolean) body.get("requerido"),
                (Integer) body.get("orden")
        ));
    }

    // PUT /api/campos/{id}/estado — Admin activa o desactiva
    @PutMapping("/{id}/estado")
    public ResponseEntity<CampoFormularioModel> cambiarEstado(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> body)
    {
        return ResponseEntity.ok(campoService.cambiarEstado(id, body.get("activo")));
    }

    // PUT /api/campos/{id}/orden — Admin reordena
    @PutMapping("/{id}/orden")
    public ResponseEntity<CampoFormularioModel> reordenar(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body)
    {
        return ResponseEntity.ok(campoService.reordenar(id, body.get("orden")));
    }
}
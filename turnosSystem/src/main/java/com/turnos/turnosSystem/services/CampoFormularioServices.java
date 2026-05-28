package com.turnos.turnosSystem.services;

import com.turnos.turnosSystem.model.CampoFormularioModel;
import com.turnos.turnosSystem.repository.CampoFormularioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampoFormularioServices
{

    private final CampoFormularioRepository campoRepository;

    public CampoFormularioServices(CampoFormularioRepository campoRepository)
    {
        this.campoRepository = campoRepository;
    }

    // Campos activos
    public List<CampoFormularioModel> listarActivos()
    {
        return campoRepository.findByActivoTrueOrderByOrdenAsc();
    }

    // Todos los campos (vista admin)
    public List<CampoFormularioModel> listarTodos()
    {
        return campoRepository.findAll();
    }

    // Admin crea campo nuevo
    public CampoFormularioModel crearCampo(String etiqueta,
                                           String tipo,
                                           boolean requerido,
                                           int orden)
    {
        CampoFormularioModel campo = new CampoFormularioModel();
        campo.setEtiqueta(etiqueta);
        campo.setTipo(tipo);
        campo.setRequerido(requerido);
        campo.setOrden(orden);
        campo.setActivo(true);
        return campoRepository.save(campo);
    }

    // Admin activa o desactiva un campo
    public CampoFormularioModel cambiarEstado(Long id, boolean activo) {
        CampoFormularioModel campo = campoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campo no encontrado: " + id));
        campo.setActivo(activo);
        return campoRepository.save(campo);
    }

    // Admin reordena los campos
    public CampoFormularioModel reordenar(Long id, int nuevoOrden) {
        CampoFormularioModel campo = campoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campo no encontrado: " + id));
        campo.setOrden(nuevoOrden);
        return campoRepository.save(campo);
    }

    // Admin elimina un campo
    public void eliminarCampo(Long id) {
        if (!campoRepository.existsById(id)) {
            throw new RuntimeException("Campo no encontrado: " + id);
        }
        campoRepository.deleteById(id);
    }
}
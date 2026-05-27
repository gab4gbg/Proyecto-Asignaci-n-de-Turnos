package com.turnos.turnosSystem.services;

import com.turnos.turnosSystem.model.SectorModel;
import com.turnos.turnosSystem.repository.SectorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SectorServices
{

    private final SectorRepository sectorRepository;

    public SectorServices(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    // Listar sectores activos (vista pública)
    public List<SectorModel> listarActivos()
    {
        return sectorRepository.findByActivoTrue();
    }

    // Listar todos (vista admin)
    public List<SectorModel> listarTodos()
    {
        return sectorRepository.findAll();
    }

    // Crear sector
    public SectorModel crearSector(String nombre)
    {
        if (sectorRepository.existsByNombre(nombre))
        {
            throw new RuntimeException("Ya existe un sector con el nombre: " + nombre);
        }
        SectorModel sector = new SectorModel();
        sector.setNombre(nombre);
        sector.setActivo(true);
        return sectorRepository.save(sector);
    }

    // Activar / desactivar sector
    public SectorModel cambiarEstado(Long id, boolean activo)
    {
        SectorModel sector = sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector no encontrado: " + id));
        sector.setActivo(activo);
        return sectorRepository.save(sector);
    }

    // Renombrar sector
    public SectorModel renombrar(Long id, String nuevoNombre)
    {
        SectorModel sector = sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector no encontrado: " + id));
        sector.setNombre(nuevoNombre);
        return sectorRepository.save(sector);
    }

    //eliminar sector
    public void eliminarSector(Long id)
    {
        sectorRepository.deleteById(id);
    }
}
package com.turnos.turnosSystem.services;

import com.turnos.turnosSystem.enums.TurnosPrioridad;
import com.turnos.turnosSystem.enums.TurnosEstado;
import com.turnos.turnosSystem.model.SectorModel;
import com.turnos.turnosSystem.model.TurnosModel;
import com.turnos.turnosSystem.repository.SectorRepository;
import com.turnos.turnosSystem.repository.TurnosRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TurnosServices
{

    private final TurnosRepository turnosRepository;
    private final SectorRepository sectorRepository;

    public TurnosServices(TurnosRepository turnosRepository,
                          SectorRepository sectorRepository) {
        this.turnosRepository = turnosRepository;
        this.sectorRepository = sectorRepository;
    }

    //Crear turno

    public TurnosModel crearTurno(String nombreUsuario,
                                  String cedulaUsuario,
                                  TurnosPrioridad prioridad)
    {
        TurnosModel turno = new TurnosModel();
        turno.setNombreUsuario(nombreUsuario);
        turno.setCedulaUsuario(cedulaUsuario);
        turno.setPrioridad(prioridad != null ? prioridad : TurnosPrioridad.NINGUNO);
        turno.setEstado(TurnosEstado.EN_COLA);
        turno.setFechaCreacion(LocalDateTime.now());
        turno.setNumeroTurno(generarNumeroTurno(null));
        return turnosRepository.save(turno);
    }

    //(acción admin)

    public TurnosModel asignarSector(Long turnoId, Long sectorId)
    {
        TurnosModel turno = turnosRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado: " + turnoId));

        SectorModel sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new RuntimeException("Sector no encontrado: " + sectorId));

        turno.setSector(sector);
        turno.setNumeroTurno(generarNumeroTurno(sector));
        return turnosRepository.save(turno);
    }

    //Cambiar estado

    public TurnosModel cambiarEstado(Long turnoId, TurnosEstado nuevoEstado)
    {
        TurnosModel turno = turnosRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado: " + turnoId));

        turno.setEstado(nuevoEstado);

        if (nuevoEstado == TurnosEstado.LLAMADO)
        {
            turno.setFechaAtencion(LocalDateTime.now());
        }

        return turnosRepository.save(turno);
    }

    //Consultar cola actual

    public List<TurnosModel> obtenerCola()
    {
        return turnosRepository.findByEstadoInOrderByPrioridadDescFechaCreacionAsc(
                List.of(TurnosEstado.EN_COLA, TurnosEstado.LLAMADO)
        );
    }

    //Lógica del número de turno

    private String generarNumeroTurno(SectorModel sector)
    {
        String prefijo = sector != null
                ? obtenerPrefijo(sector.getNombre())
                : "G";

        LocalDateTime inicioDia = LocalDate.now().atStartOfDay();
        LocalDateTime finDia    = inicioDia.plusDays(1);

        List<TurnosModel> turnosHoy;

        if (sector != null)
        {
            turnosHoy = turnosRepository.findBySectorIdAndFechaCreacionBetween(
                    sector.getId(), inicioDia, finDia);
        }
        else
        {
            turnosHoy = turnosRepository.findBySectorIdAndFechaCreacionBetween(
                    null, inicioDia, finDia);
        }

        int siguiente = turnosHoy.size() + 1;
        return String.format("%s-%03d", prefijo, siguiente);
    }

    private String obtenerPrefijo(String nombreSector)
    {
        // Toma la primera letra del nombre
        if (nombreSector == null || nombreSector.isBlank()) return "G";
        String[] palabras = nombreSector.trim().split("\\s+");
        // Si el nombre tiene varias palabras, usa la última letra significativa
        String ultima = palabras[palabras.length - 1];
        return ultima.substring(0, 1).toUpperCase();
    }
}
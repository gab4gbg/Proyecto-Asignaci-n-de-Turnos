package com.turnos.turnosSystem.model;

import com.turnos.turnosSystem.enums.TurnosEstado;
import com.turnos.turnosSystem.enums.TurnosPrioridad;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TurnosModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_turno", nullable = false)
    private String numeroTurno;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    @Column(name = "cedula_usuario")
    private String cedulaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnosEstado estado = TurnosEstado.PENDIENTE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnosPrioridad prioridad = TurnosPrioridad.NINGUNO;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private SectorModel sector;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(name = "fecha_atencion")
    private LocalDateTime fechaAtencion;

    // Etiqueta libre para prioridad PERSONALIZADO (ej. "Menores de edad")
    @Column(name = "prioridad_etiqueta")
    private String prioridadEtiqueta;
}
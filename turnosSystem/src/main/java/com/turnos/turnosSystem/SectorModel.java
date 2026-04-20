package com.turnos.turnosSystem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sectores")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SectorModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private boolean activo = true;
}
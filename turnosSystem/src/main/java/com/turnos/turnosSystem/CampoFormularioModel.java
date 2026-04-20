package com.turnos.turnosSystem;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "campos_formulario")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CampoFormularioModel

{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String etiqueta;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private boolean requerido = true;

    @Column(nullable = false)
    private boolean activo = true;

    @Column(name = "orden")
    private int orden = 0;
}
package com.turnos.turnosSystem.dto;

public class GoogleAuthResponseDto
{
    private String nombre;
    private String email;
    private String foto;

    public GoogleAuthResponseDto(String nombre, String email, String foto) {
        this.nombre = nombre;
        this.email  = email;
        this.foto   = foto;
    }

    public String getNombre() { return nombre; }
    public String getEmail()  { return email; }
    public String getFoto()   { return foto; }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.entity;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author tmore
 */
public class Persona implements Serializable {
    
    @Min(value=9999, message="La cedula debe ser ayor a 9999")
    private int cedula;
    
    @Size(min=3, max=10, message="Minimo 3 caracteres maximo 10")
    @NotNull
    private String nombre;
    
    public Persona(){
        
    }

    public Persona(int cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}

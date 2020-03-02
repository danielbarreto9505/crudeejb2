/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.interfaces;

import com.udec.entity.Persona;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author tmore
 */
@Local
public interface IPersona {
    public List<Persona> retornarListaPersona();
    public Persona retornarPersonaEspecifica(int cedula);
    public Persona guardarPersona(Persona persona);
    public Persona editarPersona(Persona persona);
    public void eliminarPersona(int cedula);
    public Persona retornarPersona();
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.controller;

import com.udec.entity.Persona;
import com.udec.interfaces.IPersona;
import com.udec.services.PersonaServices;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
//no entiendo porq le sale eso s/*
/**
 * si pego las carpetas que le paso corin? 
 */

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tmore
 */
@Stateless
@Path("/personas")
public class PersonaController {
    
    @EJB 
    private PersonaServices iPersona;
            
    @GET
    @Path("/obtenerPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPersona(){
        Persona persona = iPersona.retornarPersona();
        return Response.status(Response.Status.OK).entity(persona).build();
    }
    
    @GET
    @Path("/obtenerListaPersona")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerListaPersona(){
        return Response.status(Response.Status.OK).entity(iPersona.retornarListaPersona()).build();
    }
    
    @GET
    @Path("/obtenerPersonaCedula/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPersonaCedula(@PathParam ("cedula") int cedula){
        Persona persona=iPersona.retornarPersonaEspecifica(cedula);           
        return Response.status(Response.Status.OK).entity(persona).build();
    }
    
    @POST
    @Path("/guardarPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Persona guardarPersona(@Valid Persona persona){
        System.out.println("Entro guardar :v");
        Persona pl = iPersona.guardarPersona(persona);
        return pl;
    }
    
    @PUT
    @Path("/editarPersona")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Persona editarPersona(Persona persona){
        Persona pl=iPersona.editarPersona(persona);
        return pl;
    }
    
    
    @DELETE
    @Path("/eliminarPersona/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public void eliminarPersona(@PathParam ("cedula") int cedula){
        iPersona.eliminarPersona(cedula);
    }
}

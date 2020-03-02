/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.services;

import com.udec.entity.Persona;
import com.udec.interfaces.IPersona;
import java.io.BufferedReader;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.ejb.Stateless;

/**
 *
 * @author tmore
 */
@Stateless
public class PersonaServices implements IPersona{
    
    
    private ArrayList<Persona> listaPersonas = new ArrayList<>();
    
    
    @Override
    public Persona retornarPersona(){
        Persona persona=new Persona(123,"Tatiana");
        return persona;
    }
    
    
    
    
    @Override
    public Persona retornarPersonaEspecifica(int cedula){
           if(listaPersonas==null){
               listaPersonas=new ArrayList(); 
           }
        for(Persona lp:listaPersonas){
            if(lp.getCedula()==cedula){
                return lp;
            }
        }
        return new Persona();
    }
    
    @Override
    public Persona guardarPersona(Persona persona){
        System.out.println("Ahora llego aqui :v");
        FileWriter fichero = null;
        PrintWriter pw = null;
        String registroString = "";
        try
        {
            String datos = traerDatos();
            fichero = new FileWriter("C:\\Users\\tmore\\PROYECTOS_JAVA\\LineaDeProfundicacion2\\EjbCrud\\EjbCrud-web\\PersonasLista.txt");
            pw = new PrintWriter(fichero);

            
            String[] auxiliar = datos.split("\n");
            for (int i = 0; i < auxiliar.length; i++) {
                pw.println(auxiliar[i]);
            }
            
            pw.println(persona.getCedula() + "," + persona.getNombre() + ",");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return persona;
    }
    
    @Override
    public Persona editarPersona(Persona persona){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String registroString = "";
        try
        {
            String datos = traerDatos();
            List<Persona> personas = conversorPersonas(datos);
            fichero = new FileWriter("C:\\Users\\tmore\\PROYECTOS_JAVA\\LineaDeProfundicacion2\\EjbCrud\\EjbCrud-web\\PersonasLista.txt");
            pw = new PrintWriter(fichero);

            
            try {
                for (Persona persona1 : personas) {
                    if (persona1.getCedula()== persona.getCedula()) {
                       
                        personas.remove(persona1);
                    }
                }
            } catch (Exception e) {
                System.out.println("");
            }
            try {
                for (Persona persona1 : personas) {
                    String auxiliarRegistro = "";
                    pw.println(persona1.getCedula()+ "," + persona1.getNombre() + "," );
                }
            } catch (Exception e) {
                System.out.println("");
            }
            
            pw.println(persona.getCedula()+ "," + persona.getNombre() + "," );
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return persona;
    }
    
    @Override
    public List<Persona> retornarListaPersona(){
        List<Persona> personas = new ArrayList<>();
        FileReader fr = null;
        String datos = "";
        
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            File archivo = new File ("C:\\Users\\tmore\\PROYECTOS_JAVA\\LineaDeProfundicacion2\\EjbCrud\\EjbCrud-web\\PersonasLista.txt");
            fr = new FileReader (archivo);
            BufferedReader br = new BufferedReader(fr);
            
            // Lectura del fichero
            String linea = "";
            while((linea=br.readLine())!=null){
                datos += linea + "\n";
            }
            
            String[] personaString = datos.split("\n");
            for (int i = 0; i < personaString.length; i++) {
                Persona persona = new Persona();
                String[] auxiliar = personaString[i].split(",");                
                persona.setCedula(Integer.parseInt(auxiliar[0]));
                persona.setNombre(auxiliar[1]);
                personas.add(persona);
                return personas;
            }
            int i = 0;
            for (Persona persona : personas) {
                System.out.println(i + ". " + persona.getNombre() + " "); 
                i++;
            }
            
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        } finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return listaPersonas;
    }
    
    @Override
    public void eliminarPersona(int cedula){    
        
    }

    public List<Persona> conversorPersonas(String datos){
        List<Persona> personas = new ArrayList<>();
        try {
            String[] personaString = datos.split("\n");
            for (int i = 0; i < personaString.length; i++) {
                List<Persona> registros = new ArrayList<>();
                Persona persona = new Persona();
                String[] auxiliar = personaString[i].split(",");
                try {
                    String[] registroString = auxiliar[3].split("~");
                    for (int j = 0; j < registroString.length; j++) {
                        Persona registro = new Persona();
                        String[] registroS = registroString[j].split("&");
                        boolean estado = false;
                        if(registroS[2].equals("true")){
                            estado = true;
                        }else if (registroS[2].equals("false")) {
                            estado = false;
                        }
                        registro.setCedula(Integer.parseInt(registroS[0]));
                        registro.setNombre(registroS[1]);
                        registros.add(registro);
                    }
                } catch (Exception e) {
                    System.out.println("");
                }
                persona.setCedula(Integer.parseInt(auxiliar[0]));
                persona.setNombre(auxiliar[1]); 
                personas.add(persona);
            }
        } catch(Exception e){
            System.out.println("Algo salio mal");
        }
        return personas;
    }
    
    public void llenarDatos(){
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1, "Pepito"));
        personas.add(new Persona(2, "Bananin"));
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:\\Users\\tmore\\PROYECTOS_JAVA\\LineaDeProfundicacion2\\EjbCrud\\EjbCrud-web\\PersonasLista.txt");
            pw = new PrintWriter(fichero);
            for (Persona persona : personas) {
                String registroString = "";
                pw.println(persona.getNombre()+ "," + persona.getCedula());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public String traerDatos() throws FileNotFoundException, IOException, ClassNotFoundException{
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String datos = "";
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File ("C:\\Users\\tmore\\PROYECTOS_JAVA\\LineaDeProfundicacion2\\EjbCrud\\EjbCrud-web\\PersonasLista.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            // Lectura del fichero
            String linea = "";
            while((linea=br.readLine())!=null){
                datos += linea + "\n";
            }
        }
        catch(Exception e){
            e.printStackTrace();
        } finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return datos;
    }
                 
}

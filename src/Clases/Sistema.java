/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author juan
 */
public class Sistema {
    
    Conexion conexion=new Conexion();
    Statement Instruccion;
    ResultSet Registro;
    
    public boolean existe_tablas(){ 
        try
        {   
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT t.table_name tablas FROM information_schema.tables t WHERE t.table_schema = 'public' ORDER BY t.table_name");
            Registro.next();
            String rpta = Registro.getString("tablas"); 
            if(rpta.equals("")){
                System.out.println("Sin tablas en la base de datos");
                return false; 
            }else{
                return true; 
            }
        }
        catch(Exception X){ 
            System.out.println(X.getMessage()); 
            System.out.println("Sin tablas en la base de datos");
            return false;
        }
    }
    
    public void eliminar_tablas(){ 
        try {
            System.err.println("Eliminar tablas");
            PreparedStatement pst  = conexion.con.prepareStatement("drop table if exists Alumnos;drop table if exists Area_Academica;drop table if exists Asignaturas;drop table if exists Calificaciones;drop table if exists Grado;drop table if exists Matricula;drop table if exists Profesores;");
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author juan
 */
public class Conexion {
    //  Declaramos las variables con las que vamos a realizar la conexion con la base de datos
    String Usuario;
    String Clave;
    String Driver;
    String BD;
    public Connection con; // Esta variable para contener el resultado de la conexion
    public Conexion() {
        Usuario="postgres"; // Este es el nombre del usuario de la base de datos de postgret
        Clave="admin"; // Este es la contraseña para entrar a la base de datos
        Driver="org.postgresql.Driver"; // Direccion de la libreria para conectarse a la base de datos
        BD="jdbc:postgresql://localhost:5432/colegio"; // Puerto y nombre de la base datos
        try
        {
            Class.forName(Driver);  // Registramos el driver que  vamos a utilizar
            con = DriverManager.getConnection(BD, Usuario, Clave); // Pasamos las variables a la funcion y el resultado de la conexion, la  tendra la variable "con"          
            System.out.println("Conexion Exitosa");
        }
        catch(Exception e)
        {   
            System.out.println("Error en la Conexion");
            System.out.print(e.getMessage()); // En caso de error vendra aqui e imprimira el mensaje
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan
 */
public class Alumno {
    int IdAlumno;
    String Nombres;
    String Apellidos;
    String Direccion;
    String Telefono;
    String Email;
    String Dni;
    String Clave;
    String Sexo;
    String Fenac;
    String Estado;
    
    Conexion conexion=new Conexion();
    Statement Instruccion;
    ResultSet Registro;
    String []datos = new String [13];
    
    public Alumno(){
    }
    
    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdArea) {
        this.IdAlumno = IdArea;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getFenac() {
        return Fenac;
    }

    public void setFenac(String Fenac) {
        this.Fenac = Fenac;
    }
    
        public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    public DefaultTableModel tabla(){
        Arrays.fill(datos,"");

        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Nombres");
            modelo.addColumn("Dni");
            modelo.addColumn("Estado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT Id_Alumno, Nombres, Apellidos, Dni, Estado FROM Alumnos ORDER BY Apellidos" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Alumno");
                datos[1]= Registro.getString("Apellidos");
                datos[2]= Registro.getString("Nombres");
                datos[3]= Registro.getString("Dni");
                datos[4]= Registro.getString("Estado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public DefaultTableModel Buscar(String dni,String nom,String ape){
        Arrays.fill(datos,"");
        
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Apellidos");
            modelo.addColumn("Nombres");
            modelo.addColumn("Dni");
            modelo.addColumn("Estado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("select * from Alumnos WHERE LOWER(Dni) LIKE LOWER('%"+dni+"%') and LOWER(Nombres) LIKE LOWER('%"+nom+"%') and LOWER(Apellidos) LIKE LOWER('%"+ape+"%') ORDER BY Apellidos" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Alumno");
                datos[1]= Registro.getString("Apellidos");
                datos[2]= Registro.getString("Nombres");
                datos[3]= Registro.getString("Dni");
                datos[4]= Registro.getString("Estado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public boolean dni_validar(String num){ 
        try
        {   
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT count(*) cant from Alumnos WHERE Dni =\'"+num+"\'" );
            Registro.next();
            int cant = Registro.getInt("cant"); 
            if(cant == 0){ 
                return false; 
            }else{
                return true; 
            }
        }
        catch(Exception X){ 
            System.out.println(X.getMessage()); 
            return false;
        }
    }
    
    public String dni_obtener(int cod){ 
        try
        {   
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT coalesce(Dni, '') Dni from Alumnos WHERE Id_Alumno =\'"+cod+"\'" );
            Registro.next();
            String nroDni = Registro.getString("Dni"); 
            System.out.println("dni "+ nroDni);
            if(nroDni.equals("")){ 
                return ""; 
            }else{
                return nroDni; 
            }
        }
        catch(Exception X){ 
            System.out.println(X.getMessage()); 
            return "";
        }
    }
  
    
    public void Insertar(){
        try {
            System.err.println("INSERTAR");
            PreparedStatement pst  = conexion.con.prepareStatement("INSERT INTO Alumnos(Nombres,Apellidos,Telefono,Email,Dni,Clave,Sexo,Fenac,Direccion,Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, Nombres);
            pst.setString(2, Apellidos);
            pst.setString(3, Telefono);
            pst.setString(4, Email);
            pst.setString(5, Dni);
            pst.setString(6, Clave);
            pst.setString(7, Sexo);
            pst.setString(8, Fenac);
            pst.setString(9, Direccion);
            pst.setString(10, Estado);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public String[] Obtener(int codigo){
        Arrays.fill(datos,"");

        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("Select Nombres, Apellidos, Telefono, Email, Dni, Clave, Sexo, Fenac, Direccion,Estado FROM Alumnos WHERE Id_Alumno =\'"+codigo+"\' LIMIT 1" );
            if(Registro.next())
            {
                datos[0]= Registro.getString("Nombres");
                datos[1]= Registro.getString("Apellidos");
                datos[2]= Registro.getString("Telefono");
                datos[3]= Registro.getString("Email");
                datos[4]= Registro.getString("Dni");
                datos[5]= Registro.getString("Clave");
                datos[6]= Registro.getString("Sexo");
                datos[7]= Registro.getString("Fenac");
                datos[8]= Registro.getString("Direccion");
                datos[9]= Registro.getString("Estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public void Modificar(){
        try {
            System.err.println("MODIFICAR");
            PreparedStatement pst  = conexion.con.prepareStatement("UPDATE Alumnos SET Nombres=?, Apellidos=?, Telefono=?, Email=?, Dni=?, Clave=?, Sexo=?, Fenac=?, Direccion=?, Estado=? WHERE Id_Alumno=?");
            pst.setString(1, Nombres);
            pst.setString(2, Apellidos);
            pst.setString(3, Telefono);
            pst.setString(4, Email);
            pst.setString(5, Dni);
            pst.setString(6, Clave);
            pst.setString(7, Sexo);
            pst.setString(8, Fenac);
            pst.setString(9, Direccion);
            pst.setString(10, Estado);
            pst.setInt(11, IdAlumno);


            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Eliminar(int Id){
        try {
            System.err.println("Eliminar");
            PreparedStatement pst  = conexion.con.prepareStatement("DELETE FROM Alumnos  WHERE Id_Alumno=?");
            pst.setInt(1, Id);
            
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

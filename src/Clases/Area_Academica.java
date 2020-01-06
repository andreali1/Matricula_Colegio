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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan
 */
public class Area_Academica {
    int IdArea;
    String Nombres;
    String Apellidos;
    String Direccion;
    String Telefono;
    String Email;
    String Dni;
    String Clave;
    String Sexo;
    String Fenac;
    String Cargo;
    String Tipo;
    String Estado;
    
    Conexion conexion=new Conexion();
    Statement Instruccion;
    ResultSet Registro;
    String []datos = new String [13];
    
    public Area_Academica(){
    }
    
    public int getIdArea() {
        return IdArea;
    }

    public void setIdArea(int IdArea) {
        this.IdArea = IdArea;
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

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
        public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    public String login(String N, String P){ 
        try
        {   
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT Tipo FROM Area_Academica WHERE Dni =\'"+N+"\' AND Clave =\'"+P+"\'" );
            Registro.next();
            String tipo = Registro.getString("Tipo"); 
            if(!tipo.equals("")){ 
                return tipo; 
            }else{
                JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos"); 
                return ""; 
            }
        }
        catch(Exception X){ 
            JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrectos"); 
            System.out.println(X.getMessage()); 
            return "";
        }
    }
    
    public DefaultTableModel tabla(){
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
            modelo.addColumn("Cargo");
            modelo.addColumn("Tipo");
            modelo.addColumn("Estado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT Id_Areaacademica, Nombres, Apellidos, Dni, Cargo, Tipo, Estado FROM Area_Academica ORDER BY Apellidos" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Areaacademica");
                datos[1]= Registro.getString("Apellidos");
                datos[2]= Registro.getString("Nombres");
                datos[3]= Registro.getString("Dni");
                datos[4]= Registro.getString("Cargo");
                datos[5]= Registro.getString("Tipo");
                datos[6]= Registro.getString("Estado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public DefaultTableModel Buscar(String dni,String nom,String ape){
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
            modelo.addColumn("Cargo");
            modelo.addColumn("Tipo");
            modelo.addColumn("Estado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("select * from Area_Academica WHERE LOWER(Dni) LIKE LOWER('%"+dni+"%') and LOWER(Nombres) LIKE LOWER('%"+nom+"%') and LOWER(Apellidos) LIKE LOWER('%"+ape+"%') ORDER BY Apellidos" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Areaacademica");
                datos[1]= Registro.getString("Apellidos");
                datos[2]= Registro.getString("Nombres");
                datos[3]= Registro.getString("Dni");
                datos[4]= Registro.getString("Cargo");
                datos[5]= Registro.getString("Tipo");
                datos[6]= Registro.getString("Estado");
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
            Registro = Instruccion.executeQuery("SELECT count(*) cant from Area_Academica WHERE Dni =\'"+num+"\'" );
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
            Registro = Instruccion.executeQuery("SELECT coalesce(Dni, '') Dni from Area_Academica WHERE Id_Areaacademica =\'"+cod+"\'" );
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
            PreparedStatement pst  = conexion.con.prepareStatement("INSERT INTO Area_Academica(Nombres,Apellidos,Telefono,Email,Dni,Clave,Sexo,Fenac,Cargo,Tipo,Direccion,Estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, Nombres);
            pst.setString(2, Apellidos);
            pst.setString(3, Telefono);
            pst.setString(4, Email);
            pst.setString(5, Dni);
            pst.setString(6, Clave);
            pst.setString(7, Sexo);
            pst.setString(8, Fenac);
            pst.setString(9, Cargo);
            pst.setString(10, Tipo);
            pst.setString(11, Direccion);
            pst.setString(12, Estado);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public String[] Obtener(int codigo){
        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("Select Nombres, Apellidos, Telefono, Email, Dni, Clave, Sexo, Fenac, Cargo, Tipo, Direccion,Estado FROM Area_Academica WHERE Id_areaacademica =\'"+codigo+"\' LIMIT 1" );
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
                datos[8]= Registro.getString("Cargo");
                datos[9]= Registro.getString("Tipo");
                datos[10]= Registro.getString("Direccion");
                datos[11]= Registro.getString("Estado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public void Modificar(){
        try {
            System.err.println("MODIFICAR");
            PreparedStatement pst  = conexion.con.prepareStatement("UPDATE Area_Academica SET Nombres=?, Apellidos=?, Telefono=?, Email=?, Dni=?, Clave=?, Sexo=?, Fenac=?, Cargo=?, Tipo=?, Direccion=?, Estado=? WHERE Id_areaacademica=?");
            pst.setString(1, Nombres);
            pst.setString(2, Apellidos);
            pst.setString(3, Telefono);
            pst.setString(4, Email);
            pst.setString(5, Dni);
            pst.setString(6, Clave);
            pst.setString(7, Sexo);
            pst.setString(8, Fenac);
            pst.setString(9, Cargo);
            pst.setString(10, Tipo);
            pst.setString(11, Direccion);
            pst.setString(12, Estado);
            pst.setInt(13, IdArea);


            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Eliminar(int IdArea){
        try {
            System.err.println("Eliminar");
            PreparedStatement pst  = conexion.con.prepareStatement("DELETE FROM Area_Academica  WHERE Id_areaacademica=?");
            pst.setInt(1, IdArea);
            
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

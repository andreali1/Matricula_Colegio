/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexion.Conexion;

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
public class Grado {
    int IdGrado;
    String Pabellon;
    String Piso;
    String Grado;
    
    Conexion conexion=new Conexion();
    Statement Instruccion;
    ResultSet Registro;
    String []datos = new String [4];

    public String getGrado() {
        return Grado;
    }

    public void setGrado(String Grado) {
        this.Grado = Grado;
    }

    public int getIdGrado() {
        return IdGrado;
    }

    public void setIdGrado(int IdGrado) {
        this.IdGrado = IdGrado;
    }

    public String getPabellon() {
        return Pabellon;
    }

    public void setPabellon(String Pabellon) {
        this.Pabellon = Pabellon;
    }

    public String getPiso() {
        return Piso;
    }

    public void setPiso(String Piso) {
        this.Piso = Piso;
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
            modelo.addColumn("Pabellón");
            modelo.addColumn("Piso");
            modelo.addColumn("Grado");
            
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT Id_Grado, Pabellon, Piso, Grado FROM Grado ORDER BY Pabellon" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Grado");
                datos[1]= Registro.getString("Pabellon");
                datos[2]= Registro.getString("Piso");
                datos[3]= Registro.getString("Grado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public DefaultTableModel Buscar(String pabellon, String piso, String nom){
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Pabellón");
            modelo.addColumn("Piso");
            modelo.addColumn("Grado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("select * from Grado WHERE LOWER(Pabellon) LIKE LOWER('%"+pabellon+"%') and LOWER(Piso) LIKE LOWER('%"+piso+"%') and LOWER(Grado) LIKE LOWER('%"+nom+"%') ORDER BY Pabellon" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Grado");
                datos[1]= Registro.getString("Pabellon");
                datos[2]= Registro.getString("Piso");
                datos[3]= Registro.getString("Grado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public void Insertar(){
        try {
            System.err.println("INSERTAR");
            PreparedStatement pst  = conexion.con.prepareStatement("INSERT INTO Grado(Pabellon, Piso, Grado) VALUES (?, ?, ?)");
            pst.setString(1, Pabellon);
            pst.setString(2, Piso);
            pst.setString(3, Grado);
            
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public String[] Obtener(int codigo){
        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("Select Pabellon, Piso, Grado FROM Grado WHERE Id_Grado =\'"+codigo+"\' LIMIT 1" );
            if(Registro.next())
            {
                datos[0]= Registro.getString("Pabellon");
                datos[1]= Registro.getString("Piso");
                datos[2]= Registro.getString("Grado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public void Modificar(){
        try {
            System.err.println("MODIFICAR");
            PreparedStatement pst  = conexion.con.prepareStatement("UPDATE Grado SET Pabellon=?, Piso=?, Grado=? WHERE Id_Grado=?");
            pst.setString(1, Pabellon);
            pst.setString(2, Piso);
            pst.setString(3, Grado);
            pst.setInt(4, IdGrado);


            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Eliminar(int IdGrado){
        try {
            System.err.println("Eliminar");
            PreparedStatement pst  = conexion.con.prepareStatement("DELETE FROM Grado WHERE Id_Grado=?");
            pst.setInt(1, IdGrado);
            
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

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
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juan
 */
public class Matricula {
    
    int IdMaticula,IdAlumno,IdGrado;
    double Costo;
    String Grado;
    String Fecha;
    String Estado;
    String Apoderado;
    String Ocupacion;
    String Vinculo;
    String Dni;
    String Apellidos;
    String Nombres;
    
    Conexion conexion=new Conexion();
    Statement Instruccion;
    ResultSet Registro;
    String []datos = new String [13];

    ArrayList<String> cboGrado = new ArrayList<String>();

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

    public String getVinculo() {
        return Vinculo;
    }

    public void setVinculo(String Vinculo) {
        this.Vinculo = Vinculo;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public int getIdMaticula() {
        return IdMaticula;
    }

    public void setIdMaticula(int IdMaticula) {
        this.IdMaticula = IdMaticula;
    }

    public double getCosto() {
        return Costo;
    }

    public void setCosto(double Costo) {
        this.Costo = Costo;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getApoderado() {
        return Apoderado;
    }

    public void setApoderado(String Apoderado) {
        this.Apoderado = Apoderado;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public Statement getInstruccion() {
        return Instruccion;
    }

    public void setInstruccion(Statement Instruccion) {
        this.Instruccion = Instruccion;
    }

    public DefaultTableModel tabla(String fechaIni, String fechaFin){
        Arrays.fill(datos,"");
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Dni");
            modelo.addColumn("Alumno");
            modelo.addColumn("Apoderado");
            modelo.addColumn("Fecha");
            modelo.addColumn("Grado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT m.Id_Matricula, a.Apellidos,a.Nombres, a.Dni,m.Apoderado,m.Fecha,m.Costo,g.Grado FROM Matricula m INNER JOIN Alumnos a ON m.Id_Alumno = a.Id_Alumno LEFT JOIN Grado g ON g.Id_Grado = m.Id_Grado WHERE m.Fecha >=\'"+fechaIni+"\' and m.Fecha <=\'"+fechaFin+"\' ORDER BY a.Apellidos" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Matricula");
                datos[1]= Registro.getString("Dni");
                datos[2]= Registro.getString("Apellidos")+" "+Registro.getString("Nombres");
                datos[3]= Registro.getString("Apoderado");
                datos[4]= Registro.getString("Fecha");
                datos[5]= Registro.getString("Grado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public ArrayList<String> grado(){
        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT Id_Grado, Grado FROM Grado");
            while(Registro.next())
            {
                cboGrado.add("("+(Registro.getString("Id_Grado"))+") "+(Registro.getString("Grado")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cboGrado;
    }
    
    public DefaultTableModel Buscar(String dni,String nom,String ape,String fechaIni,String fechaFin){
        Arrays.fill(datos, "");
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Dni");
            modelo.addColumn("Alumno");
            modelo.addColumn("Apoderado");
            modelo.addColumn("Fecha");
            modelo.addColumn("Grado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT m.Id_Matricula, a.Apellidos,a.Nombres, a.Dni,m.Apoderado,m.Fecha,m.Costo,g.Grado FROM Matricula m INNER JOIN Alumnos a ON m.Id_Alumno = a.Id_Alumno LEFT JOIN Grado g ON g.Id_Grado = m.Id_Grado WHERE LOWER(a.Dni) LIKE LOWER('%"+dni+"%') and LOWER(a.Nombres) LIKE LOWER('%"+nom+"%') and LOWER(a.Apellidos) LIKE LOWER('%"+ape+"%') and m.Fecha >=\'"+fechaIni+"\' and m.Fecha <=\'"+fechaFin+"\'" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Matricula");
                datos[1]= Registro.getString("Dni");
                datos[2]= Registro.getString("Apellidos")+" "+Registro.getString("Nombres");
                datos[3]= Registro.getString("Apoderado");
                datos[4]= Registro.getString("Fecha");
                datos[5]= Registro.getString("Grado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public DefaultTableModel Buscar_Matricula(String aDni,String aNom,String aApe,String fechaIni,String fechaFin,String pDni,String pNom,String pApe){
        Arrays.fill(datos, "");
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Id");
            modelo.addColumn("Dni");
            modelo.addColumn("Alumno");
            modelo.addColumn("Apoderado");
            modelo.addColumn("Fecha");
            modelo.addColumn("Profesor");
            modelo.addColumn("Grado");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT m.Id_Matricula, a.Apellidos ape,a.Nombres nom,p.Dni,p.Apellidos,p.Nombres, a.Dni,m.Apoderado,m.Fecha,m.Costo,g.Grado FROM Matricula m INNER JOIN Alumnos a ON m.Id_Alumno = a.Id_Alumno LEFT JOIN Grado g ON g.Id_Grado = m.Id_Grado INNER JOIN Profesores p ON p.Id_Grado = g.Id_Grado WHERE LOWER(a.Dni) LIKE LOWER('%"+aDni+"%') and LOWER(a.Nombres) LIKE LOWER('%"+aNom+"%') and LOWER(p.Dni) LIKE LOWER('%"+pDni+"%') and LOWER(p.Nombres) LIKE LOWER('%"+pNom+"%') and LOWER(a.Apellidos) LIKE LOWER('%"+aApe+"%') and LOWER(p.Apellidos) LIKE LOWER('%"+pApe+"%') and m.Fecha >=\'"+fechaIni+"\' and m.Fecha <=\'"+fechaFin+"\'" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Matricula");
                datos[1]= Registro.getString("Dni");
                datos[2]= Registro.getString("ape") + " " + Registro.getString("nom");
                datos[3]= Registro.getString("Apoderado");
                datos[4]= Registro.getString("Fecha");
                datos[5]= Registro.getString("Apellidos") + " " + Registro.getString("Nombres");
                datos[6]= Registro.getString("Grado");
                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modelo;
    }
    
    public DefaultTableModel Buscar_Monto(String aDni,String aNom,String aApe,String fechaIni,String fechaFin,String pDni,String pNom,String pApe){
        Arrays.fill(datos, "");
        DefaultTableModel modelo= new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return  false;
            }
        };
        try {
            modelo.addColumn("Monto");
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("SELECT sum(m.Costo) Monto FROM Matricula m WHERE m.Fecha >=\'"+fechaIni+"\' and m.Fecha <=\'"+fechaFin+"\'" );
            while(Registro.next())
            {
                datos[0]= "S/. "+Registro.getString("Monto");
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
            PreparedStatement pst  = conexion.con.prepareStatement("INSERT INTO Matricula (Id_Alumno, Costo,Fecha,Estado,Apoderado,Ocupacion,Vinculo,Id_Grado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, IdAlumno);
            pst.setDouble(2, Costo);
            pst.setString(3, Fecha);
            pst.setString(4, Estado);
            pst.setString(5, Apoderado);
            pst.setString(6, Ocupacion);
            pst.setString(7, Vinculo);
            pst.setInt(8, IdGrado);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
    public String[] Buscar_Alumno(String nro){
        Arrays.fill(datos, "");

        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("Select Id_Alumno,Dni,Nombres,Apellidos FROM Alumnos WHERE Dni =\'"+nro+"\' LIMIT 1" );
            while(Registro.next())
            {
                datos[0]= Registro.getString("Id_Alumno");
                datos[1]= Registro.getString("Dni");
                datos[2]= Registro.getString("Nombres");
                datos[3]= Registro.getString("Apellidos");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public String[] Obtener(int codigo){
        Arrays.fill(datos, "");

        try {
            Instruccion = conexion.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            Registro = Instruccion.executeQuery("Select m.Id_Alumno, m.Costo, m.Fecha, m.Estado, m.Apoderado, m.Ocupacion, m.Vinculo,a.Dni,a.Nombres,a.Apellidos, m.Id_Grado FROM Matricula m INNER JOIN Alumnos a ON a.Id_alumno = m.Id_Alumno WHERE m.Id_Matricula =\'"+codigo+"\' LIMIT 1" );
            if(Registro.next())
            {
                datos[0]= Registro.getString("Id_Alumno");
                datos[1]= Registro.getString("Costo");
                datos[2]= Registro.getString("Fecha");
                datos[3]= Registro.getString("Estado");
                datos[4]= Registro.getString("Apoderado");
                datos[5]= Registro.getString("Ocupacion");
                datos[6]= Registro.getString("Vinculo");
                datos[7]= Registro.getString("Dni");
                datos[8]= Registro.getString("Nombres");
                datos[9]= Registro.getString("Apellidos");
                datos[10]= Registro.getString("Id_Grado");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datos;
    }
    
    public void Modificar(){
        try {
            System.err.println("MODIFICAR");
            PreparedStatement pst  = conexion.con.prepareStatement("UPDATE Matricula SET Id_Alumno=?, Costo=?, Fecha=?, Estado=?, Apoderado=?, Ocupacion=?, Vinculo=?, Id_Grado=? WHERE Id_Matricula=?");
            pst.setInt(1, IdAlumno);
            pst.setDouble(2, Costo);
            pst.setString(3, Fecha);
            pst.setString(4, Estado);
            pst.setString(5, Apoderado);
            pst.setString(6, Ocupacion);
            pst.setString(7, Vinculo);
            pst.setInt(8, IdGrado);
            pst.setInt(9, IdMaticula);

            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void Eliminar(int Id){
        try {
            System.err.println("Eliminar");
            PreparedStatement pst  = conexion.con.prepareStatement("DELETE FROM Matricula  WHERE Id_Matricula=?");
            pst.setInt(1, Id);
            
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

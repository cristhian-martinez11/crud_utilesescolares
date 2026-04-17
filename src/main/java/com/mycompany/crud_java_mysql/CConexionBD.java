/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_java_mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Cristhian Martinez
 */
public class CConexionBD {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasena = "ruth";
    String bd = "bd_utilesescolares";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection establecerCOnexion(){
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar= DriverManager.getConnection(cadena,usuario,contrasena);
            //JOptionPane.showMessageDialog(null,"La conexion fue exitosa");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"La conexion NO fue exitosa, error"+ e.toString());
        } 
        return conectar;
    }
    public void cerrarConexion(){
        try {
            if (conectar!= null && !conectar.isClosed())
            conectar.close();
           // JOptionPane.showMessageDialog(null,"La conexion fue cerrada");  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"No se logro cerrar la conexion, error:"+e.toString()); 
        } 
    }
}
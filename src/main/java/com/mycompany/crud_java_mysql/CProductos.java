/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_java_mysql;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Cristhian Martinez
 */
public class CProductos {
    
    public void AgregarProducto(JTextField producto,JTextField precioPorducto, JTextArea descripcionProducto){
        
        CConexionBD objetoConexion = new CConexionBD();
        EntidadProducto objetoEntidadProducto = new EntidadProducto();
        
        String consulta = "insert into productos (producto,precioPorducto,descripcionProducto) values (?,?,?)";
        
        try {
            objetoEntidadProducto.setProducto(producto.getText());
            objetoEntidadProducto.setPrecioProducto(Double.parseDouble(precioPorducto.getText()));
            objetoEntidadProducto.setDescripcionProducto(descripcionProducto.getText());
            
            CallableStatement cs = objetoConexion.establecerCOnexion().prepareCall(consulta);
            cs.setString(1, objetoEntidadProducto.getProducto());
            cs.setDouble(2, objetoEntidadProducto.getPrecioProducto());
            cs.setString(3, objetoEntidadProducto.getDescripcionProducto());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se guardo correctamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se guardo correctamente"+e.toString());
        } 
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    
    public void MostrarProducto(JTable tablaTotalProductos ){
        
        CConexionBD objetoConexion = new CConexionBD();
        EntidadProducto objetoEntidadProducto = new EntidadProducto();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("ID");
        modelo.addColumn("Producto");
        modelo.addColumn("PrecioProducto");
        modelo.addColumn("DescripcionProducto");
        
        tablaTotalProductos.setModel(modelo);
        
                
        
        String consulta = "select productos.id, productos.producto, productos.precioPorducto, productos.descripcionProducto from productos;";
        
        try {
             Statement st= objetoConexion.establecerCOnexion().createStatement();
             ResultSet rs=st.executeQuery(consulta);
             
             while (rs.next()){
                objetoEntidadProducto.setId(rs.getInt("id"));
                objetoEntidadProducto.setProducto(rs.getString("producto"));
                objetoEntidadProducto.setPrecioProducto(rs.getDouble("precioPorducto"));
                objetoEntidadProducto.setDescripcionProducto(rs.getString("descripcionProducto"));
                
                modelo.addRow(new Object []{objetoEntidadProducto.getId(),objetoEntidadProducto.getProducto(),objetoEntidadProducto.getPrecioProducto(),objetoEntidadProducto.getDescripcionProducto()});
                
                
            }
             
            tablaTotalProductos.setModel(modelo);
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al mostrar los datos"+e.toString());
        } 
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    public void Seleccionar(JTable totalproductos,JTextField id,JTextField Producto ,JTextField precioPorducto,JTextArea descripcionProducto){
    
        int fila= totalproductos.getSelectedRow();
        
        try {
             if (fila>=0){
                 id.setText(totalproductos.getValueAt(fila,0).toString());
                 Producto.setText(totalproductos.getValueAt(fila,1).toString());
                 precioPorducto.setText(totalproductos.getValueAt(fila,2).toString());
                 descripcionProducto.setText(totalproductos.getValueAt(fila,3).toString());
             }
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"Error al seleccionar,error:"+e.toString());
        } 
        
    }
    
    
    
        public void ModificarProducto(JTextField id,JTextField producto,JTextField precioPorducto, JTextArea descripcionProducto){
        
        CConexionBD objetoConexion = new CConexionBD();
        EntidadProducto objetoEntidadProducto = new EntidadProducto();
        
        String consulta = "UPDATE productos SET productos.producto= ?,productos.precioPorducto=?,productos.descripcionProducto=? WHERE productos.id=?;";
        
        try {
            objetoEntidadProducto.setId(Integer.parseInt(id.getText()));
            objetoEntidadProducto.setProducto(producto.getText());
            objetoEntidadProducto.setPrecioProducto(Double.parseDouble(precioPorducto.getText()));
            objetoEntidadProducto.setDescripcionProducto(descripcionProducto.getText());
            
            CallableStatement cs = objetoConexion.establecerCOnexion().prepareCall(consulta);
            cs.setString(1, objetoEntidadProducto.getProducto());
            cs.setDouble(2, objetoEntidadProducto.getPrecioProducto());
            cs.setString(3, objetoEntidadProducto.getDescripcionProducto());
            cs.setInt(4, objetoEntidadProducto.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se modifico correctamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se modifico correctamente"+e.toString());
        } 
        finally {
            objetoConexion.cerrarConexion();
        }
    }
        
        public void LimpiarCamposProductos(JTextField id,JTextField producto,JTextField precioPorducto, JTextArea descripcionProducto){
        
            id.setText("");
            producto.setText("");
            precioPorducto.setText("");
            descripcionProducto.setText("");
        }
        
        public void EliminarProducto(JTextField id){
        
        CConexionBD objetoConexion = new CConexionBD();
        EntidadProducto objetoEntidadProducto = new EntidadProducto();
        
        String consulta = "DELETE FROM productos WHERE productos.id=?;";
        
        try {
            objetoEntidadProducto.setId(Integer.parseInt(id.getText()));
           
            
            CallableStatement cs = objetoConexion.establecerCOnexion().prepareCall(consulta);
            cs.setInt(1, objetoEntidadProducto.getId());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se elimino correctamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se elimino correctamente"+e.toString());
        } 
        finally {
            objetoConexion.cerrarConexion();
        }
    }
    
    
}

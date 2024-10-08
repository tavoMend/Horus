/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Gabriel
 */
public class ConsultasPeriodos extends DBConexion {
    
    public void insertarPeriodos(JTextField periodo) {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT INTO periodos (periodo) VALUES (?)";
    
    if (periodo == null){
    JOptionPane.showMessageDialog(null, "Error, Nose permiten campos vacios");
    } else {
         
    try {
        ps = con.prepareStatement(consulta);
        ps.setString(1, periodo.getText());
        
       
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Periodo ingresado correctamente");
        periodo.setText(null);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error, periodo no ingresado: " + e.toString());
    } finally {
       
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }
    }
}
    
    
    public void mostrarPeriodos(JTable paramTablaPeriodos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaPeriodos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id de Periodo");
        mTabla.addColumn("Periodo");
        paramTablaPeriodos.setModel(mTabla);
        
        consulta = "SELECT * from periodos";
        
        String[] datos = new String[2];
        
        Statement st;
        
        try {
            Connection connection = con.establecerConexion();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next())
            {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                mTabla.addRow(datos);
            }
            paramTablaPeriodos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    
    public void seleccionarPeriodos(JTable paramTablaPeriodos, 
            JTextField paramIdPeriodo, JTextField paramPeriodo
            )
    {
        try {
    int fila = paramTablaPeriodos.getSelectedRow();

    if (fila >= 0) {
        paramIdPeriodo.setText(paramTablaPeriodos.getValueAt(fila, 0).toString());
        paramPeriodo.setText(paramTablaPeriodos.getValueAt(fila, 1).toString());
   
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
}

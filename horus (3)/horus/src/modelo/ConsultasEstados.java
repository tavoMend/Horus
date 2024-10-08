/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Gabriel
 */
public class ConsultasEstados extends DBConexion{
    
    public void insertarEstados(
            JTextField paramNombreEstado)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into estados (nombreEstado)"
            + "VALUES (?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramNombreEstado.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Registro no ingresado, error: "+e.toString());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
                
            } catch (SQLException e) {
                
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        } 
    }
    
    public void mostrarEstados(JTable paramTablaEstados)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaEstados.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Estado");
        mTabla.addColumn("Nombre de Estado");
        
        paramTablaEstados.setModel(mTabla);
        
        consulta = "SELECT * from estados";
        
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
            paramTablaEstados.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarEstados(JTable paramTablaEstados, 
            JTextField paramIdEstado, JTextField paramNombreEstado
            )
    {
        try {
    int fila = paramTablaEstados.getSelectedRow();

    if (fila >= 0) {
        paramIdEstado.setText(paramTablaEstados.getValueAt(fila, 0).toString());
        paramNombreEstado.setText(paramTablaEstados.getValueAt(fila, 1).toString());
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
    Estado est = new Estado();
    
    public void actualizarEstados(JTextField paramIdEstado, 
            JTextField paramNombreEstado)
{
    est.setIdEstado(Integer.parseInt(paramIdEstado.getText()));
    est.setNombreEstado(paramNombreEstado.getText());
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE estados SET nombreEstado=? WHERE idEstado=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, est.getNombreEstado());
        cs.setInt(2, est.getIdEstado());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
          
    public void eliminarEstados(JTextField paramIdEstado)
    {
    est.setIdEstado(Integer.parseInt(paramIdEstado.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from estados WHERE idEstado=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, est.getIdEstado());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
}

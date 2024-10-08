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
import javax.swing.JComboBox;
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
public class ConsultasClasificacionIngresos extends DBConexion{
    
    public void insertarClasificacionIngresos(
            JTextField paramNombreClasificacionIngreso)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into clasificaciones_ingresos (nombreClasificacionIngreso)"
            + "VALUES (?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramNombreClasificacionIngreso.getText());
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
    
    public void mostrarClasificacionIngresos(JTable paramClasificacionIngresos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramClasificacionIngresos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Clasificacion de Ingreso");
        mTabla.addColumn("Nombre Clasificacion de Ingreso");
        
        paramClasificacionIngresos.setModel(mTabla);
        
        consulta = "SELECT * from clasificaciones_ingresos";
        
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
            paramClasificacionIngresos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarClasificacionIngresos(JTable paramClasificacionIngresos, 
            JTextField paramIdClasifiacionIngreso, JTextField paramNombreClasificacionIngreso
            )
    {
        try {
    int fila = paramClasificacionIngresos.getSelectedRow();

    if (fila >= 0) {
        paramIdClasifiacionIngreso.setText(paramClasificacionIngresos.getValueAt(fila, 0).toString());
        paramNombreClasificacionIngreso.setText(paramClasificacionIngresos.getValueAt(fila, 1).toString());
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
    ClasificacionIngreso cI = new ClasificacionIngreso();
    
    public void actualizarClasificacionIngresos(JTextField paramIdClasifiacionIngreso,
            JTextField paramNombreClasificacionIngreso
            )
{
    cI.setIdClasificacionIngreso(Integer.parseInt(paramIdClasifiacionIngreso.getText()));
    cI.setNombreClasificacionIngreso(paramNombreClasificacionIngreso.getText());
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE clasificaciones_ingresos SET nombreClasificacionIngreso=? WHERE idClasificacionIngreso=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, cI.getNombreClasificacionIngreso());
        cs.setInt(2, cI.getIdClasificacionIngreso());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
          
    public void eliminarClasificacionIngresos(JTextField paramIdClasificacionIngresos)
    {
    cI.setIdClasificacionIngreso(Integer.parseInt(paramIdClasificacionIngresos.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from clasificaciones_ingresos WHERE idClasificacionIngreso =?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, cI.getIdClasificacionIngreso());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
}

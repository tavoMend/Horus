
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
public class ConsultasMetodosPagos extends DBConexion{
    
    public void insertarMetodosPagos(
            JTextField paramNombreMetodoPago)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into metodos_pagos (nombreMetodoPago)"
            + "VALUES (?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramNombreMetodoPago.getText());
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
    
    public void mostrarMetodosPagos(JTable paramTablaMetodosPagos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaMetodosPagos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Metodo Pago");
        mTabla.addColumn("Nombre Metodo de Pago");
        
        paramTablaMetodosPagos.setModel(mTabla);
        
        consulta = "SELECT * from metodos_pagos";
        
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
            paramTablaMetodosPagos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarMetodosPagos(JTable paramTablaMetodosPagos, 
            JTextField paramIdMetodoPago, JTextField paramNombreMetodoPago
            )
    {
        try {
    int fila = paramTablaMetodosPagos.getSelectedRow();

    if (fila >= 0) {
        paramIdMetodoPago.setText(paramTablaMetodosPagos.getValueAt(fila, 0).toString());
        paramNombreMetodoPago.setText(paramTablaMetodosPagos.getValueAt(fila, 1).toString());
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
    MetodosPago mP = new MetodosPago();
    
    public void actualizarMetodosPagos(JTextField paramIdMetodoPago, 
            JTextField paramNombreMetodoPago)
{
    mP.setIdMetodoPago(Integer.parseInt(paramIdMetodoPago.getText()));
    mP.setNombreMetodoPago(paramNombreMetodoPago.getText());
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE metodos_pagos SET nombreMetodoPago=? WHERE idMetodoPago=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, mP.getNombreMetodoPago());
        cs.setInt(2, mP.getIdMetodoPago());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
          
    public void eliminarMetodosPagos(JTextField paramIdMetodoPago)
    {
    mP.setIdMetodoPago(Integer.parseInt(paramIdMetodoPago.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from metodos_pagos WHERE idMetodoPago=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, mP.getIdMetodoPago());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
}

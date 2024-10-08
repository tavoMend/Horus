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
public class ConsultasFuentes extends DBConexion{
    
     public void insertarFuentes(
            JTextField paramCodigoFuente, JTextField paramNombreFuente,
            JTextField paramOrigen,
            JComboBox paramIdClasificacionIngreso)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into fuentes (codigoFuente,nombreFuente,origen,idClasificacionIngreso)"
            + "VALUES (?,?,?,?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramCodigoFuente.getText());
            ps.setString(2, paramNombreFuente.getText());
            ps.setString(3, paramOrigen.getText());
            ps.setString(4, (String) paramIdClasificacionIngreso.getSelectedItem());
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
    
    public void mostrarFuentes(JTable paramTablaFuentes)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaFuentes.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id de Fuente");
        mTabla.addColumn("Codigo de Fuente");
        mTabla.addColumn("Nombre de Fuente");
        mTabla.addColumn("Origen");
        mTabla.addColumn("Id Clasificacion de Ingreso");
        
        paramTablaFuentes.setModel(mTabla);
        
        consulta = "SELECT * from fuentes";
        
        String[] datos = new String[5];
        
        Statement st;
        
        try {
            Connection connection = con.establecerConexion();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while (rs.next())
            {
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);
                
                mTabla.addRow(datos);
            }
            paramTablaFuentes.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarFuentes(JTable paramTablaFuentes, 
            JTextField paramIdFuente, JTextField paramCodigoFuente, 
            JTextField paramNombreFuente, JTextField paramOrigen,
            JComboBox paramIdClasificacionIngreso
            )
    {
        try {
    int fila = paramTablaFuentes.getSelectedRow();

    if (fila >= 0) {
        paramIdFuente.setText(paramTablaFuentes.getValueAt(fila, 0).toString());
        paramCodigoFuente.setText(paramTablaFuentes.getValueAt(fila, 1).toString());
        paramNombreFuente.setText(paramTablaFuentes.getValueAt(fila, 2).toString());
        paramOrigen.setText(paramTablaFuentes.getValueAt(fila, 3).toString());
       
        String idPeriodo = paramTablaFuentes.getValueAt(fila, 4).toString();
        for (int i = 0; i < paramIdClasificacionIngreso.getItemCount(); i++) {
            if (paramIdClasificacionIngreso.getItemAt(i).toString().equals(idPeriodo)) {
                paramIdClasificacionIngreso.setSelectedIndex(i);
                break;
            }
        }
   
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
    Fuente f = new Fuente();
    
    public void actualizarFuentes(JTextField paramIdFuente, JTextField paramCodigoFuente, 
            JTextField paramNombreFuente,JTextField paramOrigen,
            JComboBox<String> paramIdClasificacionIngreso)
{
    f.setIdFuente(Integer.parseInt(paramIdFuente.getText()));
    f.setCodigoFuente(paramCodigoFuente.getText());
    f.setNombreFuente(paramNombreFuente.getText());
    f.setOrigen(paramOrigen.getText());
    f.setIdClasificacionIngreso(Integer.parseInt((String)paramIdClasificacionIngreso.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE fuentes SET codigoFuente=?, nombreFuente=?, origen=?, idClasificacionIngreso=? WHERE idFuente=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, f.getCodigoFuente());
        cs.setString(2, f.getNombreFuente());
        cs.setString(3, f.getOrigen());
        cs.setInt(4, f.getIdClasificacionIngreso());
        cs.setInt(5, f.getIdFuente());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
          
    public void eliminarFuentes(JTextField paramIdFuente)
    {
    f.setIdFuente(Integer.parseInt(paramIdFuente.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from fuentes WHERE idFuente=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, f.getIdFuente());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
    /* public DefaultTableModel filterData(String query) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Fuente", "Código Fuente", "Nombre Fuente", "Origen", "ID Clasificación Ingreso"}, 0);
        
        String consulta = "SELECT idFuente, codigoFuente, nombreFuente, origen, idClasificacionIngreso FROM fuentes " +
                     "WHERE idFuente LIKE ? OR codigoFuente LIKE ? OR nombreFuente LIKE ? OR origen LIKE ? OR idClasificacionIngreso LIKE ?";

        
        try (Connection con = DBConexion.establecerConexion();
             PreparedStatement pst = con.prepareStatement(consulta)) {
             
            for (int i = 1; i <= 5; i++) {
                pst.setString(i, "%" + consulta + "%");
            }
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int idFuente = rs.getInt("idFuente");
                String codigoFuente = rs.getString("codigoFuente");
                String nombreFuente = rs.getString("nombreFuente");
                String origen = rs.getString("origen");
                int idClasificacionIngreso = rs.getInt("idClasificacionIngreso");
                model.addRow(new Object[]{idFuente, codigoFuente, nombreFuente, origen, idClasificacionIngreso});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return model;
    }
*/
    
}

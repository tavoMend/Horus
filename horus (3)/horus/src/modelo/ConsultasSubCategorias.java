
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
public class ConsultasSubCategorias extends DBConexion{
    
    public void insertarSubCategorias(
            JTextField paramNombreSubCategoria,
            JComboBox paramIdCategoria)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into subcategorias (nombreSubcategoria,idCategoria)"
            + "VALUES (?,?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramNombreSubCategoria.getText());
            ps.setString(2, (String) paramIdCategoria.getSelectedItem());
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
        
        
    public void mostrarSubCategorias(JTable paramTablaSubCategorias)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaSubCategorias.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id SubCategoria");
        mTabla.addColumn("Nombre SubCategoria");
        mTabla.addColumn("Id de Categoria");
        
        paramTablaSubCategorias.setModel(mTabla);
        
        consulta = "SELECT * from subcategorias";
        
        String[] datos = new String[3];
        
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
                
                mTabla.addRow(datos);
            }
            paramTablaSubCategorias.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
        
        
    public void seleccionarSubCategorias(JTable paramTablaSubCategorias, 
            JTextField paramIdSubCategoria, 
            JTextField paramNombreSubCategoria,
            JComboBox paramIdCategoria)
    {
        try {
    int fila = paramTablaSubCategorias.getSelectedRow();

    if (fila >= 0) {
        paramIdSubCategoria.setText(paramTablaSubCategorias.getValueAt(fila, 0).toString());
        paramNombreSubCategoria.setText(paramTablaSubCategorias.getValueAt(fila, 1).toString());
       
        String idPeriodo = paramTablaSubCategorias.getValueAt(fila, 2).toString();
        for (int i = 0; i < paramIdCategoria.getItemCount(); i++) {
            if (paramIdCategoria.getItemAt(i).toString().equals(idPeriodo)) {
                paramIdCategoria.setSelectedIndex(i);
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
        
        
        SubCategoria sC = new SubCategoria();
        
    public void actualizarGastos(JTextField paramIdSubCategoria, JTextField paramNombreSubCategoria, 
        JComboBox<String> paramIdCategoria)
{
    sC.setIdSubCategoria(Integer.parseInt(paramIdSubCategoria.getText()));
    sC.setNombreSubCategoria(paramNombreSubCategoria.getText());
    sC.setIdCategoria(Integer.parseInt((String)paramIdCategoria.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE subcategorias SET nombreSubCategoria=?, idCategoria=? WHERE idSubCategoria=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, sC.getNombreSubCategoria());
        cs.setInt(3, sC.getIdCategoria());
        cs.setInt(4, sC.getIdSubCategoria());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
    
    public void eliminarPresupuestos(JTextField paramIdSubCategoria)
    {
    sC.setIdSubCategoria(Integer.parseInt(paramIdSubCategoria.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from subcategorias WHERE idSubCategoria=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, sC.getIdSubCategoria());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
}

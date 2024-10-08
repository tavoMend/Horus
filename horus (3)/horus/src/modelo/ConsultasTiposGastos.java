package modelo;

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
public class ConsultasTiposGastos extends DBConexion{
    
        public void insertarPresupuestos(
            JTextField paramNombreTipoGasto)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into tipos_gastos (nombreTipoGasto)"
            + "VALUES (?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramNombreTipoGasto.getText());
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
        
        
        public void mostrarTiposGastos(JTable paramTablaTiposGastos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaTiposGastos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Tipo de Gasto");
        mTabla.addColumn("Nombre Tipo de Gasto");
        
        paramTablaTiposGastos.setModel(mTabla);
        
        consulta = "SELECT * from tipos_gastos";
        
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
            paramTablaTiposGastos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
        
            public void seleccionarPresupuestos(JTable paramTablaTiposGastos, 
            JTextField paramIdTipoGasto, JTextField paramNombreTipoGasto
            )
    {
        try {
    int fila = paramTablaTiposGastos.getSelectedRow();

    if (fila >= 0) {
        paramIdTipoGasto.setText(paramTablaTiposGastos.getValueAt(fila, 0).toString());
        paramNombreTipoGasto.setText(paramTablaTiposGastos.getValueAt(fila, 1).toString());
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

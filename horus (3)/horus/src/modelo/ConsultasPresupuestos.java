
package modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.HashMap;

/**
 *
 * @author Gabriel
 */
public class ConsultasPresupuestos extends DBConexion{
    
    private HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
    
    public void insertarPresupuestos(
            JTextField paramCodigoPresupuesto, JTextField paramObjetivo,
            JComboBox paramIdPeriodo)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into presupuestos (codigoPresupuesto,objetivo,idPeriodo)"
            + "VALUES (?,?,?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramCodigoPresupuesto.getText());
            ps.setString(2, paramObjetivo.getText());
            ps.setString(3, (String) paramIdPeriodo.getSelectedItem());
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
    
    public void mostrarPresupuestos(JTable paramTablaPresupuestos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaPresupuestos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id de Presupuesto");
        mTabla.addColumn("Codigo de Presupuesto");
        mTabla.addColumn("Objetivo");
        mTabla.addColumn("Id de Periodo");
        mTabla.addColumn("Nombre de Periodo");
        
        
        paramTablaPresupuestos.setModel(mTabla);
        
        consulta = "SELECT t1.*, t2.* FROM presupuestos t1 INNER JOIN periodos t2 ON t1.idPeriodo = t2.idPeriodo;";
        
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
            paramTablaPresupuestos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarPresupuestos(JTable paramTablaGastos, 
            JTextField paramIdPresupuesto, JTextField paramCodigoPresupuesto, 
            JTextField paramObjetivo, JComboBox paramIdPeriodo, JComboBox paramNombrePeriodo
            )
    {
        try {
    int fila = paramTablaGastos.getSelectedRow();

    if (fila >= 0) {
        paramIdPresupuesto.setText(paramTablaGastos.getValueAt(fila, 0).toString());
        paramCodigoPresupuesto.setText(paramTablaGastos.getValueAt(fila, 1).toString());
        paramObjetivo.setText(paramTablaGastos.getValueAt(fila, 2).toString());
       
        String idPeriodo = paramTablaGastos.getValueAt(fila, 3).toString();
        for (int i = 0; i < paramIdPeriodo.getItemCount(); i++) {
            if (paramIdPeriodo.getItemAt(i).toString().equals(idPeriodo)) {
                paramIdPeriodo.setSelectedIndex(i);
                break;
            }
        }
        
         String nombrePeriodo = paramTablaGastos.getValueAt(fila, 4).toString();
            paramNombrePeriodo.setSelectedItem(nombrePeriodo);
        
    }
    
    else
    {
    JOptionPane.showMessageDialog(null, "Fila no seleccionada");
    }
} catch (Exception e) {
    
    JOptionPane.showMessageDialog(null, "Error de seleccion, error: "+e.toString());
        }
    }
    
    Presupuesto pre = new Presupuesto();
    
    public void actualizarGastos(JTextField paramIdPresupuesto, JTextField paramCodigoPresupuesto, 
            JTextField paramObjetivo, JComboBox<String> paramIdPeriodo)
{
    pre.setIdPresupuesto(Integer.parseInt(paramIdPresupuesto.getText()));
    pre.setCodigoPresupuesto(paramCodigoPresupuesto.getText());
    pre.setObjetivo(paramObjetivo.getText());
    pre.setIdPeriodo(Integer.parseInt((String)paramIdPeriodo.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE presupuestos SET codigoPresupuesto=?, objetivo=?, idPeriodo=? WHERE idPresupuesto=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, pre.getCodigoPresupuesto());
        cs.setString(2, pre.getObjetivo());
        cs.setInt(3, pre.getIdPeriodo());
        cs.setInt(4, pre.getIdPresupuesto());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
          
    public void eliminarPresupuestos(JTextField paramIdPresupuesto)
    {
    pre.setIdPresupuesto(Integer.parseInt(paramIdPresupuesto.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from presupuestos WHERE idPresupuesto=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, pre.getIdPresupuesto());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
    public void LlenarComboBox(String valor, String valor2, JComboBox<String> combo, JComboBox<String> combo2) {
    String consulta = "SELECT * from periodos";
    Statement st;
    DBConexion con = new DBConexion();
    
    try {
        st = con.establecerConexion().createStatement();
        ResultSet rs = st.executeQuery(consulta);
        
        while (rs.next()) {
            String key = rs.getString(valor);
            String value = rs.getString(valor2);
            
            combo2.addItem(value);
            
            if (!dataMap.containsKey(value)) {
                dataMap.put(value, new ArrayList<>());
            }
            dataMap.get(value).add(key);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.toString());
    }
    
    combo2.addActionListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent e) {
            String selectedItem = (String) combo2.getSelectedItem();
            combo.removeAllItems();
            
            if (selectedItem != null && dataMap.containsKey(selectedItem)) {
                for (String item : dataMap.get(selectedItem)) {
                    combo.addItem(item);
                }
            }
        }
    });
}
}

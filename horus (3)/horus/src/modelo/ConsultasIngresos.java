
package modelo;

import com.mysql.cj.jdbc.CallableStatement;
import com.toedter.calendar.JDateChooser;
import controlador.CtrlIngreso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author Gabriel
 */
public class ConsultasIngresos extends DBConexion{
   
    public void insertarIngresos
        (JTextField codigoIngreso, JDateChooser fechaIngreso, JTextField monto,
                JTextField descripcion, JComboBox idMetodoPago,
                JComboBox idEstado,JComboBox idFuente ) 
        {
            
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT INTO ingresos"
            + " (codigoIngreso, fechaIngreso,monto,descripcion,"
            + "idMetodoPago,idEstado,idFuente) VALUES (?,?,?,?,?,?,?)";
    
    try {
        ps = con.prepareStatement(consulta);
        ps.setString(1, codigoIngreso.getText());
        
       
        java.util.Date fechaUtil = fechaIngreso.getDate();
        if (fechaUtil != null) {
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(2, fechaSQL);
        } else {
            ps.setDate(2, null);
        }
        
        ps.setDouble(3, Double.parseDouble(monto.getText()));
        ps.setString(4, descripcion.getText());
        ps.setString(5, (String) idMetodoPago.getSelectedItem());
        ps.setString(6, (String) idEstado.getSelectedItem());
        ps.setString(7, (String) idFuente.getSelectedItem());
        
       
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");   
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error, Registro no ingresado: " + e.toString());
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }
}
        
        public void mostrarIngresos(JTable paramTablaIngresos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaIngresos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Ingreso");
        mTabla.addColumn("Codigo Ingreso");
        mTabla.addColumn("fecha Ingreso");
        mTabla.addColumn("monto");
        mTabla.addColumn("descripcion");
        mTabla.addColumn("Id Metodo Pago");
        mTabla.addColumn("Id Estado");
        mTabla.addColumn("Id Categoria");
        
        paramTablaIngresos.setModel(mTabla);
        
        consulta = "SELECT * from ingresos";
        
        String[] datos = new String[8];
        
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
                datos[5]=rs.getString(6);
                datos[6]=rs.getString(7);
                datos[7]=rs.getString(8);
                
                mTabla.addRow(datos);
            }
            paramTablaIngresos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
        
        public void seleccionarIngresos(JTable paramTablaIngresos, 
            JTextField paramIdIngreso, JTextField paramCodigoIngreso, JDateChooser paramFechaIngreso, 
            JTextField paramMonto, JTextField paramDescripcion, JComboBox paramId_Metodo_Pago, 
            JComboBox paramId_Estado, JComboBox paramId_Fuente)
    {
        try {
    int fila = paramTablaIngresos.getSelectedRow();

    if (fila >= 0) {
        paramIdIngreso.setText(paramTablaIngresos.getValueAt(fila, 0).toString());
        paramCodigoIngreso.setText(paramTablaIngresos.getValueAt(fila, 1).toString());
        
        String fechaTexto = paramTablaIngresos.getValueAt(fila, 2).toString();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según el formato de fecha en tu tabla
        Date fecha = formatoFecha.parse(fechaTexto);
        paramFechaIngreso.setDate(fecha);
        
        paramMonto.setText(paramTablaIngresos.getValueAt(fila, 3).toString());
        paramDescripcion.setText(paramTablaIngresos.getValueAt(fila, 4).toString());
        
        
        String idMetodoPago = paramTablaIngresos.getValueAt(fila, 5).toString();
        for (int i = 0; i < paramId_Metodo_Pago.getItemCount(); i++) {
            if (paramId_Metodo_Pago.getItemAt(i).toString().equals(idMetodoPago)) {
                paramId_Metodo_Pago.setSelectedIndex(i);
                break;
            }
        }

     
        String idEstado = paramTablaIngresos.getValueAt(fila, 6).toString();
        for (int i = 0; i < paramId_Estado.getItemCount(); i++) {
            if (paramId_Estado.getItemAt(i).toString().equals(idEstado)) {
                paramId_Estado.setSelectedIndex(i);
                break;
            }
        }


        String idCategoria = paramTablaIngresos.getValueAt(fila, 7).toString();
        for (int i = 0; i < paramId_Fuente.getItemCount(); i++) {
            if (paramId_Fuente.getItemAt(i).toString().equals(idCategoria)) {
                paramId_Fuente.setSelectedIndex(i);
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
    
    Ingreso ing = new Ingreso();
    public void actualizarIngresos(
            JTextField paramIdIngreso, JTextField paramCodigoIngreso, JDateChooser paramFechaIngreso, 
            JTextField paramMonto, JTextField paramDescripcion, JComboBox paramId_Metodo_Pago, 
            JComboBox paramId_Estado, JComboBox paramId_Fuente)
{
    ing.setId(Integer.parseInt(paramIdIngreso.getText()));
    ing.setCodIngreso(paramCodigoIngreso.getText());
    Date fecha = paramFechaIngreso.getDate();
    
    if (fecha != null) {
        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
        ing.setFechaIngreso(sqlFecha);
    }
    
    
    ing.setMonto(Double.parseDouble(paramMonto.getText()));
    ing.setDescripcion(paramDescripcion.getText());
    ing.setIdMetodoPago(Integer.parseInt((String) paramId_Metodo_Pago.getSelectedItem()));
    ing.setIdEstado(Integer.parseInt((String) paramId_Estado.getSelectedItem()));
    ing.setIdFuente(Integer.parseInt((String)paramId_Fuente.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE ingresos SET codigoIngreso=?, fechaIngreso=?, monto=?, descripcion=?, idMetodoPago=?, idEstado=?, idFuente=? WHERE idIngreso=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, ing.getCodIngreso());
        
        if (fecha != null) {
            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
            cs.setDate(2, sqlFecha);
        } else {
            cs.setNull(2, java.sql.Types.DATE);
        }
        
        cs.setDouble(3, ing.getMonto());
        cs.setString(4, ing.getDescripcion());
        cs.setInt(5, ing.getIdMetodoPago());
        cs.setInt(6, ing.getIdEstado());
        cs.setInt(7, ing.getIdFuente());
        cs.setInt(8, ing.getId());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
    
    public void eliminarIngresos(JTextField paramIdIngreso)
    {
    ing.setId(Integer.parseInt(paramIdIngreso.getText()));
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from ingresos WHERE idIngreso=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, ing.getId());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
}

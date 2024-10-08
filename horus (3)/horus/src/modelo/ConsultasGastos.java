
package modelo;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Statement;
import com.toedter.calendar.JDateChooser;
import controlador.CtrlGasto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTextField;
import java.sql.SQLException;
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
public class ConsultasGastos extends DBConexion{
    
    Gasto gas = new Gasto();
    
    public void insertarGastos
        (JTextField codigoIngreso, JDateChooser fechaIngreso, JTextField monto,
                JTextField descripcion, JComboBox idMetodoPago,
                JComboBox idEstado,JComboBox idCategoria ) 
        {
            
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT INTO gastos"
            + " (codigoGasto, fechaGasto,monto,descripcion,"
            + "id_Metodo_Pago,id_estado,id_Categoria) VALUES (?,?,?,?,?,?,?)";
    
    try {
        ps = con.prepareStatement(consulta);
        ps.setString(1, codigoIngreso.getText());
        
        // Convertir java.util.Date a java.sql.Date
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
        ps.setString(7, (String) idCategoria.getSelectedItem());
        
        
        ps.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");   
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error, Registro no ingresado: " + e.toString());
    } finally {
        // Cerrar el PreparedStatement y la conexión para evitar fugas de recursos
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }
}
    
    //metodo no utilizado
    public boolean buscarGastos(Gasto gas)
    {
    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection con = establecerConexion();
    String sql = "SELECT * from gastos where codigoGasto=?";
    
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, gas.getCodIngreso());
            rs = ps.executeQuery();
            
            if(rs.next()){
            gas.setId(Integer.parseInt(rs.getString("idGasto")));
            gas.setCodIngreso("codigoGasto");
            
            java.sql.Date sqlDate = rs.getDate("fechaGasto");
                if (sqlDate != null) {
                    Date utilDate = new Date(sqlDate.getTime());
                    gas.setFechaIngreso(utilDate);
                }  
            gas.setMonto(rs.getDouble("monto"));
            gas.setDescripcion(rs.getString("descripcion"));
            gas.setIdMetodoPago(rs.getInt("id_Metodo_Pago"));
            gas.setIdEstado(rs.getInt("id_Estado"));
            gas.setIdCategoria(rs.getInt("id_Categoria"));
            return true;
            }
            
            
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error"+"e");
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error"+"e");
            }
        }
    
    }
    
    public void mostrarGastos(JTable paramTablaGastos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaGastos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("idGasto");
        mTabla.addColumn("codigoGasto");
        mTabla.addColumn("fechaGasto");
        mTabla.addColumn("monto");
        mTabla.addColumn("descripcion");
        mTabla.addColumn("id_metodo_Pago");
        mTabla.addColumn("id_Estado");
        mTabla.addColumn("id_Categoria");
        
        paramTablaGastos.setModel(mTabla);
        
        consulta = "SELECT * from gastos";
        
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
            paramTablaGastos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarGastos(JTable paramTablaGastos, 
            JTextField paramIdGasto, JTextField paramCodigoGasto, JDateChooser paramFechaGasto, 
            JTextField paramMonto, JTextField paramDescripcion, JComboBox paramId_Metodo_Pago, 
            JComboBox paramId_Estado, JComboBox paramId_Categoria)
    {
        try {
    int fila = paramTablaGastos.getSelectedRow();

    if (fila >= 0) {
        paramIdGasto.setText(paramTablaGastos.getValueAt(fila, 0).toString());
        paramCodigoGasto.setText(paramTablaGastos.getValueAt(fila, 1).toString());
        
        String fechaTexto = paramTablaGastos.getValueAt(fila, 2).toString();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); // Ajusta el formato según el formato de fecha en tu tabla
        Date fecha = formatoFecha.parse(fechaTexto);
        paramFechaGasto.setDate(fecha);
        
        paramMonto.setText(paramTablaGastos.getValueAt(fila, 3).toString());
        paramDescripcion.setText(paramTablaGastos.getValueAt(fila, 4).toString());
        
        
        String idMetodoPago = paramTablaGastos.getValueAt(fila, 5).toString();
        for (int i = 0; i < paramId_Metodo_Pago.getItemCount(); i++) {
            if (paramId_Metodo_Pago.getItemAt(i).toString().equals(idMetodoPago)) {
                paramId_Metodo_Pago.setSelectedIndex(i);
                break;
            }
        }

     
        String idEstado = paramTablaGastos.getValueAt(fila, 6).toString();
        for (int i = 0; i < paramId_Estado.getItemCount(); i++) {
            if (paramId_Estado.getItemAt(i).toString().equals(idEstado)) {
                paramId_Estado.setSelectedIndex(i);
                break;
            }
        }


        String idCategoria = paramTablaGastos.getValueAt(fila, 7).toString();
        for (int i = 0; i < paramId_Categoria.getItemCount(); i++) {
            if (paramId_Categoria.getItemAt(i).toString().equals(idCategoria)) {
                paramId_Categoria.setSelectedIndex(i);
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
    
   public void actualizarGastos(
    JTextField paramIdGasto, JTextField paramCodigoGasto, JDateChooser paramFechaGasto, 
    JTextField paramMonto, JTextField paramDescripcion, JComboBox<String> paramId_Metodo_Pago, 
    JComboBox<String> paramId_Estado, JComboBox<String> paramId_Categoria)
{
    gas.setId(Integer.parseInt(paramIdGasto.getText()));
    gas.setCodIngreso(paramCodigoGasto.getText());
    Date fecha = paramFechaGasto.getDate();
    
    if (fecha != null) {
        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
        gas.setFechaIngreso(sqlFecha);
    }
    
    
    gas.setMonto(Double.parseDouble(paramMonto.getText()));
    gas.setDescripcion(paramDescripcion.getText());
    gas.setIdMetodoPago(Integer.parseInt((String) paramId_Metodo_Pago.getSelectedItem()));
    gas.setIdEstado(Integer.parseInt((String) paramId_Estado.getSelectedItem()));
    gas.setIdCategoria(Integer.parseInt((String)paramId_Categoria.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE gastos SET codigoGasto=?, fechaGasto=?, monto=?, descripcion=?, id_Metodo_Pago=?, id_Estado=?, id_Categoria=? WHERE idGasto=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, gas.getCodIngreso());
        
        if (fecha != null) {
            java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
            cs.setDate(2, sqlFecha);
        } else {
            cs.setNull(2, java.sql.Types.DATE);
        }
        
        cs.setDouble(3, gas.getMonto());
        cs.setString(4, gas.getDescripcion());
        cs.setInt(5, gas.getIdMetodoPago());
        cs.setInt(6, gas.getIdEstado());
        cs.setInt(7, gas.getIdCategoria());
        cs.setInt(8, gas.getId());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
    
    public void eliminarGastos(JTextField paramIdGasto)
    {
    gas.setId(Integer.parseInt(paramIdGasto.getText()));
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from gastos WHERE idGasto=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, gas.getId());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
    public void limpiar(){}
}

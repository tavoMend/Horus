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
public class ConsultasCategoriasGastos extends DBConexion{
    
    public void insertarCategoriasGastos(
            JTextField paramCodigoCategoria, JTextField paramNombreCategoria,
            JComboBox paramIdTipoGasto)
    {
    PreparedStatement ps = null;
    Connection con = establecerConexion();
    String consulta = "INSERT into categorias (codigCategoria,nombreCategoria,idTipoGasto)"
            + "VALUES (?,?,?)";
    
        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramCodigoCategoria.getText());
            ps.setString(2, paramNombreCategoria.getText());
            ps.setString(3, (String) paramIdTipoGasto.getSelectedItem());
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
    
    
    public void mostrarCategoriasGastos(JTable paramTablaCategoriasGastos)
    {
        DBConexion con = new DBConexion();
        
        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel>(mTabla);
        paramTablaCategoriasGastos.setRowSorter(ordenarTabla);
        
        String consulta = "";
        
        mTabla.addColumn("Id Categoria de Gastos");
        mTabla.addColumn("Codigo de Categoria");
        mTabla.addColumn("Nombre de Categoria");
        mTabla.addColumn("Id Tipo de Gasto");
        
        paramTablaCategoriasGastos.setModel(mTabla);
        
        consulta = "SELECT * from categorias";
        
        String[] datos = new String[4];
        
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
                
                mTabla.addRow(datos);
            }
            paramTablaCategoriasGastos.setModel(mTabla);
            
        } catch (SQLException e) {
            JOptionPane.showInputDialog(null,"Error, no se puedieron cargar los datos"+e.toString());
        }  
        
    }
    
    public void seleccionarCategoriasGastos(JTable paramTablaCategoriasGastos, 
            JTextField paramIdCategoria, JTextField paramCodigoCategoria, 
            JTextField paramNombreCategoria, JComboBox paramIdTipoGasto
            )
    {
        try {
    int fila = paramTablaCategoriasGastos.getSelectedRow();

    if (fila >= 0) {
        paramIdCategoria.setText(paramTablaCategoriasGastos.getValueAt(fila, 0).toString());
        paramCodigoCategoria.setText(paramTablaCategoriasGastos.getValueAt(fila, 1).toString());
        paramNombreCategoria.setText(paramTablaCategoriasGastos.getValueAt(fila, 2).toString());
       
        String idPeriodo = paramTablaCategoriasGastos.getValueAt(fila, 3).toString();
        for (int i = 0; i < paramIdTipoGasto.getItemCount(); i++) {
            if (paramIdTipoGasto.getItemAt(i).toString().equals(idPeriodo)) {
                paramIdTipoGasto.setSelectedIndex(i);
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
    
    CategoriasGasto cat = new CategoriasGasto();
    
    public void actualizarCategoriasGastos(JTextField paramIdCategoria, JTextField paramCodigoCategoria, 
            JTextField paramNombreCategoria, JComboBox paramIdTipoGasto
            )
{
    cat.setIdCategoria(Integer.parseInt(paramIdCategoria.getText()));
    cat.setCodigoCategoria(paramCodigoCategoria.getText());
    cat.setNombreCategoria(paramNombreCategoria.getText());
    cat.setIdTipoGasto(Integer.parseInt((String)paramIdTipoGasto.getSelectedItem()));
   
    
    DBConexion con = new DBConexion();
    String consulta = "UPDATE categorias SET codigCategoria=?, NombreCategoria=?, idTipoGasto=? WHERE idCategoria=?;";
    //String consulta = "UPDATE `bdsgf`.`gastos` SET `codigoGasto` = ?, `fechaGasto` = ?, `monto` = ?, `descripcion` = ?, `id_Metodo_Pago` = ?, `id_Estado`, `id_Categoria` = ? WHERE (`idGasto` = ?);";
    try {
        java.sql.CallableStatement cs = con.establecerConexion().prepareCall(consulta);
        
        cs.setString(1, cat.getCodigoCategoria());
        cs.setString(2, cat.getNombreCategoria());
        cs.setInt(3, cat.getIdTipoGasto());
        cs.setInt(4, cat.getIdCategoria());
        
        cs.execute();
        
        JOptionPane.showMessageDialog(null, "Registro Actualizado");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro error: " + e.toString());
    }
}
    
        public void eliminarCategoriasGastos(JTextField paramIdCategoria)
    {
    cat.setIdCategoria(Integer.parseInt(paramIdCategoria.getText()));
    
    DBConexion con = new DBConexion();
        
    String consulta = "Delete from categorias WHERE idCategoria=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, cat.getIdCategoria());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el error, error:"+e.toString());
        }
    }
    
    
    
}

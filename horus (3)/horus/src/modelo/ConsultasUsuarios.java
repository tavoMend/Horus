package modelo;

// Asegúrate de importar la librería correctamente

import com.mysql.cj.jdbc.CallableStatement;
import org.mindrot.jbcrypt.BCrypt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.*;

/**
 *
 * @author Gabriel
 */
public class ConsultasUsuarios extends DBConexion {

    // Método para insertar usuarios en la base de datos
    public void insertarUsuarios(
            JTextField paramCodigoUsuario, JTextField paramUser,
            JPasswordField paramPassword, JTextField paramCorreo,
            JComboBox<String> paramIdRol) {

        // Conexión a la base de datos
        PreparedStatement ps = null;
        Connection con = establecerConexion();
        
        // Consulta SQL para insertar datos en la tabla usuarios
        String consulta = "INSERT INTO usuarios (codigoUsuario, user, password, correo, rol) VALUES (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(consulta);
            ps.setString(1, paramCodigoUsuario.getText());
            ps.setString(2, paramUser.getText());

            // Encriptar la contraseña antes de guardarla
            String hashedPassword = BCrypt.hashpw(new String(paramPassword.getPassword()), BCrypt.gensalt());
            ps.setString(3, hashedPassword);

            ps.setString(4, paramCorreo.getText());
            ps.setString(5, (String) paramIdRol.getSelectedItem());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Registro ingresado correctamente");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Registro no ingresado, error: " + e.toString());
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        }
    }

    // Método para mostrar usuarios en una tabla
    public void mostrarUsuarios(JTable paramTablaUsuarios) {
        DBConexion con = new DBConexion();

        DefaultTableModel mTabla = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(mTabla);
        paramTablaUsuarios.setRowSorter(ordenarTabla);

        String consulta = "";

        mTabla.addColumn("Id de usuario");
        mTabla.addColumn("Codigo Usuario");
        mTabla.addColumn("Usuario");
        mTabla.addColumn("Contraseña");
        mTabla.addColumn("Correo");
        mTabla.addColumn("Rol");

        paramTablaUsuarios.setModel(mTabla);

        consulta = "SELECT * FROM usuarios";

        String[] datos = new String[6];

        Statement st;

        try {
            Connection connection = con.establecerConexion();
            st = connection.createStatement();
            ResultSet rs = st.executeQuery(consulta);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                mTabla.addRow(datos);
            }
            paramTablaUsuarios.setModel(mTabla);

        } catch (SQLException e) {
            JOptionPane.showInputDialog(null, "Error, no se pudieron cargar los datos: " + e.toString());
        }
    }

    // Método para seleccionar usuarios desde una tabla
    public void seleccionarUsuarios(JTable paramTablaPresupuestos, JTextField paramIdUsuario,
                                    JTextField paramCodigoUsuario, JTextField paramUser,
                                    JPasswordField paramPassword, JTextField paramCorreo,
                                    JComboBox<String> paramIdRol) {
        try {
            int fila = paramTablaPresupuestos.getSelectedRow();

            if (fila >= 0) {
                paramIdUsuario.setText(paramTablaPresupuestos.getValueAt(fila, 0).toString());
                paramCodigoUsuario.setText(paramTablaPresupuestos.getValueAt(fila, 1).toString());
                paramUser.setText(paramTablaPresupuestos.getValueAt(fila, 2).toString());
                paramPassword.setText(paramTablaPresupuestos.getValueAt(fila, 3).toString());
                paramCorreo.setText(paramTablaPresupuestos.getValueAt(fila, 4).toString());

                String idPeriodo = paramTablaPresupuestos.getValueAt(fila, 5).toString();
                for (int i = 0; i < paramIdRol.getItemCount(); i++) {
                    if (paramIdRol.getItemAt(i).equals(idPeriodo)) {
                        paramIdRol.setSelectedIndex(i);
                        break;
                    }
                }

            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección, error: " + e.toString());
        }
    }

    // Método para actualizar usuarios
    public void actualizarUsuarios(JTextField paramIdUsuario,
                                   JTextField paramCodigoUsuario, JTextField paramUser,
                                   JPasswordField paramPassword, JTextField paramCorreo,
                                   JComboBox<String> paramIdRol) {
        Usuario u = new Usuario();
        u.setIdUsuario(Integer.parseInt(paramIdUsuario.getText()));
        u.setCodigoUsuario(paramCodigoUsuario.getText());
        u.setUser(paramUser.getText());
        
        // Encriptar la nueva contraseña antes de actualizarla
        String hashedPassword = BCrypt.hashpw(new String(paramPassword.getPassword()), BCrypt.gensalt());
        u.setPassword(hashedPassword);
        
        u.setCorreo(paramCorreo.getText());
        u.setRol(Integer.parseInt((String) paramIdRol.getSelectedItem()));

        DBConexion con = new DBConexion();
        String consulta = "UPDATE usuarios SET codigoUsuario=?, user=?, password=?, correo=?, rol=? WHERE idUsuario=?";

        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);

            cs.setString(1, u.getCodigoUsuario());
            cs.setString(2, u.getUser());
            cs.setString(3, u.getPassword());
            cs.setString(4, u.getCorreo());
            cs.setInt(5, u.getRol());
            cs.setInt(6, u.getIdUsuario());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el registro, error: " + e.toString());
        }
    }

    // Método para eliminar usuarios
    public void eliminarUsuarios(JTextField paramIdUsuario) {
        Usuario u = new Usuario();
        u.setIdUsuario(Integer.parseInt(paramIdUsuario.getText()));

        DBConexion con = new DBConexion();

        String consulta = "DELETE FROM usuarios WHERE idUsuario=?";
        try {
            CallableStatement cs = (CallableStatement) con.establecerConexion().prepareCall(consulta);
            cs.setInt(1, u.getIdUsuario());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Registro Eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro, error: " + e.toString());
        }
    }
}

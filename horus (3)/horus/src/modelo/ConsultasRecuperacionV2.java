package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import vista.Login;
import vista.Recuperacion;

/**
 * Clase para realizar consultas relacionadas con la recuperación y actualización de contraseñas.
 */
public class ConsultasRecuperacionV2 extends DBConexion {

    /**
     * Actualiza la contraseña del usuario asociado al correo electrónico proporcionado.
     * @param correoUsuario Correo electrónico del usuario.
     * @param paramPassword1 Primer campo de contraseña.
     * @param paramPassword2 Segundo campo de contraseña para confirmación.
     */
    public void actualizarContraseña(String correoUsuario, JPasswordField paramPassword1, JPasswordField paramPassword2) {
        // Verificar que las contraseñas coincidan
        String password1 = new String(paramPassword1.getPassword());
        String password2 = new String(paramPassword2.getPassword());

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            return; // Salir del método sin hacer nada
        }

        // Conexión a la base de datos
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = establecerConexion();

            // Verificar si el correo existe
            if (!existeCorreo(con, correoUsuario)) {
                JOptionPane.showMessageDialog(null, "El correo electrónico no está registrado");
                return; // Salir del método si el correo no existe
            }

            // Consulta SQL para actualizar la contraseña en la tabla usuarios
            String consulta = "UPDATE usuarios SET password = ? WHERE correo = ?";
            ps = con.prepareStatement(consulta);

            // Encriptar la contraseña antes de guardarla
            String hashedPassword = BCrypt.hashpw(password1, BCrypt.gensalt());
            ps.setString(1, hashedPassword);
            ps.setString(2, correoUsuario);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Contraseña actualizada correctamente, Regresaras al inicio de sesion");
                Login frmLogin = new Login();
                Recuperacion recV2 = new Recuperacion();
                frmLogin.setVisible(true);
                //recV2.dispose();
                recV2.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar la contraseña");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la contraseña: " + e.getMessage());
        } finally {
            // Cerrar recursos en el bloque finally para asegurar que siempre se cierren
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    /**
     * Verifica si existe un registro con el correo electrónico proporcionado.
     * @param con Conexión a la base de datos.
     * @param correo Correo electrónico a verificar.
     * @return Verdadero si el correo existe, falso en caso contrario.
     * @throws SQLException En caso de error en la consulta.
     */
    private boolean existeCorreo(Connection con, String correo) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(consulta)) {
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}

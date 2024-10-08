package modelo;
import org.mindrot.jbcrypt.BCrypt; // Asegúrate de tener esta dependencia en tu proyecto
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import vista.MenuPrincipal;
import vista.Login;

/**
 * Clase para manejar la autenticación de usuarios.
 */
public class mLogin {

    private int rol; // Variable para almacenar el rol del usuario
    public void validarUsuario(JTextField usuario, JPasswordField contrasena) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            modelo.DBConexion objetoBConexion = new modelo.DBConexion();
            String consulta = "SELECT password, rol FROM usuarios WHERE user = ?;";
            ps = objetoBConexion.establecerConexion().prepareStatement(consulta);
            
            String userName = usuario.getText();
            String passwordInput = String.valueOf(contrasena.getPassword());
            
            ps.setString(1, userName);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                // Obtener la contraseña encriptada de la base de datos
                String hashedPasswordFromDB = rs.getString("password");

                // Verificar la contraseña proporcionada con la contraseña encriptada
                if (BCrypt.checkpw(passwordInput, hashedPasswordFromDB)) {
                    // Recuperar el campo rol como entero
                    rol = rs.getInt("rol");

                    JOptionPane.showMessageDialog(null, "Usuario ingresado correctamente");
                    Login formLogin = new Login();
                    formLogin.setVisible(false);
                    MenuPrincipal formMenu = new MenuPrincipal();
                    formMenu.setRol(rol);
                    formMenu.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta, intenta nuevamente.");
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Usuario incorrecto, intenta nuevamente.");
            }
            
        } catch (Exception e) {
            e.printStackTrace(); // Imprime el stack trace para depuración
            JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR al cerrar recursos: " + e.getMessage());
            }
        }
    }

    
}

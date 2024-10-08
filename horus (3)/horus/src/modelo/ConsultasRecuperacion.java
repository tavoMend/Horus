package modelo;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.HashMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import vista.Recuperacion;

public class ConsultasRecuperacion extends DBConexion {

    // Map to store the last OTP request time for each email
    private HashMap<String, Long> otpRequestTime = new HashMap<>();

    public interface Callback {
        void onSuccess();
        void onFailure(String mensaje);
    }

    public void enviarCorreo(String correoUsuario, Callback callback) {
        // Verificar si el correo existe en la base de datos
        if (!verificarCorreoEnBaseDatos(correoUsuario)) {
            callback.onFailure("El correo electrónico no se encuentra registrado.");
            return;
        }

        // Verificar si el último OTP fue enviado hace menos de 2 minutos
        if (verificarTiempoEnvioOTP(correoUsuario)) {
            callback.onFailure("Ya has solicitado un código recientemente. Por favor, espera 2 minutos antes de solicitar uno nuevo.");
            return;
        }

        // Generar un código OTP
        String otp = generarOTP();

        // Actualizar el campo OTP en la base de datos
        if (actualizarOTPEnBaseDatos(correoUsuario, otp)) {
            // Configuración de propiedades para el servidor SMTP
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com"); // Servidor SMTP de Gmail
            props.put("mail.smtp.port", "587"); // Puerto para TLS
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true"); // Habilitar STARTTLS
            props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Especificar protocolo TLS

            // Autenticador de la sesión
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("pdn.grupo1@gmail.com", "jpqe iogo ffso ahtz");
                }
            });

            try {
                // Crear un mensaje de correo
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("pdn.grupo1@gmail.com", "Soporte Técnico Example.com"));
                msg.addRecipient(Message.RecipientType.TO,
                                 new InternetAddress(correoUsuario));
                msg.setSubject("Código de Verificación (OTP) - Example.com");

                // Personalizar el cuerpo del mensaje
                String mensaje = "Estimado usuario,\n\n"
                               + "Hemos recibido una solicitud para restablecer tu contraseña. "
                               + "Por favor, utiliza el siguiente código de verificación (OTP) para completar el proceso:\n\n"
                               + "Código OTP: " + otp + "\n\n"
                               + "Este código es válido por 10 minutos.\n\n"
                               + "Si no solicitaste este código, por favor ignora este mensaje.\n\n"
                               + "Atentamente,\n"
                               + "El equipo de soporte de Example.com";

                msg.setText(mensaje);

                // Enviar el mensaje
                Transport.send(msg);
                callback.onSuccess();
                
                // Actualizar la marca de tiempo de la última solicitud de OTP
                otpRequestTime.put(correoUsuario, System.currentTimeMillis());

            } catch (AddressException e) {
                e.printStackTrace(); // Manejar la excepción de dirección inválida
                callback.onFailure("Dirección de correo electrónico inválida.");
            } catch (MessagingException e) {
                e.printStackTrace(); // Manejar problemas de mensajería
                callback.onFailure("Error al enviar el correo electrónico.");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace(); // Manejar problemas de codificación de caracteres
                callback.onFailure("Error de codificación de caracteres.");
            }
        } else {
            callback.onFailure("Error al actualizar el OTP en la base de datos.");
        }
    }

    
    public void verificarOTP(String correoUsuario, String otpIngresado, Callback callback) {
        // Verificar si el correo existe en la base de datos
        if (!verificarCorreoEnBaseDatos(correoUsuario)) {
            callback.onFailure("El correo electrónico no se encuentra registrado.");
            return;
        }

        // Verificar el código OTP en la base de datos
        String otpAlmacenado = obtenerOTPDeBaseDatos(correoUsuario);
        if (otpAlmacenado == null) {
            callback.onFailure("No se encontró un código OTP asociado con este correo.");
            return;
        }

        // Comparar el código OTP almacenado con el ingresado
        if (otpAlmacenado.equals(otpIngresado)) {
            callback.onSuccess();
        } else {
            callback.onFailure("El código OTP ingresado es incorrecto.");
        }
    }
   
    
    private String obtenerOTPDeBaseDatos(String correoUsuario) {
        Connection con = establecerConexion();
        String sql = "SELECT otp FROM usuarios WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("otp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
// Métodos auxiliares

    private boolean verificarCorreoEnBaseDatos(String correoUsuario) {
        Connection con = establecerConexion();
        String sql = "SELECT * FROM usuarios WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correoUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay un resultado, el correo existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean verificarTiempoEnvioOTP(String correoUsuario) {
        if (otpRequestTime.containsKey(correoUsuario)) {
            long ultimaSolicitud = otpRequestTime.get(correoUsuario);
            long diferencia = System.currentTimeMillis() - ultimaSolicitud;
            return diferencia < 2 * 60 * 1000; // 2 minutos en milisegundos
        }
        return false;
    }

    private boolean actualizarOTPEnBaseDatos(String correoUsuario, String otp) {
        Connection con = establecerConexion();
        String sql = "UPDATE usuarios SET otp = ? WHERE correo = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, otp);
            ps.setString(2, correoUsuario);
            return ps.executeUpdate() > 0; // Devuelve true si se actualizó una fila
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String generarOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            otp.append(random.nextInt(10)); // Añadir un dígito aleatorio
        }
        return otp.toString();
    }
    
}

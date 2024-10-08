
package modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConexion {

    
    Connection con = null;
    String usuario = "root";
    String contra = "ingresar_clave_de_SQL";
    String bd = "bdsgf";
    String ip = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
            
 public Connection establecerConexion(){
     try {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection(cadena,usuario,contra);
         //JOptionPane.showMessageDialog(null, "Conexion Exitosa");
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Error en la conexion: " + e.toString());
     }
     return con;
 }

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vista.Usuarios;

/**
 *
 * @author Gabriel
 */
public class CtrlUsuario {
    
    private Usuarios vista;
    
    public void limpiar(JTextField paramIdUsuario, JTextField paramCodigoUsuario,
            JTextField paramUser,
            JPasswordField paramPassword, JTextField paramCorreo ,JComboBox paramIdRol)
    {
        
        paramIdUsuario.setText(null);
        paramCodigoUsuario.setText(null);
        paramUser.setText(null);
        paramPassword.setText(null);
        paramCorreo.setText(null);
        paramIdRol.setSelectedIndex(0);
    }
    
}

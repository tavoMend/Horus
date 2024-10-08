/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Gabriel
 */
public class CtrlSubCategoria {
    
    public void limpiar(JTextField paramIdSubCategoria, JTextField paramNombreCategoria,
            JComboBox paramIdCategoria)
    {
        
        paramIdSubCategoria.setText(null);
        paramNombreCategoria.setText(null);
        paramIdCategoria.setSelectedIndex(0);
    }
    
}

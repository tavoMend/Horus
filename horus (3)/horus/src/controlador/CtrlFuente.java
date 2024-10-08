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
public class CtrlFuente {
    
    public void limpiar(JTextField paramIdFuente, JTextField paramCodigoFuente,
            JTextField paramNombreFuente,
            JTextField paramOrigen, JComboBox paramIdClasificacionIngreso)
    {
        
        paramIdFuente.setText(null);
        paramCodigoFuente.setText(null);
        paramNombreFuente.setText(null);
        paramOrigen.setText(null);
        paramIdClasificacionIngreso.setSelectedIndex(-1);
    }
    
}

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
public class CtrlMetodosPago {
    
    public void limpiar(JTextField paramIdMetodoPago, JTextField paramNombreMetodoPago)
    {
        paramIdMetodoPago.setText(null);
        paramNombreMetodoPago.setText(null);
    }
    
}
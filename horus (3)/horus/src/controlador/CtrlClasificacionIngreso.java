/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JTextField;

/**
 *
 * @author Gabriel
 */
public class CtrlClasificacionIngreso {
    
    public void limpiar(JTextField paramIdClasificacionIngreso,
            JTextField paramNombreClasificacionIngreso)
    {
        
        paramIdClasificacionIngreso.setText(null);
        paramNombreClasificacionIngreso.setText(null);
    }
    
}

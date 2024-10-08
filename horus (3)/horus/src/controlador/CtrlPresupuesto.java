/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import javax.swing.JComboBox;
import javax.swing.JTextField;
import vista.Presupuestos;

/**
 *
 * @author Gabriel
 */
public class CtrlPresupuesto {
    
    private Presupuestos vista;
    
    public void limpiar(JTextField paramIdPresupuesto, JTextField paramCodigoPresupuesto,
            JTextField paramObjetivo, JComboBox paramIdPeriodo)
    {
        
        paramIdPresupuesto.setText(null);
        paramCodigoPresupuesto.setText(null);
        paramObjetivo.setText(null);
        paramIdPeriodo.setSelectedIndex(-1);
    }
    
}

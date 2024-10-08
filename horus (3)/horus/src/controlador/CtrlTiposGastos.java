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
public class CtrlTiposGastos {
    
    public void limpiar(JTextField paramIdTipoGasto, JTextField paramNombreTipoGasto)
    {
        
        paramIdTipoGasto.setText(null);
        paramNombreTipoGasto.setText(null);
    }
}

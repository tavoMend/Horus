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
public class CtrlCategoriasGasto {
    
    public void limpiar(JTextField paramIdCategoria, JTextField paramCodigoCategoria,
            JTextField paramNombreCategoria, JComboBox paramIdTipoGasto)
    {
        
        paramIdCategoria.setText(null);
        paramCodigoCategoria.setText(null);
        paramNombreCategoria.setText(null);
        paramIdTipoGasto.setSelectedIndex(-1);
    }
}

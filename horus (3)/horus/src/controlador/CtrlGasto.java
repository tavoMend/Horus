
package controlador;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import vista.Gastos;

/**
 *
 * @author Gabriel
 */
public class CtrlGasto {
    
    private Gastos vista;
    
    public void limpiar()
    {
    vista.txtId.setText(null);
    vista.txtCodigoIngreso.setText(null);
    vista.dcrFecha.setDate(null);
    vista.txtMonto.setText(null);
    vista.txtDescripcion.setText(null);
    vista.cboMetodoPago.setSelectedIndex(-1);
    vista.cboEstado.setSelectedIndex(-1);
    vista.cboCategoria.setSelectedIndex(-1);

    }
    
    public void limpiar2(JTextField paramIdIngreso, JTextField paramCodigoIngreso,
            JDateChooser paramFechaIngreso,
            JTextField paramMonto,JTextField paramDescripcion,
            JComboBox paramIdMetodoPago, JComboBox paramIdEstado,JComboBox paramIdFuente)
    {
        
        paramIdIngreso.setText(null);
        paramCodigoIngreso.setText(null);
        paramFechaIngreso.setDate(null);
        paramMonto.setText(null);
        paramDescripcion.setText(null);
        paramIdMetodoPago.setSelectedIndex(0);
        paramIdEstado.setSelectedIndex(0);
        paramIdFuente.setSelectedIndex(0);
    }
}

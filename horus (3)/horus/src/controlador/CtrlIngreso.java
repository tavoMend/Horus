package controlador;


import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import vista.Ingresos;

/**
 *
 * @author Gabriel
 */
public class CtrlIngreso   {
    
    public void limpiar(JTextField paramIdIngreso, JTextField paramCodigoIngreso,
            JDateChooser paramFechaIngreso,
            JTextField paramMonto,JTextField paramDescripcion,
            JComboBox paramIdMetodoPago, JComboBox paramIdEstado,JComboBox paramIdFuente)
    {
        
        paramIdIngreso.setText(null);
        paramCodigoIngreso.setText(null);
        paramFechaIngreso.setDate(null);
        paramMonto.setText(null);
        paramDescripcion.setText(null);
        paramIdMetodoPago.setSelectedIndex(-1);
        paramIdEstado.setSelectedIndex(-1);
        paramIdFuente.setSelectedIndex(-1);
    }
}

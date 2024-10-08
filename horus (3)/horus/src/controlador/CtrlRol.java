package controlador;

import vista.CategoriasGastos;
import vista.ClasificacionesIngresos;
import vista.DetallesPresupuestos;
import vista.Estados;
import vista.Fuentes;
import vista.GastosPrueba;
import vista.Ingresos;
import vista.MenuPrincipal;
import vista.MetodosPagos;
import vista.Periodos;
import vista.Presupuestos;
import vista.Subcategorias;
import vista.TiposGastos;
import vista.Usuarios;
import modelo.mLogin;



/**
 *
 * @author Gabriel
 */
public class CtrlRol {
    
     private MenuPrincipal mP;

    public CtrlRol(MenuPrincipal menuPrincipal) {
        this.mP = menuPrincipal;
    }

    public void ajustarVisibilidadPorRol(int rol) {
        if (rol == 2){
            mP.jMenu6.setVisible(false);
        }
    }

}

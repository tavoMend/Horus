package modelo;

import vista.MenuPrincipal;

public class CtrlRol {
    private MenuPrincipal menuPrincipal;


    public CtrlRol(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
}
    public void ajustarVisibilidadPorRol(int cboRol) {
        // Aquí se definen los roles, por ejemplo, 1 para Administrador y 2 para Usuario regular
        switch (cboRol) {
            case 1: // Administrador
                menuPrincipal.mimNuevoUsuario.setVisible(true);

                break;
            case 2: // Usuario regular
                menuPrincipal.mimNuevoUsuario.setVisible(false);
                break;
            default:
                break;
        }
    }
}




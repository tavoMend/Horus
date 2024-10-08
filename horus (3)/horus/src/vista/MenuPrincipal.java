package vista;

import Reportes.ReporteGasto;
import Reportes.ReporteIngreso;
import controlador.CtrlInactividad;
import controlador.CtrlRol;

import javax.swing.*;

/**
 *
 * @author hasse
 */
public class MenuPrincipal extends javax.swing.JFrame {
    
    private CtrlRol ctrlR;
    private int rol;
    
    
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Menu principal");
        CtrlInactividad ctrlInactividad = CtrlInactividad.getInstance();
        ctrlInactividad.startInactivityListener(this);
        ctrlR = new CtrlRol(this);
        
        
    }
    public void setRol(int rol) {
        this.rol = rol;
        ctrlR.ajustarVisibilidadPorRol(rol);
        //label para comprobar que valor se esta recibiendo del login
        jLabel2.setText(Integer.toString(rol));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mimSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mimNuevoIngreso = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mimNuevoGasto = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mimCrearPresupuesto = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mimNuevoUsuario = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mimReporteGastos = new javax.swing.JMenuItem();
        mimReporteIngresos = new javax.swing.JMenuItem();
        mimReportePresupuesto = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        mimFuenteIngreso = new javax.swing.JMenuItem();
        mimClasificacionFuente = new javax.swing.JMenuItem();
        mimNuevoMetodoPago = new javax.swing.JMenuItem();
        mimCategoriaGastos = new javax.swing.JMenuItem();
        mimSubcategoriaGastos = new javax.swing.JMenuItem();
        mimDetallesPresupuesto = new javax.swing.JMenuItem();
        mimTipoGasto = new javax.swing.JMenuItem();
        mimTipoEstado = new javax.swing.JMenuItem();
        mimPeriodoPresupuesto1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Calisto MT", 1, 80)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Horus");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(jLabel2)))
                .addContainerGap(256, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jMenu1.setText("Inicio");
        jMenu1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMenu1StateChanged(evt);
            }
        });

        mimSalir.setText("Salir");
        mimSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimSalirActionPerformed(evt);
            }
        });
        jMenu1.add(mimSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ingresos");
        jMenu2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMenu2StateChanged(evt);
            }
        });

        mimNuevoIngreso.setText("Nuevo ingreso");
        mimNuevoIngreso.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mimNuevoIngresoStateChanged(evt);
            }
        });
        mimNuevoIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimNuevoIngresoActionPerformed(evt);
            }
        });
        jMenu2.add(mimNuevoIngreso);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Gastos");
        jMenu3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jMenu3StateChanged(evt);
            }
        });

        mimNuevoGasto.setText("Nuevo Gasto");
        mimNuevoGasto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                mimNuevoGastoStateChanged(evt);
            }
        });
        mimNuevoGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimNuevoGastoActionPerformed(evt);
            }
        });
        jMenu3.add(mimNuevoGasto);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Presupuestos");

        mimCrearPresupuesto.setText("Crear presupuesto");
        mimCrearPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimCrearPresupuestoActionPerformed(evt);
            }
        });
        jMenu4.add(mimCrearPresupuesto);

        jMenuBar1.add(jMenu4);

        jMenu6.setText("Usuarios");

        mimNuevoUsuario.setText("Nuevo usuario");
        mimNuevoUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimNuevoUsuarioActionPerformed(evt);
            }
        });
        jMenu6.add(mimNuevoUsuario);

        jMenuBar1.add(jMenu6);

        jMenu5.setText("Reportes");

        mimReporteGastos.setText("Reporte de gastos");
        mimReporteGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimReporteGastosActionPerformed(evt);
            }
        });
        jMenu5.add(mimReporteGastos);

        mimReporteIngresos.setText("Reporte de ingresos");
        mimReporteIngresos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimReporteIngresosActionPerformed(evt);
            }
        });
        jMenu5.add(mimReporteIngresos);

        mimReportePresupuesto.setText("Reporte de presupuestos");
        jMenu5.add(mimReportePresupuesto);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Herramientas");

        mimFuenteIngreso.setText("Nueva fuente de ingresos");
        mimFuenteIngreso.setToolTipText("");
        mimFuenteIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimFuenteIngresoActionPerformed(evt);
            }
        });
        jMenu7.add(mimFuenteIngreso);

        mimClasificacionFuente.setText("Nueva clasificacion de fuente");
        mimClasificacionFuente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimClasificacionFuenteActionPerformed(evt);
            }
        });
        jMenu7.add(mimClasificacionFuente);

        mimNuevoMetodoPago.setText("Nuevo método de pago");
        mimNuevoMetodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimNuevoMetodoPagoActionPerformed(evt);
            }
        });
        jMenu7.add(mimNuevoMetodoPago);

        mimCategoriaGastos.setText("Nueva categoria de gastos");
        mimCategoriaGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimCategoriaGastosActionPerformed(evt);
            }
        });
        jMenu7.add(mimCategoriaGastos);

        mimSubcategoriaGastos.setText("Nueva subcategoria de gastos");
        mimSubcategoriaGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimSubcategoriaGastosActionPerformed(evt);
            }
        });
        jMenu7.add(mimSubcategoriaGastos);

        mimDetallesPresupuesto.setText("Nuevo tipo de detalle de presupuesto");
        mimDetallesPresupuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimDetallesPresupuestoActionPerformed(evt);
            }
        });
        jMenu7.add(mimDetallesPresupuesto);

        mimTipoGasto.setText("Nuevo tipo de gasto");
        mimTipoGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimTipoGastoActionPerformed(evt);
            }
        });
        jMenu7.add(mimTipoGasto);

        mimTipoEstado.setText("Nuevo tipo de estado");
        mimTipoEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimTipoEstadoActionPerformed(evt);
            }
        });
        jMenu7.add(mimTipoEstado);

        mimPeriodoPresupuesto1.setText("Nuevo periodo de presupuesto");
        mimPeriodoPresupuesto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mimPeriodoPresupuesto1ActionPerformed(evt);
            }
        });
        jMenu7.add(mimPeriodoPresupuesto1);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mimNuevoUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimNuevoUsuarioActionPerformed
        // TODO add your handling code here:
        Usuarios vistaU = new Usuarios();
        vistaU.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimNuevoUsuarioActionPerformed

    private void jMenu1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMenu1StateChanged
        
      
    }//GEN-LAST:event_jMenu1StateChanged

    private void jMenu3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMenu3StateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu3StateChanged

    private void mimNuevoGastoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mimNuevoGastoStateChanged

        
    }//GEN-LAST:event_mimNuevoGastoStateChanged

    private void mimNuevoIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimNuevoIngresoActionPerformed

        Ingresos vistaI = new Ingresos();
        vistaI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimNuevoIngresoActionPerformed

    private void mimNuevoIngresoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_mimNuevoIngresoStateChanged

    }//GEN-LAST:event_mimNuevoIngresoStateChanged

    private void jMenu2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jMenu2StateChanged
            
    }//GEN-LAST:event_jMenu2StateChanged

    private void mimNuevoGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimNuevoGastoActionPerformed
        // TODO add your handling code here:
        GastosPrueba  vistaG = new GastosPrueba();
        vistaG.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_mimNuevoGastoActionPerformed

    private void mimCrearPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimCrearPresupuestoActionPerformed
        // TODO add your handling code here:
        Presupuestos vistaP = new Presupuestos();
        vistaP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimCrearPresupuestoActionPerformed

    private void mimTipoGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimTipoGastoActionPerformed
        // TODO add your handling code here:
        TiposGastos vistaTG = new TiposGastos();
        vistaTG.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimTipoGastoActionPerformed

    private void mimCategoriaGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimCategoriaGastosActionPerformed
        // TODO add your handling code here:
        CategoriasGastos vistaCG = new CategoriasGastos();
        vistaCG.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_mimCategoriaGastosActionPerformed

    private void mimTipoEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimTipoEstadoActionPerformed
        // TODO add your handling code here:
        Estados vistaE = new Estados();
        vistaE.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_mimTipoEstadoActionPerformed

    private void mimDetallesPresupuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimDetallesPresupuestoActionPerformed
        // TODO add your handling code here:
        DetallesPresupuestos vistaDP = new DetallesPresupuestos();
        vistaDP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimDetallesPresupuestoActionPerformed

    private void mimSubcategoriaGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimSubcategoriaGastosActionPerformed
        // TODO add your handling code here:
        Subcategorias vistaSC = new Subcategorias();
        vistaSC.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimSubcategoriaGastosActionPerformed

    private void mimNuevoMetodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimNuevoMetodoPagoActionPerformed
        // TODO add your handling code here:
        MetodosPagos vistaMP = new MetodosPagos();
        vistaMP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimNuevoMetodoPagoActionPerformed

    private void mimPeriodoPresupuesto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimPeriodoPresupuesto1ActionPerformed
        // TODO add your handling code here:
        Presupuestos vistaP = new Presupuestos();
        vistaP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimPeriodoPresupuesto1ActionPerformed

    private void mimClasificacionFuenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimClasificacionFuenteActionPerformed
        // TODO add your handling code here:
        Fuentes vistaF = new Fuentes();
        vistaF.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimClasificacionFuenteActionPerformed

    private void mimFuenteIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimFuenteIngresoActionPerformed
        // TODO add your handling code here:
        ClasificacionesIngresos vistaCI = new ClasificacionesIngresos();
        vistaCI.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mimFuenteIngresoActionPerformed

    private void mimSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimSalirActionPerformed
        // TODO add your handling code here:
        int opcion = JOptionPane.showConfirmDialog(null, "¿Deseas salir del sistema?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
             System.exit(0);
        } else if (opcion == JOptionPane.NO_OPTION) {
             
        }
       
    }//GEN-LAST:event_mimSalirActionPerformed

    private void mimReporteGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimReporteGastosActionPerformed
        // TODO add your handling code here:
        ReporteGasto.reporte();
    }//GEN-LAST:event_mimReporteGastosActionPerformed

    private void mimReporteIngresosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mimReporteIngresosActionPerformed
        // TODO add your handling code here:
        ReporteIngreso.reporte();
    }//GEN-LAST:event_mimReporteIngresosActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    public javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem mimCategoriaGastos;
    private javax.swing.JMenuItem mimClasificacionFuente;
    private javax.swing.JMenuItem mimCrearPresupuesto;
    private javax.swing.JMenuItem mimDetallesPresupuesto;
    private javax.swing.JMenuItem mimFuenteIngreso;
    private javax.swing.JMenuItem mimNuevoGasto;
    public javax.swing.JMenuItem mimNuevoIngreso;
    private javax.swing.JMenuItem mimNuevoMetodoPago;
    public javax.swing.JMenuItem mimNuevoUsuario;
    private javax.swing.JMenuItem mimPeriodoPresupuesto1;
    private javax.swing.JMenuItem mimReporteGastos;
    private javax.swing.JMenuItem mimReporteIngresos;
    private javax.swing.JMenuItem mimReportePresupuesto;
    private javax.swing.JMenuItem mimSalir;
    private javax.swing.JMenuItem mimSubcategoriaGastos;
    private javax.swing.JMenuItem mimTipoEstado;
    private javax.swing.JMenuItem mimTipoGasto;
    // End of variables declaration//GEN-END:variables
}

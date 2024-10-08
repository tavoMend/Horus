package controlador;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import vista.Login;

/**
 * Controlador de inactividad implementado con el patrón Singleton.
 */
public class CtrlInactividad {

    private static CtrlInactividad instance;
    private Timer inactivityTimer;
    private final int INACTIVITY_LIMIT = 15 * 60 * 1000; // 5 minutos en milisegundos
    private JFrame currentFrame;
    private boolean isInactive; // Flag to prevent multiple dialogs

    // Constructor privado para evitar instanciación externa
    private CtrlInactividad() {}

    // Método estático para obtener la instancia única
    public static synchronized CtrlInactividad getInstance() {
        if (instance == null) {
            instance = new CtrlInactividad();
        }
        return instance;
    }

    // Método para iniciar el listener de inactividad
    public void startInactivityListener(JFrame currentFrame) {
        this.currentFrame = currentFrame;
        isInactive = false; // Inicializar la bandera

        // Inicializar el listener de eventos de actividad del usuario
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (event.getID() == KeyEvent.KEY_PRESSED || event.getID() == MouseEvent.MOUSE_MOVED || event.getID() == MouseEvent.MOUSE_DRAGGED) {
                    resetInactivityTimer();
                }
            }
        }, AWTEvent.KEY_EVENT_MASK | AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);

        // Iniciar el temporizador de inactividad
        startInactivityTimer();
    }

    private void startInactivityTimer() {
        // Cancelar el temporizador si ya existe
        if (inactivityTimer != null) {
            inactivityTimer.cancel();
        }
        
        inactivityTimer = new Timer(true);
        inactivityTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isInactive) { // Comprobar la bandera de inactividad
                    isInactive = true; // Establecer la bandera para evitar reentrada

                    // Sincronizar la actualización en el hilo de eventos de Swing
                    SwingUtilities.invokeLater(() -> {
                        if (!(currentFrame instanceof Login)) {
                            // Mostrar mensaje y cerrar sesión
                            JOptionPane.showMessageDialog(null, "Inactividad detectada. Cerrando sesión.");
                            
                            currentFrame.dispose(); // Cerrar la ventana actual
                            
                            // Crear y mostrar la ventana de login
                            Login frmLogin = new Login();
                            frmLogin.setVisible(true);
                        } else {
                            // Si la ventana actual es una instancia de Login, cerrar la aplicación
                            JOptionPane.showMessageDialog(null, "Inactividad detectada. Cerrando sistema.");
                            System.exit(0);
                        }
                    });
                }
            }
        }, INACTIVITY_LIMIT); // Limite de inactividad en milisegundos
    }

    private void resetInactivityTimer() {
        if (isInactive) {
            isInactive = false; // Resetear la bandera cuando se detecta actividad
        }
        if (inactivityTimer != null) {
            inactivityTimer.cancel(); // Cancelar el temporizador existente
        }
        startInactivityTimer(); // Iniciar un nuevo temporizador
    }
}

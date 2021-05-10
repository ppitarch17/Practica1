package Vista;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

//Clase adaptada (no implementa la interfaz listener. Extiende la clase adaptadora)
//para no dejar metodos vacios
public class EscuchadoraVentana extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        //Guardar datos del proyecto?
        System.exit(0); //Cierro la ventana si X
    }

}

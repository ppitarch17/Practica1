package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class EscuchadoraRaton implements Serializable {
    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    public EscuchadoraRaton(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
    }

    public void mouseClicked(MouseEvent e) {
        JList<Object> list = (JList)e.getSource();
        if (e.getClickCount() == 2) {
            int index = list.locationToIndex(e.getPoint());
            System.out.println("Double clicked on Item " + index);
        }
    }
}

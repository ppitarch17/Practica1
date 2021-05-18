package Vista;

import Controlador.Controlador;
import Modelo.Tarea;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EscuchadoraLista implements ListSelectionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;

    public EscuchadoraLista(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList lista = (JList) e.getSource();
        interfazGrafica.setTareaSeleccionada((Tarea)lista.getSelectedValue());
        System.out.println(lista.getSelectedValue());
    }


}

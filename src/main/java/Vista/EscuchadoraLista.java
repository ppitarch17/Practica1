package Vista;

import Controlador.Controlador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EscuchadoraLista implements ListSelectionListener {

    Controlador controlador;

    public EscuchadoraLista(Controlador controlador){
        this.controlador = controlador;
    }

    @Override
    public void valueChanged(ListSelectionEvent evento) {
        System.out.println(evento.getValueIsAdjusting());
        System.out.println(evento.getFirstIndex());
        System.out.println(evento.getLastIndex());

        JList lista = (JList) evento.getSource();
        System.out.println(lista.getSelectedValue());
        System.out.println("-----");
    }
}

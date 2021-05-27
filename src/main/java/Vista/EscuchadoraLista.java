package Vista;

import Controlador.Controlador;
import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.Serializable;

public class EscuchadoraLista implements ListSelectionListener, Serializable {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    EscuchadoraBoton escuchadoraBoton;

    public EscuchadoraLista(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
    }

    public EscuchadoraLista(Controlador controlador, InterfazGrafica interfazGrafica, EscuchadoraBoton escuchadoraBoton) {
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
        this.escuchadoraBoton = escuchadoraBoton;
    }

    public void setEscuchadoraBoton(EscuchadoraBoton escuchadoraBoton) {
        this.escuchadoraBoton = escuchadoraBoton;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList lista = (JList) e.getSource();


        switch (lista.getName()){
            case "cuadroPersonas" -> interfazGrafica.setPersonaSeleccioanda((Persona) lista.getSelectedValue());
            case "cuadroTareas" -> {
                interfazGrafica.setTareaSeleccionada((Tarea) lista.getSelectedValue());
                interfazGrafica.actualizarInfoTareaSeleccionada();
            }
            case "cuadroPersonasEnTarea" -> interfazGrafica.setPersonaDeTareaSeleccionada((Persona) lista.getSelectedValue());
        }


        //interfazGrafica.setTareaSeleccionada((Tarea)lista.getSelectedValue());
    }


}

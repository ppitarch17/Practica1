package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;
import Modelo.UtilidadesParaListas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EscuchadoraCheckBox implements ActionListener, Serializable {

    InterfazGrafica interfazGrafica;
    Controlador controlador;
    InterrogaModelo modelo;

    public EscuchadoraCheckBox(Controlador controlador, InterfazGrafica interfazGrafica, InterrogaModelo modelo) {
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox check = (JCheckBox) e.getSource();
        if ("No responsables".equals(check.getName())) {
            if (check.isSelected())
                interfazGrafica.setPersonas(modelo.listarNoResponsables().toArray(new Persona[0]));
            else {
                interfazGrafica.setPersonas(modelo.getPersonasEnProyecto());
            }
        }
        else if ("Sin personas".equals(check.getName())) {
            if (check.isSelected())
                interfazGrafica.setTareas(UtilidadesParaListas.elementosConListaVacia(modelo.getTareasEnModelo()).toArray(new Tarea[0]));
            else
                interfazGrafica.setTareas(modelo.getTareasEnProyecto());
        }
    }


}

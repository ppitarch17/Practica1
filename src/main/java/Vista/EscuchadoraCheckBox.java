package Vista;

import Controlador.Controlador;
import Modelo.Persona;
import Modelo.Tarea;
import Modelo.UtilidadesParaListas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EscuchadoraCheckBox implements ActionListener {

    InterfazGrafica interfazGrafica;
    Controlador controlador;

    public EscuchadoraCheckBox(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
        System.out.println(controlador + "check");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JCheckBox check = (JCheckBox) e.getSource();
        if ("No responsables".equals(check.getName())) {
            if (check.isSelected())
                interfazGrafica.setPersonas(listarNoResponsables().toArray(new Persona[0]));
            else {
                interfazGrafica.setPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
                System.out.println(controlador.getListaPersonas());
                System.out.println(listarNoResponsables());
            }
        }
        else if ("Sin personas".equals(check.getName())) {
            if (check.isSelected())
                interfazGrafica.setTareas(UtilidadesParaListas.elementosConListaVacia(controlador.getListaTareas()).toArray(new Tarea[0]));
            else
                interfazGrafica.setTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        }
    }

    public List<Persona> listarNoResponsables() {
        List<Persona> personas = new ArrayList<>(controlador.getListaPersonas());
        for (Tarea tarea : controlador.getListaTareas())
            personas.remove(tarea.getResponsable());
        return personas;
    }
    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }
}

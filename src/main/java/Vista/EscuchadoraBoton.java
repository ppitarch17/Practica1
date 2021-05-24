package Vista;

import Controlador.*;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraBoton implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;
    EscuchadoraTextField escuchadoraTextField;

    public EscuchadoraBoton(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        JButton boton = (JButton)evento.getSource();

        switch (boton.getText()){
            case "Crear Proyecto" -> interfazGrafica.ventanaCrear();
            case "Abrir Proyecto" -> System.out.println("Abrir");
            case "Crear" -> crear();
            case "Añadir tarea" -> System.out.println(interfazGrafica.getTareaSeleccionada());
            case "Añadir persona" -> addPersona();
            case "Añadir Persona a Proyecto" -> addPersonaAProyecto();
            //case "Finalizar tarea" -> controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
        }

    }

    public void crear(){
        interfazGrafica.setControlador(new Proyecto()); //TODO nombre al Proyecto
        interfazGrafica.setVectorPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
        interfazGrafica.setVectorTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        interfazGrafica.ventanaMain();
    }

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }

    public void setEscuchadoraTextField(EscuchadoraTextField escuchadoraTextField) {
        this.escuchadoraTextField = escuchadoraTextField;
    }

    public void addPersona(){
        interfazGrafica.ventanaPersona();
    }

    public void addPersonaAProyecto(){
        System.out.println("Añadiendo Persona");
        System.out.println(escuchadoraTextField.getNombrePersona());
        System.out.println(escuchadoraTextField.getCorreoPersona());
        System.out.println(escuchadoraTextField.getDniPersona());

        controlador.addPersona(escuchadoraTextField.getNombrePersona(), escuchadoraTextField.getCorreoPersona(), escuchadoraTextField.getDniPersona());
        System.out.println(controlador.getListaPersonas());

        actualizarInterfaz();
    }

    public void actualizarInterfaz(){
        interfazGrafica.setTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        interfazGrafica.setPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
    }


}

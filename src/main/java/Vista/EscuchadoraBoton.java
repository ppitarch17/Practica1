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
            case "Añadir persona a tarea" -> System.out.println("añadir personita");
            case "Quitar persona de tarea" -> System.out.println("quitar personita");
            //case "Finalizar tarea" -> controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
            case "Añadir etiqueta" -> System.out.println("añadir eti");
            case "Set Responsable" -> System.out.println("set resp");
            case "Seleccionar coste" -> System.out.println("cuanto cuesta??");
            case "Seleccionar facturación" -> System.out.println("facturacion");
            case "Salir del programa" -> System.out.println("mueranse");

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


    public void addPersona(){
        interfazGrafica.ventanaPersona();
    }

    public void addPersonaAProyecto(){
        System.out.println("Anadiendo Persona");
        //controlador.addPersona();
    }


}

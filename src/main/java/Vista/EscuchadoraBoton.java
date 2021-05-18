package Vista;

import Controlador.*;
import Modelo.InterrogaModelo;

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
            case "Crear" -> interfazGrafica.ventanaMain();
            case "Añadir tarea" -> System.out.println(interfazGrafica.getTareaSeleccionada());
            case "Añadir persona" -> interfazGrafica.ventanaPersona();
            case "Finalizar tarea" -> controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
        }


    }
}

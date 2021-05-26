package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraTextField implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    private String nombrePersona;
    private String correoPersona;
    private String dniPersona;
    private String texto;

    private String nombreTarea;
    private String descripcionTarea;
    private double costeTarea;

    public EscuchadoraTextField(Controlador controlador, InterfazGrafica interfazGrafica){
        this.controlador = controlador;
        System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField text = (JTextField) e.getSource();
        if (text.getName() != null)
            switch (text.getName()){
                case "dniDato" -> this.dniPersona = text.getText();
                case "correoDato" -> this.correoPersona = text.getText();
                case "nombreDato" -> this.nombrePersona = text.getText();
                case "nombreTareaDato" -> this.nombreTarea = text.getText();
                case "descripcionDato" -> this.descripcionTarea = text.getText();
                case "costeInicialDatos" -> costeInicial(text);
                case "seleccionarCoste" -> seleccionarCoste(Double.parseDouble(text.getText()));
            }
        else this.texto = text.getText();

        System.out.println(text.getName() + ": " + text.getText());

    }

    public void seleccionarCoste(double nuevoCoste){
        interfazGrafica.getTareaSeleccionada().setCoste(nuevoCoste);
        interfazGrafica.actualizarInfoTareaSeleccionada();
    }

    public void costeInicial(JTextField text) {
        try {
            this.costeTarea = Double.parseDouble(text.getText());
        }
        catch (NumberFormatException e) {
            interfazGrafica.ventanaError("No es un número");
        }
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public String getCorreoPersona() {
        return correoPersona;
    }

    public String getDniPersona() {
        return dniPersona;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public String getDescripcionTarea() {
        return descripcionTarea;
    }

    public double getCosteTarea() {
        return costeTarea;
    }

    public String getTexto() {
        return texto;
    }
}
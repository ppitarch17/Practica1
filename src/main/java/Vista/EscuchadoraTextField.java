package Vista;

import Controlador.Controlador;
import Excepciones.NoSePuedeInsertarException;
import Modelo.Etiqueta;
import Modelo.InterrogaModelo;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.Serializable;

public class EscuchadoraTextField implements ActionListener, FocusListener, Serializable {

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

    private Etiqueta etiqueta;

    public EscuchadoraTextField(Controlador controlador, InterfazGrafica interfazGrafica, InterrogaModelo modelo){
        this.controlador = controlador;
        System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
        this.modelo = modelo;
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
                case "seleccionarCoste" -> seleccionarCoste(text.getText());
                case "addEtiqueta" -> addEtiqueta(text.getText());
            }
        else this.texto = text.getText();
    }

    public void addEtiqueta(String texto){
        this.etiqueta = new Etiqueta(texto);

        if (interfazGrafica.getTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una Tarea");
            return;
        }

        if (texto.length() + getCantidadDeCaracteresEnListaDeEtiquetas(interfazGrafica.getTareaSeleccionada()) > 40){
            interfazGrafica.ventanaError("No se puede añadir una etiqueta tan larga");
            return;
        }

        try {
            if(!controlador.addEtiquetaATarea()){
                throw new NoSePuedeInsertarException();
            }
        }catch (NoSePuedeInsertarException e){
            interfazGrafica.ventanaError("No se puede repetir etiquetas");
        }


    }
    public Etiqueta getEtiqueta(){
        return this.etiqueta;
    }

    public void seleccionarCoste(String nuevoCoste){

        if (interfazGrafica.getTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una Tarea");
            return;
        }

        try {
            this.costeTarea = Double.parseDouble(nuevoCoste);
            this.controlador.cambiarCosteTarea();


        }catch (NumberFormatException e){
            interfazGrafica.ventanaError("No es un número");
        }

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

    public void setTexto(String string) {
        this.texto = string;
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField text = (JTextField) e.getSource();
        if (text.getName() != null)
            switch (text.getName()){
                case "dniDato" -> this.dniPersona = text.getText();
                case "correoDato" -> this.correoPersona = text.getText();
                case "nombreDato" -> this.nombrePersona = text.getText();
                case "nombreTareaDato" -> this.nombreTarea = text.getText();
                case "descripcionDato" -> this.descripcionTarea = text.getText();
                case "costeInicialDatos" -> costeInicial(text);
            }
        else this.texto = text.getText();
    }

    public void resetValues(){
        nombrePersona = null;
        correoPersona = null;
        dniPersona = null;
        texto = null;

        nombreTarea = null;
        descripcionTarea = null;
        costeTarea = 0;
    }

    int getCantidadDeCaracteresEnListaDeEtiquetas(Tarea tarea){
        int cantidad = 0;
        for (Etiqueta etiq: modelo.getEtiquetasTarea(tarea)) {
            cantidad += etiq.getNombre().length();
        }
        return cantidad;
    }
}

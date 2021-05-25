package Vista;

import Controlador.Controlador;
import Facturación.facturacion;
import Modelo.InterrogaModelo;
import Resultados.Resultado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraComboBox implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    private facturacion tipofacturacionTarea;
    private int prioridadTarea;
    private Resultado tipoResultadoTarea;

    public EscuchadoraComboBox(Controlador controlador, InterfazGrafica interfazGrafica){
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JComboBox comboBox = (JComboBox) e.getSource();
        System.out.println("ComboBox " + comboBox.getName() +": " + comboBox.getSelectedItem());

        switch (comboBox.getName()){
            case "facturacionDato" -> this.tipofacturacionTarea = (facturacion) comboBox.getSelectedItem();
            case "prioridadDato" -> {
                this.prioridadTarea = (int) comboBox.getSelectedItem();
                System.out.println("Entra prioridad");
            }
            case "resultadoDato" -> {
                this.tipoResultadoTarea = (Resultado) comboBox.getSelectedItem();
                System.out.println("Entra resultado");
            }
        }

    }

    public void tipofacturacion(String selectedItem){

        System.out.println("facturacion seleccionada: " + selectedItem);
        //System.out.println(prioridadTarea);
    }

    public void tipoResultado(String selectedItem){
        System.out.println("resultado seleccionada: " + selectedItem);
    }

    public facturacion getTipofacturacionTarea() {
        return tipofacturacionTarea;
    }

    public int getPrioridadTarea() {
        return prioridadTarea;
    }

    public Resultado getTipoResultadoTarea() {
        return tipoResultadoTarea;
    }
}

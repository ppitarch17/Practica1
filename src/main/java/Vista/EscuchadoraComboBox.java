package Vista;

import Controlador.Controlador;
import Facturación.ConsumoInterno;
import Facturación.facturacion;
import Modelo.InterrogaModelo;
import Resultados.Biblioteca;
import Resultados.Resultado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraComboBox implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    private facturacion tipofacturacionTarea = new ConsumoInterno();
    private int prioridadTarea = 1;
    private Resultado tipoResultadoTarea = new Biblioteca();

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
            case "facturacionTarea" -> facturacionTarea((facturacion) comboBox.getSelectedItem());
        }
    }

    public void facturacionTarea(facturacion nuevaFacturacion){
        interfazGrafica.getTareaSeleccionada().setFacturacion(nuevaFacturacion);
        interfazGrafica.actualizarInfoTareaSeleccionada();
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


    public void resetValues(){
        tipofacturacionTarea = new ConsumoInterno();
        prioridadTarea = 1;
        tipoResultadoTarea = new Biblioteca();
    }
}

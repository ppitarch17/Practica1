package Vista;

import Controlador.Controlador;
import Facturación.ConsumoInterno;
import Facturación.Descuento;
import Facturación.facturacion;
import Modelo.InterrogaModelo;
import Resultados.Biblioteca;
import Resultados.Resultado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class EscuchadoraComboBox implements ActionListener, Serializable {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    private facturacion tipofacturacionTarea = new ConsumoInterno();
    private int prioridadTarea = 1;
    private Resultado tipoResultadoTarea = new Biblioteca();

    public EscuchadoraComboBox(Controlador controlador, InterfazGrafica interfazGrafica, InterrogaModelo modelo){
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
        this.modelo = modelo;
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
        this.tipofacturacionTarea = nuevaFacturacion;

        if (interfazGrafica.getTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una Tarea");
            return;
        }

        controlador.cambiarFacturacionTarea();

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

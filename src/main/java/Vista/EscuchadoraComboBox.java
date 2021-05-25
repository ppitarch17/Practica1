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

    private facturacion TipofacturacionTarea;
    private int prioridadTarea;
    private Resultado tipoResultadoTarea;

    public EscuchadoraComboBox(Controlador controlador, InterfazGrafica interfazGrafica){
        this.controlador = controlador;
        this.interfazGrafica = interfazGrafica;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ComboBox");
        JComboBox comboBox = (JComboBox) e.getSource();

        switch (comboBox.getName()){
            case "facturacionDato" -> tipofacturacion((String) comboBox.getSelectedItem());
            case "prioridadDato" -> this.prioridadTarea = Integer.parseInt((String) comboBox.getSelectedItem());
            case "resultadoDato" -> tipoResultado((String) comboBox.getSelectedItem());
        }

    }

    public void tipofacturacion(String selectedItem){
        System.out.println(selectedItem);
        System.out.println(prioridadTarea);
    }

    public void tipoResultado(String selectedItem){
        System.out.println(selectedItem);
    }

    public facturacion getTipofacturacionTarea() {
        return TipofacturacionTarea;
    }

    public int getPrioridadTarea() {
        return prioridadTarea;
    }

    public Resultado getTipoResultadoTarea() {
        return tipoResultadoTarea;
    }
}

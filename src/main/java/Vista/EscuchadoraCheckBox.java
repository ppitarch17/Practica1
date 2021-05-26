package Vista;

import Facturación.facturacion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraCheckBox implements ActionListener {

    InterfazGrafica interfazGrafica;

    public EscuchadoraCheckBox(InterfazGrafica interfazGrafica){
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Checkbox checkbox = (Checkbox) e.getSource();

//        switch (checkbox.getName()){
//            case "facturacionDato" -> facturacionDato((facturacion) checkbox.getSelectedObjects();
//        }
    }

    public void facturacionDato(facturacion facturacion){
        //interfazGrafica.getTareaSeleccionada().setFacturacion();
    }

}

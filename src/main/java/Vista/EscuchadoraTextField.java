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

    public EscuchadoraTextField(Controlador controlador, InterfazGrafica interfazGrafica){
        this.controlador = controlador;
        System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField text = (JTextField) e.getSource();

        switch (text.getName()){
            case "dniDato" -> this.dniPersona = text.getText();
            case "correoDato" -> this.correoPersona = text.getText();
            case "nombreDato" -> this.nombrePersona = text.getText();
        }

        System.out.println(text.getName());
        System.out.println(text.getText());

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
}

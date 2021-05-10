package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;

import javax.swing.*;

public class InterfazGrafica implements InterrogaVista, InformaVista {
    //MVC
    private Controlador controlador;
    private InterrogaModelo modelo;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){ //Multiples Hilos
            @Override
            public void run(){
                new InterfazGrafica().ventana();
            }
        });
    }

    public void ventana(){
        JFrame ventana = new JFrame("Primera Ventana"); //Ventana principal

        //ventana.addWindowListener(new EscuchadoraVentana()); //para guardar el proyecto luego?
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//lo mismo que arriba pero easy

        JButton boton = new JButton("Crear Proyecto");

        boton.addActionListener(new EscuchadoraBoton());//Registro al boton
        ventana.getContentPane().add(boton);

        ventana.setSize(400,400);
        ventana.setVisible(true);
    }
}

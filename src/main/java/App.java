import Controlador.*;
import Modelo.InterrogaModelo;
import Vista.InformaVista;
import Vista.InterfazGrafica;
import Vista.InterrogaVista;

import javax.swing.*;

public class App {


    public static void main(String[] args) {

        Proyecto controlador = new Proyecto();
        InterfazGrafica interfaz = new InterfazGrafica(controlador);

        interfaz.setControlador(controlador);
        controlador.setInterrogaVista(interfaz);

    }
}

package Vista;

import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;

public interface InterrogaVista {
    //Metodos para Controlador
    Tarea getTareaSeleccionada();
    Persona getPersonaSeleccioanda();
    Persona getPersonaDeTareaSeleccionada();
    EscuchadoraTextField getEscuchadoraTextField();
    EscuchadoraComboBox getEscuchadoraComboBox();
//    public JList<Tarea> getTareas();
//    public JList<Persona> getPersonas();
}

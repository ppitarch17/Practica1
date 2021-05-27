package Vista;

import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;

public interface InterrogaVista {
    Tarea getTareaSeleccionada();
    Persona getPersonaSeleccioanda();
    Persona getPersonaDeTareaSeleccionada();
    EscuchadoraTextField getEscuchadoraTextField();
    EscuchadoraComboBox getEscuchadoraComboBox();
}

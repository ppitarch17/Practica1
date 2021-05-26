package Modelo;

import java.util.List;

public interface CambioModelo {
    //Metodos para Controlador
    //Vista.Interfaz en: Modelo.Persona y Modelo.Tarea
    void setTareasEnModelo(List<Tarea> tareas);
    void setPersonasEnModelo(List<Persona> personas);

    List<Tarea> getTareasEnModelo();
    List<Persona> getPersonasEnModelo();

}

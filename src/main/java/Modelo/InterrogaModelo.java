package Modelo;

import Controlador.Controlador;
import Facturación.facturacion;

public interface InterrogaModelo {
    //Metodos para Vista
    //void getTareaFinalizada();

    Persona[] getListaPersonasEnTarea(Tarea tarea);
    Persona[] getPersonasProyecto(Controlador controlador);
    Tarea[] getTareasDePersona(Persona persona);
    Persona getResponsable(Tarea tarea);
    String getTitulo(Tarea tarea);
    double getCosteFinalTarea(Tarea tarea);
    Etiqueta[] getEtiquetasTarea(Tarea tarea);
    boolean isTareaFinalizada(Tarea tarea);
    facturacion getFacturacionTarea(Tarea tarea);
    Persona[] getPersonasEnProyecto(Controlador controlador);
    Tarea[] getTareasEnProyecto(Controlador controlador);
}

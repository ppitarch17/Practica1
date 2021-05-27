package Modelo;

import Facturación.facturacion;
import Resultados.Resultado;

import java.util.List;

public interface InterrogaModelo {
    //Metodos del modelo para Vista
    //void getTareaFinalizada();

    Persona[] getListaPersonasEnTarea(Tarea tarea);
    Tarea[] getTareasDePersona(Persona persona);
    Persona getResponsable(Tarea tarea);
    String getTitulo(Tarea tarea);
    double getCosteFinalTarea(Tarea tarea);
    Etiqueta[] getEtiquetasTarea(Tarea tarea);
    boolean isTareaFinalizada(Tarea tarea);
    facturacion getFacturacionTarea(Tarea tarea);
    Persona[] getPersonasEnProyecto();
    Tarea[] getTareasEnProyecto();
    List<Tarea> getTareasEnModelo();
    List<Persona> getPersonasEnModelo();
    String getNombreProyecto();
    List<Persona> listarNoResponsables();
    double getCosteTotalProyecto();
    int getPrioridad(Tarea tarea);
    Resultado getResultado(Tarea tarea);
}

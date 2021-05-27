package Modelo;

import Facturación.facturacion;
import Resultados.Resultado;

import java.util.List;

public interface InterrogaModelo {

    Persona[] getListaPersonasEnTarea(Tarea tarea);
    Persona getResponsable(Tarea tarea);
    String getTitulo(Tarea tarea);
    double getCosteFinalTarea(Tarea tarea);
    Etiqueta[] getEtiquetasTarea(Tarea tarea);
    boolean isTareaFinalizada(Tarea tarea);
    facturacion getFacturacionTarea(Tarea tarea);
    Persona[] getPersonasEnProyecto();
    Tarea[] getTareasEnProyecto();
    List<Tarea> getTareasEnModelo();
    String getNombreProyecto();
    List<Persona> listarNoResponsables();
    double getCosteTotalProyecto();
    int getPrioridad(Tarea tarea);
    Resultado getResultado(Tarea tarea);
}

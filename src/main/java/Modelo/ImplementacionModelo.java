package Modelo;

import Controlador.Controlador;
import Facturación.facturacion;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo{

    Controlador controlador;
    //TODO actualizaciones

    public ImplementacionModelo(Controlador controlador){
        this.controlador = controlador;
    }

    //METODOS PARA CONTROLADOR

    //METODOS PARA VISTA
    @Override
    public Persona[] getListaPersonasEnTarea(Tarea tarea) {
        return tarea.getLista().toArray(new Persona[0]);
    }

    @Override
    public Tarea[] getTareasDePersona(Persona persona){
        return persona.getLista().toArray(new Tarea[0]);
    }

    @Override
    public Persona getResponsable(Tarea tarea) {
        return tarea.getResponsable();
    }

    @Override
    public String getTitulo(Tarea tarea) {
        return tarea.getTitulo();
    }

    @Override
    public double getCosteFinalTarea(Tarea tarea) {
        return tarea.getCosteFinal();
    }

    @Override
    public Etiqueta[] getEtiquetasTarea(Tarea tarea) {
        return tarea.getEtiquetas().toArray(new Etiqueta[0]);
    }

    @Override
    public boolean isTareaFinalizada(Tarea tarea) {
        return tarea.isFinalizada();
    }

    @Override
    public facturacion getFacturacionTarea(Tarea tarea) {
        return tarea.getFacturacion();
    }

    @Override
    public Persona[] getPersonasProyecto(Controlador controlador){
        return controlador.getListaPersonas().toArray(new Persona[0]);
    }

    @Override
    public Persona[] getPersonasEnProyecto(Controlador controlador){
        return controlador.getListaPersonas().toArray(new Persona[0]);
    }

    @Override
    public Tarea[] getTareasEnProyecto(Controlador controlador){
        return controlador.getListaTareas().toArray(new Tarea[0]);
    }



}

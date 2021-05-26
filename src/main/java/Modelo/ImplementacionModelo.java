package Modelo;

import Controlador.Controlador;
import Facturación.facturacion;
import Vista.InformaVista;
import Vista.InterfazGrafica;

import java.util.List;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo{

    private InformaVista vista;    //TODO actualizaciones

    List<Persona> personasEnProyecto;
    List<Tarea> tareasEnProyecto;

    public ImplementacionModelo(InformaVista vista){
        this.vista = vista;
    }

    //METODOS PARA CONTROLADOR

    @Override
    public void setTareasEnModelo(List<Tarea> tareas) {
        this.tareasEnProyecto = tareas;
    }

    @Override
    public void setPersonasEnModelo(List<Persona> personas) {
        this.personasEnProyecto = personas;
    }

    @Override
    public List<Tarea> getTareasEnModelo() {
        return tareasEnProyecto;
    }

    @Override
    public List<Persona> getPersonasEnModelo() {
        return personasEnProyecto;
    }

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
    public Persona[] getPersonasEnProyecto(){
        return personasEnProyecto.toArray(new Persona[0]);
    }

    @Override
    public Tarea[] getTareasEnProyecto(){
        return tareasEnProyecto.toArray(new Tarea[0]);
    }


}

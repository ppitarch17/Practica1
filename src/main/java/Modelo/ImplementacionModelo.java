package Modelo;

import Controlador.Controlador;
import Facturación.facturacion;
import Resultados.Resultado;
import Vista.InformaVista;
import Vista.InterrogaVista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImplementacionModelo implements CambioModelo, InterrogaModelo, Serializable {

    private List<Persona> listaPersonas = new ArrayList<>();
    private List<Tarea> listaTareas = new ArrayList<>();
    private String nombre;
    private double costeTotal = 0;

    InformaVista vista;

    public ImplementacionModelo(InformaVista vista) {
        this.vista = vista;
    }

    @Override
    public boolean addPersona(String nombre, String correo, String DNI) {
        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return false;
        }
        listaPersonas.add(new Persona(nombre,correo, DNI));
        vista.actualizarListasInterfaz();
        vista.resetValue();
        return true;
    }


    @Override
    public boolean addTarea(String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion) {
        for (Tarea tarea : listaTareas){
            if (tarea.getTitulo().equals(titulo))
                return false;
        }
        listaTareas.add(new Tarea(titulo, descripcion, prioridad, resultado, coste,facturacion));
        calcularCosteTotal();
        vista.actualizarListasInterfaz();
        vista.resetValue();
        return true;
    }


    @Override
    public boolean addPersonaToTarea(Persona persona, Tarea tarea) {
        if (!listaTareas.contains(tarea) || !listaPersonas.contains(persona))
            return false;
        tarea.addPersona(persona);
        persona.addTarea(tarea);
        vista.actualizarInfoTareaSeleccionada();
        return true;
    }

    @Override
    public boolean removePersonaDeTarea(Persona persona, Tarea tarea) {
        if(!tarea.getLista().contains(persona))
            return false;
        tarea.removePersona(persona);
        persona.removeTarea(tarea);
        vista.actualizarInfoTareaSeleccionada();
        return true;
    }

    @Override
    public void setTareaFinalizada(Tarea tarea) {
        tarea.marcarComoFinalizada();
        vista.actualizarInfoTareaSeleccionada();
    }

    @Override
    public void cambiarCosteTarea(Tarea tarea, double coste) {
        tarea.setCoste(coste);
        vista.actualizarInfoTareaSeleccionada();
    }

    @Override
    public void cambiarFacturacionTarea(Tarea tarea, facturacion facturacion) {
        tarea.setFacturacion(facturacion);
        tarea.calcularFacturacion();
        vista.actualizarInfoTareaSeleccionada();
    }

    @Override
    public boolean setResponsable(Tarea tarea, Persona persona) {
        if(tarea.setResponsable(persona)){
            vista.actualizarInfoTareaSeleccionada();
            vista.actualizarListasInterfaz();
            return true;
        }
        return false;
    }

    @Override
    public void calcularCosteTotal() {
        costeTotal = 0;
        for (Tarea tarea: listaTareas) {
            costeTotal += tarea.getCosteFinal();
        }
    }

    @Override
    public void setNombreProyecto(String nombreProyecto) {
        this.nombre = nombreProyecto;
    }

    @Override
    public boolean addEtiquetaATarea(Tarea tarea, Etiqueta etiqueta) {
        if(tarea.addEtiqueta(etiqueta)){
            vista.actualizarInfoTareaSeleccionada();
            return true;
        }
        return false;
    }

    @Override
    public void setVista(InformaVista vista) {
        this.vista = vista;
    }

    @Override
    public Persona[] getListaPersonasEnTarea(Tarea tarea) {
        return tarea.getLista().toArray(new Persona[0]);
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
        return tarea.getListaEtiquetas().toArray(new Etiqueta[0]);
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
    public Persona[] getPersonasEnProyecto() {
        return listaPersonas.toArray(new Persona[0]);
    }

    @Override
    public Tarea[] getTareasEnProyecto() {
        return listaTareas.toArray(new Tarea[0]);
    }

    @Override
    public List<Tarea> getTareasEnModelo() {
        return listaTareas;
    }

    @Override
    public String getNombreProyecto() {
        return nombre;
    }

    @Override
    public List<Persona> listarNoResponsables() {
        List<Persona> personas = new ArrayList<>(listaPersonas);
        for (Tarea tarea : listaTareas)
            personas.remove(tarea.getResponsable());
        return personas;
    }

    @Override
    public double getCosteTotalProyecto() {
        return costeTotal;
    }

    @Override
    public int getPrioridad(Tarea tarea) {
        return tarea.getPrioridad();
    }

    @Override
    public Resultado getResultado(Tarea tarea) {
        return tarea.getResultado();
    }
}

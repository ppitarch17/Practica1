package Controlador;

import Controlador.*;
import Facturación.*;
import Modelo.*;
import Vista.InterrogaVista;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proyecto implements Serializable, Controlador {
    private List<Persona> listaPersonas;
    private List<Tarea> listaTareas;
    private String nombre;
    private double costeTotal = 0;

    //MVC
    private InterrogaVista vista;
    private CambioModelo modelo;

    public Proyecto(){
        this.nombre = "";
        this.listaPersonas = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
    }

    public Proyecto(String nombre){
        this.nombre = nombre;
        this.listaPersonas = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
    }

    @Override
    public boolean addPersona(String nombre, String correo, String DNI){

        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return false;
        }
        listaPersonas.add(new Persona(nombre,correo, DNI));
        return true;
    }
    @Override
    public boolean addTarea(String titulo){

        for (Tarea tarea : listaTareas){
            if (tarea.getTitulo().equals(titulo))
                return false;
        }

        listaTareas.add(new Tarea(titulo));
        calcularCosteTotal();
        return true;
    }

    @Override
    public boolean addPersonaToTarea(Persona persona, Tarea tarea){

        if (!listaTareas.contains(tarea) || !listaPersonas.contains(persona))
            return false;

        tarea.addPersona(persona);
        persona.addTarea(tarea);
        return true;
    }

    @Override
    public boolean removePersonaDeTarea (Persona persona, Tarea tarea){

        if(!tarea.getLista().contains(persona))
            return false;

        tarea.removePersona(persona);
        persona.removeTarea(tarea);
        return true;
    }

    @Override
    public Persona getPersona(String DNI){
        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return persona;
        }
        return null;
    }

    @Override
    public void setTareaFinalizada(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

    @Override
    public Tarea buscaTarea (String titulo) {
        for (Tarea tarea : getListaTareas()) {
            if (tarea.getTitulo().equals(titulo)) {
                return tarea;
            }
        }

        return null;
    }

    public void calcularCosteTotal(){
        costeTotal = 0;
        for (Tarea tarea: listaTareas) {
            costeTotal += tarea.getCosteFinal();
        }
    }

    @Override
    public void cambiarCosteTarea(Tarea tarea, double coste){
        tarea.setCoste(coste);
        calcularCosteTotal();
    }

    @Override
    public void cambiarFacturacionTarea(Tarea tarea, facturacion facturacion) {
        tarea.setFacturacion(facturacion);
        calcularCosteTotal();
    }

    @Override
    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    @Override
    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public double getCosteTotal() {
        return costeTotal;
    }

    @Override
    public boolean setResponsable(Tarea tarea, Persona persona) {
        return tarea.setResponsable(persona);
    }

    @Override
    public void setInterrogaVista(InterrogaVista vista) {
        this.vista = vista;
    }
}

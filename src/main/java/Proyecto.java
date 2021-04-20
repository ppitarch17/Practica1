import Excepciones.NoSePuedeInsertarException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Proyecto implements Serializable {
    private List<Persona> listaPersonas;
    private List<Tarea> listaTareas;
    private String nombre;
    private double costeTotal = 0;

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

    public boolean addPersona(String nombre, String correo, String DNI){

        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return false;
        }
        listaPersonas.add(new Persona(nombre,correo, DNI));
        return true;
    }

    public boolean addTarea(String titulo){

        for (Tarea tarea : listaTareas){
            if (tarea.getTitulo().equals(titulo))
                return false;
        }

        listaTareas.add(new Tarea(titulo));
        calcularCosteTotal();
        return true;
    }

    public boolean addPersonaToTarea(Persona persona, Tarea tarea){

        if (!listaTareas.contains(tarea) || !listaPersonas.contains(persona))
            return false;

        tarea.addPersona(persona);
        persona.addTarea(tarea);
        return true;
    }

    public boolean removePersonaDeTarea (Persona persona, Tarea tarea){

        if(!tarea.getLista().contains(persona))
            return false;

        tarea.removePersona(persona);
        persona.removeTarea(tarea);
        return true;
    }

    public Persona getPersona(String DNI){
        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return persona;
        }
        return null;
    }

    public void setTareaFinalizada(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

    public Tarea buscaTarea (String titulo) {
        for (Tarea tarea : getListaTareas()) {
            if (tarea.getTitulo().equals(titulo)) {
                return tarea;
            }
        }
        return null;
    }

    public void calcularCosteTotal(){
        for (Tarea tarea: listaTareas) {
            costeTotal += tarea.getCoste();
        }
    }
    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCosteTotal() {
        return costeTotal;
    }
}

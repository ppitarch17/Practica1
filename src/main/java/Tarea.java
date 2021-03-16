import Resultados.Resultado;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
    String titulo;
    String descripcion;
    List<Persona> listaPersonas;
    Persona responsable;
    int prioridad;
    String fechaCreado;
    String fechaFin; // (puede estar en blanco)
    boolean isFinalizada;
    Resultado resultado;
    List<Etiqueta> listaEtiquetas;

    String identificador;

    //-----CONSTRUCTORES-----
    public Tarea(String titulo) {
        this.titulo = titulo;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        //set fecha creacion
    }

    public Tarea(){}

    //-----METODOS-----
    public void marcarComoFinalizada(){
        isFinalizada = true;
        //set fecha fin
    }
    public void addPersona(Persona persona) {
        listaPersonas.add(persona);
    }
    public void removePersona(Persona persona) {
        listaPersonas.remove(persona);
    }
    public void addEtiqueta(Etiqueta etiqueta) {
        listaEtiquetas.add(etiqueta);
    }
    //-----GETTERS-----


    //-----SETTERS-----
    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(int prioridad) throws Exception {
        if (prioridad < 1 || prioridad > 5) //exception?
            throw new Exception("La p");
        this.prioridad = prioridad;
    }
}

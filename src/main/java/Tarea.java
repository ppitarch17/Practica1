import Resultados.Resultado;

import java.util.ArrayList;
import java.util.List;

public class Tarea {
    private String titulo;
    private String descripcion;
    private List<Persona> listaPersonas;
    private Persona responsable;
    private int prioridad;
    private String fechaCreado;
    private String fechaFin; // (puede estar en blanco)
    private boolean isFinalizada;
    private Resultado resultado;
    private List<Etiqueta> listaEtiquetas;

    //-----CONSTRUCTORES-----
    public  Tarea(){
        //titulo = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        prioridad = 1;
    }

    public Tarea(String titulo) {
        this.titulo = titulo;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        //inicializo la prioridad a 1 para que no sea 0 (no es valido)
        prioridad = 1;
        //set fecha creacion
    }


    //-----METODOS-----
    public void marcarComoFinalizada(){ //Fecha fin es opcional

        //si ya esta finalizada?

        isFinalizada = true;
    }

    public void marcarComoFinalizada(String fechaFin){
        isFinalizada = true;
        this.fechaFin = fechaFin;
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

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public Persona getResponsable() {
        return responsable;
    }

    //-----SETTERS-----
    public void setResponsable(Persona responsable) {

        //Si el responsable no esta en la lista de la tarea lo añadimos
        if(!listaPersonas.contains(responsable))
            listaPersonas.add(responsable);

        this.responsable = responsable;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(int prioridad) throws Exception {
        if (prioridad < 1 || prioridad > 5) //exception?
            throw new IllegalArgumentException();
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", listaPersonas=" + listaPersonas +
                ", responsable =" + responsable +
                ", isFinalizada=" + isFinalizada +
                ", resultado=" + resultado +
                '}';
    }
}

import Resultados.Resultado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tarea {
    private String titulo;
    private String descripcion;
    private List<Persona> listaPersonas;
    private Persona responsable = null;
    private int prioridad;
    private LocalDate fechaCreado;
    private LocalDate fechaFin; // opcional
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
        fechaCreado = LocalDate.now();
    }


    //-----METODOS-----
    public void marcarComoFinalizada(){ //Fecha fin es opcional
        isFinalizada = true;
    }

    public void marcarComoFinalizada(LocalDate fechaFin){
        isFinalizada = true;
        this.fechaFin = fechaFin;
    }

    public boolean addPersona(Persona persona) {

        if(listaPersonas.contains(persona))
            return false;

        return listaPersonas.add(persona);
    }

    public boolean removePersona(Persona persona) {
        return listaPersonas.remove(persona);
    }

    public boolean addEtiqueta(Etiqueta etiqueta) {

        if(listaEtiquetas.contains(etiqueta))
            return false;

        return listaEtiquetas.add(etiqueta);
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
    public boolean setResponsable(Persona responsable) {

        //El responsable debe estar en ListaPersonas
        if(!listaPersonas.contains(responsable))
            return false;

        this.responsable = responsable;
        return true;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(int prioridad){
        if (prioridad < 1 || prioridad > 5)
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

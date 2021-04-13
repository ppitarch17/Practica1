import Resultados.Resultado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tarea implements tieneLista<Persona>, tieneClave<String> {
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
        titulo = "";
        this.resultado = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        prioridad = 1;
        fechaCreado = LocalDate.now();
    }

    public Tarea(String titulo) {
        this.titulo = titulo;
        this.resultado = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        prioridad = 1; //inicializo la prioridad a 1 para que no sea 0 (no es valido)
        fechaCreado = LocalDate.now();
    }


    //-----GETTERS-----

    public String getTitulo() {
        return titulo;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public Persona getPersona(String dni){
        for (Persona persona : listaPersonas) {
            if (persona.getDNI().equals(dni))
                return persona;

        }
        return null;
    }

    //-----SETTERS-----
    public boolean setResponsable(Persona responsable) {
        if(!listaPersonas.contains(responsable))
            return false;

        this.responsable = responsable;
        responsable.addResponsabilidad(this);
        return true;
    }

    public void setPrioridad(int prioridad){
        if (prioridad < 1 || prioridad > 5)
            throw new IllegalArgumentException();
        this.prioridad = prioridad;
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

        if(listaPersonas.contains(persona) || persona == null)
            return false;

        if (listaPersonas.isEmpty())
            this.responsable = persona;

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

    public boolean containsEtiqueta(String etiqueta) {
        for (Etiqueta etiq : listaEtiquetas){
            if (etiqueta.equals(etiq.getNombre()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return  titulo + "{" +
                ", listaPersonas=" + listaPersonas +
                ", responsable =" + responsable +
                ", isFinalizada=" + isFinalizada +
                ", resultado=" + resultado +
                ", etiquetas=" + listaEtiquetas +
                '}';
    }

    @Override
    public List getLista() {
        return listaPersonas;
    }

    @Override
    public String getClave() {
        return titulo;
    }
}

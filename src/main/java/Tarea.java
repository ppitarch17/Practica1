import Excepciones.FechaNoValidaException;
import Excepciones.NoSePuedeInsertarException;
import Resultados.Resultado;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tarea implements tieneLista<Persona>, tieneClave<String>, Serializable {
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
        try {

            if (prioridad < 1 || prioridad > 5)
                throw new IllegalArgumentException("La prioridad " + prioridad + " no es valida. Debe estar entre 1 y 5");

            this.prioridad = prioridad;

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    //-----METODOS-----
    public void marcarComoFinalizada(){ //Fecha fin es opcional
        isFinalizada = true;
    }

    public void marcarComoFinalizada(LocalDate fechaFin){

        try{
            if(fechaFin.isBefore(fechaCreado))
                throw new FechaNoValidaException
                        ("La fecha de creacion " + fechaCreado + " no puede ser posterior a la fecha de finalización: " + fechaFin);

            isFinalizada = true;
            this.fechaFin = fechaFin;

        } catch (FechaNoValidaException e) {
            e.printStackTrace();
        }

    }

    public boolean addPersona(Persona persona) {

        try {
            if(persona == null)
                throw new NullPointerException("La persona no puede ser " + persona);
            if(!UtilidadesParaListas.sePuedeInsertar(persona, this))
                throw new NoSePuedeInsertarException("La persona " + persona + " ya esta en la tarea " + this.toString());

            return listaPersonas.add(persona);
        } catch (NullPointerException | NoSePuedeInsertarException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean removePersona(Persona persona) {
        return listaPersonas.remove(persona);
    }

    public boolean addEtiqueta(Etiqueta etiqueta) {

        try {
            if(etiqueta ==  null)
                throw new NullPointerException("La etiqueta no puede ser " + etiqueta);

            for (Etiqueta etiquetaEnLista : listaEtiquetas)
                if (etiquetaEnLista.getNombre().equals(etiqueta.getNombre()))
                    throw new NoSePuedeInsertarException("La etiqueta " + etiqueta.getNombre() + " ya esta en la tarea " + this.toString());

            return listaEtiquetas.add(etiqueta);

        } catch (NullPointerException | NoSePuedeInsertarException e){
            e.printStackTrace();
        }

        return false;

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
    public List<Persona> getLista() {
        return listaPersonas;
    }

    @Override
    public String getClave() {
        return titulo;
    }

    public List<Etiqueta> getListaEtiquetas() {
        return listaEtiquetas;
    }
}

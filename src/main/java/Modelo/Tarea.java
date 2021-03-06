package Modelo;

import Excepciones.FechaNoValidaException;
import Excepciones.NoSePuedeInsertarException;
import Facturación.facturacion;
import Resultados.Resultado;
import Vista.InformaVista;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tarea implements tieneLista<Persona>, tieneClave<String>, Serializable {
    private String titulo;
    private String descripcion;
    private List<Persona> listaPersonas;
    private Persona responsable = null;
    private int prioridad = 1;
    private LocalDate fechaCreado;
    private LocalDate fechaFin; // opcional
    private boolean isFinalizada;
    private Resultado resultado;
    private List<Etiqueta> listaEtiquetas;
    private double coste = 0;
    private double costeFinal = 0;
    private facturacion facturacion;

    //-----CONSTRUCTORES-----
    public  Tarea(){
        titulo = "";
        this.resultado = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        fechaCreado = LocalDate.now();
    }

    public Tarea(String titulo) {
        this.titulo = titulo;
        this.resultado = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        fechaCreado = LocalDate.now();
    }

    public Tarea(String titulo, facturacion facturacion) {
        this.titulo = titulo;
        this.resultado = null;
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
        fechaCreado = LocalDate.now();
    }

    public Tarea(String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.resultado = resultado;
        this.coste = coste;
        setFacturacion(facturacion);
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
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

    public double getCoste() {
        return coste;
    }

    //-----SETTERS-----

    public boolean setResponsable(Persona responsable) {
        if(!listaPersonas.contains(responsable))
            return false;

        if (responsable != null)
            responsable.removeResponsabilidad(this);

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

    public void setCoste(double coste) {
        this.coste = coste;
        calcularFacturacion();
    }

    //-----METODOS-----

    public void marcarComoFinalizada(){ //Fecha fin es opcional
        isFinalizada = true;
    }

    public void marcarComoFinalizada(LocalDate fechaFin) {

        try {
            if (fechaFin.isBefore(fechaCreado))
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
                    return false;//throw new NoSePuedeInsertarException("La etiqueta " + etiqueta.getNombre() + " ya esta en la tarea " + this.toString());

            return listaEtiquetas.add(etiqueta);
        } catch (NullPointerException e){
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

    public void setFacturacion(Facturación.facturacion facturacion){
        this.facturacion = facturacion;
        calcularFacturacion();
    }

    public boolean calcularFacturacion(){
        try{
            if (this.facturacion == null)
                throw new NullPointerException("Se debe asignar un tipo de Facturación.facturacion antes de calcular el coste final");
            costeFinal = facturacion.costeFacturacion(this.coste);
            return true;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return  titulo;
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

    public double getCosteFinal() {
        return costeFinal;
    }

    public boolean isFinalizada() {
        return isFinalizada;
    }

    public Facturación.facturacion getFacturacion() {
        return facturacion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public Resultado getResultado() {
        return resultado;
    }
}

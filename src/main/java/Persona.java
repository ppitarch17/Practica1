import Excepciones.NoSePuedeInsertarException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Persona implements tieneLista<Tarea>, tieneClave<String>, Serializable {
    private String nombre;
    private String correo;
    private String DNI; //identificador
    private List<Tarea> listaTareas;
    private List<Tarea> tareasResponsable = new ArrayList<>();

    //-----CONSTRUCTORES-----

    public Persona() {
        this.nombre = "";
        this.correo = "";
        this.DNI = "";
        listaTareas = new ArrayList<>();
    }

    public Persona(String nombre, String correo, String DNI) {
        this.nombre = nombre;
        this.correo = correo;
        this.DNI = DNI;
        listaTareas = new ArrayList<>();
    }

    //-----GETTERS-----
    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public String getDNI() {
        return DNI;
    }
    public List<Tarea> getTareasResponsable() {return tareasResponsable;}

    //-----METODOS-----
    public boolean addTarea(Tarea tarea){

        try {
            if (tarea == null)
                throw new NullPointerException("La tarea no puede ser " + tarea);

            if (!UtilidadesParaListas.sePuedeInsertar(tarea, this))
                throw new NoSePuedeInsertarException("La tarea " + tarea + " ya esta en la lista de " + this.toString());

            return listaTareas.add(tarea);

        } catch (NullPointerException | NoSePuedeInsertarException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean addResponsabilidad (Tarea tarea) {

        //falta comprobar

        return tareasResponsable.add(tarea);
    }

    public boolean removeTarea(Tarea tarea){
        return listaTareas.remove(tarea);
    }

    @Override
    public String toString() {
        return DNI + ": " + nombre;
    }

    @Override
    public List<Tarea> getLista() {
        return listaTareas;
    }

    @Override
    public String getClave() {
        return DNI;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Persona implements tieneLista<Tarea>, tieneClave<String> {
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
    public List<Tarea> getListaTareas(){return listaTareas;}
    public String getDNI() {
        return DNI;
    }
    public List getTareasResponsable() {return tareasResponsable;}

    //-----METODOS-----
    public boolean addTarea(Tarea tarea){

        if(!UtilidadesParaListas.sePuedeInsertar(getLista(), tarea))
            return false;

       return listaTareas.add(tarea);
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
    public List getLista() {
        return listaTareas;
    }

    @Override
    public String getClave() {
        return DNI;
    }
}

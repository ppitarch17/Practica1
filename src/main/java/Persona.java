import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private String DNI; //identificador
    private List<Tarea> listaTareas;

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


    //-----METODOS-----
    public boolean addTarea(Tarea tarea){

        if(listaTareas.contains(tarea) || tarea == null)
            return false;

       return listaTareas.add(tarea);
    }

    public boolean removeTarea(Tarea tarea){
        return listaTareas.remove(tarea);
    }

    @Override
    public String toString() {
        return DNI + ": " + nombre;
    }
}

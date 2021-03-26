import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private String DNI; //identificador
    private List<Tarea> listaTareas;

    public Persona(String nombre, String correo, String DNI) {
        this.nombre = nombre;
        this.correo = correo;
        this.DNI = DNI;
        listaTareas = new ArrayList<>();
    }

    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public List<Tarea> getListaTareas(){return listaTareas;}
    public String getDNI() {
        return DNI;
    }

    public boolean addTarea(Tarea tarea){

        if(listaTareas.contains(tarea))
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

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private String DNI; //identificador
    private List<Tarea> listaTareas; //tareas de las que es responsable esta persona

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

    public void addTarea(Tarea tarea){
        listaTareas.add(tarea);
    }

    public void removeTarea(Tarea tarea){
        listaTareas.remove(tarea);
    }

    @Override
    public String toString() {
        return DNI + " " + nombre;
    }
}

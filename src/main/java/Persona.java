import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private String DNI; //identificador
    private List<Tarea> tareas; //tareas de las que es responsable esta persona

    public Persona(String nombre, String correo, String DNI) {
        this.nombre = nombre;
        this.correo = correo;
        this.DNI = DNI;
        tareas = new ArrayList<>();
    }

    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public List<Tarea> getTareas(){return tareas;}

    public void addTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void removeTarea(Tarea tarea){
        tareas.remove(tarea);
    }

    @Override
    public String toString() {
        return DNI + " " + nombre;
    }
}

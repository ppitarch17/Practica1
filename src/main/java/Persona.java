import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private List<Tarea> tareas; //tareas de las que es responsable esta persona

    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
        tareas = new ArrayList<>();
        //hola
    }

    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public List<Tarea> getTareas(){return tareas;}

    //fuction add persona
}

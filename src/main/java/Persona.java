import java.util.List;

public class Persona {
    private String nombre;
    private String correo;
    private List<Tarea> tareas;

    public Persona(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getNombre(){return nombre;}
    public String getCorreo(){return correo;}
    public List<Tarea> getTareas(){return tareas;}
}

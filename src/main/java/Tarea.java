import java.util.List;

public class Tarea {
    String titulo;
    String descripcion; //permite NULL
    List<Persona> listaPersonas;
    Persona responsable;
    int prioridad;
    String fechaCreado;

    String fechaFin;
    boolean isFinalizada;
    Resultado resultado;
    List<Etiqueta> listaEtiquetas;

    String identificador;

    public Tarea(String titulo) {
        this.titulo = titulo;
        //set fecha creacion
    }
    public void marcarComoFinalizada(){
        isFinalizada = true;
        //set fecha fin
    }
    public void addPersona(Persona persona) {
        listaPersonas.add(persona);
    }
    public void removePersona(Persona persona) {
        listaPersonas.remove(persona);
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
}

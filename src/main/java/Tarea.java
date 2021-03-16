import java.util.ArrayList;
import java.util.List;

public class Tarea {
    String titulo;
    String descripcion;
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
        listaEtiquetas = new ArrayList<>();
        listaPersonas = new ArrayList<>();
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
        if (prioridad < 1 || prioridad > 5) //exception?
            return;
        this.prioridad = prioridad;
    }
    public void addEtiqueta(Etiqueta etiqueta) {
        listaEtiquetas.add(etiqueta);
    }
}

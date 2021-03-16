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
    }
    public void marcarComoFinalizada(){
        isFinalizada = true;
    }
    public void addPersona(Persona persona) {
        listaPersonas.add(persona);
    }
    public void removePersona(Persona persona) {
        listaPersonas.remove(persona);
    }
}

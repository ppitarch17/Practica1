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
    }
    public void marcarComoFinalizada(){
        isFinalizada = true;
    }
    public void anyadirPersona(Persona persona) {
        listaPersonas.add(persona);
    }
    public void quitarPersona(Persona persona) {
        listaPersonas.remove(persona);
    }
    public void addEtiqueta(Etiqueta etiqueta) {
        listaEtiquetas.add(etiqueta);
    }
}

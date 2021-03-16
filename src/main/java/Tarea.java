import java.util.List;
//otro test
//otro coment
//otro otro comentario
//otroooooo comentario


//oollaaa

//adios

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
    public void anyadirPersona(Persona persona) {
        listaPersonas.add(persona);
    }
    public void quitarPersona(Persona persona) {
        listaPersonas.remove(persona);
    }
}

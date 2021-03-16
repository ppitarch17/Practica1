import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    List<Persona> listaPersonas;
    List<Tarea> listaTareas;
    String nombre;




    public Proyecto(String nombre){
        this.nombre = nombre;
        this.listaPersonas = new ArrayList<>();
        this.listaTareas = new ArrayList<>();
    }

    public void newPersona(String nombre, String correo){
        listaPersonas.add(new Persona(nombre,correo));
    }
    public void newTarea(String titulo){
        listaTareas.add(new Tarea(titulo));
    }
    public void finalizada(Tarea tarea){
        tarea.marcarComoFinalizada();
    }
    public void addPersona (Persona persona, Tarea tarea){
        tarea.anyadirPersona(persona);
    }
    public void removePersona (Persona persona, Tarea tarea){
        tarea.quitarPersona(persona);
    }
    public void imprimirPersonas(){
        Interfaz.imprimirPersonas(listaPersonas);
    }
    public void imprimirTareas(){
        Interfaz.imprimirTareas(listaTareas);
    }
}

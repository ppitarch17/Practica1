import java.util.List;

public class Proyecto {
    List<Persona> listaPersonas;
    List<Tarea> listaTareas;




    public Proyecto(String nombre){

    }
    public void newPersona(String nombre, String correo){
        Persona persona = new Persona(nombre,correo);
        listaPersonas.add(persona);
    }
    public void newTarea(String titulo){
        Tarea tarea = new Tarea(titulo);
        listaTareas.add(tarea);
    }
    public void finalizada(Tarea tarea){

    }
    public void addPersona (Persona persona, Tarea tarea){

    }
    public void removePersona (Persona persona, Tarea tarea){

    }
    public void imprimirPersonas(){
        Interfaz.imprimirPersonas(listaPersonas);
    }
    public void imprimirTareas(){
        Interfaz.imprimirTareas(listaTareas);
    }
}

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

    public Proyecto(String nombre, List<Persona> listaPersonas, List<Tarea> listaTareas){
        this.nombre = nombre;
        this.listaPersonas = listaPersonas;
        this.listaTareas = listaTareas;
    }

    public void newPersona(String nombre, String correo, String DNI){
        listaPersonas.add(new Persona(nombre,correo, DNI));
    }
    public void newTarea(String titulo){
        listaTareas.add(new Tarea(titulo));
    }

    public void finalizada(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

    public void addPersona (Persona persona, Tarea tarea){

        //Que pasa si nos dan una tarea que no esta en el proyecto?

        if (!listaTareas.contains(tarea))
            listaTareas.add(tarea);

        //que pasa si nos dan una persona que no esta en el proyecto?
        if(!listaPersonas.contains(persona))
            listaPersonas.add(persona);

        //que pasa si esa persona ya pertenece a esa tarea?
        if (tarea.listaPersonas.contains(persona))
            return;

        tarea.addPersona(persona);
        persona.addTarea(tarea);
    }
    public void removePersona (Persona persona, Tarea tarea){
        tarea.removePersona(persona);
        persona.removeTarea(tarea);
    }
    public void imprimirPersonas(){
        Interfaz.imprimirPersonas(listaPersonas);
    }
    public void imprimirTareas(){
        Interfaz.imprimirTareas(listaTareas);
    }
}

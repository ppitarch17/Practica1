import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private List<Persona> listaPersonas;
    private List<Tarea> listaTareas;
    private String nombre;

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

    public void addPersona(String nombre, String correo, String DNI){
        listaPersonas.add(new Persona(nombre,correo, DNI));
    }

    public void addTarea(String titulo){
        listaTareas.add(new Tarea(titulo));
    }

    public void setTareaComofinalizada(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

    public boolean addPersonaToTarea(Persona persona, Tarea tarea){

        //Si la tarea o la persona no estan en el proyecto return false.
        //Si la persona ya esta en esa tarea return false.
        if (!listaTareas.contains(tarea) || !listaPersonas.contains(persona) || tarea.getListaPersonas().contains(persona))
            return false;

        tarea.addPersona(persona);
        persona.addTarea(tarea);
        return true;
    }

    public boolean removePersonaDeTarea (Persona persona, Tarea tarea){

        if(!tarea.getListaPersonas().contains(persona))
            return false;

        tarea.removePersona(persona);
        persona.removeTarea(tarea);
        return true;
    }

  //  public boolean removeTareadePersona(Persona persona, Tarea tarea){

    //}

    public void removePersona (Persona persona){
        //elimino persona de la lista del proyecto
        listaPersonas.remove(persona);
        //elimino a la persona de todas las tareas en las que aparece
        for (Tarea tarea : listaTareas) {
            if (tarea.getListaPersonas().contains(persona))
                tarea.removePersona(persona);
        }
    }

    public void removeTarea(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

    public void imprimirPersonas(){
        Interfaz.imprimirPersonas(listaPersonas);
    }
    public void imprimirTareas(){
        Interfaz.imprimirTareas(listaTareas);
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public String getNombre() {
        return nombre;
    }
}

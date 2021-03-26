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

//    public Proyecto(String nombre, List<Persona> listaPersonas, List<Tarea> listaTareas){
//        this.nombre = nombre;
//        this.listaPersonas = listaPersonas;
//        this.listaTareas = listaTareas;
//    }

    public boolean addPersona(String nombre, String correo, String DNI){

        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return false;
        }
        listaPersonas.add(new Persona(nombre,correo, DNI));
        return true;
    }

    public boolean addTarea(String titulo){

        for (Tarea tarea : listaTareas){
            if (tarea.getTitulo().equals(titulo))
                return false;
        }

        listaTareas.add(new Tarea(titulo));
        return true;
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

    public Persona getPersona(String DNI){
        for (Persona persona: listaPersonas) {
            if (persona.getDNI().equals(DNI))
                return persona;
        }
        return null;
    }

    public void removePersona (Persona persona){
        //elimino persona de la lista del proyecto
        listaPersonas.remove(persona);
        //elimino a la persona de todas las tareas en las que aparece
        for (Tarea tarea : listaTareas) {
            //if (tarea.getListaPersonas().contains(persona))
            tarea.removePersona(persona);

        }
    }

    public void removeTarea(Tarea tarea){
        tarea.marcarComoFinalizada();
    }

/*    public void imprimirPersonas(){
        Interfaz.imprimirPersonas(listaPersonas);
    }
    public void imprimirTareas(){
        Interfaz.imprimirTareas(listaTareas);
    }
*/

    public Tarea buscaTarea (String titulo) {
        for (Tarea tar : getListaTareas()) {
            if (tar.getTitulo().equals(titulo)) {
                return tar;
            }
        }
        return null;
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

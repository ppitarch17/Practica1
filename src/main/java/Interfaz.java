import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

    public static Proyecto crearProyecto(){
        String nombre = scanStr("Introduce el nombre del proyecto: ");
        return new Proyecto(nombre);
    }

    public static void main(String[] args) {
        Proyecto proyecto = crearProyecto();
        showMenu(proyecto);
    }

    public static void showMenu(Proyecto proyecto){

        System.out.println("0 Finalizar programa");
        System.out.println("1 Crear Persona");
        System.out.println("2 Crear Tarea");
        System.out.println("3 añadir persona a tarea");
        System.out.println("4 quitar persona de tarea");
        System.out.println("5 finalizar tarea");
        System.out.println("6 listar personas");
        System.out.println("7 listar tareas");
        System.out.println("8 añadir etiqueta");
        System.out.println("9 modificar responsable");

        int op = scanInt("Selecciona una Opcion: ");
        switch (op) {
            case 0 -> System.out.println("Fin del Programa");
            case 1 -> crearPersona(proyecto);
            case 2 -> crearTarea(proyecto);
            case 3 -> addPersonaATarea(proyecto);
            case 4 -> removePersonaDeTarea(proyecto);
            case 5 -> finalizarTarea(proyecto);
            case 6 -> listarPersonas(proyecto);
            case 7 -> listarTareas(proyecto);
            case 8 -> añadirEtiqueta(proyecto);
            case 9 -> setResponsable(proyecto);
            default -> error(proyecto, "Opción no válida.");
        }
    }
    public static String scanStr(String mensaje){
        System.out.print(mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    public static int scanInt(String mensaje){
        System.out.print(mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static void crearPersona(Proyecto proyecto){
        String nombre = scanStr("Introduce el nombre de la persona: ");
        String correo = scanStr("Introduce el correo de la persona: ");
        String dni = scanStr("Introduce el DNI de la persona: ");

        proyecto.addPersona(nombre, correo, dni);
        showMenu(proyecto);
    }

    public static void crearTarea(Proyecto proyecto){
        String titulo = scanStr("Introduce el titulo de la tarea: ");

        proyecto.addTarea(titulo);
        showMenu(proyecto);
    }

    public static void addPersonaATarea(Proyecto proyecto){
        String dni = scanStr("Introduce el DNI de la persona: ");

        Persona persona = proyecto.getPersona(dni);

        String titulo = scanStr("Introduce el titulo de la tarea: ");

        Tarea tarea = proyecto.buscaTarea(titulo);

        if(persona == null || tarea == null)
            error(proyecto, "Persona o tarea no encontrada");
        else
            proyecto.addPersonaToTarea(persona, tarea);

        showMenu(proyecto);
    }

    public static void removePersonaDeTarea(Proyecto proyecto) {
        String dni = scanStr("Introduce el DNI de la persona: ");
        Persona persona = proyecto.getPersona(dni);
        String titulo = scanStr("Introduce el titulo de la tarea: ");
        Tarea tarea = proyecto.buscaTarea(titulo);
        if(persona == null || tarea == null)
            error(proyecto, "Persona o tarea no encontrada");
        else
            proyecto.removePersonaDeTarea(persona, tarea);
        showMenu(proyecto);
    }

    public static void finalizarTarea(Proyecto proyecto) {
        String tarea = scanStr("Indica la tarea: ");
        proyecto.buscaTarea(tarea).marcarComoFinalizada();
        showMenu(proyecto);
    }
    public static void listarPersonas (Proyecto proyecto) {
        System.out.println("Personas en \"" + proyecto.getNombre() + "\": " + proyecto.getListaPersonas().toString());
        showMenu(proyecto);
    }
    public static void listarTareas (Proyecto proyecto) {
        System.out.println("Tareas de \"" + proyecto.getNombre() + "\": " + proyecto.getListaTareas().toString());
        showMenu(proyecto);
    }

    public static void  añadirEtiqueta(Proyecto proyecto) {
        String nombre = scanStr("Indica la tarea: ");
        Tarea tarea = proyecto.buscaTarea(nombre);
        if (tarea == null)
            error(proyecto, "No existe esa tarea");
        else {
            String etiqueta = scanStr("Indica el nombre de la etiqueta: ");
            if (tarea.containsEtiqueta(etiqueta))
                error(proyecto, "La tarea ya tiene esa etiqueta");
            tarea.addEtiqueta(new Etiqueta(etiqueta));
            showMenu(proyecto);
        }
    }

    public static void setResponsable(Proyecto proyecto){
        Tarea tarea = proyecto.buscaTarea(scanStr("Dame el titulo de la tarea: "));

        if (tarea == null)
            error(proyecto, "Tarea no encontrada");

        Persona persona = tarea.getPersona(scanStr("Dame el dni de la persona: "));

        if (persona == null)
            error(proyecto, "Persona no encontrada");

        if(!tarea.setResponsable(persona))
            error(proyecto,"No se pudo cambiar de responsable");

        showMenu(proyecto);
    }


    public static void error(Proyecto proyecto, String mensaje){
        System.out.println(mensaje);
        showMenu(proyecto);
    }

}

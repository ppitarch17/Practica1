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
        while(true){
            showMenu(proyecto);

            int op = scanInt("Selecciona una Opcion: ");

            if (op == 0){
                System.out.println("Fin del Programa");
                break;
            }

            switch (op) {
                case 1 -> crearPersona(proyecto);
                case 2 -> crearTarea(proyecto);
                case 3 -> addPersonaATarea(proyecto);
                case 4 -> removePersonaDeTarea(proyecto);
                case 5 -> finalizarTarea(proyecto);
                case 6 -> listarPersonas(proyecto);
                case 7 -> listarTareas(proyecto);
                case 8 -> añadirEtiqueta(proyecto);
                case 9 -> setResponsable(proyecto);
                default -> error("Opción no válida.");
            }
        }

    }

    public static void showMenu(Proyecto proyecto){

        System.out.println();
        System.out.println("\t0 Finalizar programa");
        System.out.println("\t1 Crear Persona");
        System.out.println("\t2 Crear Tarea");
        System.out.println("\t3 Añadir persona a tarea");
        System.out.println("\t4 Quitar persona de tarea");
        System.out.println("\t5 Finalizar tarea");
        System.out.println("\t6 listar personas");
        System.out.println("\t7 Listar tareas");
        System.out.println("\t8 Añadir etiqueta");
        System.out.println("\t9 Modificar responsable");

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
    }

    public static void crearTarea(Proyecto proyecto){
        String titulo = scanStr("Introduce el titulo de la tarea: ");

        proyecto.addTarea(titulo);
    }

    public static void addPersonaATarea(Proyecto proyecto){
        String dni = scanStr("Introduce el DNI de la persona: ");

        Persona persona = proyecto.getPersona(dni);

        String titulo = scanStr("Introduce el titulo de la tarea: ");

        Tarea tarea = proyecto.buscaTarea(titulo);

        if(persona == null || tarea == null)
            error("Persona o tarea no encontrada");
        else
            proyecto.addPersonaToTarea(persona, tarea);

    }

    public static void removePersonaDeTarea(Proyecto proyecto) {
        String dni = scanStr("Introduce el DNI de la persona: ");
        Persona persona = proyecto.getPersona(dni);
        String titulo = scanStr("Introduce el titulo de la tarea: ");
        Tarea tarea = proyecto.buscaTarea(titulo);
        if(persona == null || tarea == null)
            error("Persona o tarea no encontrada");
        else
            proyecto.removePersonaDeTarea(persona, tarea);
    }

    public static void finalizarTarea(Proyecto proyecto) {
        String tarea = scanStr("Indica la tarea: ");
        proyecto.setTareaFinalizada(proyecto.buscaTarea(tarea));
    }

    public static void listarPersonas (Proyecto proyecto) {
        System.out.println("Personas en \"" + proyecto.getNombre() + "\": " + proyecto.getListaPersonas().toString());
    }

    public static void listarTareas (Proyecto proyecto) {
        System.out.println("Tareas de \"" + proyecto.getNombre() + "\": " + proyecto.getListaTareas().toString());
    }

    public static void  añadirEtiqueta(Proyecto proyecto) {
        String nombre = scanStr("Indica la tarea: ");
        Tarea tarea = proyecto.buscaTarea(nombre);
        if (tarea == null)
            error("No existe esa tarea");
        else {
            String etiqueta = scanStr("Indica el nombre de la etiqueta: ");
            if (tarea.containsEtiqueta(etiqueta))
                error("La tarea ya tiene esa etiqueta");
            tarea.addEtiqueta(new Etiqueta(etiqueta));
        }
    }

    public static void setResponsable(Proyecto proyecto){
        Tarea tarea = proyecto.buscaTarea(scanStr("Indica el titulo de la tarea: "));

        if (tarea == null)
            error("Tarea no encontrada");

        Persona persona = tarea.getPersona(scanStr("Indica el dni de la persona: "));

        if (persona == null)
            error("Persona no encontrada");

        if(!tarea.setResponsable(persona))
            error("No se pudo cambiar de responsable");
    }


    public static void error(String mensaje){
        System.out.println(mensaje);
    }

}

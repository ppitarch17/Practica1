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
        System.out.println(proyecto.getNombre());
        showMenu(proyecto);
    }

    public static void showMenu(Proyecto proyecto){

//        System.out.println(proyecto.getListaPersonas());
//        System.out.println(proyecto.getListaTareas());
        System.out.println("0 Finalizar programa");
        System.out.println("1 Crear Persona");
        System.out.println("2 Crear Tarea");
        System.out.println("3 añadir persona a tarea");
        System.out.println("4 finalizar tarea");
        System.out.println("5 listar personas");
        System.out.println("6 listar tareas");

        int op = scanInt("Selecciona: ");

        switch (op) {
            case 0 -> System.out.println("Fin");
            case 1 -> crearPersona(proyecto);
            case 2 -> crearTarea(proyecto);
            case 3 -> addPersonaATarea(proyecto);
            case 4 -> finalizarTarea(proyecto);
            case 5 -> listarPersonas(proyecto);
            case 6 -> listarTareas(proyecto);
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
    public static void finalizarTarea(Proyecto proyecto) {
        String tarea = scanStr("Indica la tarea: ");
        proyecto.buscaTarea(tarea).marcarComoFinalizada();
        showMenu(proyecto);
    }
    public static void listarPersonas (Proyecto proyecto) {
        System.out.println(proyecto.getListaPersonas().toString());
        showMenu(proyecto);
    }
    public static void listarTareas (Proyecto proyecto) {
        System.out.println(proyecto.getListaTareas().toString());
        showMenu(proyecto);
    }

    public static void error(Proyecto proyecto, String mensaje){
        System.out.println(mensaje);
        showMenu(proyecto);
    }

}

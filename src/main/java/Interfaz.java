import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

    public static void imprimirPersonas(List<Persona> personas){
        System.out.println(personas.toString());
    }
    public static void imprimirTareas(List<Tarea> tareas){ System.out.println(tareas.toString()); }

    public static Proyecto crearProyecto(){
        System.out.print("Introduce el nombre del proyecto: ");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.next();
        return new Proyecto(nombre);
    }

    public static void main(String[] args) {
        Proyecto proyecto = crearProyecto();
        System.out.println(proyecto.getNombre());
        showMenu(proyecto);
    }

    public static void showMenu(Proyecto proyecto){

        System.out.println(proyecto.getListaPersonas());
        System.out.println(proyecto.getListaTareas());

        System.out.println("1 Crear Persona");
        System.out.println("2 Crear Tarea");
        System.out.println("3 añadir persona a tarea");
        System.out.println("4 finalizar tarea");
        System.out.println("5 listar personas");
        System.out.println("6 listar tareas");
        System.out.print("Selecciona: ");

        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();

        switch (op) {
            case 0 -> System.out.println("Fin");
            case 1 -> crearPersona(proyecto);
            case 2 -> crearTarea(proyecto);
            case 3 -> addPersonaATarea(proyecto);
            default -> error(proyecto);
        }
        //recibir num
        //verificar que el numero
    }

    public static void crearPersona(Proyecto proyecto){
        System.out.print("Introduce el nombre de la persona: ");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.next();

        System.out.print("Introduce el correo de la persona: ");
        scanner = new Scanner(System.in);
        String correo = scanner.next();

        System.out.print("Introduce el DNI de la persona: ");
        scanner = new Scanner(System.in);
        String dni = scanner.next();

        proyecto.addPersona(nombre, correo, dni);
        showMenu(proyecto);
    }

    public static void crearTarea(Proyecto proyecto){

        System.out.print("Introduce el titulo de la tarea: ");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.next();

        proyecto.addTarea(titulo);
        showMenu(proyecto);
    }

    public static void addPersonaATarea(Proyecto proyecto){
        System.out.print("Introduce el DNI de la persona: ");
        Scanner scanner = new Scanner(System.in);
        String dni = scanner.next();

        Persona persona = proyecto.getPersona(dni);

        System.out.print("Introduce el titulo de la tarea: ");
        scanner = new Scanner(System.in);
        String titulo = scanner.next();

        Tarea tarea = proyecto.buscarTarea(titulo);

        if(persona == null || tarea == null)
            error(proyecto);

        proyecto.addPersonaToTarea(persona, tarea);
    }


    public static void error(Proyecto proyecto){
        System.out.println("Error: -------");
        showMenu(proyecto);
    }

}

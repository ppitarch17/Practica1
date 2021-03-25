import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

    Proyecto proyecto;

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
        showMenu();
    }

    public static void showMenu(){
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
            case 1 -> op1();
            case 2 -> op2();
            default -> error();
        }
        //recibir num
        //verificar que el numero
    }

    public static void op1(){
        System.out.println("op 1");
        showMenu();
    }

    public static void op2(){
        System.out.println("op 2");
        showMenu();
    }


    public static void error(){
        System.out.println("Error: -------");
        showMenu();
    }

}

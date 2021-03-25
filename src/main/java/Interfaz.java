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

    public static void menu() {

    }

    public static void main(String[] args) {
        Proyecto proyecto = crearProyecto();
        System.out.println(proyecto.getNombre());
        menu();
    }
}

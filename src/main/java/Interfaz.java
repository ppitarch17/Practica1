import java.util.Arrays;
import java.util.List;

public class Interfaz {
    public static void imprimirPersonas(List<Persona> personas){
        System.out.println(personas.toString());
    }
    public static void imprimirTareas(List<Tarea> tareas){

        System.out.println(tareas.toString());

        /*
        for (Tarea tarea : tareas){
            System.out.println(tarea.titulo);
            System.out.println(tarea.listaPersonas);
            System.out.println(tarea.isFinalizada);
            System.out.println(tarea.resultado);
        }
        */
    }
}

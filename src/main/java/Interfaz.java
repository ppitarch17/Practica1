import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

    Proyecto proyecto;

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

    public static void main(String[] args) {
        Proyecto proyecto = createProyect();
        ShowMenu();
    }

    public static void ShowMenu(){
        System.out.println("Selecciona: ");
        System.out.println("1 Crear Persona");
        System.out.println("2 Crear Tarea");
        System.out.println("3 añadir persona a tarea");
        System.out.println("4 finalizar tarea");
        System.out.println("5 listar personas");
        System.out.println("6 listar tareas");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();

        switch (op){
            case 1:
                op1();
                break;
            case 2:
                op2();
                break;
            default:
                error();
                break;
        }
        //recibir num
        //verificar que el numero
    }

    public static void op1(){
        System.out.println("op 1");
    }

    public static void op2(){
        System.out.println("op 2");
    }


    public static void error(){
        System.out.println("Error: -------");
        ShowMenu();
    }

}

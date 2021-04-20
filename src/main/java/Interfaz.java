import Facturación.ConsumoInterno;
import Facturación.Descuento;
import Facturación.Urgente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Interfaz {

    public static Proyecto crearProyecto(){
        String nombre = scanStr("Introduce el nombre del proyecto: ");
        return new Proyecto(nombre);
    }

    public static void main(String[] args) {

        Proyecto proyecto;

        System.out.println("\t1 Crear Un Nuevo Proyecto");
        System.out.println("\t2 Cargar Proyecto desde Fichero");

        int op = scanInt("Selecciona una Opcion: ");

        switch (op){
            case 1 -> proyecto = crearProyecto();
            case 2 -> {
                proyecto = recuperacionDeDatos();
                if (proyecto == null){
                    error("No se pudo cargar el proyecto");
                    System.out.println("Fin del Programa");
                    return;
                }
            }
            default -> {
                error("Opción no válida.");
                System.out.println("Fin del Programa");
                return;
            }
        }


        while(true){
            showMenu();

            op = scanInt("Selecciona una Opcion: ");

            if (op == 0){
                grabacionDeDatos(proyecto);
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
                case 10 -> listarTareasSinPersonas(proyecto);
                case 11 -> listarNoResponsables(proyecto);
                case 12 -> setCoste(proyecto);
                case 13 -> setFacturacion(proyecto);
                case 14 -> mostrarCoste(proyecto);
                default -> error("Opción no válida.");
            }
        }
    }

    public static void showMenu(){

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
        System.out.println("\t10 Listar tareas sin personas asignadas");
        System.out.println("\t11 Listar personas que no son responsables");
        System.out.println("\t12 Cambiar coste de tarea");
        System.out.println("\t13 Seleccionar facturacion");
        System.out.println("\t14 Mostrar coste total");
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

    public static double scanDou(String mensaje){
        System.out.print(mensaje);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
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
            if (!proyecto.addPersonaToTarea(persona, tarea))
                error("Persona no está en la tarea");

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

        if (tarea == null) {
            error("Tarea no encontrada");
            return;
        }
        Persona persona = tarea.getPersona(scanStr("Indica el dni de la persona: "));

        if (persona == null) {
            error("Persona no encontrada");
            return;
        }
        if(!tarea.setResponsable(persona))
            error("No se pudo cambiar de responsable");
    }

    public static void listarTareasSinPersonas(Proyecto proyecto) {
        System.out.println(UtilidadesParaListas.elementosConListaVacia(proyecto.getListaTareas()));
    }

    public static void listarNoResponsables(Proyecto proyecto) {
        List<Persona> personas = new ArrayList<>(proyecto.getListaPersonas());
        for (Tarea tarea : proyecto.getListaTareas()) {
            personas.remove(tarea.getResponsable());
        }
        System.out.println(personas);
    }

    public static void setCoste(Proyecto proyecto) {
        String  titulo = scanStr("Introduce la tarea: ");
        Tarea tarea = proyecto.buscaTarea(titulo);
        double coste = scanDou("Introduce el coste de la tarea: ");

        tarea.setCoste(coste);
    }

    public static void setFacturacion(Proyecto proyecto) {
        String  titulo = scanStr("Introduce la tarea: ");
        Tarea tarea = proyecto.buscaTarea(titulo);
        System.out.println("\t1 Consumo Interno");
        System.out.println("\t2 Descuento");
        System.out.println("\t3 Urgente");

        int facturacion = scanInt("Introduce el tipo de facturacion: ");
        switch (facturacion) {
            case 1 -> tarea.setFacturacion(new ConsumoInterno());
            case 2 -> tarea.setFacturacion(new Descuento());
            case 3 -> tarea.setFacturacion(new Urgente());
        }
    }

    public static void mostrarCoste(Proyecto proyecto) {
        System.out.println("El coste de la tarea es: " + proyecto.getCosteTotal());
    }



    public static void grabacionDeDatos(Proyecto proyecto){

        String nomFichero = scanStr("Introduce el nombre del fichero para almacenar el proyecto: ");

        ObjectOutputStream oos = null;
        try{
            try {
                FileOutputStream fos = new FileOutputStream(nomFichero);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(proyecto);
            }finally {
                if(oos != null) oos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Proyecto recuperacionDeDatos(){

        String nomFichero = scanStr("Introduce el nombre del fichero a cargar: ");

        ObjectInputStream ois = null;
        try{
            try{
                FileInputStream fis = new FileInputStream(nomFichero);
                ois = new ObjectInputStream(fis);
                Proyecto proyecto = (Proyecto) ois.readObject();
                return proyecto;
            }finally {
                if(ois != null) ois.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        return null;

    }

    public static void error(String mensaje){
        System.out.println(mensaje);
    }

}

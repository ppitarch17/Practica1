import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfazTest {
/*
    public static void main(String[] args) {

        //Pruebas

        Proyecto proyecto = new Proyecto("proyecto Prueba");
        Tarea tarea =  new Tarea("TituloTarea");
        Tarea tarea1 = new Tarea("TituloTarea1");
        Tarea tarea2 = new Tarea("TituloTarea2");

        proyecto.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"), tarea);
        proyecto.addPersona(new Persona("Persona2", "persona2@uji.es", "22222222A"), tarea);
        proyecto.addPersona(new Persona("Persona3", "persona3@uji.es", "33333333A"), tarea1);
        proyecto.addPersona(new Persona("Persona4", "persona4@uji.es", "44444444A"), tarea1);
        proyecto.addPersona(new Persona("Persona5", "persona5@uji.es", "555555555A"), tarea2);

        Interfaz.imprimirPersonas(proyecto.listaPersonas);

        System.out.println("---");
        Interfaz.imprimirTareas(proyecto.listaTareas);
    }
*/

    @Test
    void imprimirPersonas() {
        Proyecto proyecto = new Proyecto("proyecto Prueba");
        proyecto.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"), new Tarea("TituloTarea"));

        assertDoesNotThrow(() -> Interfaz.imprimirPersonas(proyecto.listaPersonas));
    }

    @Test
    void imprimirTareas() {
    }
}
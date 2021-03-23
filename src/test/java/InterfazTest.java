import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterfazTest {

    public static void main(String[] args) {
        Proyecto proyecto = new Proyecto("proyecto Prueba");
        proyecto.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"), new Tarea("TituloTarea"));
        Interfaz.imprimirPersonas(proyecto.listaPersonas);
    }

    @Test
    void imprimirPersonas() {
        Proyecto proyecto = new Proyecto("proyecto Prueba");
        proyecto.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"), new Tarea("TituloTarea"));
        Interfaz.imprimirPersonas(proyecto.listaPersonas);
    }

    @Test
    void imprimirTareas() {
    }
}
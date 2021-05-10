import Modelo.Persona;
import Modelo.Tarea;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    Persona persona = new Persona("Modelo.Persona", "persona@uji.es", "11111111A");

    @org.junit.jupiter.api.Test
    void addTarea() {
        assertFalse(persona.addTarea(null));

        Tarea tarea = new Tarea();

        assertTrue(persona.addTarea(tarea));
        assertFalse(persona.addTarea(tarea));

        assertDoesNotThrow(() -> persona.addTarea(null), "Error: se debe capturar la excepción");
    }

    @org.junit.jupiter.api.Test
    void removeTarea(){
        Tarea tarea = new Tarea();

        assertFalse(persona.removeTarea(tarea));

        persona.addTarea(tarea);
        assertTrue(persona.removeTarea(tarea));

    }
}
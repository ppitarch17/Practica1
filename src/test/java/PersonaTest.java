import Excepciones.NoSePuedeInsertarException;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");

    @org.junit.jupiter.api.Test
    void addTarea() {
        assertFalse(persona.addTarea(null));

        Tarea tarea = new Tarea();

        assertTrue(persona.addTarea(tarea));
        assertFalse(persona.addTarea(tarea));
    }

    @org.junit.jupiter.api.Test
    void removeTarea(){
        Tarea tarea = new Tarea();

        assertFalse(persona.removeTarea(tarea));

        persona.addTarea(tarea);
        assertTrue(persona.removeTarea(tarea));

    }

    @org.junit.jupiter.api.Test
    void excepcionesEnPersona(){
        Tarea tarea = new Tarea();

        //assertThrows(NullPointerException.class, () -> persona.addTarea(null));
        //assertTrue(persona.addTarea(tarea));
        //assertThrows(NoSePuedeInsertarException.class, () -> persona.addTarea(tarea));
    }
}
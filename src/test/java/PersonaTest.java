import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    @org.junit.jupiter.api.Test
    void addTarea() {
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");

        assertFalse(persona.addTarea(null));

        Tarea tarea = new Tarea();

        assertTrue(persona.addTarea(tarea));
        assertFalse(persona.addTarea(tarea));
    }

    @org.junit.jupiter.api.Test
    void removeTarea(){
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
        Tarea tarea = new Tarea();

        assertFalse(persona.removeTarea(tarea));

        persona.addTarea(tarea);
        assertTrue(persona.removeTarea(tarea));

    }

}
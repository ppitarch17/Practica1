import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.*;

class TareaTest {

    @org.junit.jupiter.api.Test
    void setPrioridad() {
        Tarea tarea = new Tarea();

        String mensajeSiError = "Test.setPropiedad() lanzo una expecion que no deberia. propidad debe estar entre 1 y 5";
        assertDoesNotThrow(() -> tarea.setPrioridad(1), mensajeSiError);
        assertDoesNotThrow(() -> tarea.setPrioridad(3), mensajeSiError);
        assertDoesNotThrow(() -> tarea.setPrioridad(5), mensajeSiError);

        mensajeSiError = "Test.setPropiedad() no lanzo expecion. propidad debe estar entre 1 y 5";
        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(-1), mensajeSiError);
        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(6),mensajeSiError);
        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(0),mensajeSiError);
    }

    @org.junit.jupiter.api.Test
    void setResposable(){
        Tarea tarea = new Tarea();
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
        tarea.addPersona(persona);

        assertEquals(persona, tarea.getResponsable());

        Persona persona2 = new Persona("Persona2", "persona2@uji.es", "222222222A");

        tarea.setResponsable(persona);

        assertEquals(persona, tarea.getResponsable());

        tarea.addPersona(persona);
        tarea.setResponsable(persona);

        assertNotNull(tarea.getResponsable());
    }

    @org.junit.jupiter.api.Test
    void getResposable(){
        Tarea tarea = new Tarea();
        tarea.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"));

        Persona persona = new Persona("Persona2", "persona2@uji.es", "222222222A");
        assertFalse(tarea.setResponsable(persona));

        tarea.addPersona(persona);
        assertTrue(tarea.setResponsable(persona));
    }

    @org.junit.jupiter.api.Test
    void addPersona() {
        Tarea tarea = new Tarea();
        assertFalse(tarea.addPersona(null));

        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
        assertTrue(tarea.addPersona(persona));
        assertFalse(tarea.addPersona(persona));
    }


}
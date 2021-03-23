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

        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(-1), mensajeSiError); //El segundo parámetro emite una excepción del tipo que se pasa como primer parametro.
        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(6),mensajeSiError);
        assertThrows(IllegalArgumentException.class, () -> tarea.setPrioridad(0),mensajeSiError);
    }

    @org.junit.jupiter.api.Test
    void getPrioridad(){
        Tarea tarea = new Tarea();
        assertEquals(1, tarea.getPrioridad());
    }

    @org.junit.jupiter.api.Test
    void Responsable(){
        Tarea tarea = new Tarea();
        tarea.addPersona(new Persona("Persona", "persona@uji.es", "11111111A"));

        assertNull(tarea.getResponsable());

        tarea.setResponsable(new Persona("Persona2", "persona2@uji.es", "222222222A"));

        assertNotNull(tarea.getResponsable());



    }
}


import Facturación.*;
import Modelo.Persona;
import Modelo.Tarea;

import static org.junit.jupiter.api.Assertions.*;

class TareaTest {

    Tarea tarea = new Tarea();

    @org.junit.jupiter.api.Test
    void setPrioridad() {

        String mensajeSiError = "Test.setPropiedad() lanzo una expecion que no deberia. propidad debe estar entre 1 y 5";
        assertDoesNotThrow(() -> tarea.setPrioridad(1), mensajeSiError);
        assertDoesNotThrow(() -> tarea.setPrioridad(3), mensajeSiError);
        assertDoesNotThrow(() -> tarea.setPrioridad(5), mensajeSiError);

    }

    @org.junit.jupiter.api.Test
    void setResposable(){
        Persona persona = new Persona("Modelo.Persona", "persona@uji.es", "11111111A");
        tarea.addPersona(persona);

        tarea.setResponsable(persona);

        assertEquals(persona, tarea.getResponsable());

        tarea.addPersona(persona);
        tarea.setResponsable(persona);

        assertNotNull(tarea.getResponsable());
    }

    @org.junit.jupiter.api.Test
    void getResposable(){
        Tarea tarea = new Tarea();
        tarea.addPersona(new Persona("Modelo.Persona", "persona@uji.es", "11111111A"));

        Persona persona = new Persona("Persona2", "persona2@uji.es", "222222222A");
        assertFalse(tarea.setResponsable(persona));

        tarea.addPersona(persona);

        assertTrue(tarea.setResponsable(persona));
    }

    @org.junit.jupiter.api.Test
    void addPersona() {
        Tarea tarea = new Tarea();
        assertFalse(tarea.addPersona(null));

        Persona persona = new Persona("Modelo.Persona", "persona@uji.es", "11111111A");
        assertTrue(tarea.addPersona(persona));
        assertFalse(tarea.addPersona(persona));


        assertDoesNotThrow(() -> tarea.addPersona(null), "Error: se debe capturar la excepción");
    }

    @org.junit.jupiter.api.Test
    void facturacion(){
        tarea.setFacturacion(new ConsumoInterno());
        assertEquals(0, tarea.getCoste());
        assertEquals(0, tarea.getCosteFinal());

        tarea.setCoste(100);

        assertEquals(100, tarea.getCoste());
        assertEquals(100, tarea.getCosteFinal());

        tarea.setFacturacion(new Descuento());
        assertEquals(100, tarea.getCoste());
        assertEquals(90, tarea.getCosteFinal());

        tarea.setFacturacion(new Urgente());
        assertEquals(100, tarea.getCoste());
        assertEquals(110, tarea.getCosteFinal());
    }

}
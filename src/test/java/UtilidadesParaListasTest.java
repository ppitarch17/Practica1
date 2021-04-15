import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilidadesParaListasTest {

    @Test
    void elementosConListaVacia() {
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");

        Tarea tarea = new Tarea("tarea con lista");
        Tarea tareaConListaVacia = new Tarea("tarea con lista vacia");
        Tarea tareaConListaVacia2 = new Tarea("tarea con lista vacia2");

        tarea.addPersona(persona);
        persona.addTarea(tareaConListaVacia);
        persona.addTarea(tareaConListaVacia2);

        ArrayList<Tarea> resp = new ArrayList<>();
        resp.add(tareaConListaVacia);
        resp.add(tareaConListaVacia2);

        assertEquals(resp,UtilidadesParaListas.elementosConListaVacia(persona.getLista()));

        tarea = new Tarea();
        persona = new Persona("Persona con lista vacia", "persona@uji.es", "11111111A");
        Persona persona2 = new Persona("Persona con lista", "persona2@uji.es", "2A");
        persona2.addTarea(tarea);
        tarea.addPersona(persona);
        tarea.addPersona(persona2);

        ArrayList<Persona> resp2 = new ArrayList<>();
        resp2.add(persona);

        assertEquals(resp2, UtilidadesParaListas.elementosConListaVacia(tarea.getLista()));
    }

    @Test
    void sePuedeInsertar() {

        Tarea tarea = new Tarea();
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
        assertTrue(tarea.addPersona(persona));
        assertFalse(tarea.addPersona(persona));


        Persona persona2 = new Persona("Persona2", "persona2@uji.es", "22222222A");
        assertTrue(persona.addTarea(tarea));
        assertFalse(persona.addTarea(tarea));
    }
}
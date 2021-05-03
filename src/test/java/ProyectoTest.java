import Facturación.ConsumoInterno;
import Facturación.Descuento;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProyectoTest {
    Proyecto proyecto = new Proyecto("proj");
    @Test
    void addPersona() {
        assertTrue(proyecto.addPersona("pepe", "pepe@uji.es", "123F"));
        assertTrue(proyecto.addPersona(null,null , "123A"));
    }

    @Test
    void addTarea() {
        assertTrue(proyecto.addTarea("tarea"));
    }

    @Test
    void addPersonaToTarea() {
        proyecto.addPersona("pepe", "pepe@uji.es", "123F");
        proyecto.addTarea("tarea");
        Persona persona = proyecto.getPersona("123F");
        Tarea tarea = proyecto.getListaTareas().get(0);
        assertTrue(proyecto.addPersonaToTarea(persona, tarea));
    }

    @Test
    void removePersonaDeTarea() {
        proyecto.addPersona("pepe", "pepe@uji.es", "123F");
        proyecto.addTarea("tarea");
        Persona persona = proyecto.getPersona("123F");
        Tarea tarea = proyecto.getListaTareas().get(0);
        proyecto.addPersonaToTarea(persona, tarea);
        assertTrue(proyecto.removePersonaDeTarea(persona, tarea));
    }

    @Test
    void getPersona() {
        proyecto.addPersona("pepe", "pepe@uji.es", "123F");
        Persona persona = proyecto.getListaPersonas().get(0);
        assertEquals(persona, proyecto.getPersona("123F"));
    }

    @Test
    void buscaTarea() {
        proyecto.addTarea("tarea");
        proyecto.addTarea("casa");
        Tarea tarea = proyecto.getListaTareas().get(1);
        assertEquals(tarea, proyecto.buscaTarea("casa"));
    }

    @Test
    void calcularCosteTotal() {
        proyecto.addTarea("tarea");
        Tarea tarea = proyecto.buscaTarea("tarea");
        proyecto.cambiarFacturacionTarea(tarea, new Descuento());
        proyecto.cambiarCosteTarea(tarea, 100);
        assertEquals(90, proyecto.getCosteTotal());
        proyecto.cambiarFacturacionTarea(tarea, new ConsumoInterno());
        assertEquals(100, proyecto.getCosteTotal());
    }

    @Test
    void cambiarCosteTarea() {
        proyecto.addTarea("tarea");
        Tarea tarea = proyecto.buscaTarea("tarea");
        proyecto.cambiarFacturacionTarea(tarea, new ConsumoInterno());
        proyecto.cambiarCosteTarea(tarea, 20);
        assertEquals(20, tarea.getCoste());
        proyecto.cambiarCosteTarea(tarea, 10);
        assertEquals(10, tarea.getCoste());
    }

    @Test
    void cambiarFacturacionTarea() {
        proyecto.addTarea("tarea");
        Tarea tarea = proyecto.buscaTarea("tarea");
        proyecto.cambiarFacturacionTarea(tarea, new ConsumoInterno());
        proyecto.cambiarCosteTarea(tarea, 100);
        assertEquals(100, tarea.getCoste());
        proyecto.cambiarFacturacionTarea(tarea, new Descuento());
        assertEquals(90, proyecto.getCosteTotal());
    }
}
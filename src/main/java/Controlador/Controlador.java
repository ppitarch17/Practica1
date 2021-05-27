package Controlador;

import Facturación.facturacion;
import Modelo.Persona;
import Modelo.Tarea;
import Resultados.Resultado;
import Vista.InterrogaVista;

import java.util.List;

public interface Controlador {

    boolean addPersona();
    boolean addTarea();
    boolean addPersonaToTarea();
    boolean removePersonaDeTarea ();
    void setTareaFinalizada();
    void cambiarCosteTarea();
    void cambiarFacturacionTarea();
    boolean setResponsable();
    void calcularCosteTotal();
    void setNombreProyecto();
    boolean addEtiquetaATarea();
}

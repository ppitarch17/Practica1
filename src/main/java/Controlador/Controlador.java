package Controlador;

import Facturación.facturacion;
import Modelo.Persona;
import Modelo.Tarea;
import Resultados.Resultado;
import Vista.InterrogaVista;

import java.util.List;

public interface Controlador {
    //Metodos para Vista

    boolean addPersona();
    boolean addTarea();
    boolean addPersonaToTarea();
    boolean removePersonaDeTarea ();
    void setTareaFinalizada();
    void cambiarCosteTarea();
    void cambiarFacturacionTarea();
    void calcularFacturacion();
    boolean setResponsable();
    void calcularCosteTotal();
    void setNombreProyecto();
    void addEtiquetaATarea();
}

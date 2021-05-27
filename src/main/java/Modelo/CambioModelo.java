package Modelo;

import Facturación.facturacion;
import Resultados.Resultado;
import Vista.InformaVista;


public interface CambioModelo {

    boolean addPersona(String nombre, String correo, String DNI);
    boolean addTarea(String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion);
    boolean addPersonaToTarea(Persona persona, Tarea tarea);
    boolean removePersonaDeTarea (Persona persona, Tarea tarea);
    void setTareaFinalizada(Tarea tarea);
    void cambiarCosteTarea(Tarea tarea, double coste);
    void cambiarFacturacionTarea(Tarea tarea, facturacion facturacion);
    boolean setResponsable(Tarea tarea, Persona persona);
    void calcularCosteTotal();
    void setNombreProyecto(String nombreProyecto);
    boolean addEtiquetaATarea(Tarea tarea, Etiqueta etiqueta);
    void setVista(InformaVista vista);

}

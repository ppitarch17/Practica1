package Modelo;

import Facturación.facturacion;
import Resultados.Resultado;
import Vista.InterrogaVista;

import java.util.List;

public interface CambioModelo {
    //Metodos para Controlador
    //Vista.Interfaz en: Modelo.Persona y Modelo.Tarea

    boolean addPersona(String nombre, String correo, String DNI);
    boolean addTarea(String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion);
//    Persona getPersona(String DNI);
//    Tarea buscaTarea (String titulo);
    boolean addPersonaToTarea(Persona persona, Tarea tarea);
    boolean removePersonaDeTarea (Persona persona, Tarea tarea);
    void setTareaFinalizada(Tarea tarea);
//    List<Persona> getListaPersonas();
//    List<Tarea> getListaTareas();
    void cambiarCosteTarea(Tarea tarea, double coste);
    void cambiarFacturacionTarea(Tarea tarea, facturacion facturacion);
    void calcularFacturacionTarea(Tarea tarea);
//    double getCosteTotal();
    boolean setResponsable(Tarea tarea, Persona persona);
//    String getNombre();
    void calcularCosteTotal();
    void setNombreProyecto(String nombreProyecto);
    void addEtiquetaATarea(Tarea tarea, Etiqueta etiqueta);

}

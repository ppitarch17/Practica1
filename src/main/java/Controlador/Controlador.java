package Controlador;

import Facturación.facturacion;
import Modelo.Persona;
import Modelo.Tarea;
import Vista.InterrogaVista;

import java.util.List;

public interface Controlador {
    //Metodos para Vista

    boolean addPersona(String nombre, String correo, String DNI);
    boolean addTarea(String titulo);
    Persona getPersona(String DNI);
    Tarea buscaTarea (String titulo);
    boolean addPersonaToTarea(Persona persona, Tarea tarea);
    boolean removePersonaDeTarea (Persona persona, Tarea tarea);
    void setTareaFinalizada(Tarea tarea);
    List<Persona> getListaPersonas();
    List<Tarea> getListaTareas();
    void cambiarCosteTarea(Tarea tarea, double coste);
    void cambiarFacturacionTarea(Tarea tarea, facturacion facturacion);
    double getCosteTotal();
    void setInterrogaVista(InterrogaVista vista);
    boolean setResponsable(Tarea tarea, Persona persona);
    String getNombre();
}

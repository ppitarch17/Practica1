package Controlador;

import Facturación.facturacion;
import Modelo.CambioModelo;
import Modelo.ImplementacionModelo;
import Resultados.Resultado;
import Vista.InterrogaVista;

import java.io.Serializable;

public class ImplementacionControlador implements Controlador, Serializable {

    InterrogaVista vista;
    CambioModelo modelo;

    public ImplementacionControlador(InterrogaVista vista, CambioModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public boolean addPersona() {
        return modelo.addPersona(vista.getEscuchadoraTextField().getNombrePersona(), vista.getEscuchadoraTextField().getCorreoPersona(), vista.getEscuchadoraTextField().getDniPersona());
    }

    @Override
    public boolean addTarea() {
        System.out.println("En controladore----");
        System.out.println(vista.getEscuchadoraTextField().getNombreTarea());
        System.out.println(vista.getEscuchadoraTextField().getDescripcionTarea());
        System.out.println(vista.getEscuchadoraComboBox().getPrioridadTarea());
        System.out.println(vista.getEscuchadoraComboBox().getTipoResultadoTarea());
        System.out.println(vista.getEscuchadoraTextField().getCosteTarea());
        System.out.println(vista.getEscuchadoraComboBox().getTipofacturacionTarea());
        System.out.println();
        System.out.println();
        System.out.println();

        boolean resp =  modelo.addTarea(vista.getEscuchadoraTextField().getNombreTarea(), vista.getEscuchadoraTextField().getDescripcionTarea(), vista.getEscuchadoraComboBox().getPrioridadTarea(), vista.getEscuchadoraComboBox().getTipoResultadoTarea(), vista.getEscuchadoraTextField().getCosteTarea(), vista.getEscuchadoraComboBox().getTipofacturacionTarea());
        return true;
    }

    @Override
    public boolean addPersonaToTarea() {
        return modelo.addPersonaToTarea(vista.getPersonaSeleccioanda(), vista.getTareaSeleccionada());
    }

    @Override
    public boolean removePersonaDeTarea() {
        return modelo.removePersonaDeTarea(vista.getPersonaDeTareaSeleccionada(), vista.getTareaSeleccionada());
    }

    @Override
    public void setTareaFinalizada() {
        modelo.setTareaFinalizada(vista.getTareaSeleccionada());
    }

    @Override
    public void cambiarCosteTarea() {
        modelo.cambiarCosteTarea(vista.getTareaSeleccionada(), vista.getEscuchadoraTextField().getCosteTarea());
    }

    @Override
    public void cambiarFacturacionTarea() {
        modelo.cambiarFacturacionTarea(vista.getTareaSeleccionada(), vista.getEscuchadoraComboBox().getTipofacturacionTarea());
    }

    @Override
    public void calcularFacturacion() {
        modelo.calcularFacturacionTarea(vista.getTareaSeleccionada());
    }

    @Override
    public boolean setResponsable() {
        return modelo.setResponsable(vista.getTareaSeleccionada(), vista.getPersonaDeTareaSeleccionada());
    }

    @Override
    public void calcularCosteTotal() {

        modelo.calcularCosteTotal();
    }

    @Override
    public void setNombreProyecto() {
        modelo.setNombreProyecto(vista.getEscuchadoraTextField().getTexto());
    }

    @Override
    public void addEtiquetaATarea() {
        modelo.addEtiquetaATarea(vista.getTareaSeleccionada(), vista.getEscuchadoraTextField().getEtiqueta());
    }
}

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
        return modelo.addTarea(vista.getEscuchadoraTextField().getNombreTarea(), vista.getEscuchadoraTextField().getDescripcionTarea(), vista.getEscuchadoraComboBox().getPrioridadTarea(), vista.getEscuchadoraComboBox().getTipoResultadoTarea(), vista.getEscuchadoraTextField().getCosteTarea(), vista.getEscuchadoraComboBox().getTipofacturacionTarea());
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
    public boolean addEtiquetaATarea() {
        return modelo.addEtiquetaATarea(vista.getTareaSeleccionada(), vista.getEscuchadoraTextField().getEtiqueta());
    }
}

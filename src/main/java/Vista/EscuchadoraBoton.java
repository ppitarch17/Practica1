package Vista;

import Controlador.*;
import Facturación.facturacion;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;
import Resultados.Resultado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EscuchadoraBoton implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;
    EscuchadoraTextField escuchadoraTextField;
    EscuchadoraComboBox escuchadoraComboBox;

    public EscuchadoraBoton(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        //System.out.println(controlador);
        this.interfazGrafica = interfazGrafica;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        JButton boton = (JButton)evento.getSource();

        switch (boton.getText()){
            case "Crear Proyecto" -> crearP();
            case "Abrir Proyecto" -> abrirP();
            case "Crear" -> crear();
            case "Abrir" -> abrir();
            case "Añadir tarea" -> addTarea();
            case "Añadir persona" -> addPersona();
            case "Añadir Persona a Proyecto" -> addPersonaAProyecto();
            case "Añadir persona a tarea" -> addPersonaATarea();
            case "Quitar persona de tarea" -> removePersonaDeTarea();
            case "Finalizar tarea" -> finalizarTarea();
            case "Añadir etiqueta" -> addEtiqueta();
            case "Set Responsable" -> setResponsable();
            case "Seleccionar coste" -> System.out.println("cuanto cuesta??");
            case "Seleccionar facturación" -> System.out.println("facturacion");
            case "Añadir tarea a Proyecto" -> addTareaAProyecto();
            case "Salir del programa" -> salir();
            case "Ok👍👍👍" -> interfazGrafica.getVentana().dispose();
        }


    }

    public void crearP(){
        interfazGrafica.getVentana().dispose();
        interfazGrafica.ventanaCrear();
    }

    public void abrirP() {
        interfazGrafica.getVentana().dispose();
        interfazGrafica.ventanaAbrir();
    }

    public void crear() {
        interfazGrafica.setControlador(new Proyecto(escuchadoraTextField.getTexto())); //TODO nombre al Proyecto
        System.out.println(controlador.getNombre());
        interfazGrafica.setVectorPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
        interfazGrafica.setVectorTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        interfazGrafica.setVectorPersonasEnTarea(new Persona[0]);
        interfazGrafica.getVentana().dispose();
        interfazGrafica.ventanaMain();
    }

    public void abrir() {
        String nomFichero = escuchadoraTextField.getTexto();

        ObjectInputStream ois = null;
        try{
            try{
                FileInputStream fis = new FileInputStream(nomFichero);
                ois = new ObjectInputStream(fis);
                Proyecto proyecto = (Proyecto) ois.readObject();
                interfazGrafica.setControlador(proyecto); //TODO nombre al Proyecto
                System.out.println(controlador.getNombre());
                interfazGrafica.setVectorPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
                interfazGrafica.setVectorTareas(controlador.getListaTareas().toArray(new Tarea[0]));
                interfazGrafica.setVectorPersonasEnTarea(new Persona[0]);
                //interfazGrafica.setVectorPersonasEnTarea(controlado);
                interfazGrafica.getVentana().dispose();
                interfazGrafica.ventanaMain();
            }finally {
                if(ois != null) ois.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }

    public void setEscuchadoraTextField(EscuchadoraTextField escuchadoraTextField) {
        this.escuchadoraTextField = escuchadoraTextField;
    }

    public void setEscuchadoraComboBox(EscuchadoraComboBox escuchadoraComboBox) {
        this.escuchadoraComboBox = escuchadoraComboBox;
    }

    public void addTarea() {
        interfazGrafica.ventanaTarea();
    }

    public void addPersona(){
        interfazGrafica.ventanaPersona();
    }

    public void addPersonaAProyecto(){
        System.out.println("---Añadiendo Persona");
        System.out.println(escuchadoraTextField.getNombrePersona());
        System.out.println(escuchadoraTextField.getCorreoPersona());
        System.out.println(escuchadoraTextField.getDniPersona());

        if (!controlador.addPersona(escuchadoraTextField.getNombrePersona(), escuchadoraTextField.getCorreoPersona(), escuchadoraTextField.getDniPersona()))
            interfazGrafica.ventanaError("La persona ya existe en el proyecto");
        else {
            interfazGrafica.getVentana().dispose();
            actualizarInterfaz();
        }
        System.out.println(controlador.getListaPersonas());

    }


    public void addTareaAProyecto(){
        System.out.println("---Añadiendo Tarea");

        //String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion
        System.out.println(escuchadoraTextField.getNombreTarea());
        System.out.println(escuchadoraTextField.getDescripcionTarea());
        System.out.println(escuchadoraComboBox.getPrioridadTarea());
        System.out.println(escuchadoraComboBox.getTipoResultadoTarea());
        System.out.println(escuchadoraTextField.getCosteTarea());
        System.out.println(escuchadoraComboBox.getTipofacturacionTarea());

        String titulo = escuchadoraTextField.getNombreTarea();
        String descripcion = escuchadoraTextField.getDescripcionTarea();
        int prioridad = escuchadoraComboBox.getPrioridadTarea();
        Resultado resultado = escuchadoraComboBox.getTipoResultadoTarea();
        double coste = escuchadoraTextField.getCosteTarea();
        facturacion facturacion = escuchadoraComboBox.getTipofacturacionTarea();

        controlador.addTarea(titulo, descripcion, prioridad, resultado, coste, facturacion);
        resetValues();

        interfazGrafica.getVentana().dispose();
        actualizarInterfaz();
    }

    public void actualizarInterfaz(){
        interfazGrafica.setTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        interfazGrafica.setPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
        if (interfazGrafica.getTareaSeleccionada() != null)
            interfazGrafica.setPersonasEnTarea( (Persona[]) interfazGrafica.getTareaSeleccionada().getListaAlmacacenada());
    }

    public void addPersonaATarea() {

        if (interfazGrafica.getPersonaSeleccioanda() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        controlador.addPersonaToTarea(interfazGrafica.getPersonaSeleccioanda(), interfazGrafica.getTareaSeleccionada());
        resetValues();
        actualizarInterfaz();
    }

    public void removePersonaDeTarea(){

        System.out.println("REMOVE PERSONA-------");
        if (interfazGrafica.getPersonaDeTareaSeleccionada() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        System.out.println("-------");
        System.out.println(interfazGrafica.getTareaSeleccionada());
        System.out.println(interfazGrafica.getPersonaSeleccioanda());
        System.out.println(interfazGrafica.getTareaSeleccionada().getLista());
        controlador.removePersonaDeTarea(interfazGrafica.getPersonaDeTareaSeleccionada(), interfazGrafica.getTareaSeleccionada());

        actualizarInterfaz();
        interfazGrafica.actualizarInfoTareaSeleccionada();
    }

    public void finalizarTarea() {
        controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
        actualizarInterfaz();
        interfazGrafica.actualizarInfoTareaSeleccionada();
//        controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
    }

    public void addEtiqueta() {
        InterrogaModelo tarea= interfazGrafica.getTareaSeleccionada();
    }

    public void setResponsable() {
//        controlador.setResponsable(interfazGrafica.getTareaSeleccionada(), interfazGrafica.getPersonaSeleccioanda());
    }

    public void salir() {
        grabacionDeDatos(controlador);
        System.exit(0);
    }

    public void grabacionDeDatos(Controlador proyecto){
        System.out.println("mueranse");
        String nomFichero = controlador.getNombre();

        ObjectOutputStream oos = null;
        try{
            try {
                FileOutputStream fos = new FileOutputStream(nomFichero);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(proyecto);
            }finally {
                if(oos != null) oos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void resetValues(){
        escuchadoraComboBox.resetValues();
        escuchadoraTextField.resetValues();
    }
}

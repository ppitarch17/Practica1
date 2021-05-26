package Vista;

import Controlador.*;
import Facturación.facturacion;
import Modelo.ImplementacionModelo;
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
        //System.out.println(controlador + "boton");
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
            case "Añadir etiqueta" -> interfazGrafica.ventanaEtiquetas();
            case "Set Responsable" -> setResponsable();
            case "Seleccionar coste" -> System.out.println("cuanto cuesta??");
            case "Seleccionar facturación" -> System.out.println("facturacion");
            case "Añadir tarea a Proyecto" -> addTareaAProyecto();
            case "Salir del programa" -> salir();
            case "Ok👍👍👍" -> interfazGrafica.getVentana().dispose();
            case "Agregar Nueva Etiqueta" -> addEtiqueta();
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
        interfazGrafica.setModelo(new ImplementacionModelo(controlador));
//        System.out.println(controlador.getNombre());
        interfazGrafica.setVectorPersonas(modelo.getPersonasEnProyecto(controlador));//controlador.getListaPersonas().toArray(new Persona[0]));
        interfazGrafica.setVectorTareas(modelo.getTareasEnProyecto(controlador));//controlador.getListaTareas().toArray(new Tarea[0]));
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
                interfazGrafica.setVectorPersonas(modelo.getPersonasEnProyecto(controlador));
                interfazGrafica.setVectorTareas(modelo.getTareasEnProyecto(controlador));
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

        if (titulo == null)
            interfazGrafica.ventanaError("Indica el nombre de la tarea");
        else if (descripcion == null)
            interfazGrafica.ventanaError("Indica la descripción de la tarea");
        else {
            controlador.addTarea(titulo, descripcion, prioridad, resultado, coste, facturacion);
            interfazGrafica.getVentana().dispose();
            resetValues();
            actualizarInterfaz();
        }
    }

    public void actualizarInterfaz(){
        interfazGrafica.setTareas(modelo.getTareasEnProyecto(controlador));
        interfazGrafica.setPersonas(modelo.getPersonasProyecto(controlador));
        if (interfazGrafica.getTareaSeleccionada() != null)
            interfazGrafica.setPersonasEnTarea(modelo.getListaPersonasEnTarea(interfazGrafica.getTareaSeleccionada()));
    }

    public void addPersonaATarea() {

        if (interfazGrafica.getPersonaSeleccioanda() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        controlador.addPersonaToTarea(interfazGrafica.getPersonaSeleccioanda(), interfazGrafica.getTareaSeleccionada());
        resetValues();
        actualizarInterfaz();
        interfazGrafica.actualizarInfoTareaSeleccionada();
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
        controlador.addEtiquetaATarea(interfazGrafica.getTareaSeleccionada(), escuchadoraTextField.getEtiqueta());//interfazGrafica.getTareaSeleccionada().addEtiqueta(escuchadoraTextField.getEtiqueta());
        actualizarInterfaz();
        interfazGrafica.actualizarInfoTareaSeleccionada();
    }

    public void setResponsable() {
       controlador.setResponsable(interfazGrafica.getTareaSeleccionada(), interfazGrafica.getPersonaDeTareaSeleccionada());
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

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }
}

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

public class EscuchadoraBoton implements ActionListener, Serializable {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;
    EscuchadoraTextField escuchadoraTextField;
    EscuchadoraComboBox escuchadoraComboBox;

    public EscuchadoraBoton(Controlador controlador, InterfazGrafica interfazGrafica, InterrogaModelo modelo) {
        this.controlador = controlador;
        //System.out.println(controlador + "boton");
        this.interfazGrafica = interfazGrafica;
        this.modelo = modelo;
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
//        System.out.println(controlador.getNombre());
        String nomFichero = escuchadoraTextField.getTexto();
        if (!nomFichero.contains(".bin"))
            escuchadoraTextField.setTexto(nomFichero + ".bin");

        controlador.setNombreProyecto();
        interfazGrafica.setVectorPersonas(modelo.getPersonasEnProyecto());
        interfazGrafica.setVectorTareas(modelo.getTareasEnProyecto());
        interfazGrafica.setVectorPersonasEnTarea(new Persona[0]);
        interfazGrafica.getVentana().dispose();
        interfazGrafica.ventanaMain();
    }

    public void abrir() {
        String nomFichero = escuchadoraTextField.getTexto();
        if (!nomFichero.contains(".bin"))
            nomFichero = nomFichero + ".bin";


        ObjectInputStream ois = null;
        try{
            try{
                FileInputStream fis = new FileInputStream(nomFichero);
                ois = new ObjectInputStream(fis);
                ImplementacionControlador proyecto = (ImplementacionControlador) ois.readObject();
                interfazGrafica.setControlador(proyecto);
                crear();
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

        if (!controlador.addPersona())
            interfazGrafica.ventanaError("La persona ya existe en el proyecto");
        else {
            interfazGrafica.getVentana().dispose();
            //actualizarInterfaz();
        }
    }


    public void addTareaAProyecto(){
        System.out.println("---Añadiendo Tarea");

        //String titulo, String descripcion, int prioridad, Resultado resultado, double coste, facturacion facturacion


        String titulo = escuchadoraTextField.getNombreTarea();
        String descripcion = escuchadoraTextField.getDescripcionTarea();
        double coste = escuchadoraTextField.getCosteTarea();

        if (titulo == null)
            interfazGrafica.ventanaError("Indica el nombre de la tarea");
        else if (descripcion == null)
            interfazGrafica.ventanaError("Indica la descripción de la tarea");
        else if (controlador.addTarea()){

            System.out.println("*****Añadiendo Tarea con Datos:");
            System.out.println(escuchadoraTextField.getNombreTarea());
            System.out.println(escuchadoraTextField.getDescripcionTarea());
            System.out.println(escuchadoraComboBox.getPrioridadTarea());
            System.out.println(escuchadoraComboBox.getTipoResultadoTarea());
            System.out.println(escuchadoraTextField.getCosteTarea());
            System.out.println(escuchadoraComboBox.getTipofacturacionTarea());
            interfazGrafica.getVentana().dispose();

            //actualizarInterfaz();
            //interfazGrafica.actualizarInfoTareaSeleccionada();

            //resetValues();
        }
        else
            interfazGrafica.ventanaError("Ya existe la tarea");
    }

    public void actualizarInterfaz(){
        interfazGrafica.setTareas(modelo.getTareasEnProyecto());
        interfazGrafica.setPersonas(modelo.getPersonasEnProyecto());
        if (interfazGrafica.getTareaSeleccionada() != null)
            interfazGrafica.setPersonasEnTarea(modelo.getListaPersonasEnTarea(interfazGrafica.getTareaSeleccionada()));
    }

    public void addPersonaATarea() {

        if (interfazGrafica.getPersonaSeleccioanda() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        controlador.addPersonaToTarea();
        //resetValues();
        //actualizarInterfaz();
    }

    public void removePersonaDeTarea(){

        System.out.println("REMOVE PERSONA-------");
        if (interfazGrafica.getPersonaDeTareaSeleccionada() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        System.out.println("-------");
        System.out.println(interfazGrafica.getTareaSeleccionada());
        System.out.println(interfazGrafica.getPersonaSeleccioanda());
        System.out.println(interfazGrafica.getTareaSeleccionada().getLista());
        controlador.removePersonaDeTarea();

        //actualizarInterfaz();
        //interfazGrafica.actualizarInfoTareaSeleccionada();
    }

    public void finalizarTarea() {
        controlador.setTareaFinalizada();
//        actualizarInterfaz();
        //interfazGrafica.actualizarInfoTareaSeleccionada();
//        controlador.setTareaFinalizada(interfazGrafica.getTareaSeleccionada());
    }

    public void addEtiqueta() {
//        InterrogaModelo tarea= interfazGrafica.getTareaSeleccionada(); //TODO
    }

    public void setResponsable() {
        if (interfazGrafica.getTareaSeleccionada() == null)
            interfazGrafica.ventanaError("No hay ninguna tarea seleccionada");
        else if (interfazGrafica.getPersonaDeTareaSeleccionada() == null)
            interfazGrafica.ventanaError("Selecciona una persona que pertenezca a la tarea");
        else {
            controlador.setResponsable();
            //interfazGrafica.actualizarInfoTareaSeleccionada();
        }
    }

    public void salir() {
        grabacionDeDatos();
        System.exit(0);
    }

    public void grabacionDeDatos(){
        String nomFichero = modelo.getNombreProyecto();

        ObjectOutputStream oos = null;
        try{
            try {
                FileOutputStream fos = new FileOutputStream(nomFichero);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(controlador);
            }finally {
                if(oos != null) oos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

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
            case "Set Responsable" -> setResponsable();
            case "Seleccionar coste" -> System.out.println("cuanto cuesta??");
            case "Seleccionar facturación" -> System.out.println("facturacion");
            case "Añadir tarea a Proyecto" -> addTareaAProyecto();
            case "Salir y Guardar" -> salir();
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

        String nomFichero = escuchadoraTextField.getTexto();
        if (nomFichero.equals(""))
            interfazGrafica.ventanaError("Introduce un nombre al proyecto");
        else {
            if (!nomFichero.contains(".bin"))
                escuchadoraTextField.setTexto(nomFichero + ".bin");

            controlador.setNombreProyecto();
            interfazGrafica.setVectorPersonas(modelo.getPersonasEnProyecto());
            interfazGrafica.setVectorTareas(modelo.getTareasEnProyecto());
            interfazGrafica.setVectorPersonasEnTarea(new Persona[0]);
            interfazGrafica.getVentana().dispose();
            interfazGrafica.ventanaMain();
            interfazGrafica.actualizarListasInterfaz();
        }
    }

    public void abrir() {
        String nomFichero = escuchadoraTextField.getTexto();
        if (nomFichero.equals(""))
            interfazGrafica.ventanaError("Introduce qué proyecto quieres abrir");
        else {
            if (!nomFichero.contains(".bin"))
                nomFichero = nomFichero + ".bin";

            ObjectInputStream ois = null;
            try {
                try {
                    FileInputStream fis = new FileInputStream(nomFichero);
                    ois = new ObjectInputStream(fis);
                    ImplementacionModelo modelo = (ImplementacionModelo) ois.readObject();
                    ImplementacionControlador controlador = new ImplementacionControlador(interfazGrafica, modelo);
                    interfazGrafica.setModelo(modelo);
                    interfazGrafica.setControlador(controlador);
                    modelo.setVista(interfazGrafica);
                    System.out.println("abrir conectar");
                    interfazGrafica.conectarReferenciasEscuchadores();
                    crear();
                } finally {
                    if (ois != null) ois.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
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

        if(escuchadoraTextField.getNombrePersona() == null)
            interfazGrafica.ventanaError("Escribe un nombre");
        else  if(escuchadoraTextField.getDniPersona() == null || escuchadoraTextField.getDniPersona().equals(""))
            interfazGrafica.ventanaError("Escribe un DNI");
        else if(escuchadoraTextField.getCorreoPersona() == null)
            interfazGrafica.ventanaError("Escribe un correo");
        else {
            if (!controlador.addPersona())
                interfazGrafica.ventanaError("La persona ya existe en el proyecto");
            else {
                interfazGrafica.getVentana().dispose();
            }
        }

    }

    public void addTareaAProyecto(){

        String titulo = escuchadoraTextField.getNombreTarea();
        String descripcion = escuchadoraTextField.getDescripcionTarea();
        if (titulo == null || titulo.equals(""))
            interfazGrafica.ventanaError("Indica el nombre de la tarea");
        else if(titulo.length() > 20){
            interfazGrafica.ventanaError("El nombre de la tarea debe tener menos de 20 caracteres");
        }else if (descripcion == null)
            interfazGrafica.ventanaError("Indica la descripción de la tarea");

        else if(escuchadoraTextField.getCosteTarea() == 0) {
            interfazGrafica.ventanaError("Indica un coste inicial");
        }else {



            if (controlador.addTarea()){
                interfazGrafica.getVentana().dispose();
            }
            else
                interfazGrafica.ventanaError("Ya existe la tarea");
        }

    }

    public void addPersonaATarea() {

        if (interfazGrafica.getPersonaSeleccioanda() == null || interfazGrafica.getTareaSeleccionada() == null)
            return;//TODO error

        controlador.addPersonaToTarea();

    }

    public void removePersonaDeTarea(){

        System.out.println("REMOVE PERSONA-------");
        if (interfazGrafica.getPersonaDeTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una persona en la tarea");
            return;
        }

        if (interfazGrafica.getTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una Tarea");
            return;
        }

        controlador.removePersonaDeTarea();
    }

    public void finalizarTarea() {

        if (interfazGrafica.getTareaSeleccionada() == null){
            interfazGrafica.ventanaError("Selecciona una Tarea");
            return;
        }

        if (modelo.isTareaFinalizada(interfazGrafica.getTareaSeleccionada())){
            interfazGrafica.ventanaError("La tarea ya esta finalizada");
            return;
        }

        controlador.setTareaFinalizada();
    }


    public void setResponsable() {

        if (interfazGrafica.getTareaSeleccionada() == null)
            interfazGrafica.ventanaError("No hay ninguna tarea seleccionada");
        else if (interfazGrafica.getPersonaDeTareaSeleccionada() == null)
            interfazGrafica.ventanaError("Selecciona una persona que pertenezca a la tarea");
        else if (modelo.getResponsable(interfazGrafica.getTareaSeleccionada()) == interfazGrafica.getPersonaDeTareaSeleccionada()){
            interfazGrafica.ventanaError("Ya es responsable");
        }else {
            controlador.setResponsable();
        }
    }

    public void salir() {
        grabacionDeDatos();
        System.exit(0);
    }

    public void grabacionDeDatos(){
        String nomFichero = modelo.getNombreProyecto();

        if (!nomFichero.contains(".bin"))
            nomFichero = nomFichero + ".bin";

        ObjectOutputStream oos = null;
        try{
            try {
                FileOutputStream fos = new FileOutputStream(nomFichero);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(modelo);
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

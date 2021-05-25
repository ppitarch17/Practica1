package Vista;

import Controlador.*;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EscuchadoraBoton implements ActionListener {

    Controlador controlador;
    InterfazGrafica interfazGrafica;
    InterrogaModelo modelo;

    EscuchadoraTextField escuchadoraTextField;
    public EscuchadoraBoton(Controlador controlador, InterfazGrafica interfazGrafica) {
        this.controlador = controlador;
        System.out.println(controlador);
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
            case "Quitar persona de tarea" -> System.out.println("quitar personita");
            case "Finalizar tarea" -> finalizarTarea();
            case "Añadir etiqueta" -> addEtiqueta();
            case "Set Responsable" -> setResponsable();
            case "Seleccionar coste" -> System.out.println("cuanto cuesta??");
            case "Seleccionar facturación" -> System.out.println("facturacion");
            case "Salir del programa" -> salir();

        }


    }

    public void crearP(){
        interfazGrafica.ventanaCrear();
    }

    public void abrirP() {
        interfazGrafica.ventanaAbrir();
    }

    public void crear() {
        interfazGrafica.setControlador(new Proyecto(escuchadoraTextField.getTexto())); //TODO nombre al Proyecto
        System.out.println(controlador.getNombre());
        interfazGrafica.setVectorPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
        interfazGrafica.setVectorTareas(controlador.getListaTareas().toArray(new Tarea[0]));
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
    public void addTarea() {
        interfazGrafica.ventanaTarea();
    }

    public void addPersona(){
        interfazGrafica.ventanaPersona();
    }

    public void addPersonaAProyecto(){
        System.out.println("Añadiendo Persona");
        System.out.println(escuchadoraTextField.getNombrePersona());
        System.out.println(escuchadoraTextField.getCorreoPersona());
        System.out.println(escuchadoraTextField.getDniPersona());

        controlador.addPersona(escuchadoraTextField.getNombrePersona(), escuchadoraTextField.getCorreoPersona(), escuchadoraTextField.getDniPersona());
        System.out.println(controlador.getListaPersonas());

        actualizarInterfaz();
    }

    public void actualizarInterfaz(){
        interfazGrafica.setTareas(controlador.getListaTareas().toArray(new Tarea[0]));
        interfazGrafica.setPersonas(controlador.getListaPersonas().toArray(new Persona[0]));
    }

    public void addPersonaATarea() {

    }

    public void finalizarTarea() {
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
}

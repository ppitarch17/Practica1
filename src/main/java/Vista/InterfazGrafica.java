package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InterfazGrafica implements InterrogaVista, InformaVista {
    //MVC
    private Controlador controlador;
    private InterrogaModelo modelo;
    private EscuchadoraBoton escuchadoraBoton;
    private EscuchadoraLista escuchadoraLista;

    private Tarea tareaSeleccionada; //Tarea
    private InterrogaModelo personaSeleccioanda; //Persona

    Tarea[] vectorTareas;
    Persona[] vectorPersonas;


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){ //Multiples Hilos
            @Override
            public void run(){
                new InterfazGrafica().ventanaStart();
            }
        });
    }

    public void matame(){
        ventanaStart();
    }

    //-------VENTANAS----------

    public void ventanaStart(){
        JFrame ventana = new JFrame("Primera Ventana"); //Ventana principal
        this.escuchadoraBoton = new EscuchadoraBoton(controlador, this);
        //ventana.addWindowListener(new EscuchadoraVentana()); //para guardar el proyecto luego?
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//lo mismo que arriba pero easy

        Container container = ventana.getContentPane();
        container.setLayout(new BorderLayout());

        JButton boton = new JButton("Crear Proyecto");
        boton.addActionListener(escuchadoraBoton);//Registro al boton
        JButton boton2 = new JButton("Abrir Proyecto");
        boton2.addActionListener(escuchadoraBoton);//Registro al boton
        container.add(boton);
        container.add(boton2);

        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.NORTH);
        panel.add(boton); panel.add(boton2);

        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaCrear() {
        JFrame ventana = new JFrame("Crear Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        JButton boton = new JButton("Crear");
        panel.add(nombre); panel.add(nom); panel.add(boton);
        boton.addActionListener(escuchadoraBoton);
        container.add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaMain(){
        JFrame ventana = new JFrame("Ventana Proyecto"); //Ventana principal
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//lo mismo que arriba pero easy

        //CONT
        Container container = ventana.getContentPane();
        container.setLayout(new BorderLayout());

        //NORTE
        JPanel panelNorte = new JPanel();
        container.add(panelNorte, BorderLayout.NORTH);
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        panelNorte.add(nombre); panelNorte.add(nom);


        //TAREAS
        JPanel panelOeste = new JPanel();
        container.add(panelOeste, BorderLayout.WEST);
        panelOeste.setLayout(new BorderLayout());

        panelOeste.add(new JLabel("Tareas: "), BorderLayout.NORTH);
        JButton aTarea = new JButton("Añadir tarea");
        aTarea.addActionListener(new EscuchadoraBoton(controlador, this));
        panelOeste.add(aTarea, BorderLayout.AFTER_LAST_LINE);

        //Tarea[] datos = (Tarea[]) controlador.getListaTareas().toArray();//{new Tarea("t1"), new Tarea("t2")};
        //List<Tarea> datos = controlador.getListaTareas();
        JList<Tarea> tareas = new JList<Tarea>(vectorTareas);

        tareas.addListSelectionListener(new EscuchadoraLista(controlador, this));
        tareas.setVisibleRowCount(3);
        tareas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelOeste.add(tareas, BorderLayout.CENTER);


        //PERSONAS
        JPanel panelCentral = new JPanel();
        container.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout());

        panelCentral.add(new JLabel("Personas: "), BorderLayout.NORTH);
        JButton aPersona = new JButton("Añadir persona");
        aPersona.addActionListener(new EscuchadoraBoton(controlador, this));
        panelCentral.add(aPersona, BorderLayout.AFTER_LAST_LINE);

        //List<Persona> lpersonas = controlador.getListaPersonas();//{new Persona("dni"), new Persona("nif")};
        JList<Persona> personas = new JList<>(vectorPersonas);
        personas.setVisibleRowCount(3);
        personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelCentral.add(personas, BorderLayout.CENTER);

        //INTERFAZ
        JPanel panelEste = new JPanel();
        container.add(panelEste, BorderLayout.EAST);
        panelEste.setLayout(new GridLayout(4,3));

        JButton finalizar = new JButton("Finalizar tarea");
        finalizar.addActionListener(new EscuchadoraBoton(controlador, this));


        panelEste.add(new JButton("Añadir persona a tarea"));
        panelEste.add(new JButton("Quitar persona de tarea"));
        panelEste.add(finalizar);
        panelEste.add(new JButton("Añadir etiqueta"));
        panelEste.add(new JButton("Set responsable"));
        panelEste.add(new JButton("Seleccionar coste"));
        panelEste.add(new JButton("Seleccionar facturación"));
        panelEste.add(new JButton("Salir del programa"));



        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaPersona(){
        JFrame ventana = new JFrame("Crear Persona");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();

        JLabel nombre = new JLabel("Nombre: ");
        JTextField nombreDato = new JTextField(20);

        JLabel correo = new JLabel("Correo: ");
        JTextField correoDato = new JTextField(20);

        JLabel dni = new JLabel("DNI: ");
        JTextField dniDato = new JTextField(20);

        JButton botonPersona = new JButton("Añadir Persona a Proyecto");
        botonPersona.addActionListener(new EscuchadoraBoton(controlador, this));

        panel.add(dni); panel.add(dniDato);
        panel.add(correo);panel.add(correoDato);
        panel.add(nombre); panel.add(nombreDato); panel.add(botonPersona);


        container.add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaTarea() {
        JFrame ventana = new JFrame("Crear Tarea");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        JButton tarea = new JButton("Añadir tarea");
        panel.add(nombre); panel.add(nom); panel.add(tarea);
        tarea.addActionListener(new EscuchadoraBoton(controlador, this));
        container.add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    //---------METODOS----------

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
        escuchadoraBoton.setControlador(controlador);
    }

    public InterrogaModelo getTareaSeleccionada() {
        return tareaSeleccionada;
    }

    public InterrogaModelo getPersonaSeleccioanda() {
        return personaSeleccioanda;
    }

    public void setTareaSeleccionada(Tarea tareaSeleccionada) {
        this.tareaSeleccionada = tareaSeleccionada;
    }

    public void setPersonaSeleccioanda(Persona personaSeleccioanda) {
        this.personaSeleccioanda = personaSeleccioanda;
    }

    public void setVectorTareas(Tarea[] vectorTareas) {
        this.vectorTareas = vectorTareas;
    }

    public void setVectorPersonas(Persona[] vectorPersonas) {
        this.vectorPersonas = vectorPersonas;
    }
}

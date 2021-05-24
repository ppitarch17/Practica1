package Vista;

import Controlador.Controlador;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InterfazGrafica implements InterrogaVista, InformaVista {
    //MVC
    private Controlador controlador;
    private InterrogaModelo modelo;
    private EscuchadoraBoton escuchadoraBoton;
    private EscuchadoraLista escuchadoraLista;

    private Tarea tareaSeleccionada;
    private Persona personaSeleccioanda;

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


        JList<Tarea> tareas = new JList<>((Tarea[]) controlador.getListaTareas().toArray());
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

        JList<Persona> personas = new JList<>((Persona[]) controlador.getListaPersonas().toArray());
        personas.setVisibleRowCount(3);
        personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelCentral.add(personas, BorderLayout.CENTER);

        //INTERFAZ
        JPanel panelEste = new JPanel();
        container.add(panelEste, BorderLayout.EAST);
        panelEste.setLayout(new GridLayout(4,3));

        JButton addPaT = new JButton("Añadir persona a tarea");
        addPaT.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton removePdT = new JButton("Quitar persona de tarea");
        removePdT.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton finalizar = new JButton("Finalizar tarea");
        finalizar.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton addEtiqueta = new JButton("Añadir etiqueta");
        addEtiqueta.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton setResp = new JButton("Set Responsable");
        setResp.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton selectCoste = new JButton("Seleccionar coste");
        selectCoste.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton selectFact = new JButton("Seleccionar facturación");
        selectFact.addActionListener(new EscuchadoraBoton(controlador, this));
        JButton salir = new JButton("Salir del programa");
        salir.addActionListener(new EscuchadoraBoton(controlador, this));

        panelEste.add(addPaT);
        panelEste.add(removePdT);
        panelEste.add(finalizar);
        panelEste.add(addEtiqueta);
        panelEste.add(setResp);
        panelEste.add(selectCoste);
        panelEste.add(selectFact);
        panelEste.add(salir);



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
        JTextField nom = new JTextField(20);
        JButton persona = new JButton("Añadir persona");
        panel.add(nombre); panel.add(nom); panel.add(persona);
        persona.addActionListener(new EscuchadoraBoton(controlador, this));
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
}

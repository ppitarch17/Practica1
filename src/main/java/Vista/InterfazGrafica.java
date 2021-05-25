package Vista;

import Controlador.Controlador;
import Facturación.facturacion;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;
import Facturación.*;

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
    private EscuchadoraTextField escuchadoraTextField;
    private EscuchadoraComboBox escuchadoraComboBox;
    private EscuchadoraCheckBox escuchadoraCheckBox;

    private Tarea tareaSeleccionada; //Tarea
    private Persona personaSeleccioanda; //Persona

    Tarea[] vectorTareas;
    Persona[] vectorPersonas;

    JList<Tarea> tareas;
    JList<Persona> personas;

    JLabel nombreTarea;
    JLabel coste;
    JLabel etiquetas;
    JLabel finalizada;


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
        this.escuchadoraTextField = new EscuchadoraTextField(controlador,this);
        escuchadoraBoton.setEscuchadoraTextField(escuchadoraTextField);
        this.escuchadoraComboBox = new EscuchadoraComboBox(controlador, this);
        escuchadoraBoton.setEscuchadoraComboBox(escuchadoraComboBox);
        //ventana.addWindowListener(new EscuchadoraVentana()); //para guardar el proyecto luego?
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//lo mismo que arriba pero easy

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

    public void ventanaAbrir(){
        JFrame ventana = new JFrame("Abrir Proyecto");
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        JButton boton = new JButton("Abrir");
        panel.add(nombre); panel.add(nom); panel.add(boton);
        nom.addActionListener(escuchadoraTextField);
        boton.addActionListener(escuchadoraBoton);
        container.add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaCrear() {
        JFrame ventana = new JFrame("Crear Proyecto");
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        JButton boton = new JButton("Crear");
        panel.add(nombre); panel.add(nom); panel.add(boton);
        nom.addActionListener(escuchadoraTextField);
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
        JLabel nombre = new JLabel(controlador.getNombre());
        panelNorte.add(nombre);


        //TAREAS
        JPanel panelOeste = new JPanel();
        container.add(panelOeste, BorderLayout.WEST);
        panelOeste.setLayout(new BorderLayout());

        panelOeste.add(new JLabel("Tareas: "), BorderLayout.NORTH);
        JButton aTarea = new JButton("Añadir tarea");
        aTarea.addActionListener(escuchadoraBoton);
        panelOeste.add(aTarea, BorderLayout.AFTER_LAST_LINE);

        //Tarea[] datos = (Tarea[]) controlador.getListaTareas().toArray();//{new Tarea("t1"), new Tarea("t2")};
        //List<Tarea> datos = controlador.getListaTareas();
        tareas = new JList<Tarea>(vectorTareas);

        tareas.addListSelectionListener(new EscuchadoraLista(controlador, this));
        tareas.setVisibleRowCount(20);
        JScrollPane scrollPane = new JScrollPane(tareas);
        tareas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelOeste.add(scrollPane, BorderLayout.CENTER);
//        panelOeste.add(tareas, BorderLayout.CENTER);


        //PERSONAS
        JPanel panelCentral = new JPanel();
        container.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout());

        panelCentral.add(new JLabel("Personas: "), BorderLayout.NORTH);
        JButton aPersona = new JButton("Añadir persona");
        aPersona.addActionListener(new EscuchadoraBoton(controlador, this));
        panelCentral.add(aPersona, BorderLayout.AFTER_LAST_LINE);

        //List<Persona> lpersonas = controlador.getListaPersonas();//{new Persona("dni"), new Persona("nif")};
        personas = new JList<>(vectorPersonas);
        personas.setVisibleRowCount(20);
        personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        JScrollPane scrollPanel = new JScrollPane(personas);
        personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelCentral.add(scrollPanel, BorderLayout.CENTER);
//        panelCentral.add(personas, BorderLayout.CENTER);

        //INTERFAZ
        JPanel panelEste = new JPanel();
        container.add(panelEste, BorderLayout.EAST);
        panelEste.setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        nombreTarea = new JLabel("Nombre tarea: ");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panelEste.add(nombreTarea, c);
        coste = new JLabel("Coste final: ");
        c.gridx = 0;
        c.gridy = 1;
        panelEste.add(coste, c);
        etiquetas = new JLabel("Etiquetas: ");
        c.gridx = 1;
        c.gridy = 0;
        panelEste.add(etiquetas, c);
        finalizada = new JLabel("Finalizada: ");
        c.gridx = 1;
        c.gridy = 1;
        panelEste.add(finalizada, c);



        JButton finalizar = new JButton("Finalizar tarea");
        finalizar.addActionListener(escuchadoraBoton);
        c.gridx = 0;
        c.gridy = 2;
        panelEste.add(finalizar, c);

        JButton addEtiqueta = new JButton("Añadir etiqueta");
        addEtiqueta.addActionListener(escuchadoraBoton);
        c.gridx = 0;
        c.gridy = 3;
        panelEste.add(addEtiqueta, c);

        JLabel introduceCoste = new JLabel("Seleccionar coste: ");
        c.gridx = 1;
        c.gridy = 2;
        panelEste.add(introduceCoste, c);

        JTextField selectCoste = new JTextField();
        selectCoste.addActionListener(escuchadoraTextField);
        c.gridx = 2;
        c.gridy = 2;
        panelEste.add(selectCoste, c);

        facturacion[] tiposFacturacion = { new Descuento(), new ConsumoInterno(), new Urgente()};
        JComboBox<facturacion> facturacionDato = new JComboBox<>(tiposFacturacion);
        facturacionDato.addActionListener(escuchadoraComboBox);
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 3;
        panelEste.add(facturacionDato, c);

        JList<Persona> personas2 = new JList<>(vectorPersonas);
        personas2.setVisibleRowCount(20);
        personas2.setLayoutOrientation(JList.VERTICAL_WRAP);
        JScrollPane scrollPanele = new JScrollPane(personas2);
        personas2.setLayoutOrientation(JList.VERTICAL_WRAP);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 4;
        panelEste.add(scrollPanele, c);

        JButton addPaT = new JButton("Añadir persona a tarea");
        addPaT.addActionListener(escuchadoraBoton);
        c.ipady = 0;       //reset to default
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 5;
        panelEste.add(addPaT, c);

        JButton removePdT = new JButton("Quitar persona de tarea");
        removePdT.addActionListener(escuchadoraBoton);
        c.gridx = 1;
        c.gridy = 5;
        panelEste.add(removePdT, c);

        JButton setResp = new JButton("Set Responsable");
        setResp.addActionListener(escuchadoraBoton);
        c.gridx = 2;
        c.gridy = 5;
        panelEste.add(setResp, c);

        JCheckBox noResponsables = new JCheckBox("No responsables");
        noResponsables.addActionListener(escuchadoraCheckBox);
        c.gridx = 0;
        c.gridy = 6;
        panelEste.add(noResponsables, c);

        JCheckBox sinPersonas = new JCheckBox("Sin personas");
        sinPersonas.addActionListener(escuchadoraCheckBox);
        c.gridx = 1;
        c.gridy = 6;
        panelEste.add(sinPersonas, c);



        JButton salir = new JButton("Salir del programa");
        salir.addActionListener(escuchadoraBoton);
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 2;       //aligned with button 2
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 6;       //third row
        panelEste.add(salir, c);







//
//        JButton addPaT = new JButton("Añadir persona a tarea");
//        addPaT.addActionListener(escuchadoraBoton);
//        JButton removePdT = new JButton("Quitar persona de tarea");
//        removePdT.addActionListener(escuchadoraBoton);
//        JButton finalizar = new JButton("Finalizar tarea");
//        finalizar.addActionListener(escuchadoraBoton);
//        JButton addEtiqueta = new JButton("Añadir etiqueta");
//        addEtiqueta.addActionListener(escuchadoraBoton);
//        JButton setResp = new JButton("Set Responsable");
//        setResp.addActionListener(escuchadoraBoton);
//        JButton selectCoste = new JButton("Seleccionar coste");
//        selectCoste.addActionListener(escuchadoraBoton);
//        JButton selectFact = new JButton("Seleccionar facturación");
//        selectFact.addActionListener(escuchadoraBoton);
//        JButton salir = new JButton("Salir del programa");
//        salir.addActionListener(escuchadoraBoton);
//
//        panelEste.add(addPaT);
//        panelEste.add(removePdT);
//        panelEste.add(finalizar);
//        panelEste.add(addEtiqueta);
//        panelEste.add(setResp);
//        panelEste.add(selectCoste);
//        panelEste.add(selectFact);
//        panelEste.add(salir);



        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaPersona(){
        JFrame ventana = new JFrame("Crear Persona");
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));//container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nombre = new JLabel("Nombre: ");
        JTextField nombreDato = new JTextField(20);
        nombreDato.setName("nombreDato");
        nombreDato.addActionListener(escuchadoraTextField);

        JLabel correo = new JLabel("Correo: ");
        JTextField correoDato = new JTextField(20);
        correoDato.setName("correoDato");
        correoDato.addActionListener(escuchadoraTextField);

        JLabel dni = new JLabel("DNI: ");
        JTextField dniDato = new JTextField(20);
        dniDato.setName("dniDato");
        dniDato.addActionListener(escuchadoraTextField);


        JButton botonPersona = new JButton("Añadir Persona a Proyecto");
        botonPersona.addActionListener(escuchadoraBoton);

        panel.add(dni); panel.add(dniDato);
        panel.add(correo);panel.add(correoDato);
        panel.add(nombre); panel.add(nombreDato); panel.add(botonPersona);


        container.add(panel);
        ventana.pack();
        ventana.setVisible(true);
    }

    public void ventanaTarea() {
        JFrame ventana = new JFrame("Crear Tarea");
        //ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = ventana.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));//new GridLayout(1,3));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nombreTarea = new JLabel("Nombre: ");
        JTextField nombreTareaDato = new JTextField(20);
        nombreTareaDato.setName("nombreTareaDato");
        nombreTareaDato.addActionListener(escuchadoraTextField);

        JLabel descripcion = new JLabel("descripcion: ");
        JTextField descripcionDato = new JTextField(20);
        descripcionDato.setName("descripcionDato");
        descripcionDato.addActionListener(escuchadoraTextField);

        //int[] tiposPrioridades = { 1, 2, 3, 4, 5 };
        JLabel prioridad = new JLabel("prioridad: ");
        String[] tiposPrioridades = { "1", "2", "3", "4", "5" };
        JComboBox<String> prioridadDato = new JComboBox<String>(tiposPrioridades);
        prioridadDato.setName("prioridadDato");
        prioridadDato.addActionListener(escuchadoraComboBox);


//        JLabel slider = new JLabel("Slider: ");
//        JSlider sliderDato = new JSlider(JSlider.HORIZONTAL,1,5, 1);
//        sliderDato.setMinorTickSpacing(1);
//        sliderDato.setMajorTickSpacing(5);
//        sliderDato.setPaintTicks(true);

        JLabel resultado = new JLabel("resultado: ");
        String[] tiposResultado = { "Biblioteca", "Documentacion", "Pagina Web","Programa"};
        JComboBox<String> resultadoDato = new JComboBox<String>(tiposResultado);
        resultadoDato.setName("resultadoDato");
        resultadoDato.addActionListener(escuchadoraComboBox);

        JLabel costeInicial = new JLabel("Coste Inicial: ");
        JTextField costeInicialDatos = new JTextField(20);
        costeInicialDatos.setName("costeInicialDatos");
        costeInicialDatos.addActionListener(escuchadoraTextField);

        JLabel facturacion = new JLabel("facturacion: ");
        facturacion[] tiposFacturacion = { new Descuento(), new ConsumoInterno(), new Urgente()};
        JComboBox<facturacion> facturacionDato = new JComboBox<>(tiposFacturacion);
        facturacionDato.setName("facturacionDato");
        facturacionDato.addActionListener(escuchadoraComboBox);

        JButton tareaBoton = new JButton("Añadir tarea a Proyecto");
        tareaBoton.addActionListener(escuchadoraBoton);

        panel.add(nombreTarea); panel.add(nombreTareaDato); panel.add(descripcion);panel.add(descripcionDato);
        panel.add(prioridad);panel.add(prioridadDato);panel.add(costeInicial); panel.add(costeInicialDatos);
        panel.add(facturacion); panel.add(facturacionDato);
        panel.add(tareaBoton);

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

    public void setTareas(Tarea[] tareas) {
        this.tareas.setListData(tareas);
    }

    public void setPersonas(Persona[] personas) {
        this.personas.setListData(personas);
    }
}

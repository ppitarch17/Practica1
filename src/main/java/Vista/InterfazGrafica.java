package Vista;

import Controlador.*;
import Facturación.facturacion;
import Modelo.ImplementacionModelo;
import Modelo.InterrogaModelo;
import Modelo.Persona;
import Modelo.Tarea;
import Facturación.*;
import Resultados.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.Serializable;
import java.util.Arrays;


public class InterfazGrafica implements InterrogaVista, InformaVista, Serializable {
    //MVC
    private Controlador controlador;
    private InterrogaModelo modelo;
    private EscuchadoraBoton escuchadoraBoton;
    private EscuchadoraLista escuchadoraLista;
    private EscuchadoraTextField escuchadoraTextField;
    private EscuchadoraComboBox escuchadoraComboBox;
    private EscuchadoraCheckBox escuchadoraCheckBox;

    JFrame ventana;

    private Tarea tareaSeleccionada;
    private Persona personaSeleccioanda;
    private Persona personaDeTareaSeleccionada;

    Tarea[] vectorTareas;
    Persona[] vectorPersonas;
    Persona[] vectorPersonasEnTarea = new Persona[0];

    JList<Tarea> tareas = new JList<>();
    JList<Persona> personas = new JList<>();
    JList<Persona> personasEnTarea = new JList<>();

    JLabel costeTotal;
    JLabel nombreTarea;
    JLabel coste;
    JLabel etiquetas;
    JLabel finalizada;
    JLabel responsable;
    JLabel prioridad;
    JLabel resultado;

    JTextField selectCoste;
    JTextField selectEtiqueta;
    JComboBox<facturacion> facturacionDato;
    facturacion[] tiposFacturacion = { new ConsumoInterno(), new Descuento(), new Urgente()};


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                new InterfazGrafica().interfaz();
            }
        });
    }

    public void interfaz(){
        conectar();
        ventanaStart();
    }

    public void conectar(){
        System.out.println("Metodo conectar");
        ImplementacionModelo modelo = new ImplementacionModelo(this);
        ImplementacionControlador controlador = new ImplementacionControlador(this, modelo);

        this.modelo = modelo;
        this.controlador = controlador;

        conectarReferenciasEscuchadores();
    }

    public void conectarReferenciasEscuchadores(){
        this.escuchadoraBoton = new EscuchadoraBoton(this.controlador, this, this.modelo);
        this.escuchadoraTextField = new EscuchadoraTextField(this.controlador,this, modelo);
        escuchadoraBoton.setEscuchadoraTextField(escuchadoraTextField);
        this.escuchadoraComboBox = new EscuchadoraComboBox(this.controlador, this, this.modelo);
        escuchadoraBoton.setEscuchadoraComboBox(escuchadoraComboBox);
        this.escuchadoraLista = new EscuchadoraLista(this.controlador, this);
        escuchadoraLista.setEscuchadoraBoton(escuchadoraBoton);
        this.escuchadoraCheckBox = new EscuchadoraCheckBox(this.controlador, this, this.modelo);
    }


    //-------VENTANAS----------

    public void ventanaStart(){
        ventana = new JFrame("Primera Ventana");

        Container container = ventana.getContentPane();
        container.setLayout(new BorderLayout());

        JButton boton = new JButton("Crear Proyecto");
        boton.addActionListener(escuchadoraBoton);
        JButton boton2 = new JButton("Abrir Proyecto");
        boton2.addActionListener(escuchadoraBoton);
        container.add(boton);
        container.add(boton2);

        JPanel panel = new JPanel();
        container.add(panel, BorderLayout.NORTH);
        panel.add(boton); panel.add(boton2);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void ventanaAbrir(){
        ventana = new JFrame("Abrir Proyecto");

        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));
        JPanel panel = new JPanel();

        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Fichero bin", "bin");
        selector.setFileFilter(filtro);
        selector.showDialog(ventana, "Elige un fichero");
        panel.add(selector);
        escuchadoraBoton.abrir(selector.getSelectedFile().getAbsolutePath());

        container.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void ventanaCrear() {
        ventana = new JFrame("Crear Proyecto");

        Container container = ventana.getContentPane();
        container.setLayout(new GridLayout(1,3));

        JPanel panel = new JPanel();
        JLabel nombre = new JLabel("Nombre: ");
        JTextField nom = new JTextField(20);
        JButton boton = new JButton("Crear");

        panel.add(nombre); panel.add(nom); panel.add(boton);
        nom.addFocusListener(escuchadoraTextField);
        boton.addActionListener(escuchadoraBoton);
        container.add(panel);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void ventanaMain(){
        ventana = new JFrame("Ventana Proyecto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ventana.setMinimumSize(new Dimension(900, 450));

        //CONT
        Container container = ventana.getContentPane();
        container.setLayout(new GridBagLayout());

        //NORTE
        JPanel panelNorte = new JPanel();
        GridBagConstraints dim = new GridBagConstraints();
        dim.fill = GridBagConstraints.BOTH;

        dim.weightx = 7;
        dim.gridwidth = 7;
        dim.gridx = 0;
        dim.gridy = 0;
        container.add(panelNorte, dim);

        JLabel nombre = new JLabel("<html>" + "<B>" + modelo.getNombreProyecto() + "</B>" + "</html>");
        panelNorte.add(nombre);

        controlador.calcularCosteTotal();
        costeTotal = new JLabel("Coste total: " + modelo.getCosteTotalProyecto());
        panelNorte.add(costeTotal);

        //PERSONAS
        JPanel panelOeste = new JPanel();
        dim.gridwidth = 2;
        dim.gridx = 0;
        dim.gridy = 1;
        container.add(panelOeste, dim);
        panelOeste.setLayout(new BorderLayout());

        //TAREAS
        panelOeste.add(new JLabel("Tareas: "), BorderLayout.NORTH);
        JButton aTarea = new JButton("Añadir tarea");
        aTarea.addActionListener(escuchadoraBoton);
        panelOeste.add(aTarea, BorderLayout.AFTER_LAST_LINE);

        tareas = new JList<Tarea>(vectorTareas);
        tareas.setName("cuadroTareas");
        tareas.addListSelectionListener(new EscuchadoraLista(controlador, this));
        tareas.setVisibleRowCount(20);
        JScrollPane scrollPane = new JScrollPane(tareas);
        tareas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelOeste.add(scrollPane, BorderLayout.CENTER);


        //PERSONAS EN TAREA
        JPanel panelCentral = new JPanel();
        dim.gridx = 2;
        dim.gridy = 1;
        container.add(panelCentral, dim);
        panelCentral.setLayout(new BorderLayout());

        panelCentral.add(new JLabel("Personas: "), BorderLayout.NORTH);
        JButton aPersona = new JButton("Añadir persona");
        aPersona.addActionListener(escuchadoraBoton);
        panelCentral.add(aPersona, BorderLayout.AFTER_LAST_LINE);

        this.personas = new JList<>(vectorPersonas);
        this.personas.setName("cuadroPersonas");
        this.personas.addListSelectionListener(escuchadoraLista);
        this.personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        JScrollPane scrollPanel = new JScrollPane(this.personas);
        this.personas.setLayoutOrientation(JList.VERTICAL_WRAP);
        panelCentral.add(scrollPanel, BorderLayout.CENTER);

        //INTERFAZ
        JPanel panelEste = new JPanel();
        dim.gridwidth = 3;
        dim.gridx = 4;
        dim.gridy = 1;
        container.add(panelEste, dim);
        panelEste.setLayout(new GridBagLayout());


        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        nombreTarea = new JLabel("Titulo: ");
        c.gridx = 0;
        c.gridy = 0;
        panelEste.add(nombreTarea, c);
        coste = new JLabel("Coste final: ");
        c.gridx = 0;
        c.gridy = 1;
        panelEste.add(coste, c);
        etiquetas = new JLabel("Etiquetas: ");
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 0;
        panelEste.add(etiquetas, c);
        finalizada = new JLabel("Finalizada: ");
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        panelEste.add(finalizada, c);
        responsable = new JLabel("Responsable: ");
        c.gridx = 2;
        c.gridy = 1;
        panelEste.add(responsable, c);
        prioridad = new JLabel("Prioridad: ");
        c.gridx = 0;
        c.gridy = 2;
        panelEste.add(prioridad, c);
        resultado = new JLabel("Resultado: ");
        c.gridx = 1;
        c.gridy = 2;
        panelEste.add(resultado, c);


        JButton finalizar = new JButton("Finalizar tarea");
        finalizar.addActionListener(escuchadoraBoton);
        c.gridx = 0;
        c.gridy = 3;
        panelEste.add(finalizar, c);

        JLabel introduceEtiqueta = new JLabel("Introduce etiqueta: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 3;
        panelEste.add(introduceEtiqueta, c);

        selectEtiqueta = new JTextField();
        selectEtiqueta.addActionListener(escuchadoraTextField);
        selectEtiqueta.setName("addEtiqueta");
        c.gridx = 2;
        c.gridy = 3;
        panelEste.add(selectEtiqueta, c);

        JLabel introduceCoste = new JLabel("Cambiar coste: ", SwingConstants.RIGHT);
        c.gridx = 1;
        c.gridy = 4;
        panelEste.add(introduceCoste, c);

        selectCoste = new JTextField();
        selectCoste.addActionListener(escuchadoraTextField);
        selectCoste.setName("seleccionarCoste");
        c.gridx = 2;
        c.gridy = 4;
        panelEste.add(selectCoste, c);

        facturacionDato = new JComboBox<>(tiposFacturacion);
        facturacionDato.addActionListener(escuchadoraComboBox);
        facturacionDato.setName("facturacionTarea");
        c.gridx = 0;
        c.gridy = 4;
        panelEste.add(facturacionDato, c);

        JLabel personasDeTarea = new JLabel("Lista de personas de la tarea: ");
        c.gridx = 0;
        c.gridy = 5;
        panelEste.add(personasDeTarea, c);

        personasEnTarea = new JList<>(vectorPersonasEnTarea);
        personasEnTarea.setName("cuadroPersonasEnTarea");
        personasEnTarea.setVisibleRowCount(20);
        personasEnTarea.setLayoutOrientation(JList.VERTICAL_WRAP);
        JScrollPane scrollPanele = new JScrollPane(personasEnTarea);
        personasEnTarea.setLayoutOrientation(JList.VERTICAL_WRAP);
        personasEnTarea.addListSelectionListener(new EscuchadoraLista(controlador,this,escuchadoraBoton));

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 80;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 6;
        panelEste.add(scrollPanele, c);

        JButton addPaT = new JButton("Añadir persona a tarea");
        addPaT.addActionListener(escuchadoraBoton);
        c.ipady = 0;
        c.gridwidth = 1;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 7;
        panelEste.add(addPaT, c);

        JButton removePdT = new JButton("Quitar persona de tarea");
        removePdT.addActionListener(escuchadoraBoton);
        c.gridx = 1;
        c.gridy = 7;
        panelEste.add(removePdT, c);

        JButton setResp = new JButton("Set Responsable");
        setResp.addActionListener(escuchadoraBoton);
        c.gridx = 2;
        c.gridy = 7;
        panelEste.add(setResp, c);

        JCheckBox noResponsables = new JCheckBox("No responsables");
        noResponsables.setName("No responsables");
        noResponsables.addActionListener(escuchadoraCheckBox);
        c.gridx = 0;
        c.gridy = 8;
        panelEste.add(noResponsables, c);

        JCheckBox sinPersonas = new JCheckBox("Sin personas");
        sinPersonas.setName("Sin personas");
        sinPersonas.addActionListener(escuchadoraCheckBox);
        c.gridx = 1;
        c.gridy = 8;
        panelEste.add(sinPersonas, c);

        JButton salir = new JButton("Salir y Guardar");
        salir.addActionListener(escuchadoraBoton);
        c.ipady = 0;
        c.weighty = 1.0;
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(10,0,0,0);
        c.gridx = 2;
        c.gridy = 9;
        panelEste.add(salir, c);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void ventanaPersona(){
        ventana = new JFrame("Crear Persona");

        Container container = ventana.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nombre = new JLabel("Nombre: ");
        JTextField nombreDato = new JTextField(20);
        nombreDato.setName("nombreDato");
        nombreDato.addFocusListener(escuchadoraTextField);

        JLabel correo = new JLabel("Correo: ");
        JTextField correoDato = new JTextField(20);
        correoDato.setName("correoDato");
        correoDato.addFocusListener(escuchadoraTextField);

        JLabel dni = new JLabel("DNI: ");
        JTextField dniDato = new JTextField(20);
        dniDato.setName("dniDato");
        dniDato.addFocusListener(escuchadoraTextField);


        JButton botonPersona = new JButton("Añadir Persona a Proyecto");
        botonPersona.addActionListener(escuchadoraBoton);

        panel.add(dni); panel.add(dniDato);
        panel.add(correo);panel.add(correoDato);
        panel.add(nombre); panel.add(nombreDato); panel.add(botonPersona);


        container.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);
    }

    public void ventanaTarea() {
        ventana = new JFrame("Crear Tarea");

        Container container = ventana.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel nombreTarea = new JLabel("Titulo: ");
        JTextField nombreTareaDato = new JTextField(20);
        nombreTareaDato.setName("nombreTareaDato");
        nombreTareaDato.addFocusListener(escuchadoraTextField);

        JLabel descripcion = new JLabel("descripcion: ");
        JTextField descripcionDato = new JTextField(20);
        descripcionDato.setName("descripcionDato");
        descripcionDato.addFocusListener(escuchadoraTextField);


        JLabel prioridad = new JLabel("prioridad: ");
        Integer[] tipos = { 1, 2, 3, 4, 5 };
        JComboBox<Integer> prioridadDato = new JComboBox<Integer>(tipos);
        prioridadDato.setName("prioridadDato");
        prioridadDato.addActionListener(escuchadoraComboBox);
        prioridadDato.setEditable(false);


        JLabel resultado = new JLabel("resultado: ");
        Resultado[] tiposResultado = {new Biblioteca(), new Documentación(), new PaginaWeb(), new Programa()};
        JComboBox<Resultado> resultadoDato = new JComboBox<Resultado>(tiposResultado);
        resultadoDato.setName("resultadoDato");
        resultadoDato.addActionListener(escuchadoraComboBox);
        resultadoDato.setEditable(false);

        JLabel costeInicial = new JLabel("Coste Inicial: ");
        JTextField costeInicialDatos = new JTextField(20);
        costeInicialDatos.setName("costeInicialDatos");
        costeInicialDatos.addFocusListener(escuchadoraTextField);

        JLabel facturacion = new JLabel("facturacion: ");

        JComboBox<facturacion> facturacionDato = new JComboBox<>(tiposFacturacion);
        facturacionDato.setName("facturacionDato");
        facturacionDato.addActionListener(escuchadoraComboBox);
        facturacionDato.setEditable(false);

        JButton tareaBoton = new JButton("Añadir tarea a Proyecto");
        tareaBoton.addActionListener(escuchadoraBoton);

        panel.add(nombreTarea); panel.add(nombreTareaDato); panel.add(descripcion);panel.add(descripcionDato);
        panel.add(prioridad); panel.add(prioridadDato); panel.add(resultado); panel.add(resultadoDato);
        panel.add(costeInicial); panel.add(costeInicialDatos);panel.add(facturacion); panel.add(facturacionDato);
        panel.add(tareaBoton);

        container.add(panel);
        ventana.pack();
        ventana.setLocationRelativeTo(null);

        ventana.setVisible(true);
    }

    public void ventanaError(String error) {
        JOptionPane.showMessageDialog(null, error, "Error 404 ¯\\_(ツ)_/¯" , JOptionPane.WARNING_MESSAGE);
    }

    //---------METODOS----------

    public void setControlador(Controlador controlador){
        this.controlador = controlador;
    }

    public void setModelo(InterrogaModelo modelo) {
        this.modelo = modelo;
    }

    public Tarea getTareaSeleccionada() {
        return tareaSeleccionada;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public Persona getPersonaSeleccioanda() {
        if (personaSeleccioanda == null)
            ventanaError("Persona no seleccionada");
        return personaSeleccioanda;
    }

    public Persona getPersonaDeTareaSeleccionada() {
        return personaDeTareaSeleccionada;
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

    public void setPersonasEnTarea(Persona[] personas) {
        this.personasEnTarea.setListData(personas);
    }

    public void setVectorPersonasEnTarea(Persona[] vectorPersonasEnTarea) {
        this.vectorPersonasEnTarea = vectorPersonasEnTarea;
    }

    public void setPersonaDeTareaSeleccionada(Persona personaDeTareaSeleccionada) {
        this.personaDeTareaSeleccionada = personaDeTareaSeleccionada;
    }


    //METODOS PARA MODELO

    @Override
    public void actualizarListasInterfaz(){
        setTareas(modelo.getTareasEnProyecto());
        setPersonas(modelo.getPersonasEnProyecto());
        if (getTareaSeleccionada() != null)
            setPersonasEnTarea( modelo.getListaPersonasEnTarea(tareaSeleccionada));
    }

    @Override
    public void actualizarInfoTareaSeleccionada(){
        if (getTareaSeleccionada() == null)
            return;
        controlador.calcularCosteTotal();
        costeTotal.setText("Coste total: " + modelo.getCosteTotalProyecto());
        setPersonasEnTarea( modelo.getListaPersonasEnTarea(tareaSeleccionada));
        nombreTarea.setText("Titulo: " + modelo.getTitulo(tareaSeleccionada));
        coste.setText("Coste Final: " + modelo.getCosteFinalTarea(tareaSeleccionada));
        etiquetas.setText("Etiquetas: " + Arrays.toString(modelo.getEtiquetasTarea(tareaSeleccionada)));
        finalizada.setText("Finalizada: " + modelo.isTareaFinalizada(tareaSeleccionada));
        responsable.setText("Responsable: " + modelo.getResponsable(tareaSeleccionada));
        prioridad.setText("Prioridad: " + modelo.getPrioridad(tareaSeleccionada));
        resultado.setText("Resultado: " + modelo.getResultado(tareaSeleccionada));

        selectCoste.setText("");
        selectEtiqueta.setText("");
        facturacionDato.setSelectedItem(tareaSeleccionada.getFacturacion());

        switch (modelo.getFacturacionTarea(tareaSeleccionada).toString()){
            case "Consumo Interno" -> facturacionDato.setSelectedItem(tiposFacturacion[0]);
            case "Descuento" -> facturacionDato.setSelectedItem(tiposFacturacion[1]);
            case "Urgente" -> facturacionDato.setSelectedItem(tiposFacturacion[2]);
        }
    }

    @Override
    public void resetValue() {
        escuchadoraComboBox.resetValues();
        escuchadoraTextField.resetValues();
    }


    //METODOS PARA CONTROLADOR

    @Override
    public EscuchadoraTextField getEscuchadoraTextField() {
        return escuchadoraTextField;
    }

    @Override
    public EscuchadoraComboBox getEscuchadoraComboBox() {
        return escuchadoraComboBox;
    }
}

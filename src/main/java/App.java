import Controlador.Controlador;
import Controlador.Proyecto;
import Vista.InterfazGrafica;

public class App {
    public static void main(String[] args) {
        Controlador proyecto = new Proyecto();
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setControlador(proyecto);
        proyecto.setInterrogaVista(interfaz);

        interfaz.matame();
    }
}

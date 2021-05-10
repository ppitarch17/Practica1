package Vista;

import Controlador.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EscuchadoraBoton implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent evento) {
        JButton boton = (JButton)evento.getSource();
        switch (boton.getText()){
            case "Crear Proyecto" -> System.out.println("Escuchador del Botón");
        }

    }
}

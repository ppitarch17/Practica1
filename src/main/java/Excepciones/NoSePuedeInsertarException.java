package Excepciones;

public class NoSePuedeInsertarException extends Exception{

    public NoSePuedeInsertarException(){
        super("No se pudo añadir el elemento a la lista");
    }

    public NoSePuedeInsertarException(String msj){
        super(msj);
    }
}

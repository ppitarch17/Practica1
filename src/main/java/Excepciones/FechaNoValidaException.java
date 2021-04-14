package Excepciones;

public class FechaNoValidaException extends Exception{

    public FechaNoValidaException(){
        super("Fecha no valida");
    }

    public FechaNoValidaException(String msj){
        super(msj);
    }
}



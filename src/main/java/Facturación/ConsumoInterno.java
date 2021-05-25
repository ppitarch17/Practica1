package Facturación;

import java.io.Serializable;

public class ConsumoInterno implements facturacion, Serializable {
    @Override
    public double costeFacturacion(double coste) {
        return coste;
    }
    @Override
    public String toString(){
        return "Consumo Interno";
    }

}

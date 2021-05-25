package Facturación;

import java.io.Serializable;

public class Descuento  implements facturacion, Serializable {
    int descuentoPorClienteEspecial = 50;
    @Override
    public double costeFacturacion(double coste) {
        return coste -= descuentoPorClienteEspecial;
    }
    @Override
    public String toString(){
        return "Descuento";
    }
}

package Facturación;

import java.io.Serializable;

import static java.lang.Math.round;

public class Descuento  implements facturacion, Serializable {
    double descuentoPorClienteEspecial = 0.9;
    @Override
    public double costeFacturacion(double coste) {
        return round(coste * descuentoPorClienteEspecial);
    }
    @Override
    public String toString(){
        return "Descuento";
    }
}

package Facturación;

import static java.lang.Math.round;

public class Urgente implements facturacion {
    double sobrecoste = 1.1;
    @Override
    public double costeFacturacion(double coste) {
        return round(coste * sobrecoste);
    }

    @Override
    public String toString(){
        return "Urgente";
    }
}

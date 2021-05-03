package Facturación;

public class Descuento  implements facturacion {
    double descuento = 0.9;
    @Override
    public double costeFacturacion(double coste) {
        return coste * descuento;
    }
}

package Facturación;

public class Urgente implements facturacion {
    double sobrecoste = 1.1;
    @Override
    public double costeFacturacion(double coste) {
        return coste * sobrecoste;
    }
}

package Facturación;

public class Descuento  implements facturacion {
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

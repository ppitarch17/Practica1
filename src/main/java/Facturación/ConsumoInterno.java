package Facturación;

public class ConsumoInterno implements facturacion {
    @Override
    public double costeFacturacion(double coste) {
        return coste;
    }
    @Override
    public String toString(){
        return "Consumo Interno";
    }

}

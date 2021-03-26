package Resultados;

public abstract class Resultado {
    String identificador;
    int nHoras;
    boolean isInterno;

    @Override
    public String toString() {
        return identificador + "{" +
                "identificador='" + identificador + '\'' +
                ", nHoras=" + nHoras +
                ", isInterno=" + isInterno +
                '}';
    }
}

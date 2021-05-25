package Resultados;

import java.io.Serializable;

public abstract class Resultado implements Serializable {
    String identificador = getClass().getName();
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

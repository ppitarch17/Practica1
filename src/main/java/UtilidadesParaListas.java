import java.util.List;

public class UtilidadesParaListas<E> implements tieneClave<E> {

    public static <E> List<E> elementosConListaVacia(List<E> lista) {
        return null;
    }

    public boolean sePuedeInsertar(E objeto, E objetoAAñadir) {
        return true;
    }


    @Override
    public E getClave() {
        return null;
    }
}

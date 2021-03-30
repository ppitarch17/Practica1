import java.util.List;

public class UtilidadesParaListas<E> implements tieneClave<E> {

    public static <E> List<E> elementosConListaVacia(List<E> lista) {
        for (E objectInList: lista) {
            if (objectInList.getLista()){
                lista.remove(objectInList);
            }
        }
        return lista;
    }

    public boolean sePuedeInsertar(E objetoConLista, E objetoAAñadir) {
        for (Object ObjectInList :objetoConLista.getLista()) {
            if(ObjectInList.getClave() == objetoAAñadir.getClave())
                return false;
        }
        return true;
    }


    @Override
    public E getClave() {
        return null;
    }
}

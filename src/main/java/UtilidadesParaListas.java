import java.util.List;

public class UtilidadesParaListas<E> implements tieneClave, tieneLista<E> {
//
//    public static <E> List<E> elementosConListaVacia(tieneLista<E> lista) {
//        for (tieneLista<E> objectInList : lista.getLista()) {
//            if (objectInList.isEmpty()){
//                lista.remove(objectInList);
//            }
//        }
//        return lista;
//    }
//
//    public boolean sePuedeInsertar(tieneLista objetoConLista, tieneClave objetoAAñadir) {
//        for (Object ObjectInList :objetoConLista.getLista()) {
//            if((tieneClave) ObjectInList.getClave() == objetoAAñadir.getClave())
//                return false;
//        }
//        return true;
//    }
//

    @Override
    public Object getClave() {
        return null;
    }

    @Override
    public List getLista() {
        return null;
    }
}

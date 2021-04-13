import java.util.ArrayList;
import java.util.List;

public class UtilidadesParaListas<E> {

    public static List<Object> test (List<tieneLista> obj){
        ArrayList result = new ArrayList<>();
        System.out.println("Probando");
        for (tieneLista objEnLista: obj) {
//            System.out.println(objEnLista.getLista());
            if (objEnLista.getLista().isEmpty())
                result.add(objEnLista);
        }
        return result;
    }

//    public static <E> List elementosConListaVacia(List<tieneLista> lista) {
//        System.out.println(lista.getLista());
//        return lista.getLista();
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
}

import java.io.FileOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilidadesParaListas implements Serializable {

    public static <T,E extends tieneLista<T>> List<E> elementosConListaVacia (List<E> obj){
        ArrayList<E> result = new ArrayList<>();
        for (E objEnLista: obj) {
            if (objEnLista.getLista().isEmpty())
                result.add(objEnLista);
        }
        return result;
    }

    public static <T,E extends tieneClave<T>> boolean sePuedeInsertar (E elemento, tieneLista<E> objetoConLista){

        for ( E objeto : objetoConLista.getLista()) {
            if(objeto.getClave().equals(elemento.getClave())){
                return false;
            }

        }
        return true;
    }

    public static void main(String[] args) {

    }
}

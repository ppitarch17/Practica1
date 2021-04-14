import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilidadesParaListas {

    public static <E extends tieneLista<E>> List<E> elementosConListaVacia (List<E> obj){
        ArrayList<E> result = new ArrayList<>();
        for (E objEnLista: obj) {
            if (objEnLista.getLista().isEmpty())
                result.add(objEnLista);
        }
        return result;
    }

    public static <T,E extends tieneClave<T>> boolean sePuedeInsertar (E elemento, tieneLista<E> objetoConLista){

        for ( E objeto : objetoConLista.getLista()) {
            if(objeto.getClave().equals(objeto.getClave()))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {


        Tarea tarea = new Tarea();
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");

        System.out.println(persona.addTarea(null));
        System.out.println(persona.addTarea(tarea));
        System.out.println(persona.addTarea(tarea));


        System.out.println(persona.getLista());

    }
}

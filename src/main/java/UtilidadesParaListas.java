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

//        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
//
//        Tarea tarea = new Tarea("tarea con lista");
//        tarea.addPersona(persona);
//
//        persona.addTarea(new Tarea("tarea con lista vacia"));
//        persona.addTarea(new Tarea("tarea con lista vacia2"));
//
//        System.out.println(elementosConListaVacia(persona.getLista()));
//
//        System.out.println("\n---\n");
//
//        tarea = new Tarea();
//        persona = new Persona("Persona con lista vacia", "persona@uji.es", "11111111A");
//        Persona persona2 = new Persona("Persona con lista", "persona2@uji.es", "2A");
//        persona2.addTarea(tarea);
//
//        tarea.addPersona(persona);
//        tarea.addPersona(persona2);
//
//        System.out.println(elementosConListaVacia(tarea.getLista()));

    }
}

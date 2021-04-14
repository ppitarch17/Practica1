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

    public static <E extends tieneClave<E>> boolean sePuedeInsertar (E elemento, tieneLista<E> objetoConLista){
        if(elemento == null || objetoConLista == null)
            return false;

        for ( E objeto : objetoConLista.getLista()) {
            if(objeto.getClave().equals(objeto.getClave()))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {

//        Tarea tarea = new Tarea();
//        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
//        System.out.println(sePuedeInsertar(persona, tarea));
//        tarea.addPersona(persona);
//        System.out.println(sePuedeInsertar(persona, tarea));
//
//        System.out.println("---");
//        tarea = new Tarea();
//        Persona persona2 = new Persona("Persona2", "persona2@uji.es", "222222222A");
//
//        System.out.println(sePuedeInsertar(tarea, persona2));
//        persona2.addTarea(tarea);
//        System.out.println(sePuedeInsertar(tarea, persona2));

//        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
//        Tarea tarea = new Tarea("Tarea con lista llena");
//
//        persona.addTarea(new Tarea("Tarea con lista vacia"));
//        persona.addTarea(new Tarea("Otra Tarea con lista vacia"));
//        persona.addTarea(tarea);
//        tarea.addPersona(persona);
//
//        Persona persona2 = new Persona("Persona2", "persona2@uji.es", "222222222A");
//        tarea.addPersona(persona2);
//
//        System.out.println("---");
//
//        //System.out.println(UtilidadesParaListas.elementosConListaVacia(persona.getLista()));
//        //System.out.println(UtilidadesParaListas.elementosConListaVacia(tarea.getLista()));
//
//        System.out.println("-------------");

        Tarea tarea = new Tarea();
        tarea.marcarComoFinalizada(LocalDate.of(1900,1,1));

    }
}

import java.util.ArrayList;
import java.util.List;

public class UtilidadesParaListas<E> {

    public static List<Object> elementosConListaVacia (List<tieneLista> obj){
        ArrayList result = new ArrayList<>();
        //System.out.println("Probando");
        for (tieneLista objEnLista: obj) {
            //System.out.println(objEnLista.getLista());
            if (objEnLista.getLista().isEmpty())
                result.add(objEnLista);
        }
        return result;
    }

    public static boolean sePuedeInsertar (List<tieneClave> lista, tieneClave objeto){
        if(lista == null || objeto == null)
            return false;

        for ( tieneClave elemento : lista) {
            if(elemento.getClave().equals(objeto.getClave()))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Tarea tarea = new Tarea();
        Persona persona = new Persona("Persona", "persona@uji.es", "11111111A");
        System.out.println(sePuedeInsertar(tarea.getLista(), persona));
        tarea.addPersona(persona);
        System.out.println(sePuedeInsertar(tarea.getLista(), persona));

        System.out.println("---");
        tarea = new Tarea();
        Persona persona2 = new Persona("Persona2", "persona2@uji.es", "222222222A");

        System.out.println(sePuedeInsertar(persona2.getLista(), tarea));
        persona2.addTarea(tarea);
        System.out.println(sePuedeInsertar(persona2.getLista(), tarea));

    }
}

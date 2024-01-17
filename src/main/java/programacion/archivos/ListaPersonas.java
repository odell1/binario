package programacion.archivos;




import java.util.ArrayList;
import java.util.List;

public class ListaPersonas {
	
    private List<Persona> lista = new ArrayList<Persona>();
    
    public ListaPersonas(){    	
    }

    public void add(Persona per) {
            lista.add(per);
    }
   
   public List<Persona> getLista() {
        return lista;
}

public List<Persona> getListaPersonas() {
            return lista;
    }

    public int tamanio(){return lista.size();}

public Persona get(int i) {
       return lista.get(i);
}
}


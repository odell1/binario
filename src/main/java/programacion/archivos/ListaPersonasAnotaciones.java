package programacion.archivos;




import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ListaPersonasMunicipio")
public class ListaPersonasAnotaciones {
	
    private List<Persona> lista = new ArrayList<Persona>();
    
    public ListaPersonasAnotaciones(){    	
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


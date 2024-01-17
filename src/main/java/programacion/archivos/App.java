package programacion.archivos;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import com.thoughtworks.xstream.XStream;

////////////////////////////////
// Programa que crea 9 objetos de tipo Persona. Se crea un archivo binario serializado con esos objetos.
// Se leen los objetos para luego crear un archivo Personas.xml
////////////////////////////////
public class App 
{

    public static void main( String[] args ) throws IOException, ClassNotFoundException
    {
        ListaPersonas listaImpresion=new ListaPersonas();
        EscribirFicheroObjeto();
        listaImpresion=LeerFicheroObjeto();
        Imprimir(listaImpresion);
        //creaXML(listaImpresion);
        creaXMLAnotaciones(listaImpresion);
      
    }//main
 


    private static void creaXMLAnotaciones(ListaPersonas listaImpresion) {
        ListaPersonasAnotaciones listaPersonasAnotaciones=new ListaPersonasAnotaciones();
        for (int i = 0; i < listaImpresion.tamanio(); i++) 
                    listaPersonasAnotaciones.add(listaImpresion.get(i));
    
        XStream xstream = new XStream();
        xstream.autodetectAnnotations(true);
           
        xstream.processAnnotations(ListaPersonasAnotaciones.class);
        xstream.processAnnotations(PersonaAnotaciones.class);
            
        String xml = xstream.toXML(listaPersonasAnotaciones);


        System.out.println(xml);
        
    }//creaXMLAnotaciones



    private static void creaXML(ListaPersonas listaImpresion) {
          try{
            XStream xstream = new XStream();
            // cambiar de nombre a las etiquetas XML
            xstream.alias("ListaPersonasMunicipio", ListaPersonas.class);
            xstream.alias("DatosPersona", Persona.class);
    
            // Insertar los objetos en el XML
            xstream.toXML(listaImpresion, new FileOutputStream("Personas.xml"));
            System.out.println("Creado fichero XML....");
    
      } catch (Exception e) {
        e.printStackTrace();
      }
    }//creaXML



    /////////////////////////
    // Escritura de fichero binario. Escritura de objeto.
    ////////////////////////
    private static void EscribirFicheroObjeto() throws IOException{
      Persona persona;//defino variable persona
   
      File fichero = new File("FichPersona.dat");//declara el fichero
      FileOutputStream fileout = new FileOutputStream(fichero,true);  //crea el flujo de salida
       //conecta el flujo de bytes al flujo de datos
      ObjectOutputStream dataOS = new ObjectOutputStream(fileout);  
      
      String nombres[] = {"Ana","Luis Miguel","Alicia","Pedro","Manuel","Andres",
                          "Julio","Antonio","Maria"};
                     
      int edades[] = {14,15,13,15,16,12,16,14,13};
      System.out.println("Grabando los datos de las personas.");      
      for (int i=0;i<edades.length; i++){ //recorro los arrays    
            persona= new Persona(nombres[i],edades[i]); //creo la persona	  
            dataOS.writeObject(persona); //escribo la persona en el fichero
            
            System.out.println(" persona "+persona.toString());
        
      }     
      dataOS.close();  //cerrar stream de salida    
   }//EscribirFicheroObjeto

   ///////////////////////////////
   // Lectura de fichero
   //////////////////////////////
private static ListaPersonas LeerFicheroObjeto() throws FileNotFoundException, IOException, ClassNotFoundException {
       Persona persona; // defino la variable persona
		File fichero = new File("FichPersona.dat");
		ObjectInputStream dataIS = new ObjectInputStream(new FileInputStream(fichero));

        ListaPersonas lista=new ListaPersonas();

		int i = 1;
		try {
			while (true) { // lectura del fichero
				persona = (Persona) dataIS.readObject(); // leer una Persona
                lista.add(persona);
				System.out.print(i + "=>");
				i++;
				System.out.printf("Nombre: %s, edad: %d %n",
						persona.getNombre(),persona.getEdad());

			}
		} catch (EOFException eo) {
			System.out.println("Fin de la lectura.");
		} catch (StreamCorruptedException x) {
		}

		dataIS.close(); // cerrar stream de entrada
        return lista;
}//LeerFicheroObjeto

   ///////////////////////////////
   // Impresión de la lista personas
   //////////////////////////////
   private static void Imprimir(ListaPersonas listaImpresion) {
   
    for (int i = 0; i < listaImpresion.tamanio(); i++) {
        
        System.out.print("\t\t "+ i + "=>");

        System.out.printf("Nombre: %s, edad: %d %n",
        listaImpresion.get(i).getNombre(),listaImpresion.get(i).getEdad());
        
    }//for
    
    }//Imprimir


}//App

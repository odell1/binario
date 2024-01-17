package programacion.archivos;



import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("judas")
public class PersonaAnotaciones implements Serializable{
	@XStreamAlias("nombre")
	private String nombre;
	@XStreamAlias("edad")
	private int edad;
	
	public PersonaAnotaciones(String nombre,int edad)	{
	  this.nombre=nombre;
	  this.edad=edad;	
	 }
	public PersonaAnotaciones() {
	  this.nombre=null;	  
	 }	
	public void setNombre(String nom){nombre=nom;}
	public void setEdad(int ed){edad=ed;}
	
	public String getNombre(){return nombre;}
	public int getEdad(){return edad;}
	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + "]";
	}	
	
}//fin Persona

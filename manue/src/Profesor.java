import java.io.Serializable;

/*
 * Clase Profesor
 * 
 * Propiedades Relevantes:
 * 		Nombre: String, consultable, modificable
 * 		Edad: int, consultable, modificable
 * 		Sexo: char, consultable, modificable
 * 
 * Propiedades Derivadas:
 * 
 * Propiedades Compartidas:
 * 
 * Interfaz:
 * 		String getNombre()
 * 		int getEdad()
 * 		char getSexo()
 * 		void setNombre(String Nombre)
 * 		void setEdad(int Edad)
 * 		void setSexo(char Sexo)
 * 
 */

public class Profesor implements Cloneable, Comparable<Profesor>, Serializable{
	//Propiedades
	private static final long serialVersionUID=1l;
	private String nombre;
	private int edad;  //20-80
	private char sexo;  // M - F
	
	//Constructores
	public Profesor(){
		nombre="default";
		edad=0;
		sexo='M';
	}
	
	public Profesor(String nombre, int edad, char sexo) throws ExcepcionProfesor{
		
		if((edad<20 && edad>80) || (sexo!='M' && sexo!='F')){
			throw new ExcepcionProfesor("Edad o Sexo erroneo.");
		}else{
			this.nombre=nombre;
			this.edad=edad;
			this.sexo=sexo;
		}
	}
	
	public Profesor(Profesor p){
		this.nombre=p.nombre;
		this.edad=p.edad;
		this.sexo=p.sexo;
	}	
	
	//Consultores
	public String getNombre(){
		return nombre;
	}
	
	public int getEdad(){
		return edad;
	}
	
	public char getSexo(){
		return sexo;
	}
	
	//Modificadores
	public void setNombre(String nombre){
		this.nombre=nombre;
	}
	
	public void setEdad(int edad) throws ExcepcionProfesor{
		if(edad<20 && edad>80){
			throw new ExcepcionProfesor("Edad erronea");
		}else{
			this.edad=edad;
		}
	}
	
	public void setSexo(char sexo) throws ExcepcionProfesor{
		if(sexo!='M' && sexo!='S'){
			throw new ExcepcionProfesor("Sexo erroneo.");
		}else{
			this.sexo=sexo;
		}
	}
	
	//M�todos Sobrescritos
	@Override
	public String toString(){
		String s;
		s=getNombre()+","+getEdad()+","+getSexo();
		return s;
	}
	
	@Override
	public int hashCode() {
		int codigo = 0;
			codigo=getNombre().hashCode();
		return codigo;
	}
	
	
	@Override
	public boolean equals(Object o) {
		boolean igual = false;

		if (o != null && o instanceof Profesor) {
			Profesor profesor = (Profesor) o;
			if (this.getNombre() == profesor.getNombre() && this.getEdad() == profesor.getEdad()) {
				igual = true;
			}
		}
		return igual;
	}
	
	@Override
	public Profesor clone(){
		Profesor copia = null;
		try {
			copia = (Profesor) super.clone();
			System.out.println("Profesor clonado con exito");
		} catch (CloneNotSupportedException error) {
			System.out.println("Profesor no clonado");
		}
		return copia;
	}

	@Override
	public int compareTo(Profesor p) {
		int compara = 0;
			if(this.getEdad()>p.getEdad()){
				compara = 1;
			}else{
				if(this.getEdad()<p.getEdad()){
					compara = -1;
				}
			}
		return compara;
	}
}

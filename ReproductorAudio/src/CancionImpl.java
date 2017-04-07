/*
 * PROPIEDADES:
 *     Basicas: 
 * 	       String ruta           | Consultable y modificable
 *         String nombre         | Consultable y modificable		     
 * 		   String numeroEnLista  | Consultable y modificable	
 * 
 * 		Derivadas: 
 * 			Ninguna
 * 
 * 		Compartidas:
 * 			Ninguna
 * 
 * RESTRICCIONES:
 * 		El fichero debe existir en la memoria secundaria (la ruta del fichero debe ser valida)
 *      El fichero tiene que tener un nombre
 *      El fichero tiene que tener un puesto en la lista de reproduccion comprendido entre 1 y 100 
 */

import java.io.Serializable;

public class CancionImpl implements Cancion, Comparable<CancionImpl>, Serializable 
{
	//Atributos
    private String ruta;
    private String nombre;
    private int numeroEnLista;
    
    private static final long serialVersionUID = 1L;
    
    //Constructores
    //Constructor por defecto
    public CancionImpl()
    {
    	ruta = " ";
    	nombre = " ";
    	numeroEnLista = 1;
    }
    
    //Constructor con parametros
    public CancionImpl(String ruta, String nombre, int numeroEnLista)
    {
    	this.ruta = ruta;
    	this.nombre = nombre;
    	this.numeroEnLista = numeroEnLista;
    }
    
    //Constructor de copia
    public CancionImpl(CancionImpl cancion)
    {
    	this.ruta = cancion.getRuta();
    	this.nombre = cancion.getNombre();
    	this.numeroEnLista = cancion.getNumeroEnLista();
    }

    //Metodos consultores
    public String getRuta() 
    {
		return ruta;
	}
    
    public String getNombre() 
    {
        return nombre;
    }
    
    public int getNumeroEnLista() 
    {
    	return numeroEnLista;
    }

    //Metodos modificadores
    public void setNombre(String nombre) 
    {
		if( nombre instanceof String )
		{
			 this.nombre = nombre;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: El nombre de la cancion no es una cadena");
		}
    }
    
    public void setRuta(String ruta) 
    {
		if( ruta instanceof String )
		{
			this.ruta = ruta;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: La ruta de la cancion no es una cadena");
		}
	
    }
    
    public void setNumeroEnLista(int numeroEnLista) 
    {
    	if( numeroEnLista > 0 && numeroEnLista < 100)
		{
			this.numeroEnLista = numeroEnLista;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: El numero de la cancion debe estar comprendido entre 1 y 100");
		}
    }
    
    //To string sobreescrito
    @Override
    public String toString()
    {
    	String string = getRuta()+", "+getNombre()+", "+getNumeroEnLista();
        return string;
    }

	/*INTERFAZ	
	 * Cabecera:        public int compareTo(CancionImpl objeto)  
	 * Descripcion:     Compara el nombre de una cancion con otra pasada por parametro
	 * Precondiciones:  El fichero debe ser un fichero de audio con un formato valido.
	 * Entradas:        Objeto con el nombre y la ruta del fichero.
	 * Salidas:		    Devuelve 0 si son iguales, 1 si son distintos
	 * Postcondiciones: Ninguna
	*/
	@Override
	public int compareTo(CancionImpl o) 
	{
    	CancionImpl cancion = (CancionImpl) o;
    	
    	int comparacion = 0;
        String nombreObjeto = cancion.getNombre().toLowerCase();
        String nombreCancion = this.getNombre().toLowerCase();
            
        if (nombreCancion == nombreObjeto)
           comparacion = 0;
        else
           comparacion = 1;
            
    		return comparacion;
	}
}

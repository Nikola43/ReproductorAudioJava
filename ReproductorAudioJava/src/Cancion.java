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
 */

public interface Cancion 
{
	//Metodos consultores
	public String getRuta();
	
	//Metodos modificadores
	public void setRuta(String ruta);
}
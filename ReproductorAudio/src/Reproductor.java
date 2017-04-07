import javax.media.Player;

/*
 * PROPIEDADES:
 *     Basicas: 
 * 	       Player myPlayer       | Consultable y modificable
 *         Cancion actual        | Consultable y modificable
 *         int estado            | Consultable y modificable
 *         boolean repetir       | Consultable y modificable 		     
 * 
 * 		Derivadas: 
 * 			Ninguna
 * 
 * 		Compartidas:
 *  		Ninguna
 * 
 * RESTRICCIONES:
 */

public interface Reproductor 
{
	//Metodos consultores
	public Player getPlayer();
	public CancionImpl getCancionActual();
	public int getEstado();
    public boolean getRepetir();
    
    //Metodos modificadores
    public void setCancionActual(CancionImpl cancionActual);
    public void setEstado(int estado);
    public void setRepetir(boolean repetir);
    public void setPlayer(Player player);
}

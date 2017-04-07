public class GestionReproductor
{
	/*INTERFAZ	
	 * Cabecera:        limpiarPantalla()
	 * Descripcion:     Dirige el reproductor a una posicion concreta del audio
	 * Precondiciones:  La posicion indicada debe estar en el rango de tiempo del fichero
	 * Entradas:        La posicion a la que se quiere dirigir
	 * Salidas:		    Ninguna 
	 * Postcondiciones: Ninguna
	*/
	public void pausa()
	{
		try
		{

	        final String os = System.getProperty("os.name");
	        if (os.contains("Windows"))
	        	Runtime.getRuntime().exec("pause");
	        else
	            Runtime.getRuntime().exec("read a");
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
	
	public void limpiarPantalla()
	{
		try
		{

	        final String os = System.getProperty("os.name");
	        if (os.contains("Windows"))
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        else
	            Runtime.getRuntime().exec("clear");
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
}

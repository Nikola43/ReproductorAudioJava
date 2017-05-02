import java.io.File;
import java.net.MalformedURLException;


public class TestReproductor 
{
	public static void main(String[] args) 
	{

	

	
		UtilReproductor reproductor = new UtilReproductor();
		
		File file1 = new File("ff.mp3");
		File file2 = new File("ff.mp3");
		
		CancionImpl cancion1 = null;
		CancionImpl cancion2 = null;
		
		try {
			cancion1 = new CancionImpl(file1.toURI().toURL().toString(), file1.getName(), 1);
			cancion2 = new CancionImpl(file2.toURI().toURL().toString(), file2.getName(), 1);
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}
		
		
		System.out.println(cancion1.getRuta().compareTo(cancion2.getRuta()));

		
		
		//reproductor.imprimirListaReproduccion("paulo2.lis");
		//reproductor.renombrarFichero("paulo2.lis", "listaActualizada.lis");
		
		
		
		
		//reproductor.eliminarCancionListaReproduccion(cancionImpl, "paulo2.lis");
		//reproductor.imprimirListaReproduccion("paulo2.lis");

		
		
		//reproductor.listarListasDisponibles("canciones");
		//reproductor.crearListaReproduccion(nombreListaReproduccion);
		
		//gestionReproductor.agregarCancionListaReproduccion(cancionImpl, "new");
		//gestionReproductor.MostrarPrg("new");
		//reproductor.imprimirListaReproduccion(nombreListaReproduccion);
		//reproductor.eliminarCancionListaReproduccion(cancionImpl, nombreListaReproduccion);
		//reproductor.imprimirListaReproduccion("listaActualizada.lis");
		
		//reproductor.reproducirListaReproduccion(nombreListaReproduccion);
	}
}

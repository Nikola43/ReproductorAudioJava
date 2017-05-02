import java.io.*;
import java.net.URL;
import javax.media.*;
import javax.media.format.AudioFormat;

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
 *  		Niunguna
 *  	
 *  	Añadidas
 * 			-----
 * 			
 * 
 * RESTRICCIONES:
 */


public class ReproductorImpl implements Reproductor
{
	//Atributos
    private Player player;
    private CancionImpl cancionActual;
    private int estado;
    private boolean repetir;
    
    //Constructores 
    //Constructor por defecto
    public ReproductorImpl()
    {
    	player = null;
        cancionActual = null;
        estado = 0;
        repetir = false; 
    }
    
    //Contructor con parametro
    public ReproductorImpl(Player player, CancionImpl cancionActual, int estado, boolean repetir)
    {
    	this.player = player;
    	this.cancionActual = cancionActual;
    	this.estado = estado;
    	this.repetir = repetir; 
    }
    
    //Contructor de copia
    public ReproductorImpl(ReproductorImpl reproductor)
    {
    	this.player = reproductor.getPlayer();
    	this.cancionActual = reproductor.getCancionActual();
    	this.estado = reproductor.getEstado();
    	this.repetir = reproductor.getRepetir();
    }
    
    //Metodos get
    public Player getPlayer() 
    {
		return player;
	}
    
    public CancionImpl getCancionActual()
    {
        return cancionActual;
    }
    
    public int getEstado() 
    {
        return estado;
    }
    
    public boolean getRepetir() 
    {
        return repetir;
    }
    
    //Metodos set
    public void setCancionActual(CancionImpl cancionActual) 
    {
		if( cancionActual instanceof CancionImpl )
		{
			this.cancionActual = cancionActual;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: No se ha introducido un objeto tipo CancionImpl como parametro");
		}
	}
    
    public void setEstado(int estado) 
    {
		if( estado < 0 )
		{
			this.estado = estado;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: El estado del reproductor no puede ser inferior a 0");
		}
        
    }

    public void setRepetir(boolean repetir) 
    {
		if( repetir == true || repetir == false )
		{
			this.repetir = repetir;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: El estado del reproductor no puede ser inferior a 0");
		}
    }
    
    public void setPlayer(Player player) 
    {
		if( player instanceof Player )
		{
			this.player = player;
	    }
		else
		{
			throw new IllegalArgumentException ("Error: No se ha introducido un objeto tipo CancionImpl como parametro");
		}
	}
    
    //Metodos de player
    public double getSegundoActual()
    {
        return player.getMediaTime().getSeconds();
    }

    public double getDuracionTotal()
    {
        return player.getDuration().getSeconds();
    }
    
	/* INTERFAZ	
	 * Cabecera:        reproducir(CancionImpl cancion) 
	 * Descripcion:     Reproduce en los altavoces un fichero de audio. mp3, wav, ogg...
	 * Precondiciones:  El fichero debe ser un fichero de audio con un formato valido.
	 * Entradas:        Objeto con el nombre y la ruta del fichero.
	 * Salidas:		    Reproduce audio por los altavoces
	 * Postcondiciones: Ninguna
	*/
    public void reproducir(CancionImpl cancion) 
    {
    	
    	
        //Importamos el decodificador de audio ffmpeg para reproducir audio de diferentes formatos
        String jffmpegAudioDecoder = "net.sourceforge.jffmpeg.AudioDecoder";
        Codec codecAudio = null;
        
        // Instanciamos un nuevo objeto tipo Codec al que le pasamos como parametro el decodificador ffmpeg 
        try 
        {
            codecAudio = (Codec) Class.forName(jffmpegAudioDecoder).newInstance();
        } 
        catch (InstantiationException e) 
        {
                e.printStackTrace();
        } 
        catch (IllegalAccessException e) 
        {
            	e.printStackTrace();
        }
        catch (ClassNotFoundException e) 
        {
        	e.printStackTrace();
        }
        
        //A�adimos el plugin ffmpeg y el codec de audio                                   //formateamos los datos como audio lineal  //Pasamos como parametro el codec
        PlugInManager.addPlugIn(jffmpegAudioDecoder, codecAudio.getSupportedInputFormats(), new Format[]{new AudioFormat("LINEAR")}, PlugInManager.CODEC);
        
        try 
        {
            player = Manager.createPlayer(new URL(cancion.getRuta()));
        } 
        catch (IOException e) 
        {
        	e.printStackTrace();
        } 
        catch (NoPlayerException e) 
        {
        	e.printStackTrace();
        }
      
        //Empezamos a reproducir la cancion
        player.start();
        cancionActual = cancion;

        //Hacemos una pausa cada segundo para ir mostrando los minutos y la barra de progreso
        try 
        {
            while ( Math.round(getSegundoActual()) < Math.round(getDuracionTotal()))
        	{
				Thread.sleep(1000);
        		mostrarReproduccionActual(cancionActual);
        	}
        	
            //Pausamos reproduccion antes de siguiente cancion
            pararReproduccion();
            
		}
        catch (InterruptedException e) 
        {
			e.printStackTrace();
		}
      }
    
	/*INTERFAZ	
	 * Cabecera:        pararReproduccion()
	 * Descripcion:     Para la reproduccion del fichero
	 * Precondiciones:  El fichero debe estar reproduciendose
	 * Entradas:        Ninguna
	 * Salidas:		    Ninguna 
	 * Postcondiciones: Ninguna
	*/
    public void pararReproduccion()
    {
        if( cancionActual != null)
        {
            player.stop();
            player.close();
        }
    }
    
	/*INTERFAZ	
	 * Cabecera:        continuarReproduccion()
	 * Descripcion:     Reanuda la reproduccion del fichero que se estaba reproduciendo
	 * Precondiciones:  El fichero debe estar abierto, pero en pausa.
	 * Entradas:        Ninguna
	 * Salidas:		    Reproduccion del fichero por los altavoces 
	 * Postcondiciones: Ninguna
	*/
    public void continuarReproduccion()
    {
        player.start();
    }
    
	/*INTERFAZ	
	 * Cabecera:        pausarReproduccion()
	 * Descripcion:     Crear un fichero binario y escribe objetos en el
	 * Precondiciones:  Ninguna
	 * Entradas:        Ninguna
	 * Salidas:		    Ninguna 
	 * Postcondiciones: Ninguna
	*/
    public void pausarReproduccion()
    {
        player.stop();
    }
    
	/*INTERFAZ	
	 * Cabecera:        irPosicion(int posicion)
	 * Descripcion:     Dirige el reproductor a una posicion concreta del audio
	 * Precondiciones:  La posicion indicada debe estar en el rango de tiempo del fichero
	 * Entradas:        La posicion a la que se quiere dirigir
	 * Salidas:		    Ninguna 
	 * Postcondiciones: Ninguna
	*/
    public void irPosicion(int posicion)
    {
        player.setMediaTime(new Time(posicion));
    }

    public void mostrarReproduccionActual(CancionImpl cancionActual)
    {
    	
		long segundoActual = Math.round(getSegundoActual());
		long minutoActual = 0;
		
		long segundosTotal = Math.round(getDuracionTotal());
		long minutosTotal = segundosTotal / 60;
		
		int contadorPasos = (int) Math.round((Math.round(segundoActual * 100) / Math.round(segundosTotal)));
		
		
		System.out.println("\nDuracion: "+minutoActual+":"+segundoActual+" / "+minutosTotal+":"+segundosTotal);
		System.out.println("Nombre:   "+cancionActual.getNumeroEnLista()+". "+cancionActual.getNombre());
		
		System.out.println(contadorPasos+" %");
		
		char  barraLlena  = '\u2588';
		char  barraVacia = '\u2591';
		
		for ( int contadorBarritas = 0; contadorBarritas < contadorPasos; contadorBarritas++)
		{
			System.out.print(barraLlena);
		}
		
		//calculamos el tiempo de la animacion por 100%
        //frame = (int) Math.round((act * 100)/total);
        //cuando ambos sean iguales quiere decir que el video a alcanzado el final de la reproduccion
    }
   

	/*INTERFAZ	
	 * Cabecera:        limpiarPantalla()
	 * Descripcion:     Limpiar la consola de caracteres
	 * Precondiciones:  Ninguna
	 * Entradas:        Ninguna
	 * Salidas:		    Consola en blanco
	 * Postcondiciones: Ninguna
	*/
    public void limpiarPantallaReproductor()
	{
		try
		{
			//Comprobamos que sistema operativo esta ejecutando el programa
	        final String os = System.getProperty("os.name");
	        
	        //Si es windows
	        if (os.contains("Windows"))
	        {
	        	new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        }
	        else 
	        {
	        	// Linux o mac
	        	Runtime.getRuntime().exec("clear");
	        } 
	    }
	    catch (final Exception e)
	    {
	        e.printStackTrace();
	    }
	}
}

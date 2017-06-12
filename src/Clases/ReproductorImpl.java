package Clases;

import Interfaces.Reproductor;
import Util.UtilFicheros;

import javax.media.*;
import javax.media.format.AudioFormat;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.Duration;

public class ReproductorImpl implements Reproductor, Serializable
{
//------------------------------- PROPIEDADES -----------------------------------------------//
    //BASICAS
    private Player reproductor;

    //DERIVADAS
        //NINGUNA

    //COMPARTIDAS
        //NINGUNA
//------------------------------- FIN PROPIEDADES --------------------------------------------//

//------------------------------- CONSTRUCTORES ----------------------------------------------//
    //CONSTRUCTOR POR DEFECTO
    public ReproductorImpl()
    {
        reproductor = null;
    }

    //CONSTRUCTOR SOBRECARGADO
    public ReproductorImpl(Player reproductor)
    {
        this.reproductor = reproductor;
    }

    //CONSTRUCTOR DE COPIA
    public ReproductorImpl(ReproductorImpl reproductor)
    {
        this.reproductor = reproductor.getReproductor();
    }
//------------------------------- FIN CONSTRUCTORES ------------------------------------------//

//------------------------------- METODOS CONSULTORES ----------------------------------------//
    @Override
    public Player getReproductor()
    {
        return reproductor;
    }
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    public void setReproductor(Player reproductor)
    {
        this.reproductor = reproductor;
    }
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS SOBRESCRITOS ---------------------------------------//
    @Override
    public String toString()
    {
        String toString;
        toString = getReproductor().toString();
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = getReproductor().toString().charAt(5) * 12 * 5 / 2 + getReproductor().toString().charAt(1);
        return hashCode;
    }

    @Override
    public ReproductorImpl clone()
    {
        ReproductorImpl clonReproductor = null;

        try
        {
            clonReproductor = (ReproductorImpl) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clonReproductor;
    }
//------------------------------- FIN METODOS SOBRESCRITOS -----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//

	/* INTERFAZ
	 * Cabecera:        reproducir(Clases.CancionImpl cancion)
	 * Descripcion:     Reproduce en los altavoces un fichero de audio. mp3, wav, ogg...
	 * Precondiciones:  El fichero debe ser un fichero de audio con un formato valido.
	 * Entradas:        Objeto con el nombre y la ruta del fichero.
	 * Salidas:		    Reproduce audio por los altavoces
	 * Postcondiciones: Ninguna
	*/
public void reproducirCancion(CancionImpl cancion)
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
        if (UtilFicheros.ficheroEsCancion(cancion.getRuta()))
        {
            reproductor = Manager.createPlayer(new URL(cancion.getRuta()));
        }
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
    reproductor.start();

}

    /*INTERFAZ
     * Cabecera:        pararReproduccion()
     * Descripcion:     Para la reproduccion del fichero
     * Precondiciones:  El fichero debe estar reproduciendose
     * Entradas:        Ninguna
     * Salidas:		    Ninguna
     * Postcondiciones: Ninguna
    */
    @Override
    public void pararReproduccion()
    {
        reproductor.stop();
        reproductor.close();
    }


    /*INTERFAZ
     * Cabecera:        pausarReproduccion()
     * Descripcion:     Crear un fichero binario y escribe objetos en el
     * Precondiciones:  Ninguna
     * Entradas:        Ninguna
     * Salidas:		    Ninguna
     * Postcondiciones: Ninguna
    */
    @Override
    public void pausarReproduccion()
    {
        reproductor.stop();
    }

    /*INTERFAZ
     * Cabecera:        irPosicion(int posicion)
     * Descripcion:     Dirige el reproductor a una posicion concreta del audio
     * Precondiciones:  La posicion indicada debe estar en el rango de tiempo del fichero
     * Entradas:        La posicion a la que se quiere dirigir
     * Salidas:		    Ninguna
     * Postcondiciones: Ninguna
    */
    @Override
    public void rebobinarAtras(int posicion)
    {
        double posicionActual = reproductor.getMediaTime().getSeconds();
        Time posicionDestino = new Time(posicionActual - posicion);

        if ( posicionActual - posicion > 1)
        {
            reproductor.setMediaTime(posicionDestino);
        }
    }

    @Override
    public void rebobinarAdelante(int posicion)
    {
        double posicionActual = reproductor.getMediaTime().getSeconds();
        Time posicionDestino = new Time(posicionActual + posicion);

        if ( posicionActual + posicion < reproductor.getDuration().getSeconds() )
        {
            reproductor.setMediaTime(posicionDestino);
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

    @Override
    public void reanudarReproduccion()
    {
        reproductor.start();
    }

    public void mostrarReproduccionActual(CancionImpl cancionActual)
    {

        long duracionTotalMilisegundos = 0;

        long horasActual;
        long minutosActual;
        long segundosActual;

        long horasTotal;
        long minutosTotal;
        long segundosTotal;

        int contadorPasos = 0;
        char  barraLlena  = '\u2588';

        Duration duracionCancionActual;
        Duration duracionCancionTotal;





        duracionTotalMilisegundos += Double.valueOf(cancionActual.extraerMetadatos()[6]);



        duracionCancionTotal = Duration.ofMillis(duracionTotalMilisegundos);
        horasTotal = duracionCancionTotal.toHours();
        minutosTotal = (int) ((duracionCancionTotal.getSeconds() % (60 * 60)) / 60);
        segundosTotal = (int) (duracionCancionTotal.getSeconds() % 60);


        duracionCancionActual = Duration.ofSeconds((long) reproductor.getMediaTime().getSeconds());
            horasActual = duracionCancionActual.toHours();
            minutosActual = (int) ((duracionCancionActual.getSeconds() % (60 * 60)) / 60);
            segundosActual = (int) (duracionCancionActual.getSeconds() % 60);

            contadorPasos = Math.round((Math.round(reproductor.getMediaTime().getSeconds() * 100) / Math.round(reproductor.getDuration().getSeconds())));


            System.out.println("Titulo :  "+cancionActual.extraerMetadatos()[0]);
            System.out.println("Artista:  "+cancionActual.extraerMetadatos()[1]);
            System.out.println("Numero:   "+cancionActual.extraerMetadatos()[2]);
            System.out.println("Album:    "+cancionActual.extraerMetadatos()[3]);
            System.out.println("Año:      "+cancionActual.extraerMetadatos()[4]);
            System.out.println("Genero:   "+cancionActual.extraerMetadatos()[5]);
            System.out.println("Duracion: "+horasTotal+":"+minutosTotal+":"+segundosTotal);

            System.out.println("\nProgreso: "+horasActual+":"+minutosActual+":"+segundosActual+" / "+horasTotal+":"+minutosTotal+":"+segundosTotal);

            for ( int contadorBarritas = 0; contadorBarritas < contadorPasos; contadorBarritas++)
            {

                System.out.print(barraLlena);
            }

            try
            {
                Thread.sleep(500);
                refescarBarraProgreso();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //manejarReproduccion();




        //calculamos el tiempo de la animacion por 100%
        //frame = (int) Math.round((act * 100)/total);
        //cuando ambos sean iguales quiere decir que el video a alcanzado el final de la reproduccion
    }


    public void manejarReproduccion()
    {
        final int TECLA_FLECHA_DERECHA   = 77;
        final int TECLA_FLECHA_IZQUIERDA = 75;
        final int TECLA_FLECHA_ARRIBA    = 72;
        final int TECLA_FLECHA_ABAJO     = 80;

        final int TECLA_P     = 112;
        final int TECLA_R     = 114;

        int tecla;

        LeerTeclasUsandoCPP leerTeclasUsandoCPP = new LeerTeclasUsandoCPP();

        if ( leerTeclasUsandoCPP.hayTeclaPulsada() )
        {
            tecla = leerTeclasUsandoCPP.leerTecla();

            switch (tecla)
            {
                case TECLA_FLECHA_DERECHA    : rebobinarAdelante(10);break;
                case TECLA_FLECHA_IZQUIERDA  : rebobinarAtras(10); break;
                case TECLA_FLECHA_ARRIBA     : System.out.println("up"); break;
                case TECLA_FLECHA_ABAJO      : System.out.println("down"); break;
                case TECLA_P      : pausarReproduccion(); break;
                case TECLA_R      : reanudarReproduccion(); break;
            }
        }
    }

    private void refescarBarraProgreso()
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
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//

}

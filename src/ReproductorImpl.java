import com.sun.media.controls.GainControlAdapter;

import javax.media.*;
import javax.media.format.AudioFormat;
import javax.sound.sampled.FloatControl;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

public class ReproductorImpl implements Reproductor, Comparable<ReproductorImpl>, Serializable
{
//------------------------------- PROPIEDADES -----------------------------------------------//
    //BASICAS
    Player reproductor;

    //DERIVADAS
    //int estadoActual

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

    /* INTERFAZ
    Cabecera:
        public int getEstadoActual()
    Descripcion:
        Devuelve el estado actual del reproductor
    Precondiciones:
        -
    Entradas:
        -
    Salidas:
        Un entero
    Postcondiciones:
        - Devolvera -1 cuando el reproductor este PARADO
        - Devolvera 0  cuando el reproductor este PAUSADO
        - Devolvera 1  cuando el reproductor este REPRODUCIENDO
*/
    public int getEstadoActual()
    {
        int estadoActual;

        estadoActual = 1;

        return estadoActual;
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
        toString = getReproductor().toString()+","+getEstadoActual();
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = getReproductor().toString().charAt(5) * 12 + getEstadoActual() * 5 / 2 + getReproductor().toString().charAt(1);
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

    /* INTERFAZ
       Cabecera:
           public int compareTo(ReproductorImpl reproductor)
       Descripcion:
          Compara un reproductor con otro segun su estado
       Precondiciones:
          Ninguna
       Entradas:
          Un objeto reproductor
       Salidas:
          Un entero
       Postcondiciones:
          - Devolvera -1 cuando sea menor, 0 cuando sean iguales, 1 cuando sea mayor
          - Sera menor cuando el estado del objeto que lanza el metodo sea inferior al objeto pasado por parametro
          - Sera mayor cuando el estado del objeto que lanza el metodo sea superior al objeto pasado por parametro
          - Sera igual cuando el estado del objeto que lanza el metodo sea iguales  al objeto pasado por parametro
    */
    @Override
    public int compareTo(ReproductorImpl reproductor)
    {
        int compareTo = -1;

        if ( reproductor != null )
        {
            if ( this.getEstadoActual() > reproductor.getEstadoActual()  )
            {
                compareTo = 1;
            }
            else if ( this.getEstadoActual() < reproductor.getEstadoActual() )
            {
                compareTo = -1;
            }
            else
            {
                compareTo = 0;
            }
        }
        return compareTo;
    }

    /* INTERFAZ
       Cabecera:
           public boolean equals(Object object)
       Descripcion:
           Comprueba si el objeto que lanza el metodo es igual que el objeto pasado por parametro
       Precondiciones:
           Ninguna
       Entradas:
           Un objeto
       Salidas:
           Un booleano
       Postcondiciones:
           - Los objetos seran iguales cuando tengan
           - Devolvera VERDADERO si los objetos son iguales y FALSO cuando no lo sean
     */
    @Override
    public boolean equals(Object object)
    {
        boolean esIgual = false;

        if (object != null && object instanceof ReproductorImpl)
        {
            ReproductorImpl reproductor = (ReproductorImpl) object;

            //Si tienen el mismo nombre son canciones iguales
            if (this.reproductor == reproductor.getReproductor() )
            {
                esIgual = true;
            }
        }
        return esIgual;
    }
//------------------------------- FIN METODOS SOBRESCRITOS -----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    public double getSegundoActual()
    {
        return reproductor.getMediaTime().getSeconds();
    }

    public double getDuracionTotal()
    {
        return reproductor.getDuration().getSeconds();
    }

	/* INTERFAZ
	 * Cabecera:        reproducir(CancionImpl cancion)
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
        //reproductor = Manager.createPlayer(new URL(cancion.getRuta()));
        reproductor = Manager.createRealizedPlayer(new URL(cancion.getRuta()));
    }
    catch (CannotRealizeException e) {
        e.printStackTrace();
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

        long segundoActual = 0;
        long minutoActual = 0;

        long segundosTotal = 0;
        long minutosTotal =  0;

        int contadorPasos = 0;
        char  barraLlena  = '\u2588';


        while ( Math.round(this.getSegundoActual()) < Math.round(this.getDuracionTotal()))
        {




            segundoActual = Math.round(this.getSegundoActual());
            segundosTotal = Math.round(this.getDuracionTotal());
            minutoActual = segundosTotal / 60;
            contadorPasos = (int) Math.round((Math.round(segundoActual * 100) / Math.round(segundosTotal)));

            System.out.println("\nDuracion: "+minutoActual+":"+segundoActual+" / "+minutosTotal+":"+segundosTotal);

            System.out.println();



            for ( int contadorBarritas = 0; contadorBarritas < contadorPasos; contadorBarritas++)
            {
                System.out.print(barraLlena);
            }
            manejarReproduccion();

            try
            {
                Thread.sleep(200);
                refescarBarraProgreso();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }



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
                case TECLA_FLECHA_DERECHA    : rebobinarAdelante(3);break;
                case TECLA_FLECHA_IZQUIERDA  : rebobinarAtras(3); break;
                case TECLA_FLECHA_ARRIBA     : System.out.println("up"); break;
                case TECLA_FLECHA_ABAJO      : System.out.println("down"); break;
                case TECLA_P      : pausarReproduccion(); break;
                case TECLA_R      : reanudarReproduccion(); break;
            }
        }
    }

    public void refescarBarraProgreso()
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

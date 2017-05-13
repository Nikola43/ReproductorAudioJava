package Clases;

import javax.media.*;
import javax.media.format.AudioFormat;
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
        reproductor = Manager.createPlayer(new URL(cancion.getRuta()));
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

    //Hacemos una pausa cada segundo para ir mostrando los minutos y la barra de progreso
    try
    {
        while ( Math.round(getSegundoActual()) < Math.round(getDuracionTotal()))
        {
            Thread.sleep(1000);
            mostrarReproduccionActual(cancion);
        }

        //Pausamos reproduccion antes de siguiente cancion
        pararReproduccion();

    }
    catch (InterruptedException e)
    {
        e.printStackTrace();
    }
}

    @Override
    public void pararReproduccion() {

    }

    @Override
    public void pausarReproduccion() {

    }

    @Override
    public void reanudarReproduccion() {

    }

    @Override
    public void rebobinar(int posicion) {

    }

    public void mostrarReproduccionActual(CancionImpl cancionActual)
    {

        long segundoActual = Math.round(getSegundoActual());
        long minutoActual = 0;

        long segundosTotal = Math.round(getDuracionTotal());
        long minutosTotal = segundosTotal / 60;

        int contadorPasos = (int) Math.round((Math.round(segundoActual * 100) / Math.round(segundosTotal)));


        System.out.println("\nDuracion: "+minutoActual+":"+segundoActual+" / "+minutosTotal+":"+segundosTotal);
        //System.out.println("Nombre:   "+cancionActual.getNumeroEnLista()+". "+cancionActual.getNombre());

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
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//

}

package Util;

import java.io.File;

public class UtilFicheros
{
    /* INTERFAZ
       Cabecera:
           public static boolean ficheroEsCancion(String rutaFichero)
       Descripcion:
           Comprueba si el fichero pasado por parametro es un fichero de audio (extension .mp3, .wav)
       Precondiciones:
           -
       Entradas:
           Una cadena
       Salidas:
           Un boolean
       Postcondiciones:
           Devolvera TRUE si el fichero es una cancion, FALSE Cuando no lo sea
       Entradas/Salidas:
    */
    public static boolean ficheroEsCancion(String rutaFichero)
    {
        boolean soyCancion = false;
        String extensionFichero;

        String [] rutaDescompuesta;

        //Dividimos la ruta usando como separador la /
        rutaDescompuesta = rutaFichero.split("/");

        File fichero = new File(rutaDescompuesta[rutaDescompuesta.length - 1]);


        //Comprobamos si el fichero existe y es tipo de fichero
        if(fichero.exists() && fichero.isFile())
        {
            //Guardamos la extencion del fichero
            extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);

            //Si la extension es .mp3, .wav entonces el fichero es un fichero de audio válido
            if (extensionFichero.compareTo(".mp3") == 0 || extensionFichero.compareTo(".wav") == 0)
            {
                soyCancion = true;
            }
        }

        return soyCancion;
    }

    /* INTERFAZ
    Cabecera:
        public static boolean ficheroEsListaReproduccion(String rutaListaReproduccion)
    Descripcion:
        Comprueba si el fichero pasado por parametro es un fichero tipo lista de reproduccion (extension .lis)
    Precondiciones:
        -
    Entradas:
        Una cadena
    Salidas:
        Un boolean
    Postcondiciones:
        Devolvera TRUE si el fichero es una lista, FALSE cuando no lo sea
    Entradas/Salidas:
    */
    public static boolean ficheroEsListaReproduccion(String rutaListaReproduccion)
    {
        boolean soyListaReproduccion = false;
        String extensionFichero;
        String extensionListaReproduccion = ".lis";
        File fichero = new File(rutaListaReproduccion);

        //Comprobamos si existe y si es tipo fichero
        if( fichero.exists() && fichero.isFile())
        {
            //Guardamos la extencion de los ficheros
            extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);

            //Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
            if (extensionFichero.compareTo(extensionListaReproduccion) == 0)
            {
                soyListaReproduccion = true;
            }
        }

        return soyListaReproduccion;
    }


    /* INTERFAZ
       Cabecera:
           public static boolean ficheroEsCancion(String rutaFichero)
       Descripcion:
           Comprueba si el fichero pasado por parametro es un fichero de audio (extension .mp3, .wav)
       Precondiciones:
           -
       Entradas:
           Una cadena
       Salidas:
           Un boolean
       Postcondiciones:
           Devolvera TRUE si el fichero es una cancion, FALSE Cuando no lo sea
       Entradas/Salidas:

    public static boolean ficheroEsCancion(String rutaFichero)
    {
        boolean soyCancion = false;
        String extensionFichero;
        File fichero = new File(rutaFichero);

        //Comprobamos si el fichero existe y es tipo de fichero
        if(fichero.exists() && fichero.isFile())
        {
            //Guardamos la extencion del fichero
            extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);

            //Si la extension es .mp3, .wav entonces el fichero es un fichero de audio válido
            if (extensionFichero.compareTo(".mp3") == 0 || extensionFichero.compareTo(".wav") == 0)
            {
                soyCancion = true;
            }
        }

        return soyCancion;
    }
    */
}

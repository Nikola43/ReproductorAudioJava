package Util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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

        String [] rutaDescompuesta;

        //Dividimos la ruta usando como separador la /
        rutaDescompuesta = rutaListaReproduccion.split("/");

        File fichero = new File(rutaDescompuesta[rutaDescompuesta.length - 1]);

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
    public boolean borrarFicheroListaDeReproduccion(String rutaListaDeReproduccion)
Descripcion:
  Borra el fichero lista del directorio
Precondiciones:
  ruta del la lista
Entradas:
  String
Salidas:
  Un booleano
Postcondiciones:
  devovera true si se borra correctamente
*/
    public static boolean borrarFicheroListaDeReproduccion(String rutaListaDeReproduccion)
    {
        boolean resultadoBorrado = false;

        File ficheroListaDeReproduccion = new File(rutaListaDeReproduccion);

        if ( ficheroListaDeReproduccion.exists() && ficheroListaDeReproduccion.delete())
        {
            resultadoBorrado = true;
        }
        return resultadoBorrado;
    }

    /* INTERFAZ
       Cabecera:
           public void actualizarFicheroListaReproduccion(String rutaListaReproduccion)
       Descripcion:
          Actualiza el fichero de la lista
       Precondiciones:
          Sera la ruta de la cancion y la ruta de la lista donde lo quiere añadir
       Entradas:
          Dos cadenas
       Salidas:
          Boolean
       Postcondiciones:
          Devolvera verdadero si se ha insertado correctamente
    */
    public void actualizarFicheroListaReproduccion(String rutaListaReproduccion)
    {
        //Asignamos el fichero a un tipo file
        File ficheroListaReproduccionSalida = new File("listaAuxiliar.lis");
        File ficheroListaReproduccionActual = new File(rutaListaReproduccion);

        RandomAccessFile randomAccessFileSalida = null;
        RandomAccessFile randomAccessFileActual = null;

        int posicionPuntero = 0;
        int numeroCanciones = 0;
        String nombreListaReproduccion;
        int contadorCancionesValidas = 0;

        String cancionAxiliar;

        //Leemos el nombre de la lista de reproduccion
        try
        {
            randomAccessFileActual = new RandomAccessFile(ficheroListaReproduccionActual, "r");
            randomAccessFileSalida = new RandomAccessFile(ficheroListaReproduccionSalida, "rw");

            randomAccessFileActual.seek(posicionPuntero);
            numeroCanciones = Integer.parseInt(randomAccessFileActual.readUTF());

            randomAccessFileSalida.writeUTF(String.valueOf(numeroCanciones));

            //Leemos el tamaño de la lista
            posicionPuntero = 50;
            randomAccessFileActual.seek(posicionPuntero);
            nombreListaReproduccion = randomAccessFileActual.readUTF();

            randomAccessFileSalida.writeUTF(nombreListaReproduccion);

            posicionPuntero = 0;
            randomAccessFileActual.seek(posicionPuntero);
            randomAccessFileSalida.seek(posicionPuntero);

            while (contadorCancionesValidas < numeroCanciones)
            {
                posicionPuntero += 100;
                randomAccessFileActual.seek(posicionPuntero);
                cancionAxiliar = randomAccessFileActual.readUTF();

                if ( UtilFicheros.ficheroEsCancion(cancionAxiliar) && cancionAxiliar.charAt(cancionAxiliar.length() - 1) != '*')
                {
                    randomAccessFileSalida.writeUTF(cancionAxiliar);
                    contadorCancionesValidas++;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                randomAccessFileActual.close();
                randomAccessFileSalida.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /* INTERFAZ
       Cabecera:
           public static void renombrarFichero(String ficheroOrigen, String ficheroDestino)
       Descripcion:
          Renombra el fichero auxiliar
       Precondiciones:
          Seran la ruta de ambos ficheros
       Entradas:
          Dos cadenas
       Salidas:
          -
       Postcondiciones:
          -
    */
    public static void renombrarFichero(String ficheroOrigen, String ficheroDestino)
    {
        File fileOrigen = new File(ficheroOrigen);
        File fileDestino = new File(ficheroDestino);


        if ( fileOrigen.exists() && fileDestino.exists() )
        {
            fileDestino.renameTo(fileOrigen);
        }
    }

}

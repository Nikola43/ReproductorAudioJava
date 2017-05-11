package Gestion;

import Clases.CancionImpl;
import Clases.ListaDeReproduccion;
import Clases.ListaDeReproduccionImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionListaReproduccion
{
    public boolean esCancion(String nombreFichero)
    {
        boolean soyCancion = false;
        String extensionFichero;
        String[] extensionesValidas = {".mp3", ".wav", ".ogg"};
        File fichero = new File(nombreFichero);


        //Comprobamos si el fichero existe
        if (fichero.exists())
        {
            //Comprobamos el tipo de fichero
            if(fichero.isFile())
            {
                //Guardamos la extencion de los ficheros
                extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);

                //Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
                if (extensionFichero.compareTo(extensionesValidas[0]) == 0 || extensionFichero.compareTo(extensionesValidas[1]) == 0 || extensionFichero.compareTo(extensionesValidas[2]) == 0 )
                {
                    soyCancion = true;
                }
            }
        }

        return soyCancion;
    }

    public boolean esListaReproduccion(String nombreListaReproduccion)
    {
        boolean soyListaReproduccion = false;
        String extensionFichero;
        String extensionListaReproduccion = ".lis";
        File fichero = new File(nombreListaReproduccion);

        //Comprobamos si el fichero existe
        if (fichero.exists())
        {
            //Comprobamos el tipo de fichero
            if(fichero.isFile())
            {
                //Guardamos la extencion de los ficheros
                extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);

                //Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
                if (extensionFichero.compareTo(extensionListaReproduccion) == 0)
                {
                    soyListaReproduccion = true;
                }
            }
        }

        return soyListaReproduccion;
    }

    public void mostrarCancionesDisponibles(String directorio)
    {
        File[] ficheros;

        //Creamos el fichero
        File miFichero = new File(directorio);
        String extensionFichero;

        //Comprobamos si el fichero existe
        if (miFichero.exists())
        {
            //Creamos array con los ficheros del directorio
            ficheros = miFichero.listFiles();

            //Recorremos todos los ficheros del directorio
            for (int i = 0; i < ficheros.length; i++)
            {
                //Comprobamos el tipo de fichero
                if (ficheros[i].isFile() == true)
                {
                    //Guardamos la extension del fichero
                    extensionFichero = "." + ficheros[i].getName().charAt(ficheros[i].getName().length() - 3) + ficheros[i].getName().charAt(ficheros[i].getName().length() - 2) + ficheros[i].getName().charAt(ficheros[i].getName().length() - 1);

                    //Si la extension es una extension de un fichero de audio
                    if (extensionFichero.compareTo(".mp3") == 0 || extensionFichero.compareTo(".wav") == 0 || extensionFichero.compareTo(".ogg") == 0)
                    {
                        System.out.println("\t" + (i + 1) + ". " + ficheros[i].getName());
                    }
                }
            }
        }
    }

    public String seleccionarCancion()
    {
        Scanner scanner = new Scanner(System.in);
        String rutaCancion;

        File ficheroCancion;

        do
        {
            //Pedimos al usuario que introduzca una cancion de la lista
            System.out.print("\nIntroduzca el nombre de la cancion que desea seleccionar: ");
            rutaCancion = "Canciones/"+scanner.nextLine();
            ficheroCancion = new File(rutaCancion);

            //Si el fichero no existe
            if (ficheroCancion.exists() == false)
            {
                System.out.print("El fichero "+rutaCancion+" no existe");
            }
            else
            {
                //Si el fichero existe pero no es un fichero de audio
                if (esCancion(rutaCancion) == false)
                {
                    System.out.print("El fichero "+rutaCancion+" no es un fichero de audio");
                }
            }
        } while (ficheroCancion.exists() == false || esCancion(rutaCancion) == false);

        return rutaCancion;
    }

    /* INTERFAZ
       Cabecera:
           public void crearListaReproduccion()
       Descripcion:
           Permite al usuario crear una lista de reproduccion con sus canciones favoritas
       Precondiciones:
           -
       Entradas:
           -
       Salidas:
           -
       Postcondiciones:
           -
       Entradas/Salidas:
           -
     */
    public void guardarListaReproduccion(ListaDeReproduccionImpl listaDeReproduccion)
    {
<<<<<<< HEAD
        File file = new File(listaDeReproduccion.getNombre());
        RandomAccessFile randomAccessFile = null;

        long posicion = 0;

        ArrayList<CancionImpl> list;
        list = listaDeReproduccion.getListaCanciones();

        int tamanaio = 0;
=======
        File ficheroListaReproduccion;
        RandomAccessFile randomAccessFile;
        ArrayList<CancionImpl> canciones;

        ficheroListaReproduccion = new File(listaDeReproduccion.getNombre());
        canciones = listaDeReproduccion.getListaCanciones();
>>>>>>> 63dacc8484b6d0b7122997a8515da8e604e70d13

        try
        {
            randomAccessFile = new RandomAccessFile(ficheroListaReproduccion, "w");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        for ( int i = 0; i < listaDeReproduccion.getNumeroCanciones(); i++ )
        {
            try
            {
                randomAccessFile.seek(posicion);
                tamanaio = list.get(i).getRuta().length();

                String t = String.valueOf(tamanaio);

                randomAccessFile.write(t.getBytes());
                System.out.println(randomAccessFile.getFilePointer());

                randomAccessFile.write(list.get(i).getRuta().getBytes());

                posicion = randomAccessFile.getFilePointer();
                System.out.println("Posicion: "+posicion);


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            randomAccessFile.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }



    /* INTERFAZ
       Cabecera:
           public void crearListaReproduccion()
       Descripcion:
           Permite al usuario crear una lista de reproduccion con sus canciones favoritas
       Precondiciones:
           -
       Entradas:
           -
       Salidas:
           -
       Postcondiciones:
           -
       Entradas/Salidas:
           -
     */
    public void crearListaReproduccion()
    {
        ListaDeReproduccionImpl listaDeReproduccion = new ListaDeReproduccionImpl();
        ArrayList<CancionImpl> listaDeCanciones = new ArrayList<>();

        File ficheroListaReproduccion;

        File ficheroCancionAuxiliar;
        CancionImpl cancionAuxiliar = null;
        String rutaCancionAuxiliar;

        char respuestaUsuario;
        Scanner scanner = new Scanner(System.in);

        //Pedimos que introduzca el nombre de la lista
        do
        {
            System.out.print("\nIntroduzca el nombre de la lista que quiere crear: ");
            listaDeReproduccion.setNombre(scanner.nextLine()+".lis");

            //Asociamos lista de reproduccion a un tipo File
            ficheroListaReproduccion = new File("ListasDeReproduccion/"+listaDeReproduccion.getNombre());

            if ( ficheroListaReproduccion.exists() == true )
            {
                System.out.println("Ya existe una lista de reproduccion '"+listaDeReproduccion.getNombre()+"', utilice otro nombre");
            }
        } while( ficheroListaReproduccion.exists() == true);

        do
        {
            //Pedimos al usuario que introduzca el nombre de la cancion que quiere añadir a su lista
            System.out.println("Estas son las canciones disponibles: ");
            mostrarCancionesDisponibles("Canciones/");

            rutaCancionAuxiliar = seleccionarCancion();
            ficheroCancionAuxiliar = new File(rutaCancionAuxiliar);


            try
            {
                cancionAuxiliar = new CancionImpl(ficheroCancionAuxiliar.toURI().toURL().toString());
                System.out.println(cancionAuxiliar.getNombre()+", "+cancionAuxiliar.getRuta()+","+cancionAuxiliar.getRuta().getBytes().length);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }

            //Insertamos una nueva cancion en la lista
            listaDeCanciones.add(cancionAuxiliar);

            //Preguntamos al usuario si desea continuar agregando canciones
            do
            {
                System.out.print("\nDesea introducir mas canciones a la lista?: (S/n): ");
                respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
                scanner.nextLine();
            } while( respuestaUsuario != 's' && respuestaUsuario != 'n');

        } while (respuestaUsuario == 's');

        //Guardamos la lista de canciones en la lista de reproduccion
        listaDeReproduccion.setListaCanciones(listaDeCanciones);

        //Guardamos lista de reproduccion en el disco duro del sistema
        guardarListaReproduccion(listaDeReproduccion);

        //Si la lista existe, se muestra un mensaje que confirma que se a creado correctamente
        if ( ficheroListaReproduccion.exists() == true)
        {
            System.out.println("\nSe ha creado la lista de reproduccion correctamente");
        }
    }

    public void leerListaDeReproduccion(String ficheroListaDeReproduccion)
    {
        //--------------------------------------------------------//

        File file = new File(ficheroListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

         int posicion = 1;
         int contador = 0;
         long tamanioTotal = file.length();

        byte[] tamanioPropiedadLeida = new byte[1];

        byte[] propiedadLeida = new byte[1000];
        System.out.println(propiedadLeida.length);

        String s = " ";

        while ( posicion < tamanioTotal )
        try
        {

            randomAccessFile = new RandomAccessFile(file, "r");

            randomAccessFile.read(tamanioPropiedadLeida, posicion - 1, 1);

            randomAccessFile.read(propiedadLeida, posicion, tamanioPropiedadLeida.length);

            posicion += tamanioPropiedadLeida.length;

            randomAccessFile.seek(posicion);

            s = new String(propiedadLeida);
            System.out.println(s);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

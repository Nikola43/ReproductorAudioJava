import javax.media.Player;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
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
        int contadorCanciones = 0;

        //Creamos el fichero
        File miFichero = new File(directorio);

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
                    //Si la extension es una extension de un fichero de audio
                    if ( esCancion(ficheros[i].getName()) )
                    {
                        contadorCanciones++;
                        System.out.println("\t" +contadorCanciones+ ". " + ficheros[i].getName());
                    }
                }
            }
        }
    }

    public void reproducirCancionUnica()
    {
        File ficheroCancion = null;
        ReproductorImpl reproductor = new ReproductorImpl();
        CancionImpl cancion = null;
        String nombreCancion;

        //Mostramos las canciones disponibles
        mostrarCancionesDisponibles(".");

        //Pedimos al usuario que seleccione una cancion
        nombreCancion = seleccionarCancion();

        //Enlazamos el fichero
        ficheroCancion = new File(nombreCancion);



        try
        {


            //Asignamos la cancion
            cancion = new CancionImpl(ficheroCancion.toURI().toURL().toString());

            //Reproducimos la cancion
            reproductor.reproducirCancion(cancion);
            reproductor.mostrarReproduccionActual(cancion);

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    public void mostrarListasDeReproduccionDisponibles(String directorio)
    {
        File[] ficheros;
        int contadorListas = 0;

        //Creamos el fichero
        File miFichero = new File(directorio);

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
                    //Si la extension es una extension de un fichero de audio
                    if ( esListaReproduccion(ficheros[i].getName()) )
                    {
                        contadorListas++;
                        System.out.println("\t" +contadorListas+ ". " + ficheros[i].getName());
                    }
                }
            }
        }
    }

    public String seleccionarCancion()
    {
        Scanner scanner = new Scanner(System.in);
        String cancionSeleccionada;

        File ficheroCancion;
        do
        {
            //Pedimos al usuario que introduzca una cancion de la lista
            System.out.print("\nIntroduzca el nombre de la cancion que desea seleccionar: ");
            //
            cancionSeleccionada = scanner.nextLine();

            ficheroCancion = new File(cancionSeleccionada);

            //Si el fichero no existe
            if (ficheroCancion.exists() == false)
            {
                System.out.print("El fichero "+ficheroCancion.getName()+" no existe");
            }
            else
            {
                //Si el fichero existe pero no es un fichero de audio
                if (esCancion(ficheroCancion.getAbsolutePath()) == false)
                {
                    System.out.print("El fichero "+ficheroCancion.getName()+" no es un fichero de audio");
                }
            }
        } while (ficheroCancion.exists() == false || esCancion(ficheroCancion.getAbsolutePath()) == false);

        return ficheroCancion.getName();
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
        //Asignamos el fichero a un tipo file
        File ficheroListaReproduccion = new File(listaDeReproduccion.getNombre());

        RandomAccessFile randomAccessFile;

        int posicionPuntero = 0;

        try
        {
            //Abrimos el fichero
            randomAccessFile = new RandomAccessFile(ficheroListaReproduccion, "rw");

            //Escribimos el nombre de la lista
            randomAccessFile.seek(posicionPuntero);
            randomAccessFile.writeUTF(listaDeReproduccion.getNombre());
            System.out.println("NOMBRE "+randomAccessFile.getFilePointer());

            //Escribimos el numero de canciones que tiene la lista
            posicionPuntero = 50;
            randomAccessFile.seek(posicionPuntero);
            randomAccessFile.writeUTF(String.valueOf(listaDeReproduccion.getNumeroCanciones()));
            System.out.println("Numernp "+randomAccessFile.getFilePointer());

            posicionPuntero = 100;
            randomAccessFile.seek(posicionPuntero);

            for (int i = 0; i < listaDeReproduccion.getNumeroCanciones(); i++)
            {
                randomAccessFile.writeUTF(listaDeReproduccion.getListaCanciones().get(i).getRuta());
                posicionPuntero += 100;
                randomAccessFile.seek(posicionPuntero);
                System.out.println("FP "+randomAccessFile.getFilePointer());
            }
            randomAccessFile.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void insertarCancionListaReproduccion(CancionImpl cancion, String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        ListaDeReproduccionImpl listaDeReproduccion = null;
        ArrayList<CancionImpl> listaDeCanciones = new ArrayList<>();

        int numeroCanciones;

        int posicionPuntero = 0;

        //Si la lista existe la mostramos por pantalla
        if( listaReproduccion.exists() == true )
        {
            try
            {
                randomAccessFile = new RandomAccessFile(listaReproduccion, "rw");

                //Leemos el tamaño de la lista
                posicionPuntero = 50;
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                randomAccessFile.seek(100 * numeroCanciones);

                for ( int i = 0; i < numeroCanciones; i++)
                {
                    listaDeCanciones.add(new CancionImpl(randomAccessFile.readUTF()));
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);
                }

                //listaDeReproduccion = new ListaDeReproduccionImpl(nombreListaReproduccion, listaDeCanciones);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                //Cerramos el fichero
                try
                {
                    randomAccessFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
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
            ficheroListaReproduccion = new File(listaDeReproduccion.getNombre());

            if ( ficheroListaReproduccion.exists() == true )
            {
                System.out.println("Ya existe una lista de reproduccion '"+listaDeReproduccion.getNombre()+"', utilice otro nombre");
            }
        } while( ficheroListaReproduccion.exists() == true);

        do
        {
            //Pedimos al usuario que introduzca el nombre de la cancion que quiere añadir a su lista
            System.out.println("Estas son las canciones disponibles: ");
            mostrarCancionesDisponibles(".");

            rutaCancionAuxiliar = seleccionarCancion();

            ficheroCancionAuxiliar = new File(rutaCancionAuxiliar);


            try
            {
                cancionAuxiliar = new CancionImpl(ficheroCancionAuxiliar.toURI().toURL().toString());
                System.out.println(cancionAuxiliar.getNombreFichero()+", "+cancionAuxiliar.getRuta()+","+cancionAuxiliar.getRuta().getBytes().length);
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

    public ListaDeReproduccionImpl leerListaReproduccion(String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        ListaDeReproduccionImpl listaDeReproduccion = null;
        ArrayList<CancionImpl> listaDeCanciones = new ArrayList<>();

        int numeroCanciones;
        String nombreListaReproduccion;


        int posicionPuntero = 0;

        //Si la lista existe la mostramos por pantalla
        if( listaReproduccion.exists() == true )
        {
            try
            {
                randomAccessFile = new RandomAccessFile(listaReproduccion, "r");

                //Leemos el nombre de la lista de reproduccion
                randomAccessFile.seek(posicionPuntero);
                nombreListaReproduccion = randomAccessFile.readUTF();

                //Leemos el tamaño de la lista
                posicionPuntero = 50;
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                posicionPuntero = 100;
                randomAccessFile.seek(posicionPuntero);

                for ( int i = 0; i < numeroCanciones; i++)
                {
                    listaDeCanciones.add(new CancionImpl(randomAccessFile.readUTF()));
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);
                }

                listaDeReproduccion = new ListaDeReproduccionImpl(nombreListaReproduccion, listaDeCanciones);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                //Cerramos el fichero
                try
                {
                    randomAccessFile.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return listaDeReproduccion;
    }

    public void mostrarCancionesListaReproduccion(String rutaListaDeReproduccion)
    {
       ListaDeReproduccionImpl listaDeReproduccion = leerListaReproduccion(rutaListaDeReproduccion);

       System.out.println("Nombre: "+listaDeReproduccion.getNombre() + "\t |" +"  Numero de canciones: "+listaDeReproduccion.getNumeroCanciones() );

       for (int i = 0; i < listaDeReproduccion.getNumeroCanciones(); i++)
       {
           System.out.println("\t"+(i+1)+". "+listaDeReproduccion.getListaCanciones().get(i).getNombreFichero());
       }
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
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
        int contadorCanciones = 1;

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
                        System.out.println("\t" +contadorCanciones+ ". " + ficheros[i].getName());
                        contadorCanciones++;
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
            System.out.print("\nIntroduzca el numero de la cancion que desea seleccionar: ");
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

        //Definimos el objeto para leer / escribir las canciones
        RandomAccessFile randomAccessFile = null;

        //Creamos una arrayList de canciones auxiliar para escribir una a una las canciones
        ArrayList<CancionImpl> listaDeCanciones = listaDeReproduccion.getListaCanciones();

        //Convertimos el tamaño de la lista en string para poner escribirlo en el fichgero
        String tamanioListaCanciones = String.valueOf(listaDeReproduccion.getNumeroCanciones());

        //Variable para controlar la posicion del puntero
        long posicionActual = 0;

        //Abrimos el fichero
        try
        {
            randomAccessFile = new RandomAccessFile(ficheroListaReproduccion, "rw");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }


        try
        {

            //Nos posicionamos al principio del fichero
            randomAccessFile.seek(0);
            System.out.println("Tamaño lista "+tamanioListaCanciones);


            //Escribimos en primer lugar el tamaño de la lista
            randomAccessFile.write(tamanioListaCanciones.getBytes());

            System.out.println("posicion tamaño lista "+randomAccessFile.getFilePointer());

            //Recorremos la lista entera, escribiendo en el fichero todos sus elementos
            for ( int i = 0; i < listaDeCanciones.size(); i++ )
            {
                //Guardamos el tamaño de la cancion actual
                randomAccessFile.write(listaDeCanciones.get(i).getRuta().getBytes().length);
                System.out.println("posicion tamaño "+randomAccessFile.getFilePointer());

                posicionActual = randomAccessFile.getFilePointer();

                //Guardamos la cancion
                randomAccessFile.write(listaDeCanciones.get(i).getRuta().getBytes());


                System.out.println("Tamaño: cancion "+listaDeCanciones.get(i).getRuta().getBytes().length);

                posicionActual += listaDeCanciones.get(i).getRuta().getBytes().length;

                System.out.println("Posicion ultima cancion: "+posicionActual);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
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

    public void leerListaReproduccion(String ficheroListaDeReproduccion)
    {
        File file = new File(ficheroListaDeReproduccion);
        RandomAccessFile randomAccessFile;

        ArrayList<CancionImpl> listaDeCanciones = new ArrayList<>();
        ArrayList<ListaDeReproduccionImpl> listaDeReproduccion = new ArrayList<>();

        CancionImpl cancionAuxiliar = new CancionImpl();
        String rutaCancionAuxiliar;

        int tamanioRegistros = 0;

        int posicion = 1;

        byte[] bufferTamanioListaCanciones = new byte[100];
        byte[] bufferTamanioCancionLeida = new byte[100];
        byte[] bufferCancionLeida = new byte[100];

        String tamanioListaCanciones;
        int tamanioCancionLeida;



        String cancionLeida;

        try
        {
            // Abrimos el fichero en modo lectura
            randomAccessFile = new RandomAccessFile(file, "r");

            //Leermos el primer byte que contiene el tamaño de la lista
            randomAccessFile.read(bufferTamanioListaCanciones, 0, 1);

            tamanioListaCanciones = new String(bufferTamanioListaCanciones);

            System.out.println(tamanioListaCanciones);
            int i = Integer.parseInt(tamanioListaCanciones);
            System.out.println(i);





            posicion = (int) randomAccessFile.getFilePointer();
            System.out.println(posicion);

            randomAccessFile.read(bufferTamanioCancionLeida, posicion, 1);


            //for ( int i = 0; i<tamanioListaCanciones; i++)
            //{
                //Leemos el tamaño que ocupa una cancion
                //randomAccessFile.read(bufferTamanioCancionLeida, posicion + 1, 1);
                //System.out.println(randomAccessFile.getFilePointer());

               // tamanioCancionLeida = new String(bufferTamanioCancionLeida);

                //Leemos una cancion
                //randomAccessFile.read(bufferCancionLeida, posicion, Integer.parseInt(tamanioCancionLeida));
               // System.out.println(randomAccessFile.getFilePointer());

                //cancionLeida = new String(bufferCancionLeida);

                //System.out.println(cancionLeida);

                //cancionAuxiliar.setRuta(rutaCancionAuxiliar);

                //listaDeCanciones.add(cancionAuxiliar);
            //}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public int toInt( byte[] bytes ) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;

    }
}

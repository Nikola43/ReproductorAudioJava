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

        //Creamos una arrayList de canciones auxiliar para escribir una a una las canciones
        ArrayList<CancionImpl> listaDeCanciones = listaDeReproduccion.getListaCanciones();

        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        try
        {
            if ( ficheroListaReproduccion.getTotalSpace() == 0 )
            {
                fileOutputStream = new FileOutputStream(ficheroListaReproduccion);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            }
            else
            {
                fileOutputStream = new FileOutputStream(ficheroListaReproduccion, true);
                objectOutputStream = new MiObjectOutputStream(fileOutputStream);
            }

            for (int i = 0; i < listaDeCanciones.size(); i++)
            {
                objectOutputStream.writeObject(listaDeCanciones.get(i));
            }
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

    public void leerListaReproduccion(String nombreListaReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(nombreListaReproduccion);
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        int contadorCanciones = 0;

        //Variable auxiliar para ir mostrando cada cancion
        CancionImpl cancionAuxiliar;

        //Si la lista existe la mostramos por pantalla
        if( listaReproduccion.exists() == true )
        {
            System.out.println("La lista de reproduccion '"+listaReproduccion.getName()+"' contiene las siguientes canciones:");
            try
            {
                fileInputStream =  new FileInputStream(listaReproduccion);
                objectInputStream = new ObjectInputStream(fileInputStream);



                while((cancionAuxiliar = (CancionImpl) objectInputStream.readObject()) != null)
                {
                    contadorCanciones++;
                    System.out.println(cancionAuxiliar.getNombre());
                }
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (EOFException e)
            {
                System.out.println("\nFin de la lista. EOF");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            finally
            {
                try
                {
                    if( fileInputStream != null)
                        fileInputStream.close();

                    if( objectInputStream != null)
                        objectInputStream.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

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

    public ArrayList<String> devolverListadoFicherosCancion(String directorio)
    {
        ArrayList<String> nombresDeCanciones = new ArrayList<>();

        File[] ficheros;

        //Creamos el fichero
        File miDirectorio = new File(directorio);

        //Comprobamos si el fichero existe
        if (miDirectorio.exists())
        {
            //Creamos array con los ficheros del directorio
            ficheros = miDirectorio.listFiles();

            //Recorremos todos los ficheros del directorio
            for (int i = 0; i < ficheros.length; i++)
            {
                //Comprobamos el tipo de fichero
                if (ficheros[i].isFile() == true)
                {
                    //Si la extension es una extension de un fichero de audio
                    if ( esCancion(ficheros[i].getName()) )
                    {
                        nombresDeCanciones.add(ficheros[i].getName());
                    }
                }
            }
        }
        return nombresDeCanciones;
    }

    public void reproducirCancionUnica()
    {
        File ficheroCancion = null;
        ReproductorImpl reproductor = new ReproductorImpl();
        CancionImpl cancion = null;
        String nombreCancion;

        ArrayList<String> listaCanciones = devolverListadoFicherosCancion(".");

        //Mostramos las canciones disponibles
        for ( int i = 0; i < listaCanciones.size(); i++)
        {
            System.out.println((i+1)+". "+listaCanciones.get(i));
        }


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

    public void mostrarListasReproduccionExistentes(String rutaDirectorioListas)
    {
        int numeroListas = devolverListadoFicherosLista(rutaDirectorioListas).size();

        if ( numeroListas > 0 )
        {
            System.out.println("Listas Disponibles: ");
            for (int i=0; i < numeroListas; i++)
            {
                System.out.println("\t"+(i+1)+". "+ devolverListadoFicherosLista(rutaDirectorioListas).get(i));
            }
        }
        else
        {
            System.out.println("No hay ninguna lista de reproduccion");
        }
    }

    public ArrayList<String> devolverListadoFicherosLista(String directorio)
    {
        ArrayList<String> nombresListas = new ArrayList<>();

        File[] ficheros;

        //Creamos el fichero
        File miDirectorio = new File(directorio);

        //Comprobamos si el fichero existe
        if (miDirectorio.exists())
        {
            //Creamos array con los ficheros del directorio
            ficheros = miDirectorio.listFiles();

            //Recorremos todos los ficheros del directorio
            for (int i = 0; i < ficheros.length; i++)
            {
                //Comprobamos el tipo de fichero
                if (ficheros[i].isFile() == true)
                {
                    //Si la extension es una extension de un fichero de audio
                    if ( esListaReproduccion(ficheros[i].getName()) )
                    {
                        nombresListas.add(ficheros[i].getName());
                    }
                }
            }
        }
        return nombresListas;
    }

    public String seleccionarCancion()
    {
        Scanner scanner = new Scanner(System.in);
        String cancionSeleccionada;
        File ficheroCancion = null;
        String nombreCancion = " ";


        ArrayList<String> canciones = devolverListadoFicherosCancion(".");

        if ( canciones.size() > 0)
        {
            do
            {
                //Pedimos al usuario que introduzca una cancion de la lista
                System.out.print("\nIntroduzca el nombre de la cancion que desea seleccionar: ");
                //
                cancionSeleccionada = scanner.nextLine();

                ficheroCancion = new File(cancionSeleccionada);
                nombreCancion = ficheroCancion.getName();

                //Si el fichero no existe
                if (ficheroCancion.exists() == false)
                {
                    System.out.print("El fichero "+nombreCancion+" no existe");
                }
                else
                {
                    //Si el fichero existe pero no es un fichero de audio
                    if (esCancion(ficheroCancion.getAbsolutePath()) == false)
                    {
                        System.out.print("El fichero "+nombreCancion+" no es un fichero de audio");
                    }
                }
            } while (ficheroCancion.exists() == false || esCancion(ficheroCancion.getAbsolutePath()) == false);
        }
        else
        {
            System.out.println("No hay canciones disponibles");
            nombreCancion = " ";
        }

        return nombreCancion;
    }

    public String seleccionarListaReproduccion()
    {
        Scanner scanner = new Scanner(System.in);
        String listaSeleccionada;
        File ficheroLista = null;

        ArrayList<String> nombresListas = devolverListadoFicherosLista(".");

        if ( nombresListas.size() > 0 )
        {
            do
            {
                //Pedimos al usuario que introduzca una lista
                System.out.print("\nIntroduzca el nombre de la cancion que desea seleccionar: ");

                listaSeleccionada = scanner.nextLine();

                ficheroLista = new File(listaSeleccionada);

                //Si el fichero no existe
                if (ficheroLista.exists() == false)
                {
                    System.out.print("El fichero "+ficheroLista.getName()+" no existe");
                }
                else
                {
                    //Si el fichero existe pero no es un fichero de audio
                    if (esListaReproduccion(ficheroLista.getAbsolutePath()) == false)
                    {
                        System.out.print("El fichero "+ficheroLista.getName()+" no es un fichero lista de reproduccion");
                    }
                }
            } while (ficheroLista.exists() == false || esListaReproduccion(ficheroLista.getAbsolutePath()) == false);
        }
        else
        {

        }

        return ficheroLista.getName();
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

            //Escribimos el numero de canciones que tiene la lista
            randomAccessFile.seek(posicionPuntero);
            randomAccessFile.writeUTF(String.valueOf(listaDeReproduccion.getNumeroCanciones()));

            //Escribimos el nombre de la lista
            posicionPuntero = 50;
            randomAccessFile.seek(posicionPuntero);
            randomAccessFile.writeUTF(listaDeReproduccion.getNombre());

            posicionPuntero = 0;

            for (int i = 0; i < listaDeReproduccion.getNumeroCanciones(); i++)
            {
                posicionPuntero += 100;
                System.out.println(posicionPuntero);
                randomAccessFile.seek(posicionPuntero);
                randomAccessFile.writeUTF(listaDeReproduccion.getListaCanciones().get(i).getRuta());
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

    public void insertarCancionListaReproduccion(String rutaCancion, String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        File archivoCancion = new File(rutaCancion);

        int numeroCanciones;

        int posicionPuntero = 0;


        //Si la lista y la cancion existen
        if( listaReproduccion.exists() == true && archivoCancion.exists() )
        {
            try
            {
                //Abrimos la lista
                randomAccessFile = new RandomAccessFile(listaReproduccion, "rw");

                //Leemos el tamaño de la lista
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());
                System.out.println(numeroCanciones);

                //Vamos a la ultima posicion
                randomAccessFile.seek(100 * (numeroCanciones + 1));

                //Escribimos una nueva cancion
                randomAccessFile.writeUTF(archivoCancion.toURI().toURL().toString());
                numeroCanciones++;

                System.out.println(archivoCancion.toURI().toURL().toString());
                System.out.println(numeroCanciones);

                //
                randomAccessFile.seek(0);
                randomAccessFile.writeUTF(String.valueOf(numeroCanciones));

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
    public ListaDeReproduccionImpl crearListaReproduccion()
    {
        ListaDeReproduccionImpl listaDeReproduccion = new ListaDeReproduccionImpl();
        ArrayList<CancionImpl> listaDeCanciones = new ArrayList<>();

        File ficheroListaReproduccion;

        File ficheroCancionAuxiliar;
        CancionImpl cancionAuxiliar = null;
        String rutaCancionAuxiliar;

        char respuestaUsuario;
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> listaCanciones = devolverListadoFicherosCancion(".");


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
            if ( listaDeReproduccion.getNombre().length() > 50 )
            {
                System.out.println("El nombre de la lista de reproduccion debe ser como maximo 50 caracteres, utilice otro nombre");
            }
        } while( ficheroListaReproduccion.exists() == true || listaDeReproduccion.getNombre().length() > 50);

        do
        {
            //Pedimos al usuario que introduzca el nombre de la cancion que quiere añadir a su lista
            System.out.println("Estas son las canciones disponibles: ");

            //Mostramos las canciones disponibles
            for ( int i = 0; i < listaCanciones.size(); i++)
            {
                System.out.println((i+1)+". "+listaCanciones.get(i));
            }


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

        guardarListaReproduccion(listaDeReproduccion);

        return listaDeReproduccion;
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
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                System.out.println(numeroCanciones);

                //Leemos el tamaño de la lista
                posicionPuntero = 50;
                randomAccessFile.seek(posicionPuntero);
                nombreListaReproduccion = randomAccessFile.readUTF();

                posicionPuntero = 0;
                randomAccessFile.seek(0);

                for ( int i = 0; i < numeroCanciones; i++)
                {
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);
                    listaDeCanciones.add(new CancionImpl(randomAccessFile.readUTF()));

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
       File ficheroListaDeReproduccion = new File(rutaListaDeReproduccion);

       if ( ficheroListaDeReproduccion.exists() )
       {
           ListaDeReproduccionImpl listaDeReproduccion = leerListaReproduccion(rutaListaDeReproduccion);

           if ( listaDeReproduccion.getNumeroCanciones() > 0 )
           {
               System.out.println("Nombre: "+listaDeReproduccion.getNombre() + "\t |" +"  Numero de canciones: "+listaDeReproduccion.getNumeroCanciones() );

               for (int i = 0; i < listaDeReproduccion.getNumeroCanciones(); i++)
               {
                   System.out.println("\t"+(i+1)+". "+listaDeReproduccion.getListaCanciones().get(i).getNombreFichero());
               }
           }
       }
    }

    public boolean borrarListaDeReproduccion(String rutaListaDeReproduccion)
    {
        boolean resultadoBorrado = false;

        File ficheroListaDeReproduccion = new File(rutaListaDeReproduccion);

        if ( ficheroListaDeReproduccion.exists() && ficheroListaDeReproduccion.delete())
        {
            resultadoBorrado = true;
        }
        return resultadoBorrado;
    }


    public void reproducirListaReproduccion(String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        ListaDeReproduccionImpl listaDeReproduccion = null;
        CancionImpl cancionActual = new CancionImpl();

        ReproductorImpl reproductor = new ReproductorImpl();

        int numeroCanciones;
        String nombreListaReproduccion;

        listaDeReproduccion = leerListaReproduccion(rutaListaDeReproduccion);




        int posicionPuntero = 0;

        //Si la lista existe la mostramos por pantalla
        if( listaReproduccion.exists() == true )
        {
            try
            {
                randomAccessFile = new RandomAccessFile(listaReproduccion, "r");

                //Leemos el nombre de la lista de reproduccion
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                System.out.println(numeroCanciones);

                //Leemos el tamaño de la lista
                posicionPuntero = 50;
                randomAccessFile.seek(posicionPuntero);
                nombreListaReproduccion = randomAccessFile.readUTF();

                posicionPuntero = 0;
                randomAccessFile.seek(0);

                for ( int i = 0; i < numeroCanciones; i++)
                {
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);
                    cancionActual.setRuta(randomAccessFile.readUTF());

                    //Reproducimos la cancion
                    reproductor.reproducirCancion(cancionActual);



                    while ( Math.round(reproductor.getReproductor().getMediaTime().getSeconds()) < Math.round(reproductor.getReproductor().getDuration().getSeconds()))
                    {
                        reproductor.mostrarReproduccionActual(cancionActual);

                        System.out.println("Lista de reproduccion: "+nombreListaReproduccion+"\n");
                        for (int j = 0; j < listaDeReproduccion.getNumeroCanciones(); j++)
                        {
                            String mensaje = " ";
                            if (listaDeReproduccion.getListaCanciones().get(j).getNombreFichero().compareTo(cancionActual.getNombreFichero()) == 0)
                            {
                                mensaje = " Reproduciendo: "+(j+1)+". "+listaDeReproduccion.getListaCanciones().get(j).getNombreFichero();
                            }
                            else
                            {
                                mensaje = "\t"+(j+1)+". "+listaDeReproduccion.getListaCanciones().get(j).getNombreFichero();
                            }

                             System.out.println(mensaje+"\n");
                        }
                    }





                }

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
}

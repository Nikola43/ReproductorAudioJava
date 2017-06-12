package Gestion;

import Clases.CancionImpl;
import Clases.ListaDeReproduccionImpl;
import Clases.ReproductorImpl;
import Excepciones.CancionException;
import Excepciones.ListaDeReproduccionException;
import Util.UtilFicheros;

import javax.media.Player;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionListaReproduccion
{


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

    public ArrayList<String> devolverListadoFicherosCancion(String directorio)
    {
        File[] ficheros;

        ArrayList<String> canciones = new ArrayList<>();

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
                if (ficheros[i].isFile() )
                {
                    if (UtilFicheros.ficheroEsCancion(ficheros[i].getName()) )
                    {
                        canciones.add(ficheros[i].getName());
                    }
                }
            }
        }
        return canciones;
    }
    
    public ArrayList<String>  devolverListadoFicherosLista(String directorio)
    {
        File[] ficheros;
        
        ArrayList<String> listas = new ArrayList<>();

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
                    if ( UtilFicheros.ficheroEsListaReproduccion(ficheros[i].getName()) )
                    {
                        listas.add(ficheros[i].getName());
                    }
                }
            }
        }
        return listas;
    }

    public void mostrarListasReproduccionExistentes(String ruta)
    {
        ArrayList<String> listasReproduccion = devolverListadoFicherosLista(".");

        for ( int i = 0; i < listasReproduccion.size(); i++)
        {
            System.out.println("\t"+(i + 1) + ". " + listasReproduccion.get(i));
        }
    }

    public void mostrarCancionesExistentes(String ruta)
    {
        ArrayList<String> listaCanciones = devolverListadoFicherosCancion(".");

        for ( int i = 0; i < listaCanciones.size(); i++)
        {
            System.out.println("\t"+(i + 1) + ". " + listaCanciones.get(i));
        }
    }

    public boolean agregarCancionesListaReproduccion()
    {
        boolean insertadoCorrectamente = false;

        char respuestaUsuario = 'n';
        Scanner scanner = new Scanner(System.in);
        int listaSeleccionada = 0;
        int cancionSeleccionada;

        ArrayList<String> nombreListasReproduccion = devolverListadoFicherosLista(".");
        ArrayList<String> nombreCanciones = devolverListadoFicherosCancion(".");

        if ( nombreCanciones.size() > 0 && nombreListasReproduccion.size() > 0)
        {
            do
            {
                //Pedimos
                System.out.println("Estas son las listas disponibles: ");

                //Mostramos las canciones disponibles
                for (int i = 0; i < nombreListasReproduccion.size(); i++)
                {
                    System.out.println((i + 1) + ". " + nombreListasReproduccion.get(i));
                }

                do
                {
                    System.out.println("Introduzca el numero de la lista a la que quiere agregar canciones");
                    listaSeleccionada = scanner.nextInt();
                } while (listaSeleccionada < 0 || listaSeleccionada > nombreListasReproduccion.size() );

                System.out.println("Usted ha seleccionado la lista "+nombreListasReproduccion.get(listaSeleccionada - 1)+"\n");

                //Pedimos al usuario que introduzca el nombre de la cancion que quiere añadir a su lista
                System.out.println("Estas son las canciones disponibles: ");

                //Mostramos las canciones disponibles
                for (int i = 0; i < nombreCanciones.size(); i++)
                {
                    System.out.println((i + 1) + ". " + nombreCanciones.get(i));
                }

                do
                {
                    System.out.print("Introduce el numero de la cancion que quieres añadir: ");
                    cancionSeleccionada = scanner.nextInt();
                } while (cancionSeleccionada < 0 || cancionSeleccionada > nombreCanciones.size() );


                insertadoCorrectamente = insertarCancionListaReproduccion(nombreCanciones.get(listaSeleccionada - 1), nombreListasReproduccion.get(cancionSeleccionada - 1));

                //Preguntamos si quiere añadir mas canciones
                do
                {
                    System.out.print("Desea agregar mas canciones a la lista(s/n)?: ");
                    respuestaUsuario  = Character.toLowerCase(scanner.next().charAt(0));
                } while ( respuestaUsuario != 's' && respuestaUsuario != 'n' );

            } while ( respuestaUsuario == 's' );
        }
        return insertadoCorrectamente;
    }

    public void eliminarCancionesListaReproduccion()
    {
        boolean eliminadoCorrectamente = false;

        char respuestaUsuario = 'n';
        Scanner scanner = new Scanner(System.in);
        int listaSeleccionada;
        int cancionSeleccionada;

        ArrayList<String> nombreListasReproduccion = devolverListadoFicherosLista(".");
        ArrayList<String> nombreCanciones = devolverListadoFicherosCancion(".");
        ListaDeReproduccionImpl listaDeReproduccion;

        if ( nombreCanciones.size() > 0 && nombreListasReproduccion.size() > 0)
        {
            do
            {
                //Pedimos
                System.out.println("Estas son las listas disponibles: ");

                //Mostramos las canciones disponibles
                for (int i = 0; i < nombreListasReproduccion.size(); i++)
                {
                    System.out.println((i + 1) + ". " + nombreListasReproduccion.get(i));
                }

                do
                {
                    System.out.println("Introduzca el numero de la lista a la que quiere aliminar canciones");
                    listaSeleccionada = scanner.nextInt();
                } while (listaSeleccionada < 0 || listaSeleccionada > nombreListasReproduccion.size() );

                System.out.println("Usted ha seleccionado la lista "+nombreListasReproduccion.get(listaSeleccionada - 1)+"\n");

                //Pedimos al usuario que introduzca el nombre de la cancion que quiere añadir a su lista
                System.out.println("Estas son las canciones disponibles: ");

                //Mostramos las canciones disponibles
                mostrarCancionesListaReproduccion(nombreListasReproduccion.get(listaSeleccionada - 1));

                do
                {
                    System.out.print("Introduce el numero de la cancion que quieres eliminar: ");
                    cancionSeleccionada = scanner.nextInt();
                } while (cancionSeleccionada < 0 || cancionSeleccionada > nombreCanciones.size() );


                //eliminadoCorrectamente =

                //Preguntamos si quiere añadir mas canciones
                do
                {
                    System.out.print("Desea agregar mas canciones a la lista(s/n)?: ");
                    respuestaUsuario  = Character.toLowerCase(scanner.next().charAt(0));
                } while ( respuestaUsuario != 's' && respuestaUsuario != 'n' );

            } while ( respuestaUsuario == 's' );
        }
        //return insertadoCorrectamente;
    }

    public boolean insertarCancionListaReproduccion(String rutaCancion, String rutaListaDeReproduccion)
    {
        boolean insertadaCorrectamente = false;

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
                insertadaCorrectamente = true;
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
        return insertadaCorrectamente;
    }

    public void marcarCancionListaReproduccion(String nombreCancion, String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        String cancionAuxiliar;

        //Posicion de la cancion que queremos eliminar
        int posicionPuntero = buscarCancionListaReproduccion(nombreCancion, rutaListaDeReproduccion);

        //Si la lista existe la mostramos por pantalla
        if( listaReproduccion.exists() == true )
        {
            try
            {
                //Abrimos el fichero
                randomAccessFile = new RandomAccessFile(listaReproduccion, "rw");

                //Movemos el puntero a la posicion donde se encuentra la cancion
                randomAccessFile.seek(posicionPuntero);

                //Leemos la cancion
                cancionAuxiliar = randomAccessFile.readUTF();

                //Marcamos la cancion con un * al final
                cancionAuxiliar += '*';

                //Volvemos a la posicion donde estaba la cancion
                randomAccessFile.seek(posicionPuntero);

                //Escribimos la cancion modificada
                randomAccessFile.writeUTF(cancionAuxiliar);
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
            randomAccessFileActual.seek(posicionPuntero);

            for ( int i = 0; i < numeroCanciones; i++)
            {
                posicionPuntero += 100;
                randomAccessFileActual.seek(posicionPuntero);
                cancionAxiliar = randomAccessFileActual.readUTF();

                if ( cancionAxiliar.substring(cancionAxiliar.length() - 1).compareTo("*") != 0 )
                {
                    randomAccessFileSalida.writeUTF(cancionAxiliar);
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
    public boolean crearListaReproduccion()
    {
        boolean listaCreadaCorrectamente = false;

        File ficheroCancionSeleccionada;
        CancionImpl cancionSeleccionada;
        int numeroCancionSeleccionada;
        int contadorCanciones = 0;
        int posicionPuntero = 0;

        File ficheroListaReproduccion;
        String nombreListaReproduccion;

        RandomAccessFile randomAccessFile = null;
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listaCanciones = devolverListadoFicherosCancion(".");

        char respuestaUsuario;

        if ( listaCanciones.size() > 0)
        {
            //Pedimos que introduzca el nombre de la lista
            do
            {
                System.out.print("\nIntroduzca el nombre de la lista que quiere crear: ");

                nombreListaReproduccion = scanner.nextLine()+".lis";
                
                //Asociamos lista de reproduccion a un tipo File
                ficheroListaReproduccion = new File(nombreListaReproduccion);

                if ( ficheroListaReproduccion.exists() )
                {
                    System.out.println("Ya existe una lista de reproduccion '"+ficheroListaReproduccion.getName()+"', utilice otro nombre");
                }
                if ( ficheroListaReproduccion.getName().length() > 50 )
                {
                    System.out.println("El nombre de la lista de reproduccion debe ser como maximo 50 caracteres, utilice otro nombre");
                }
            } while( ficheroListaReproduccion.exists() || ficheroListaReproduccion.getName().length() > 50);

            try
            {
                randomAccessFile = new RandomAccessFile(ficheroListaReproduccion, "rw");

                //Escribimos el nombre de la lista
                posicionPuntero = 50;
                randomAccessFile.seek(posicionPuntero);
                randomAccessFile.writeUTF(nombreListaReproduccion);
                posicionPuntero = 0;

                do
                {
                    mostrarCancionesExistentes(".");

                    do
                    {
                        System.out.print("Introduce el numero de la cancion que quieres añadir: ");
                        numeroCancionSeleccionada = scanner.nextInt();
                    } while (numeroCancionSeleccionada < 0 && numeroCancionSeleccionada > listaCanciones.size() );

                    try
                    {


                        ficheroCancionSeleccionada = new File(listaCanciones.get(numeroCancionSeleccionada - 1));
                        cancionSeleccionada = new CancionImpl(ficheroCancionSeleccionada.toURI().toURL().toString());

                        posicionPuntero += 100;
                        System.out.println(posicionPuntero);
                        randomAccessFile.seek(posicionPuntero);
                        randomAccessFile.writeUTF(cancionSeleccionada.getRuta());
                        System.out.println(cancionSeleccionada.getRuta());

                        contadorCanciones++;
                        System.out.println(cancionSeleccionada.getNombreFichero() + ", " + cancionSeleccionada.getRuta() + "," + cancionSeleccionada.getRuta().getBytes().length);

                    }
                    catch (CancionException e)
                    {
                        e.printStackTrace();
                    }

                    //Preguntamos al usuario si desea continuar agregando canciones
                    do
                    {
                        System.out.print("\nDesea introducir mas canciones a la lista?: (S/n): ");
                        respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
                        scanner.nextLine();
                    } while (respuestaUsuario != 's' && respuestaUsuario != 'n');
                }while (respuestaUsuario == 's') ;

                randomAccessFile.seek(0);
                randomAccessFile.writeUTF(String.valueOf(contadorCanciones));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    randomAccessFile.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            System.out.println("No hay ninguna cancion para añadir a la lista");
        }

        return listaCreadaCorrectamente;
    }

    public void mostrarCancionesListaReproduccion(String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        int numeroCanciones;
        int posicionPuntero = 0;

        //Si la lista existe la mostramos por pantalla
        if( UtilFicheros.ficheroEsListaReproduccion(rutaListaDeReproduccion) )
        {
            try
            {
                randomAccessFile = new RandomAccessFile(listaReproduccion, "r");

                //Leemos el numero de canciones
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                //Volvemos al principio
                posicionPuntero = 0;

                for ( int i = 0; i < numeroCanciones; i++)
                {
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);
                    System.out.println((i + 1) + ". " + randomAccessFile.readUTF());
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

    public int buscarCancionListaReproduccion(String nombreCancion, String rutaListaDeReproduccion)
    {
        //Variables para leer la lista
        File listaReproduccion = new File(rutaListaDeReproduccion);
        RandomAccessFile randomAccessFile = null;

        int numeroCanciones;

        String rutaCancion;
        String [] rutaCancionDescompuesta;

        int posicionCancionEncontrada = 0;
        boolean cancionEncontrada = false;

        int posicionPuntero = 0;

        //Si la lista existe
        if( listaReproduccion.exists() == true )
        {
            try
            {
                randomAccessFile = new RandomAccessFile(listaReproduccion, "r");

                //Leemos el numero de canciones que tiene la lista
                randomAccessFile.seek(posicionPuntero);
                numeroCanciones = Integer.parseInt(randomAccessFile.readUTF());

                //Ponemos el puntero al principio
                posicionPuntero = 0;

                //Recorremos el fichero hasta el final mientras no encontremos el elemento
                for ( int i = 0; i < numeroCanciones && cancionEncontrada == false; i++)
                {
                    //Avanzamos una posicion
                    posicionPuntero += 100;
                    randomAccessFile.seek(posicionPuntero);

                    //Leemos la cancion actual
                    rutaCancion = randomAccessFile.readUTF();
                    rutaCancionDescompuesta = rutaCancion.split("/");

                    //Comprobamos si la cancion actual es la cancion que estamos buscando
                    if ( nombreCancion.compareTo(rutaCancionDescompuesta[rutaCancionDescompuesta.length - 1]) == 0)
                    {
                        cancionEncontrada = true;
                        posicionCancionEncontrada = posicionPuntero;
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
        return posicionCancionEncontrada;
    }


    public boolean borrarListaReproduccion()
    {
        boolean borradoCorrectamente = false;

        Scanner scanner = new Scanner(System.in);
        int listaReproduccionSeleccionada;
        char respuestaUsuario;

        ArrayList<String> listasDeReproduccion = devolverListadoFicherosLista(".");

        if ( listasDeReproduccion.size() > 0)
        {
            do
            {
                for ( int i = 0; i < listasDeReproduccion.size(); i++)
                {
                    System.out.println("\t"+(i+1)+". "+listasDeReproduccion.get(i));
                }

                do
                {
                    System.out.print("Introduce el numero de la lista que quieres borrar: ");
                    listaReproduccionSeleccionada = scanner.nextInt();
                } while ( listaReproduccionSeleccionada < 0 && listaReproduccionSeleccionada > listasDeReproduccion.size() );

                borradoCorrectamente = borrarFicheroListaDeReproduccion(listasDeReproduccion.get(listaReproduccionSeleccionada - 1));

                do
                {
                    System.out.print("\n¿Desea borrar otra lista de reproduccion(s/n)?: ");
                    respuestaUsuario  = Character.toLowerCase(scanner.next().charAt(0));
                } while ( respuestaUsuario != 's' && respuestaUsuario != 'n' );
            } while ( respuestaUsuario == 's');
        }
        else
        {
            System.out.println("No hay ninguna lista");
        }



        return borradoCorrectamente;
    }


    public boolean borrarFicheroListaDeReproduccion(String rutaListaDeReproduccion)
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

        CancionImpl cancionActual = new CancionImpl();
        ReproductorImpl reproductor = new ReproductorImpl();

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
                    String a = randomAccessFile.readUTF();
                    System.out.println(a);
                    cancionActual.setRuta(a);


                    //Reproducimos la cancion
                    reproductor.reproducirCancion(cancionActual);



                    while ( Math.round(reproductor.getReproductor().getMediaTime().getSeconds()) < Math.round(reproductor.getReproductor().getDuration().getSeconds()))
                    {
                        reproductor.mostrarReproduccionActual(cancionActual);

                        System.out.println("Lista de reproduccion: "+nombreListaReproduccion+"\n");
                        for (int j = 0; j < numeroCanciones; j++)
                        {
                            String mensaje = " ";

                                mensaje = " Reproduciendo: "+(j+1)+". "+cancionActual.getNombreFichero();

                             System.out.println(mensaje+"\n");
                        }
                    }

                }

            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (CancionException e) {
                e.printStackTrace();
            } finally
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

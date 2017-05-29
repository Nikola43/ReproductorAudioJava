package Tests;

import Clases.CancionImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public class TestCancion
{
    public static void main(String[] args)
    {
        //Creamos 3 canciones con los diferentes constructores para probarlos todos
        CancionImpl cancionDefecto = new CancionImpl();
        CancionImpl cancionSobrecargada = null;
        CancionImpl cancionCopia = null;
        CancionImpl cancionClon = null;

        try
        {
            cancionSobrecargada = new CancionImpl("Maybe.mp3");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        cancionCopia = new CancionImpl(cancionSobrecargada);

        //Test metodos modificadores
        try
        {
            cancionDefecto.setRuta("Over.mp3");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //Test metodos consultores
        System.out.println("Test metodos consultores");
        System.out.println("\tgetRuta(): "+cancionCopia.getRuta());
        System.out.println("\tgetNombre(): "+cancionCopia.getNombreFichero());

        //Test metodos sobrescritos
        System.out.println("Test metodos sobrescritos");
        System.out.println("\ttoString():  "+cancionDefecto.toString());
        System.out.println("\thashCode():  "+cancionDefecto.hashCode());
        System.out.println("\tcompareTo(): "+cancionCopia.compareTo(cancionDefecto));
        System.out.println("\tcompareTo(): "+cancionDefecto.compareTo(cancionCopia));
        System.out.println("\tcompareTo(): "+cancionDefecto.compareTo(cancionDefecto));
        System.out.println("\tequals():    "+cancionDefecto.equals(cancionSobrecargada));
        System.out.println("\tequals():    "+cancionDefecto.equals(cancionDefecto));

        //Test clone
        cancionClon = cancionSobrecargada.clone();

        System.out.println("\tClon: "+cancionClon.toString());

    }
}

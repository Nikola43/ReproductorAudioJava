package Tests;

import Clases.CancionImpl;

import java.io.FileNotFoundException;

public class TestCancion
{
    public static void main(String[] args)
    {
        //Creamos 3 canciones con los diferentes constructores para probarlos todos
        CancionImpl cancionDefecto = new CancionImpl();
        CancionImpl cancionSobrecargada = null;
        try {
            cancionSobrecargada = new CancionImpl("Maybe.mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CancionImpl cancionCopia = new CancionImpl(cancionSobrecargada);


        try {
            cancionDefecto.setRuta("dance.mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //Test metodos modificadores


        //Test metodos consultores
        System.out.println("Test metodos consultores");
        System.out.println("\tgetRuta(): "+cancionCopia.getRuta());
        System.out.println("\tgetNombre(): "+cancionCopia.getNombreFichero());

        //Test metodos sobrescritos
        System.out.println("Test metodos sobrescritos");
        System.out.println("\ttoString():  "+cancionDefecto.toString());
        System.out.println("\thashCode():  "+cancionDefecto.hashCode());
        System.out.println("\tcompareTo(): "+cancionDefecto.compareTo(cancionSobrecargada));
        System.out.println("\tequals():    "+cancionDefecto.equals(cancionCopia));

    }
}

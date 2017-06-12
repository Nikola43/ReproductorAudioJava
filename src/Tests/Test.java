package Tests;

import Clases.CancionImpl;
import Clases.ReproductorImpl;
import Excepciones.CancionException;
import Gestion.GestionListaReproduccion;

import java.io.File;

public class Test
{
    public static void main(String[] args)
    {
        CancionImpl cancion = null;
        CancionImpl cancion1 = new CancionImpl();
        try {
            cancion = new CancionImpl("Over.mp3");
        } catch (CancionException e)
        {
            e.printStackTrace();
        }

        File file = new File(cancion.getRuta());
        System.out.println();
        System.out.println(cancion.getRuta());



        GestionListaReproduccion gestionListaReproduccion = new GestionListaReproduccion();




    }
}

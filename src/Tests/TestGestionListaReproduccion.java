package Tests;

import Clases.CancionImpl;
import Clases.ListaDeReproduccionImpl;
import Excepciones.CancionException;
import Gestion.GestionListaReproduccion;

import java.io.FileNotFoundException;

public class TestGestionListaReproduccion
{
    public static void main(String[] args)
    {
        try {
            CancionImpl cancion = new CancionImpl("Over.mp3");
        }
        catch (CancionException e)
        {
            e.printStackTrace();
        }
        GestionListaReproduccion gestionListaReproduccion = new GestionListaReproduccion();

        //Test metodos EsUn
        System.out.println("Es una cancion: "+CancionImpl.esCancion("Over.mp3"));
        System.out.println("Es una lista: "+ ListaDeReproduccionImpl.esListaReproduccion("ListasDeReproduccion/ListaReproduccion.lis"));

        //Test mostrar canciones
        //gestionListaReproduccion.mostrarCancionesDisponibles("Canciones/");

        //Test seleccionar cancion
        //gestionListaReproduccion.crearListaReproduccion();
        //gestionListaReproduccion.leerListaReproduccion("pauloSoares.lis");
        //gestionListaReproduccion.insertarCancionListaReproduccion("dance.mp3", "pauloSoares.lis");
        //gestionListaReproduccion.leerListaReproduccion("pauloSoares.lis");
        //gestionListaReproduccion.mostrarCancionesListaReproduccion("pauloSoares.lis");
        //gestionListaReproduccion.reproducirListaReproduccion("asun.lis");
        //System.out.println("Interfaces.Cancion: "+gestionListaReproduccion.seleccionarCancion());
        //System.out.println(gestionListaReproduccion.buscarCancionListaReproduccion("Maybe.mp3", "pauloSoares.lis"));
        gestionListaReproduccion.marcarCancionListaReproduccion("Maybe.mp3", "pauloSoares.lis");
    }
}

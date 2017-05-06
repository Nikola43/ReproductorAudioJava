package Tests;

import Clases.CancionImpl;
import Gestion.GestionListaReproduccion;

public class TestGestionListaReproduccion
{
    public static void main(String[] args)
    {
        CancionImpl cancion = new CancionImpl("Canciones/Over.mp3");
        GestionListaReproduccion gestionListaReproduccion = new GestionListaReproduccion();

        //Test metodos EsUn
        System.out.println("Es una cancion: "+gestionListaReproduccion.esCancion("Canciones/Over.mp3"));
        System.out.println("Es una lista: "+gestionListaReproduccion.esListaReproduccion("ListasDeReproduccion/ListaReproduccion.lis"));

        //Test mostrar canciones
        //gestionListaReproduccion.mostrarCancionesDisponibles("Canciones/");

        //Test seleccionar cancion
        gestionListaReproduccion.crearListaReproduccion();
        gestionListaReproduccion.leerListaDeReproduccion("ListasDeReproduccion/ListaReproduccion.lis");
        //System.out.println("Cancion: "+gestionListaReproduccion.seleccionarCancion());
    }
}

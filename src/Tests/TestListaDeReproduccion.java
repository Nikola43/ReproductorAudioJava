package Tests;

import Clases.CancionImpl;
import Clases.ListaDeReproduccionImpl;
import Excepciones.CancionException;
import Excepciones.ListaDeReproduccionException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestListaDeReproduccion
{
    public static void main(String[] args)
    {
        ArrayList<CancionImpl> lista1 = new ArrayList<>();
        ArrayList<CancionImpl> lista2 = new ArrayList<>();
        ArrayList<CancionImpl> lista3 = new ArrayList<>();


        try {
            lista1.add(new CancionImpl("Overs.mp3"));
        } catch (CancionException e) {
            e.printStackTrace();
        }


        //Creamos 3 Listas de reproduccion con los diferentes constructores para probarlos todos
        ListaDeReproduccionImpl listaDeReproduccionDefecto = new ListaDeReproduccionImpl();
        ListaDeReproduccionImpl listaDeReproduccionSobrecargada = null;
        try {
            listaDeReproduccionSobrecargada = new ListaDeReproduccionImpl("pepe.lis",lista1);
        } catch (ListaDeReproduccionException e) {
            e.printStackTrace();
        }
        ListaDeReproduccionImpl listaDeReproduccionCopia = new ListaDeReproduccionImpl(listaDeReproduccionSobrecargada);

        //Test metodos modificadores

        try {
            listaDeReproduccionDefecto.setNombre("paulo.lis");
        } catch (ListaDeReproduccionException e) {
            e.printStackTrace();
        }

        listaDeReproduccionDefecto.setListaCanciones(new ArrayList<>());

        try {
            listaDeReproduccionDefecto.getListaCanciones().add(new CancionImpl("dance.mp3"));
            listaDeReproduccionDefecto.getListaCanciones().add(new CancionImpl("Maybe.mp3"));
            listaDeReproduccionCopia.getListaCanciones().add(new CancionImpl("Maybe.mp3"));
        } catch (CancionException e) {
            e.printStackTrace();
        }




        //Test metodos consultores
        System.out.println("Test metodos consultores");
        System.out.println("\tgetNumeroCanciones(): "+listaDeReproduccionDefecto.getNumeroCanciones());
        System.out.println("\tgetgetNombre(): "+listaDeReproduccionCopia.getNombre());
        lista3 = listaDeReproduccionSobrecargada.getListaCanciones();


        //Test metodos sobrescritos
        System.out.println("Test metodos sobrescritos");
        System.out.println("\ttoString():  "+listaDeReproduccionDefecto.toString());
        System.out.println("\thashCode():  "+listaDeReproduccionDefecto.hashCode());
        System.out.println("\tcompareTo(): "+listaDeReproduccionDefecto.compareTo(listaDeReproduccionSobrecargada));
        System.out.println("\tequals():    "+listaDeReproduccionDefecto.equals(listaDeReproduccionSobrecargada));

        System.out.println(listaDeReproduccionDefecto.getDuracionTotal());

    }
}

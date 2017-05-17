public class TestGestionListaReproduccion
{
    public static void main(String[] args)
    {
        CancionImpl cancion = new CancionImpl("Over.mp3");
        GestionListaReproduccion gestionListaReproduccion = new GestionListaReproduccion();

        //Test metodos EsUn
        System.out.println("Es una cancion: "+gestionListaReproduccion.esCancion("Over.mp3"));
        System.out.println("Es una lista: "+gestionListaReproduccion.esListaReproduccion("ListasDeReproduccion/ListaReproduccion.lis"));

        //Test mostrar canciones
        //gestionListaReproduccion.mostrarCancionesDisponibles("Canciones/");

        //Test seleccionar cancion
        //gestionListaReproduccion.crearListaReproduccion();
        gestionListaReproduccion.leerListaReproduccion("koke.lis");
        //System.out.println("Cancion: "+gestionListaReproduccion.seleccionarCancion());
    }
}

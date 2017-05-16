import javax.media.Player;

/*
PROPIEDADES
    BASICAS
        Player reproductor | consultable y modificable

    DERIVADAS
        int estadoActual

    COMPARTIDAS
        -
*/
public interface Reproductor
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    public Player getReproductor();
    public int getEstadoActual();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    public void reproducir(CancionImpl cancion);
    public void pararReproduccion();
    public void pausarReproduccion();
    public void reanudarReproduccion();
    public void rebobinar(int posicion);
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}

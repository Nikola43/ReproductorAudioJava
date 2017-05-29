package Interfaces;

import Clases.CancionImpl;

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
    public void reproducirCancion(CancionImpl cancion);
    public void pararReproduccion();
    public void rebobinarAtras(int posicion);
    public void rebobinarAdelante(int posicion);
    public void reanudarReproduccion();
    public void pausarReproduccion();
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}

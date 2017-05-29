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
    Player getReproductor();
    int getEstadoActual();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    void reproducirCancion(CancionImpl cancion);
    void pararReproduccion();
    void rebobinarAtras(int posicion);
    void rebobinarAdelante(int posicion);
    void reanudarReproduccion();
    void pausarReproduccion();
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}

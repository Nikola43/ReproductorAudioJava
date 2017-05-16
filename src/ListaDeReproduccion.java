/*
    PROPIEDADES
        BASICAS
            String nombre                     | consultable y modificable
            ArrayList<Cancion> listaCanciones | consultable y modificable

        DERIVADAS
            int numeroCanciones | consultable

        COMPARTIDAS
            -
*/

import java.util.ArrayList;

public interface ListaDeReproduccion
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    public String getNombre();
    public ArrayList<CancionImpl> getListaCanciones();
    public int getNumeroCanciones();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    public void setNombre(String nombre);
    public void setListaCanciones(ArrayList<CancionImpl> listaCanciones);
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}
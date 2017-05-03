package Clases;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaDeReproduccionImpl implements ListaDeReproduccion, Comparable<CancionImpl>, Serializable
{
//------------------------------- PROPIEDADES -----------------------------------------------//
    //BASICAS
    private ArrayList<CancionImpl> listaCanciones;

    //DERIVADAS
    //int numeroCanciones

    //COMPARTIDAS
    //NINGUNA
//------------------------------- FIN PROPIEDADES --------------------------------------------//

//------------------------------- CONSTRUCTORES ----------------------------------------------//
    //CONSTRUCTOR POR DEFECTO
    public ListaDeReproduccionImpl()
    {
            listaCanciones = null;
    }
    //CONSTRUCTOR SOBRECARGADO
    public ListaDeReproduccionImpl(ArrayList<CancionImpl> listaCanciones)
    {
        this.listaCanciones = listaCanciones;
    }
    //CONSTRUCTOR DE COPIA
    public ListaDeReproduccionImpl(ListaDeReproduccionImpl listaDeReproduccion )
    {
        this.listaCanciones = listaDeReproduccion.getListaCanciones();
    }
//------------------------------- FIN CONSTRUCTORES ------------------------------------------//

//------------------------------- METODOS CONSULTORES ----------------------------------------//
    @Override
    public ArrayList<CancionImpl> getListaCanciones()
    {
        return listaCanciones;
    }

    @Override
    public int getNumeroCanciones()
    {
        return getListaCanciones().size();
    }
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    @Override
    public void setListaCanciones(ArrayList<CancionImpl> listaCanciones)
    {
        this.listaCanciones = listaCanciones;
    }
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS HEREDADOS ------------------------------------------//
@Override
public int compareTo(CancionImpl o)
{
    return 0;
}
//------------------------------- FIN METODOS HEREDADOS --------------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//

}

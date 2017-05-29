package Clases;

import Interfaces.ListaDeReproduccion;

import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;

public class ListaDeReproduccionImpl implements ListaDeReproduccion, Cloneable, Comparable<ListaDeReproduccionImpl>, Serializable
{
//------------------------------- PROPIEDADES -----------------------------------------------//
    //BASICAS
    private String nombre;
    private ArrayList<CancionImpl> listaCanciones;

    //DERIVADAS
    //int numeroCanciones
    //Time duracionTotal

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
    public ListaDeReproduccionImpl(String nombre, ArrayList<CancionImpl> listaCanciones)
    {
        this.nombre = nombre;
        this.listaCanciones = listaCanciones;
    }
    //CONSTRUCTOR DE COPIA
    public ListaDeReproduccionImpl(ListaDeReproduccionImpl listaDeReproduccion )
    {
        this.nombre = listaDeReproduccion.getNombre();
        this.listaCanciones = listaDeReproduccion.getListaCanciones();
    }
//------------------------------- FIN CONSTRUCTORES ------------------------------------------//

//------------------------------- METODOS CONSULTORES ----------------------------------------//
    @Override
    public String getNombre()
    {
        return nombre;
    }

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

    public String getDuracionTotal()
    {
        //Variables para almacenar las unidades de tiempo
        long duracionTotalMilisegundos = 0;
        long horas;
        long minutos;
        long segundos;

        //Variable auxiliar para devolver la duracion total de la lista como String
        String duracionTotalListaToString;

        //Variable para realizar la conversion
        Duration duracionTotalLista;

        //Recorremos la lista completa y vamos sumando la duracion de cada cancion
        for (int i = 0; i < getNumeroCanciones(); i++ )
        {
            duracionTotalMilisegundos += Double.valueOf(getListaCanciones().get(i).extraerMetadatos()[6]);
        }

        //Convertimos los milisegundos en horas, minutos y segundos
        duracionTotalLista = Duration.ofMillis(duracionTotalMilisegundos);
        horas = duracionTotalLista.toHours();
        minutos = (int) ((duracionTotalLista.getSeconds() % (60 * 60)) / 60);
        segundos = (int) (duracionTotalLista.getSeconds() % 60);

        //Devolvemos la duracion total de la lista como cadena con el formato horas:minutos:segundos
        duracionTotalListaToString = horas+":"+minutos+":"+segundos;
        return (duracionTotalListaToString);
    }
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    @Override
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    @Override
    public void setListaCanciones(ArrayList<CancionImpl> listaCanciones)
    {
        this.listaCanciones = listaCanciones;
    }
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS SOBRESCRITOS ---------------------------------------//
    @Override
    public String toString()
    {
        String toString;
        toString = getNombre()+","+getListaCanciones().toString()+","+getNumeroCanciones();
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = getListaCanciones().toString().charAt(5) + getNombre().charAt(2) + 13 / 2 * getNumeroCanciones() - getListaCanciones().toString().charAt(3) + getListaCanciones().toString().charAt(15) - getListaCanciones().toString().charAt(5)  ;
        return hashCode;
    }

    @Override
    public ListaDeReproduccionImpl clone()
    {
        ListaDeReproduccionImpl clonListaDeReproduccion = null;

        try
        {
            clonListaDeReproduccion = (ListaDeReproduccionImpl) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clonListaDeReproduccion;
    }

    /* INTERFAZ
       Cabecera:
           public int compareTo(Clases.ListaDeReproduccionImpl listaDeReproduccion)
       Descripcion:
          Compara una lista de reproduccion segun su nombre
       Precondiciones:
          Ninguna
       Entradas:
          Un objeto clonListaDeReproduccion
       Salidas:
          Un entero
       Postcondiciones:
          - Devolvera -1 cuando sea menor, 0 cuando sean iguales, 1 cuando sea mayor
          - Sera menor cuando el nombre del objeto que lanza el metodo sea inferior al objeto pasado por parametro
          - Sera mayor cuando el nombre del objeto que lanza el metodo sea superior al objeto pasado por parametro
          - Sera igual cuando el nombre del objeto que lanza el metodo sea iguales  al objeto pasado por parametro
    */
    @Override
    public int compareTo(ListaDeReproduccionImpl listaDeReproduccion)
    {
        int compareTo = -1;

        if ( listaDeReproduccion != null )
        {
            if ( this.getNombre().compareTo(listaDeReproduccion.getNombre()) > 0 )
            {
                compareTo = 1;
            }
            else if ( this.getNombre().compareTo(listaDeReproduccion.getNombre()) < 0 )
            {
                compareTo = -1;
            }
            else
            {
                compareTo = 0;
            }
        }
        return compareTo;
    }

    /* INTERFAZ
       Cabecera:
           public boolean equals(Object object)
       Descripcion:
           Comprueba si el objeto que lanza el metodo es igual que el objeto pasado por parametro
       Precondiciones:
           Ninguna
       Entradas:
           Un objeto
       Salidas:
           Un booleano
       Postcondiciones:
           - Los objetos seran iguales cuando tengan el mismo numero de canciones
           - Devolvera VERDADERO si los objetos son iguales y FALSO cuando no lo sean
     */
    @Override
    public boolean equals(Object object)
    {
        boolean esIgual = false;

        if (object != null && object instanceof ListaDeReproduccionImpl)
        {
            ListaDeReproduccionImpl listaDeReproduccion = (ListaDeReproduccionImpl) object;

            //Si tienen el mismo nombre son canciones iguales
            if (this.getListaCanciones() == listaDeReproduccion.getListaCanciones())
            {
                esIgual = true;
            }
        }
        return esIgual;
    }
//------------------------------- FIN METODOS SOBRESCRITOS -----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//

}

package Clases;
/*
    PROPIEDADES
        BASICAS
            String ruta | consultable y modificable

        DERIVADAS
            String nombre | consultable

        COMPARTIDAS
            -

    RESTRICCIONES
        -

    INTERFAZ
        METODOS SOBRESCRITOS
            public String toString()
            public int hashCode()
            public CancionImpl clone()
            public boolean equals(Object object)
            public int compareTo(CancionImpl cancion)
*/

import java.io.Serializable;

public class CancionImpl implements Cancion, Comparable<CancionImpl>, Serializable 
{
//------------------------------- PROPIEDADES -----------------------------------------------//
    //BASICAS
    private String ruta;

    //DERIVADAS
    //String nombre

    //COMPARTIDAS
//------------------------------- FIN PROPIEDADES --------------------------------------------//

//------------------------------- CONSTRUCTORES ----------------------------------------------//
    //CONSTRUCTOR POR DEFECTO
    public CancionImpl()
    {
        ruta = null;
    }
    //CONSTRUCTOR SOBRECARGADO
    public CancionImpl(String ruta)
    {
        this.ruta = ruta;
    }
    //CONSTRUCTOR DE COPIA
    public CancionImpl(CancionImpl cancion)
    {
        this.ruta = cancion.getRuta();
    }
//------------------------------- FIN CONSTRUCTORES ------------------------------------------//

//------------------------------- METODOS CONSULTORES ----------------------------------------//
    @Override
    public String getRuta()
    {
        return ruta;
    }
    /*
    INTERFAZ
        Cabecera:
            public String getNombre()
        Descripcion:
            Devuelve el nombre de la cancion sacandolo de la ruta del fichero
            divide la ruta por '/' en un array de cadenas y coge el ultimo elemento

            Ejemplo: /home/paulo/Escritorio/MiMusica/cancion.mp3
                       0     1       2         3         4
            Devolvería 'cancion.mp3'
        Entradas:
            -
        Precondiciones:
            -
        Salidas:
            String
        Postcondiciones:
            Devolvera el nombre del fichero junto con el formato
        Entrada/Salida:
            -
    */
    @Override
    public String getNombre()
    {
        String [] rutaDescompuesta;

        //Dividimos la ruta usando como separador la /
        rutaDescompuesta = ruta.split("/");

        //Devolvemos el ultimo elemento
        return rutaDescompuesta[rutaDescompuesta.length - 1];
    }
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    @Override
    public void setRuta(String ruta)
    {
        this.ruta = ruta;
    }
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS SOBRESCRITOS ---------------------------------------//
    @Override
    public String toString()
    {
        String toString;
        toString = getRuta()+","+getNombre();
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = getNombre().charAt(1) + getNombre().charAt(2) + getNombre().charAt(3) / getNombre().charAt(4) * getNombre().charAt(5);
        return hashCode;
    }

    @Override
    public CancionImpl clone()
    {
        CancionImpl clonCancion = null;

        try
        {
            clonCancion = (CancionImpl) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
        return clonCancion;
    }

    /* INTERFAZ
       Cabecera:
           public int compareTo(CancionImpl cancion)
       Descripcion:
          Compara un objeto con otro
       Precondiciones:
          Ninguna
       Entradas:
          Un objeto CancionImpl
       Salidas:
          Un entero
       Postcondiciones:
          - Devolvera -1 cuando sea menor, 0 cuando sean iguales, 1 cuando sea mayor alfabeticamente
          - Sera menor cuando el nombre del objeto que lanza el metodo sea inferior al objeto pasado por parametro
          - Sera mayor cuando el nombre del objeto que lanza el metodo sea superior al objeto pasado por parametro
          - Sera igual cuando el nombre del objeto que lanza el metodo sea iguales  al objeto pasado por parametro
    */
    @Override
    public int compareTo(CancionImpl cancion)
    {
        int compareTo = -1;

        if ( cancion != null )
        {
            if ( this.getRuta().compareTo(cancion.getRuta()) > 0 )
            {
                compareTo = 1;
            }
            else if ( this.getRuta().compareTo(cancion.getRuta()) < 0 )
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
           - Los objetos seran iguales cuando tengan el mismo nombre
           - Devolvera VERDADERO si los objetos son iguales y FALSO cuando no lo sean
     */
    @Override
    public boolean equals(Object object)
    {
        boolean esIgual = false;

        if (object != null && object instanceof CancionImpl)
        {
            CancionImpl cancion = (CancionImpl) object;

            //Si tienen el mismo nombre son canciones iguales
            if (this.getNombre().compareTo(cancion.getNombre()) == 0)
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

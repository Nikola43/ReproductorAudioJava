/*
    PROPIEDADES
        BASICAS
            NINGUNA

        DERIVADAS
            NINGUNA

        COMPARTIDAS
            NINGUNA

    RESTRICCIONES
        NINGUNA

    INTERFAZ
        METODOS CONSULTORES

        METODOS MODIFICADORES

        METODOS HEREDADOS
            public String toString()
            public int hashCode()
            public Jugador clone()
            public boolean equals(Object object)
            public int compareTo(Jugador jugador)

        METODOS AÑADIDOS
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
    public String getRuta()
    {
        return ruta;
    }
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
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
        toString = ruta;
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = 25+Integer.parseInt(ruta) * 13;
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
        catch (CloneNotSupportedException error)
        {
            System.out.println("Error: No se pudo clonar el objeto");
        }
        return clonCancion;
    }

    /* INTERFAZ
       Cabecera:
           public int compareTo(Usuario usuario)
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
            if ( this.getRuta().compareTo(cancion.getRuta()) < 0 )
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
           Comprueba si el objeto que lanza el metodo es igual que el objeto pasado
           por parametro
       Precondiciones:
           Ninguna
       Entradas:
           Un objeto
       Salidas:
           Un booleano
       Postcondiciones:
           - Los objetos seran iguales cuando tengan el mismo nombre de usuario
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
            if (this.getRuta().compareTo(cancion.getRuta()) == 0)
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

package Clases;

import java.io.*;

import Interfaces.Cancion;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

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
            public Clases.CancionImpl clone()
            public boolean equals(Object object)
            public int compareTo(Clases.CancionImpl cancion)
*/

public class CancionImpl implements Cancion, Cloneable, Comparable<CancionImpl>, Serializable
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
    public CancionImpl(String ruta) throws FileNotFoundException
    {
        //Si el fichero existe
        if (new File(ruta).exists())
        {
            this.ruta = ruta;
        }
        else
        {
            throw new FileNotFoundException("La ruta del fichero no es válida");
        }
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
                public String getNombreFichero()
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
    public String getNombreFichero()
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
    public void setRuta(String ruta) throws FileNotFoundException {
        //Si el fichero existe
        if (new File(ruta).exists())
        {
            this.ruta = ruta;
        }
        else
        {
            throw new FileNotFoundException("La ruta del fichero no es válida");
        }
        this.ruta = ruta;
    }
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS SOBRESCRITOS ---------------------------------------//
    @Override
    public String toString()
    {
        String toString;
        toString = getNombreFichero()+","+getRuta();
        return toString;
    }

    @Override
    public int hashCode()
    {
        int hashCode;
        hashCode = getNombreFichero().charAt(1) + getNombreFichero().charAt(2) + getNombreFichero().charAt(3) / getNombreFichero().charAt(4) * getNombreFichero().charAt(5);
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
           public int compareTo(Clases.CancionImpl cancion)
       Descripcion:
          Compara una cancion con otra segun su nombre
       Precondiciones:
          Ninguna
       Entradas:
          Un objeto Clases.CancionImpl
       Salidas:
          Un entero
       Postcondiciones:
          - Sera -1 cuando el nombre de la cancion que lanza el metodo sea alfabeticamente inferior a la cancion pasada por parametro
          - Sera  1 cuando el nombre de la cancion que lanza el metodo sea alfabeticamente superior a la cancion pasada por parametro
          - Sera  0 cuando el nombre de la cancion que lanza el metodo sea alfabeticamente igual    a la cancion pasada por parametro
    */
    @Override
    public int compareTo(CancionImpl cancion)
    {
        int compareTo = -1;

        if ( cancion != null )
        {
            if ( this.getNombreFichero().compareTo(cancion.getNombreFichero()) > 0 )
            {
                compareTo = 1;
            }
            else if ( this.getNombreFichero().compareTo(cancion.getNombreFichero()) < 0 )
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
            if (this.getNombreFichero().compareTo(cancion.getNombreFichero()) == 0)
            {
                esIgual = true;
            }
        }
        return esIgual;
    }
//------------------------------- FIN METODOS SOBRESCRITOS -----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
    @Override
    public String[] extraerMetadatos()
    {
        //Fichero de audio
        File file = new File(this.getNombreFichero());

        //Variables auxiliares para extraer y parsear los metadatos del fichero
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = null;
        ParseContext context = new ParseContext();

        //Array de cadenas con los diferentes datos
        String metadatosCancion[] = new String[7];

        //Abrimos el fichero
        try
        {
            inputstream = new FileInputStream(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        //Parseamos los datos
        try
        {
            parser.parse(inputstream, handler, metadata, context);
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        } catch (TikaException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        //Extraemos los datos de la cancion
        metadatosCancion[0] = metadata.get("dc:title")          == null ? "Desconocido": metadata.get("dc:title");
        metadatosCancion[1] = metadata.get("xmpDM:artist")      == null ? "Desconocido": metadata.get("xmpDM:artist");
        metadatosCancion[2] = metadata.get("xmpDM:trackNumber") == null ? "Desconocido": metadata.get("xmpDM:trackNumber");
        metadatosCancion[3] = metadata.get("xmpDM:album")       == null ? "Desconocido": metadata.get("xmpDM:album");
        metadatosCancion[4] = metadata.get("xmpDM:releaseDate") == null ? "Desconocido": metadata.get("xmpDM:releaseDate");
        metadatosCancion[5] = metadata.get("xmpDM:genre")       == null ? "Desconocido": metadata.get("xmpDM:genre");
        metadatosCancion[6] = metadata.get("xmpDM:duration")    == null ? "Desconocido": metadata.get("xmpDM:duration");

        return metadatosCancion;
    }
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//

}

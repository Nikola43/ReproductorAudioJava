package Clases;
/*
    PROPIEDADES
        BASICAS
            String ruta | consultable y modificable

        DERIVADAS
            String nombre | consultable

        COMPARTIDAS
            -
*/

public interface Cancion 
{
//------------------------------- METODOS CONSULTORES ----------------------------------------//
    public String getRuta();
	public String getNombre();
//------------------------------- FIN METODOS CONSULTORES ------------------------------------//

//------------------------------- METODOS MODIFICADORES --------------------------------------//
    public void setRuta(String ruta);
//------------------------------- FIN METODOS MODIFICADORES ----------------------------------//

//------------------------------- METODOS AÑADIDOS -------------------------------------------//
//------------------------------- FIN METODOS AÑADIDOS ---------------------------------------//
}
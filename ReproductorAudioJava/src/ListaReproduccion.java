import java.util.LinkedList;

public interface ListaReproduccion
{

    //PROPIEDADES
        //BASICAS
        //LinkedList<CancionImpl> listaCanciones

        //DERIVADAS
        //int tamanioLista

        //COMPARTIDAS

    //METODOS CONSULTORES
    public LinkedList<CancionImpl> getListaCanciones();
    public int getTamanioLista();

    //METODOS MODIFICADORES
    public void setListaCanciones(LinkedList<CancionImpl> listaCanciones);

    //METODOS AÑADIDOS
}


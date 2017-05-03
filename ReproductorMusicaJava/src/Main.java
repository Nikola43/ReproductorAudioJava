public class Main {

    public static void main(String[] args)
    {

        int teclaPulsada;

        LeerTeclasUsandoCPP teclas = new LeerTeclasUsandoCPP();

        while( true  )
        {
            if ( teclas.hayTeclaPulsada() == true )
            {
                //leemos la tecla usando el metodo escrito en c que esta dentro de la libreria que compilamos
                teclaPulsada = teclas.leerTecla();

                //Imprimimos el codigo de la tecla pulsada
                System.out.println("\nCodigo ASCII: "+teclaPulsada+"    Tecla pulsada: "+(char)teclaPulsada);
            }
        }
    }
}

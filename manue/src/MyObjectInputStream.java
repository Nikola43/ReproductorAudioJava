import java.io.*;

public class MyObjectInputStream extends ObjectInputStream  {
    //Sobrescribimos el m�todo que crea la cabecera
    @Override
    protected void readStreamHeader() throws IOException{
        // No hacer nada.
    }
 
    //Constructores
    public MyObjectInputStream () throws IOException{
        super();
    }
    public MyObjectInputStream(InputStream in) throws IOException{
                super(in);
        }
}
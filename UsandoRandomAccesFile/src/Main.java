import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    public static void main(String[] args)
    {
        File file = new File("ListaReproduccion.lis");
        RandomAccessFile randomAccessFile = null;



        CancionImpl cancion1  = new CancionImpl("Over.mp3");
        CancionImpl cancion2  = new CancionImpl("Jeepers.mp3");
        CancionImpl cancion3  = new CancionImpl("Maybe.mp3");
        CancionImpl cancion4 = new CancionImpl("Uranium.mp3");

        byte[] propiedadCancion = new byte[20];

        try
        {
            randomAccessFile = new RandomAccessFile(file, "rw");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            System.out.println(randomAccessFile.getFilePointer());

            propiedadCancion = cancion2.getRuta().getBytes();
            randomAccessFile.write(propiedadCancion);
            randomAccessFile.seek(20);

            System.out.println(randomAccessFile.getFilePointer());

            propiedadCancion = cancion2.getNombre().getBytes();
            randomAccessFile.write(propiedadCancion);
            randomAccessFile.seek(40);

            System.out.println(randomAccessFile.getFilePointer());

            //--------------------------------------------------------//

            byte[] propiedadLeida = new byte[20];

            String s;

            randomAccessFile.seek(0);
            randomAccessFile.read(propiedadLeida, 0, 20);

            s = new String(propiedadLeida);
            System.out.println(s);

            randomAccessFile.seek(20);
            randomAccessFile.read(propiedadLeida, 0, 20);

            s = new String(propiedadLeida);
            System.out.println(s);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

public class TestCancion
{
    public static void main(String[] args)
    {
        //Creamos 3 canciones con los diferentes constructores para probarlos todos
        CancionImpl cancionDefecto = new CancionImpl();
        CancionImpl cancionSobrecargada = new CancionImpl("Maybe.mp3");
        CancionImpl cancionCopia = new CancionImpl(cancionSobrecargada);

        //Test metodos modificadores
        cancionDefecto.setRuta("dance.mp3");

        //Test metodos consultores
        System.out.println("Test metodos consultores");
        System.out.println("\tgetRuta(): "+cancionCopia.getRuta());
        System.out.println("\tgetNombre(): "+cancionCopia.getNombreFichero());

        //Test metodos sobrescritos
        System.out.println("Test metodos sobrescritos");
        System.out.println("\ttoString():  "+cancionDefecto.toString());
        System.out.println("\thashCode():  "+cancionDefecto.hashCode());
        System.out.println("\tcompareTo(): "+cancionDefecto.compareTo(cancionSobrecargada));
        System.out.println("\tequals():    "+cancionDefecto.equals(cancionCopia));

    }
}

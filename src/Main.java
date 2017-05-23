import java.util.Scanner;

public class Main
{
    /*
    DESCRIPCION
        Pequeño reproductor de audio programado en java que permite crear listas de reproduccion y reproducir las listas
        con tus canciones

        LIBRERIAS USADAS
            * Java Media Framework   |  https://docs.oracle.com/cd/E17802_01/j2se/javase/technologies/desktop/media/jmf/2.1.1/apidocs/
                 Framework ofrecido por java para manejo de contenido multimedia ( Nos proporciona la clase Player )

            * Decodificador de audio |  https://ffmpeg.org/
                 Decodificador de audio muy usado en linux que permite manejar ficheros de audio, reproducir, convertir etc

            * LeerTeclasCPP
                 Libreria programada en C++ para leer las teclas pulsadas para poder mientras se reproducir y se muestra
                 por pantalla el estado de la reproducción, permitiendo pausar, reanudar, rebobinar, cambiar de canción etc.

	FUNCIONAMIENTO
        El programa permite gestionar listas de reproduccion con tus canciones (Crearlas, Borrarlas, Modificarlas)
        para mas tarde reproducirlas.
        Generará un fichero con extensión '.lis' donde almacenará una lista con las canciones que el usuario insertó.

	REQUISITOS
	    * Mostrar listas de reproduccion existentes
        * Crear lista de reproducción
        * Exportar lista de reproducción como fichero
        * Leer lista de reproducción desde un fichero
        * Borrar lista de reproducción
        * Reproducir lista de reproducción
        * Insertar cancion en lista de reproducción
        * Eliminar cancion de lista de reproducción
        * Mostrar canciones de una lista de reproducción
        * Comprobar si un fichero es una lista
        * Comprobar si un fichero es un fichero de audio soportado por el reproductor
        * Mostrar canciones disponibles para insertar en una lista
        * Cambiar de cancion mientras reproduce
        * Rebobinar cancion mientras se reproduce
        * Pausar reproducción
        * Reanudar reproducción
        * Parar reproducción
        * Reproducir fichero de audio

	ENTRADAS
	    Canciones que quiere insertar a las diferentes listas

	SALIDAS
	    Muestra por pantalla el menu de interacción
        Muestra por pantalla el estado de la reproducción

	RESTRICCIONES

    PSEUDOCODIGO GENERALIZADO
    INICIO

    HACER
       HACER
           MOSTRAR MENU PRINCIPAL
           LEER OPCION MENU
       MIENTRAS OPCION MENU NO SEA VALIDA

       SI OPCION MENU DISTINTO SALIR
           SEGUN OPCION MENU
               CASO 1 : VER ALUMNOS EN EL SISTEMA
           FIN_SEGUN

           HACER
               PREGUNTAR SI DESEA VOLVER A EJECUTAR EL PROGRAMA
               LEER RESPUESTA USUARIO
           MIENTRAS RESPUESTA USUARIO NO SEA VALIDA
       FIN_SI
    MIENTRAS RESPUESTA USUARIO IGUAL SEGUIR Y OPCION MENU DISTINTO SALIR
 */
    public static void main(String[] args)
    {
        //Constante para salir del programa
        final char SALIR = '7';

        //Variables para la respuestas del usuario
        char respuestaUsuario = 'n';
        char opcionMenu = ' ';

        Scanner scanner = new Scanner(System.in);

        GestionListaReproduccion gestionListaReproduccion = new GestionListaReproduccion();

        do
        {
            //MOSTRAR MENU PRINCIPAL
            do
            {
                mostrarMenuPrincipal();

                //LEER Y VALIDAR OPCION MENU
                System.out.print("\nIntroduce la opcion que desea realizar: ");
                opcionMenu = Character.toLowerCase(scanner.next().charAt(0));

            } while ( opcionMenu < '1' || opcionMenu > SALIR );

            //Si la opcion elegida es distinto de salir
            if ( opcionMenu != SALIR )
            {
                //Segun la opcion elegida
                switch (opcionMenu)
                {
                    //Mostrar listas de reproduccion existentes
                    case '1' : gestionListaReproduccion.mostrarListasReproduccionExistentes("."); break;

                    //Crear lista de reproducción
                    case '2' :
                        if (gestionListaReproduccion.crearListaReproduccion())
                        {
                            System.out.println("Se ha creado la lista correctamente");
                        }
                    break;

                    //Insertar cancion en lista de reproducción
                    case '3' :
                        if (gestionListaReproduccion.borrarListaReproduccion())
                        {
                            System.out.println("Se ha borrado la lista correctamente");
                        }
                    break;

                    case '4' :
                        if (gestionListaReproduccion.agregarCancionesListaReproduccion())
                        {
                            System.out.println("Se ha agregado la cancion correctamente");
                        }

                        break;
                }

                //Preguntamos si quiere volver a ejecutar el programa
                do
                {
                    System.out.print("\n¿Desea volver al menu principal(s/n)?: ");
                    respuestaUsuario  = Character.toLowerCase(scanner.next().charAt(0));
                } while ( respuestaUsuario != 's' && respuestaUsuario != 'n' );
            }
        } while ( respuestaUsuario == 's' && opcionMenu != SALIR );
    }

    private static void mostrarMenuPrincipal()
    {
        System.out.println("\t -:||Reproductor de Audio 3000 ||:- \n");
        System.out.println("1. Mostrar listas de reproduccion existentes");
        System.out.println("2. Crear lista de reproducción");
        System.out.println("3. Borrar lista de reproducción");
        System.out.println("4. Insertar cancion en lista de reproducción");
        System.out.println("5. Eliminar cancion de lista de reproducción");
        System.out.println("6. Reproducir lista de reproducción");
        System.out.println("7. Salir");
    }
}
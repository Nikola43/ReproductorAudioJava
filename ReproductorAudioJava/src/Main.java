import java.util.Scanner;

 public class Main
 {
     public static void main(String[] args)
     {
         final int SALIR = 8;
         char respuestaUsuario = ' ';
         int opcionMenu;
         Scanner scanner = new Scanner(System.in);
         UtilReproductor reproductor = new UtilReproductor();

         do
         {
             do
             {
                 mostrarMenuReproductor();
                 System.out.print("Seleccione accion desea realizar: ");
                 opcionMenu = scanner.nextInt();
             } while( opcionMenu < 0 || opcionMenu > 7);

             if ( opcionMenu != SALIR ) {
                 switch (opcionMenu) {
                     case 1:
                         reproductor.crearListaReproduccion();
                         break;
                     case 2:
                         reproductor.mostrarListasDisponibles("listas\\");
                         break;
                     case 3:
                         reproductor.reproducirListasExistentes();
                         break;
                     case 4:
                         break;
                     case 5:
                         reproductor.eliminarCancionListaReproduccion("paulo.lis");
                         break;
                     case 6:
                         reproductor.eliminarListasExistentes();
                         break;
                     case 7:
                         reproductor.reproducirCancionUnica();
                         break;
                     default:
                         System.out.println("Error: No ha seleccionado una opcion valida.");
                         break;
                 }
             }

             if ( opcionMenu != SALIR )
             {
                 do
                 {
                     System.out.print("\nDesea ejecutar de nuevo el reproductor(S/n): ");
                     respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
                 } while( respuestaUsuario != 's' && respuestaUsuario != 'n');
             }
         } while (respuestaUsuario == 's' || opcionMenu != SALIR);
     }

     public static void mostrarMenuReproductor()
     {
         System.out.println("\n\t\t PauloSoft Media Player\n");
         System.out.println("\t 1. Crear nueva lista de reproduccion");
         System.out.println("\t 2. Mostrar listas de reproduccion existentes");
         System.out.println("\t 3. Reproducir lista de reproduccion existente");
         System.out.println("\t 4. Agregar canciones a lista de reproduccion");
         System.out.println("\t 5. Eliminar canciones de lista reproduccion ");
         System.out.println("\t 6. Eliminar lista de reproduccion existente");
         System.out.println("\t 7. Reproducir cancion unica");
         System.out.println("\t 8. Salir\n");
     }
     }

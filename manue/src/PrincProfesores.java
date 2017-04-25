import java.io.*;
import java.util.*;

public class PrincProfesores {
	
	public static void menu(){
		System.out.println("------------------------------------------------------");
		System.out.println("- Administrador de Profesores. Que desea hacer? -");
		System.out.println("------------------------------------------------------");
		System.out.println("1 --> Mostrar Profesores del Centro");
		System.out.println("2 --> Edad Media de Profesores"); //Incluida Extra
		System.out.println("3 --> Profesor mas mayor");
		System.out.println("4 --> Profesor mas joven");
		System.out.println("5 --> Profesores (numero) con edad mayor a la media"); //Cambio numero por lista de profesores
		System.out.println("6 --> Profesores (numero) con edad menor a la media"); //Cambio numero por lista de profesores
		System.out.println("0 --> Salir");
	}
	
	public static void main(String[] args){
		//Atributos
				char seguir;
				int opcion;
				double mediaEdad;
				Profesor[] profesores;
				
				GestoraProfesor gestor=new GestoraProfesor();
				Scanner teclado=new Scanner(System.in);
				
				//BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				
				//LeerYValidarEjecutar
				do{
					System.out.println("Desea ejecutar el programa? S/N");
					seguir=Character.toUpperCase(teclado.next().charAt(0));
				}while(seguir!='S' && seguir!='N');
				
				//MientrasQuieraEjecutar
				while(seguir=='S'){
					
					//Generamos el fichero con los Profesores (Esto es temporal)
					gestor.GenerarFicheroProfesores();
					
					//MostrarLeerYValidarMenu
					do{
						menu();
						opcion=teclado.nextInt();
					}while(opcion<0 || opcion>6);
					
					//MientrasOpcionNoEsSalir
					if(opcion!=0){
						
						//Menu
						switch(opcion){
							case 1:
								gestor.ConocerProfesores();
							break;
							
							case 2:
								mediaEdad=gestor.MediaEdadProfesores();	
								System.out.println("La edad media del profesorado es: "+mediaEdad);
							break;
							
							case 3:
								gestor.ProfesorJoven();
							break;
								
							case 4:
								gestor.ProfesorMayor();
							break;
								
							case 5:
							break;
								
							case 6:
							break;
						}

						
					}//mientras_siNoEsSalir
					
					//LeerYvalidarReiniciar
					do{
						System.out.println("Desea reiniciar el programa? S/N");
						seguir=Character.toUpperCase(teclado.next().charAt(0));
					}while(seguir!='S' && seguir!='N');
				}//fin_mientras
	}
}

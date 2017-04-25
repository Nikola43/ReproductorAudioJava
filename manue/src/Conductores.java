public class Conductores {
	public static void main(String[] args){
		
		GestoraProfesor gestor=new GestoraProfesor();
		
		//Generar Fichero
		System.out.println("Se genera un fichero binario llamando \"profesores\".");
		gestor.GenerarFicheroProfesores();
		
		System.out.println();
		//Comprobar Fichero
		System.out.println("Comprobamos si el fichero con los profesores existe.");
		gestor.ComprobarFichero("profesores");
		
		//Mostrar Profesores
		System.out.println("Mostramos los profesores que tenemos almacenados en el fichero.");
		gestor.ConocerProfesores();
		
		//Media de Edad
		System.out.println("Edad media de los profesores:");
		double mediaEdad=gestor.MediaEdadProfesores();	
		System.out.println("La edad media del profesorado es: "+mediaEdad);
		
		//Profesor Joven y Mayor
		System.out.println("Profesores m�s jovenes y mayores:");
		gestor.ProfesorJoven();
		gestor.ProfesorMayor();
	}
}

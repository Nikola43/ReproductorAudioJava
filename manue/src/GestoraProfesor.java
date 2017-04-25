import java.io.*;
import java.util.*;

public class GestoraProfesor {
	
	/*
	 * Este m�todo solo pone unos profesores por defecto dentro del fichero para poder 
	 * usar el programa.
	 */
	public void GenerarFicheroProfesores(){
		
		try{
			Profesor[] profesores={new Profesor("Mani", 23, 'M'), new Profesor("Xexu", 33, 'M'), new Profesor("Fali", 42, 'M'),
								   new Profesor("Yesi", 27, 'F'), new Profesor("Ester", 20, 'F'), new Profesor("Yeni", 58, 'F'),
								   new Profesor("Xio", 65, 'F'), new Profesor("Sole", 75, 'F'), new Profesor("Xarli", 29, 'M'),
								   new Profesor("Montesquie", 68, 'M'), new Profesor("Curie", 79, 'F'), new Profesor("Einstein", 22, 'M')};
			
			
			//Metemos los objetos en el fichero
			try{
				File f=new File("profesores.dat");
				FileOutputStream fos=new FileOutputStream(f,true);
				ObjectOutputStream oos=new ObjectOutputStream(fos){@Override protected void writeStreamHeader(){}};
				for(int i=0; i<profesores.length;i++){
					oos.writeObject(profesores[i]);
				}
				oos.close(); //Cerramos
			}catch(IOException e){
				System.out.println(e);
			}//Fin meter objetos
			
		}catch (ExcepcionProfesor error){
			System.out.println(error);
		}
		
	}
	
	
	/*
	 * Este m�todo solo pone unos profesores por defecto dentro del fichero para poder 
	 * usar el programa.
	 */
	public void ComprobarFichero(String fichero){
		File f=new File(fichero);
		
		if(f.exists()){
			System.out.println("El fichero existe.");
		}else{
			System.out.println("El fichero indicado no existe o ruta mal introducida.");
		}
	}
	
	
	/*//INTERFAZ ConocerProfesores
	 * Cabecera: public void ConocerProfesores() throws ClassNotFoundException
	 * Comentario: Muestra los profesores que tenemos almacenados
	 * Prec: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Post: Pinta en pantalla una lista de profesores
	 */
	public void ConocerProfesores(){		
		int nprofesores=0;
		Profesor[] profesores=null;
		Profesor p=null;
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			f=new File("profesores.dat");
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};;
			
			p=(Profesor) ois.readObject(); //Lectura anticipada
			while(p!=null){ 
				 System.out.println(p.toString());
				 p=(Profesor) ois.readObject();
			}
			
		}catch(ClassNotFoundException e){
        //}catch(EOFException e){ //Aun sigue saltando esta excepcion (probar comentando este catch)
        }catch(IOException e){
        	System.out.println(e);
        }finally{
        	try{
    			ois.close();
    		}catch(IOException e){
    			System.out.println(e);
    		}
        }
	}
	/*public void ConocerProfesores(){		
		int nprofesores=0;
		Profesor[] profesores=null;
		Profesor p=null;
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			f=new File("profesores");
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis);
			
			//Contamos la cantidad de profesores que tenemos almacenados.
			while(ois.readObject()!=null){ 
				//Profesor p=(Profesor) ois.readObject();
				//System.out.println(p.toString());
				nprofesores++;   
			}                                         //Cuando acaba de contar los profesores, salta a la excepcion EOFException, y acaba el metodo
													  //Por lo que no llega a rellenar el array para luego poder manejarlo.
			
			profesores=new Profesor[nprofesores];
			
			p=(Profesor) ois.readObject();
			
			//Metemos los profesores en el Array
			while(p!=null){ 
				p=(Profesor) ois.readObject();
				for(int i=0; i<profesores.length;i++){
					Profesor profe=(Profesor) ois.readObject();
					profesores[i]=profe;
				}
			}
			
		}catch(ClassNotFoundException e){
        }catch(EOFException e){
        }catch(IOException e){
        	System.out.println(e);
        }finally{
        	try{
    			ois.close();
    		}catch(IOException e){
    			System.out.println(e);
    		}
        }
		
		//Los pinto y los muestro aqu� ya que no me deja devolver el array al estar declarado e inicializado dentro del try catch. REVISAR
		for(int i=0;i<profesores.length;i++){
			System.out.println(profesores[i].toString());
		}
	}*/
	
	
	/*//INTERFAZ MediaEdadProfesores
	 * Cabecera: public void MediaEdadProfesores() throws ClassNotFoundException
	 * Comentario: Calcula la edad media de todos los profesores
	 * Prec: Nada
	 * Entrada: Nada
	 * Salida: double
	 * Post: Devuelve asociado al nombre la edad media del grupo de profesores.
	 */	
	public double MediaEdadProfesores(){
		double mediaEdad=0;
		int nprofesores=0;
		Profesor p = null;
		//Object o =null;
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		//MyObjectInputStream mois=null;
		
		try{
			f=new File("profesores.dat");
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};
			p = (Profesor) ois.readObject();
			//System.out.println(o.toString()); //Prueba para ver si el objeto leido es correcto
			
			while(p != null) { //Contamos la cantidad de profesores que tenemos almacenados.
				//System.out.println(o.toString()); //Prueba para ver si el objeto leido es correcto
				//p=(Profesor) o;
				mediaEdad=mediaEdad+p.getEdad();
				nprofesores++;
				p = (Profesor) ois.readObject();
				//System.out.println(o.toString()); //Prueba para ver si el objeto leido es correcto
			}
				
		}catch(ClassNotFoundException e){
        //}catch(EOFException e){ //Aun sigue saltando esta excepcion (probar comentando este catch)
        }catch(IOException e){
        	System.out.println(e);
        }finally{
        	try{
    			ois.close();
    		}catch(IOException e){
    			System.out.println(e);
    		}
        }
		
		//Calculamos la Media
		mediaEdad=mediaEdad/nprofesores;
		mediaEdad=Math.round((mediaEdad * 1000) / 1000);
		//System.out.println("La edad media del profesorado es: "+mediaEdad);
		
		return mediaEdad;
	}
	
	
	/*//INTERFAZ ProfesorJoven
	 * Cabecera: public void ProfesorJoven()
	 * Comentario: Dice cual es el profesor m�s joven de todos
	 * Prec: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Post: Muestra en pantalla el nombre y la edad del profesor m�s joven del grupo
	 */	
	public void ProfesorJoven(){
		Profesor p=null;
		Profesor pjoven=null;
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			f=new File("profesores.dat");
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};
			
			p=(Profesor) ois.readObject();
			pjoven=p;
			while(p!=null){
				if(pjoven.getEdad()>p.getEdad()){
					pjoven=p;
				}
				p=(Profesor) ois.readObject();
			}
			
		}catch(ClassNotFoundException e){
		//}catch(EOFException e){ //Aun sigue saltando esta excepcion (probar comentando este catch)
		}catch(IOException e){
			System.out.println(e);
		}finally{
			try{
			ois.close();
			}catch(IOException e){
				System.out.println(e);
			}
		}
		
		System.out.println("El profesor mas joven es "+pjoven.getNombre()+" con "+pjoven.getEdad()+" anios.");
		
	}
	
	
	/*//INTERFAZ ProfesorMayor
	 * Cabecera: public void ProfesorMayor()
	 * Comentario: Dice cual es el profesor m�s viejo de todos
	 * Prec: Nada
	 * Entrada: Nada
	 * Salida: Nada
	 * Post: Muestra en pantalla el nombre y la edad del profesor m�s viejo del grupo
	 */	
	public void ProfesorMayor(){
		Profesor p=null;
		Profesor pmayor=null;
		
		File f=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		
		try{
			f=new File("profesores.dat");
			fis=new FileInputStream(f);
			ois=new ObjectInputStream(fis){@Override protected void readStreamHeader(){}};
			
			p=(Profesor) ois.readObject();
			pmayor=p;
			while(p!=null){
				if(pmayor.getEdad()<p.getEdad()){
					pmayor=p;
				}
				p=(Profesor) ois.readObject();
			}
			
		}catch(ClassNotFoundException e){
		//}catch(EOFException e){ //Aun sigue saltando esta excepcion (probar comentando este catch)
		}catch(IOException e){
			System.out.println(e);
		}finally{
			try{
			ois.close();
			}catch(IOException e){
				System.out.println(e);
			}
		}
		
		System.out.println("El profesor mas viejo es "+pmayor.getNombre()+" con "+pmayor.getEdad()+" anios.");
	}
	
}

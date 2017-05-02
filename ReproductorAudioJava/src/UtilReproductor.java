import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.util.Scanner;

public class UtilReproductor 
{
	/*INTERFAZ	
	 * Cabecera: mostrarListasDisponibles(String directorio)
	 * Descripcion: Muestra por pantalla todos los ficheros que son listas de reproduccion
	 * Precondiciones: El directorio debe existir ( ruta valida )
	 * Entradas: Ruta del directorio en el que queremos buscar canciones
	 * Salidas: Muestra por pantalla de las canciones que hay en el directorio seleccionado
	 * Postcondiciones: Ninguna
	*/
	public void mostrarListasDisponibles(String directorio)
	{
		try
		{
			
			//Creamos el fichero
			File miFichero = new File(directorio);
			File[] ficheros = null;
			
			//Comprobamos si el fichero existe
			if (miFichero.exists())
			{ 
				//Creamos array de ficheros para recorrer los ficheros del directorio
			    ficheros = miFichero.listFiles();
				
				for (int i = 0; i < ficheros.length; i++)
				{
					//Comprobamos el tipo de fichero
					if(ficheros[i].isFile() == true)
					{
						//Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
						if ( esListaReproduccion(ficheros[i].getName()) == true)
						{
							System.out.println("\t"+ficheros[i].getName());
						}
					}
				}
			}
			else
				System.out.println("La carpeta no existe");
			
		}
		catch (Exception e)
		{
			System.out.println("Error");
		}
	}
	
	/*INTERFAZ	
	 * Cabecera: mostrarCancionesDisponibles()
	 * Descripcion: Muestra por pantalla todos los ficheros que son canciones
	 * Precondiciones: Ninguna
	 * Entradas: Ninguna
	 * Salidas: Lista por pantalla de las listas que hay
	 * Postcondiciones: Ninguna
	*/
	public void mostrarCancionesDisponibles(String directorio)
	{
		try
		{			
			//Creamos el fichero
			File miFichero = new File(directorio);
			String extensionLista = ".";
			
			//Comprobamos si el fichero existe
			if (miFichero.exists())
			{ 
				//Creamos array de ficheros para recorrer los ficheros del directorio
				File[] ficheros = miFichero.listFiles();
				
				for (int i = 0; i < ficheros.length; i++)
				{
					//Comprobamos el tipo de fichero
					if(ficheros[i].isFile() == true)
					{
						extensionLista = "."+ficheros[i].getName().charAt(ficheros[i].getName().length() - 3)+ficheros[i].getName().charAt(ficheros[i].getName().length() - 2)+ficheros[i].getName().charAt(ficheros[i].getName().length() - 1);
						if (extensionLista.compareTo(".mp3") == 0 || extensionLista.compareTo(".wav") == 0 || extensionLista.compareTo(".ogg") == 0 )
						{
							System.out.println("\t"+ficheros[i].getName());
						}
					}
				}
			}
			else
				System.out.println("La carpeta no existe");
			
		}
		catch (Exception e)
		{
			System.out.println("Error");
		}
	}
	
	/*INTERFAZ	
	 * Cabecera: public boolean comprobarSiHayCancionesEnDirectorio(String directorio)
	 * Descripcion: Comprueba si hay ficheros de audio en el directorio seleccionado
	 * Precondiciones: 
	 * Entradas: Ruta del directorio donde queremos comprobar si hay ficheros de audio
	 * Salidas: Booleano asociado al nombre
	 * Postcondiciones: El booleano ser� verdadero cuando encuentre algun fichero de audio 
	 * 					en el directorio y falso cuando no encuentre ninguno
	*/
	public boolean comprobarSiHayCancionesEnDirectorio(String directorio)
	{
		boolean hayCanciones = false;
		File miDirectorio = new File(directorio);
		String extensionFicheros = ".";
		File[] ficherosDentroDelDirectorio;
		
		try
		{			
			//Comprobamos si el directorio pasado por parametro existe
			if (miDirectorio.exists())
			{ 
				//asignamos al array los ficheros del directorio
				ficherosDentroDelDirectorio = miDirectorio.listFiles();
				
				for (int contadorDeFicheros = 0; contadorDeFicheros < ficherosDentroDelDirectorio.length; contadorDeFicheros++)
				{
					//Comprobamos el tipo de fichero
					if(ficherosDentroDelDirectorio[contadorDeFicheros].isFile() == true)
					{
						extensionFicheros = "."+ficherosDentroDelDirectorio[contadorDeFicheros].getName().charAt(ficherosDentroDelDirectorio[contadorDeFicheros].getName().length() - 3)+ficherosDentroDelDirectorio[contadorDeFicheros].getName().charAt(ficherosDentroDelDirectorio[contadorDeFicheros].getName().length() - 2)+ficherosDentroDelDirectorio[contadorDeFicheros].getName().charAt(ficherosDentroDelDirectorio[contadorDeFicheros].getName().length() - 1);
						if (extensionFicheros.compareTo(".mp3") == 0 || extensionFicheros.compareTo(".wav") == 0 || extensionFicheros.compareTo(".ogg") == 0 )
						{
							hayCanciones = true;
						}
					}
					else
					{
						System.out.println("No hay ningun fichero en el directorio seleccionado");
					}
				}
			}
			else
			{
				System.out.println("Error: El directorio seleccionado no existe");
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: No se pudo listar el directorio");
		}
		return hayCanciones;
	}

	/*INTERFAZ	
	 * Cabecera:        reproducirListasExistentes()
	 * Descripcion:     Muestra las listas de reproduccion existentes y permite al usuario reproducirlas
	 * Precondiciones:  La lista debe tener canciones
	 * Entradas:        Ninguna
	 * Salidas:		    Reproduce las canciones por los altavoces
	 * Postcondiciones: Ninguna
	*/
	public void reproducirListasExistentes()
	{
		Scanner scanner = new Scanner(System.in);
		
		char respuestaUsuario = ' ';
		String nombreListaReproduccion = seleccionarListaReproduccion();

		do
		{	
			imprimirListaReproduccion(nombreListaReproduccion);
			System.out.print("Desea reproducir esta lista?(S/n): ");
			respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
		} while( respuestaUsuario != 's' && respuestaUsuario != 'n');
				
		if ( respuestaUsuario == 's' )
		{
					reproducirListaReproduccion(nombreListaReproduccion);
		}
	}
	
	/*INTERFAZ	
	 * Cabecera:       
	 * Descripcion:     
	 * Precondiciones: 
	 * Entradas:        
	 * Salidas:		    
	 * Postcondiciones: 
	*/
	public void reproducirCancionUnica()
	{
		File ficheroCancion = null;
		ReproductorImpl reproductor = new ReproductorImpl();
		CancionImpl cancion = null;
		
		
		ficheroCancion = new File(seleccionarCancion());
		try 
		{
			cancion = new CancionImpl(ficheroCancion.toURI().toURL().toString(), ficheroCancion.getName(), 1);
			reproductor.reproducir(cancion);
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}		
		
	}
	
	/*INTERFAZ	
	 * Cabecera:        reproducirListaReproduccion(String nombreListaReproduccion)
	 * Descripcion:     Permite al usuario reproducir una lista de reproduccion existente
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Nombre de la lista que quieres reproducir
	 * Salidas:		    Reproduce las canciones por los altavoces
	 * Postcondiciones: Ninguna
	*/
	public void reproducirListaReproduccion(String nombreListaReproduccion)
	{
		//Variables para leer la lista
		File listaReproduccion = new File(nombreListaReproduccion);
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		//Variable auxiliar para reproducir la cancion actual
		CancionImpl cancionAuxiliar;

		//Instanciamos un nuevo reproductor
		ReproductorImpl reproductor = new ReproductorImpl();
		try 
		{	//Si la lista existe empezamos a reproducirla
			if( listaReproduccion.exists() == true )
			{
				//Instanciamos los inputs para leer la lista
				fileInputStream =  new FileInputStream(listaReproduccion);
				objectInputStream = new ObjectInputStream(fileInputStream);
				
				//Reproducimos hasta el final de la lista
				while((cancionAuxiliar = (CancionImpl) objectInputStream.readObject()) != null)
				{
					if ( cancionAuxiliar.getNumeroEnLista() != -1 )
					{
						reproductor.reproducir(cancionAuxiliar);	
					}
				}
			}
			else
			{
				System.out.println("Error: La lista "+listaReproduccion.getName()+" no existe");
			}
			
			//Escribimos en el fichero			
			System.out.println("Fin");
			
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (EOFException e)
		{
			System.out.println("Fin de la lista. EOF");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		finally
		{
			try
			{	//Cerramos streams
				if( fileInputStream != null)
					fileInputStream.close();
				
				if( objectInputStream != null)
					objectInputStream.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	

	/*INTERFAZ	
	 * Cabecera:        crearListaReproduccion(String nombreListaReproduccion)  
	 * Descripcion:     Permite al usuario crear una lista de reproduccion con sus canciones favoritas
	 * Precondiciones:  Las canciones deben existir
	 * Entradas:        Nombre de la lista que quieres crear
	 * Salidas:		    Genera un fichero con una lista de canciones
	 * Postcondiciones: Ninguna
	*/
	public void crearListaReproduccion()
	{

		char respuestaUsuario = ' ';
		String nombreCancion = " ";
		String nombreListaReproduccion = " ";
		int numeroCancion = 1;
		
		//Variables para manejar las canciones
		File archivoCancion = null;
		CancionImpl cancionAuxiliar = null;
		File listaReproduccion = null;
		Scanner scanner = new Scanner(System.in);
		
		try 
		{
			do
			{
				System.out.print("\nIntroduzca el nombre de la lista que quiere crear: ");
				nombreListaReproduccion = scanner.nextLine();
				nombreListaReproduccion = nombreListaReproduccion+".lis";
				
				//Asociamos lista de reproduccion a un tipo File
				listaReproduccion = new File(nombreListaReproduccion);
				
				if ( listaReproduccion.exists() == true )
				{
					System.out.println("Error: Ya existe una lista de reproduccion con ese nombre");
				}
			} while( nombreListaReproduccion == " " || listaReproduccion.exists() == true);
			
			//Preguntamos si desea agregar canciones
			do
			{
				System.out.print("Desea agregar una nueva cancion a la lista(S/n): ");
				respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
			} while( respuestaUsuario != 's' && respuestaUsuario != 'n');
			scanner.nextLine();
			 
			//Si su respuesta es que si
			if ( respuestaUsuario == 's')
			{
				do
				{
					nombreCancion = seleccionarCancion();
					archivoCancion = new File(nombreCancion);
				
					//Si la cancion existe
					if (archivoCancion.exists() == true)
					{
						//Instanciamos un objeto tipo cancion, con la ruta y el nombre de la cancion
						cancionAuxiliar = new CancionImpl(archivoCancion.toURI().toURL().toString(), archivoCancion.getName(), numeroCancion++);
						agregarCancionListaReproduccion(cancionAuxiliar, nombreListaReproduccion);	
					}
					else
					{
						System.out.println("La cancion "+cancionAuxiliar.getNombre()+" no existe");
					}			
						
					//Preguntamos al usuario si desea continuar agregando canciones
					do 
					{
						System.out.print("\nDesea introducir mas canciones a la lista?: (S/n): ");
						respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
						scanner.nextLine();
					} while( respuestaUsuario != 's' && respuestaUsuario != 'n');
				} while ( respuestaUsuario == 's' ); // Repetimos el proceso mientras que quiera seguir a�adiendo
					
				//Si la lista existe, se muestra un mensaje que confirma que se a creado correctamente	
				if ( listaReproduccion.exists() == true)
				{
					System.out.println("\nSe ha creado una nueva lista de reproduccion");
				}
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*INTERFAZ	
	 * Cabecera:        reproducirListaReproduccion(String nombreListaReproduccion)
	 * Descripcion:     Permite al usuario reproducir una lista de reproduccion existente
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Nombre de la lista que quieres reproducir
	 * Salidas:		    Reproduce las canciones por los altavoces
	 * Postcondiciones: Ninguna
	*/
	public void eliminarListasExistentes()
	{
		Scanner scanner = new Scanner(System.in);
		
		char respuestaUsuario = ' ';
		String nombreListaReproduccion = seleccionarListaReproduccion();

		if ( nombreListaReproduccion.equals(' ') == false )
		{
			do
			{	
				imprimirListaReproduccion(nombreListaReproduccion);
				System.out.print("Desea eliminar esta lista?(S/n): ");
				respuestaUsuario = Character.toLowerCase(scanner.next().charAt(0));
			} while( respuestaUsuario != 's' && respuestaUsuario != 'n');
					
			if ( respuestaUsuario == 's' )
			{
				eliminarListaReproduccion(nombreListaReproduccion);
			}
		}
	}
	
	/*INTERFAZ	
	 * Cabecera:        eliminarListaReproduccion(String nombreListaReproduccion)
	 * Descripcion:     Permite al usuario borrar una lista de reproduccion existente
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Nombre de la lista que quieres borrar
	 * Salidas:		    Borra el fichero del disco
	 * Postcondiciones: Ninguna
	*/
	public void eliminarListaReproduccion(String nombreListaReproduccion)
	{
		//Instanciamos un objeto File para borrar la lista
		File listaReproduccion = new File(nombreListaReproduccion);

		//Si la lista existe, la borramos
		if (listaReproduccion.exists() == true)
		{
			if (listaReproduccion.delete())
		    	System.out.println("Lista de reproduccion "+listaReproduccion.getName()+" se elimino correctamente");
			else
		    	System.out.println("No se ha podido borrar la lista de reproduccion");
		}
		else
			System.out.println("Error: Debe introducir una lista existente");
	}
	
	/*INTERFAZ	
	 * Cabecera:        elimin arCancionListaReproduccion(CancionImpl cancion, String nombreListaReproduccion)
	 * Descripcion:     Borra una cancion de una lista
	 * Precondiciones:  La lista debe existir, y la cancion debe estar dentro de la lista
	 * Entradas:        Objeto cancion y nombre de la lista a la que quieres agregar la cancion
	 * Salidas:		    Ninguna
	 * Postcondiciones: Ninguna
	*/
	public void eliminarCancionListaReproduccion(String nombreListaReproduccion)
	{
		int contadorCanciones = 0;
		
		FileOutputStream fileOutputStreamAuxiliar = null;
		ObjectOutputStream objectOutputStreamAuxiliar = null;
		
		//para leer fichero
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		
		
		CancionImpl cancionAuxiliar = null;
		
		File listaReproduccion = new File(nombreListaReproduccion);
		File listaReproduccionActualizada = new File("listaActualizada.lis");
		
		CancionImpl cancionImpl = null;
		
	
			imprimirListaReproduccion(nombreListaReproduccion);
			String cancionNombre = seleccionarCancion();
			
			File cancion = new File(cancionNombre);
			 cancionAuxiliar = new CancionImpl();
			 
			 try 
			 {
				cancionImpl = new CancionImpl(cancion.toURI().toURL().toString(), cancion.getName(), 1);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}



		
		try 
		{
			if( listaReproduccion.exists() == false)
			{
				System.out.println("El fichero no existe");
			}
			else
			{
				//Objetos para leer la lista
				fileInputStream =  new FileInputStream(listaReproduccion);
				objectInputStream = new ObjectInputStream(fileInputStream);
				
				//Para escribir
				fileOutputStreamAuxiliar = new FileOutputStream(listaReproduccionActualizada);
				objectOutputStreamAuxiliar = new ObjectOutputStream(fileOutputStreamAuxiliar);
				
				while((cancionAuxiliar = (CancionImpl) objectInputStream.readObject()) != null)
				{
					System.out.println(cancionAuxiliar.getRuta());
					System.out.println(cancionImpl.getRuta());
					
					if (cancionAuxiliar.getRuta().compareTo(cancionImpl.getRuta()) != 0)
					{	
						contadorCanciones++;
						cancionAuxiliar.setNumeroEnLista(contadorCanciones);
						objectOutputStreamAuxiliar.writeObject(cancionAuxiliar);
					}
					else
					{
						System.out.println("iguales");
					}
				}
			}
			
			//Escribimos en el fichero			
			System.out.println("Fin");
			
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		} 
		catch (EOFException e)
		{
			System.out.println("Fin lectura fichero. EOF");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{

			try
			{	//Cerramos streams
				if( fileInputStream != null)
					fileInputStream.close();
				
				if( objectInputStream != null)
					objectInputStream.close();
				
				renombrarFichero(nombreListaReproduccion, "listaActualizada.lis");
				
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	/* renombrarFichero
	 * 
	 * Cabecera: void renombrarFichero(String fichero1, String fichero2)
	 * Comentario: Dado un fichero se renombrará el segundo con el nombre del primero
	 * Precondición: nada
	 * Entrada: 2 cadenas que representan 2 ficheros
	 * Salida: Un fichero
	 * Postcondición: Fichero2 tendrá el nombre de fichero1 
	 * 
	 */
	public void renombrarFichero(String origen, String destino) 
	{
		File ficheroOrigen = new File(origen);
		File ficheroDestino = new File(destino);

		if ( ficheroOrigen.delete() )
		{
			System.out.println("Fichero borrado '"+ficheroOrigen.getName()+"'");
		}
		
		if ( ficheroDestino.renameTo(ficheroOrigen) )
		{
			System.out.println("El fichero '"+ficheroDestino.getName()+"'");
			System.out.println("Se ha renombrado a '"+ficheroOrigen.getName()+"'");
		}
		
	}
	
	/*INTERFAZ	
	 * Cabecera:        agregarCancionListaReproduccion(CancionImpl cancion, String nombreListaReproduccion)  
	 * Descripcion:     Agrega guarda un objeto tipo CancionImpl dentro de un fichero binario
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Objeto cancion y nombre de la lista que lo quieres agregar
	 * Salidas:		    Ninguna
	 * Postcondiciones: Ninguna
	*/
	public void agregarCancionListaReproduccion(CancionImpl cancion, String nombreListaReproduccion)
	{
		//Creamos objetos para escribir fichero binario
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		//Asociamos el nombre a un objeto tipo File para comprobar su existencia
		File listaReproduccion = new File(nombreListaReproduccion);

		//Si la lista de reproduccion existe, insertamos una nueva cancion

		try 
		{
			//Si la lista esta vacia
			if (listaReproduccion.getTotalSpace() == 0)
			{
				fileOutputStream = new FileOutputStream(listaReproduccion);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
			}
			else
			{
				fileOutputStream = new FileOutputStream(listaReproduccion, true);
				objectOutputStream = new MiObjectOutputStream(fileOutputStream);
			}
			
			
			
			//Insertamos una nueva cancion en la lista
			objectOutputStream.writeObject(cancion);
			System.out.println("La cancion "+cancion.getNombre()+" fue agregada la lista "+listaReproduccion.getName());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	

	
	
	public String seleccionarListaReproduccion()
	{
		Scanner scanner = new Scanner(System.in);
		String nombreListaReproduccion = " ";
		
		File listaReproduccion = null;
		
		do
		{
		    System.out.println("\n| Listas de reproduccion |");
			mostrarListasDisponibles(System.getProperty("user.dir"));
			System.out.print("\nIntroduzca el nombre de la lista que desea seleccionar: ");
			nombreListaReproduccion = scanner.nextLine();
			listaReproduccion = new File(nombreListaReproduccion);
			if (listaReproduccion.exists() == false)
			{
					System.out.print("Error: El archivo introducido no existe\n");
			}
		} while ( listaReproduccion.exists() == false );
		return nombreListaReproduccion;
	}
	
	public String seleccionarCancion()
	{
		Scanner scanner = new Scanner(System.in);
		String nombreCancion = " ";
		
		File cancion = null;
		
		do 
		{
			System.out.println("\nEstas son las canciones disponibles: ");
			mostrarCancionesDisponibles("canciones\\");
			System.out.print("\nIntroduzca el nombre de la cancion que desea seleccionar: ");
			nombreCancion = scanner.nextLine();
			cancion = new File(nombreCancion);
			
			if (cancion.exists() == false)
			{
				System.out.print("Error: El fichero introducido no existe");
			}
			else
			{
				if (esCancion(nombreCancion) == false)
				{
					System.out.print("Error: El fichero introducido no es un fichero de audio");
				}
			}
		} while (cancion.exists() == false || esCancion(nombreCancion) == false);
		return nombreCancion;
	}
	
	public boolean esCancion(String nombreFichero)
	{
		boolean soyCancion = false;
		String extensionFichero = " ";
		String extensionCancion = ".mp3";
		File fichero = new File(nombreFichero);
		
		
		//Comprobamos si el fichero existe
		if (fichero.exists())
		{ 
			//Comprobamos el tipo de fichero
			if(fichero.isFile() == true)
			{
				//Guardamos la extencion de los ficheros
				extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);
					
				//Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
				if (extensionFichero.compareTo(extensionCancion) == 0)
				{
					soyCancion = true;
				}
			}
		}
		
		return soyCancion;
	}
	
	public boolean esListaReproduccion(String nombreListaReproduccion)
	{
		boolean soyListaReproduccion = false;
		String extensionFichero = " ";
		String extensionListaReproduccion = ".lis";
		File fichero = new File(nombreListaReproduccion);
		
		
		//Comprobamos si el fichero existe
		if (fichero.exists())
		{ 
			//Comprobamos el tipo de fichero
			if(fichero.isFile() == true)
			{
				//Guardamos la extencion de los ficheros
				extensionFichero = "."+fichero.getName().charAt(fichero.getName().length() - 3)+fichero.getName().charAt(fichero.getName().length() - 2)+fichero.getName().charAt(fichero.getName().length() - 1);
					
				//Si la extension es '.lis' entonces ese fichero es una lista de reproduccion
				if (extensionFichero.compareTo(extensionListaReproduccion) == 0)
				{
					soyListaReproduccion = true;
				}
			}
		}
		
		return soyListaReproduccion;
	}
	

	/*INTERFAZ	
	 * Cabecera:        imprimirListaReproduccion(String nombreListaReproduccion)
	 * Descripcion:     Muestra al usuario las canciones de una lista de reproduccion
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Nombre de la lista que quieres mostrar
	 * Salidas:		    Muestra las canciones por pantalla
	 * Postcondiciones: Ninguna
	*/
	public void imprimirListaReproduccion(String nombreListaReproduccion)
	{
		//Variables para leer la lista
		File listaReproduccion = new File(nombreListaReproduccion);
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;

		//Variable auxiliar para ir mostrando cada cancion
		CancionImpl cancionAuxiliar;
	
		//Si la lista existe la mostramos por pantalla
		if( listaReproduccion.exists() == true )
		{
			System.out.println("La lista de reproduccion '"+listaReproduccion.getName()+"' contiene las siguientes canciones:");
			try 
			{	
				fileInputStream =  new FileInputStream(listaReproduccion);
				objectInputStream = new ObjectInputStream(fileInputStream);
				
				
				cancionAuxiliar = (CancionImpl) objectInputStream.readObject();
				
				
				while(cancionAuxiliar != null)
				{
					System.out.println(cancionAuxiliar.getNumeroEnLista()+". "+cancionAuxiliar.getNombre());
					cancionAuxiliar = (CancionImpl) objectInputStream.readObject();
				}
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch (EOFException e)
			{
				System.out.println("\nFin de la lista. EOF");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			finally
			{
				try
				{
					if( fileInputStream != null)
						fileInputStream.close();
					
					if( objectInputStream != null)
						objectInputStream.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("Error: La lista "+listaReproduccion.getName()+" no existe");
		}
	}

	/*INTERFAZ	
	 * Cabecera:        contarCancionesListaReproduccion(String nombreListaReproduccion)
	 * Descripcion:     Devuelve el numero total de canciones en una lista
	 * Precondiciones:  La lista debe existir
	 * Entradas:        Nombre de la lista que quieres contar
	 * Salidas:		    Numero de canciones como int
	 * Postcondiciones: Ninguna
	*/
	public int contarCancionesListaReproduccion(String nombreListaReproduccion)
	{
		//Variables para leer la lista
		File listaReproduccion = new File(nombreListaReproduccion);
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		int contadorCanciones = 0;

		//Variable auxiliar para ir mostrando cada cancion
		CancionImpl cancionAuxiliar;

		//Instanciamos un nuevo reproductor
		ReproductorImpl reproductor = new ReproductorImpl();
		
		//Si la lista existe la mostramos por pantalla
		if( listaReproduccion.exists() == true )
		{
			System.out.println("La lista de reproduccion '"+listaReproduccion.getName()+"' contiene las siguientes canciones:");
			try 
			{	
				fileInputStream =  new FileInputStream(listaReproduccion);
				objectInputStream = new ObjectInputStream(fileInputStream);
				
				
				cancionAuxiliar = (CancionImpl) objectInputStream.readObject();
				while(cancionAuxiliar != null)
				{
					contadorCanciones++;
					cancionAuxiliar = (CancionImpl) objectInputStream.readObject();
				}
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			catch (EOFException e)
			{
				System.out.println("\nFin de la lista. EOF");
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			finally
			{
				try
				{
					if( fileInputStream != null)
						fileInputStream.close();
					
					if( objectInputStream != null)
						objectInputStream.close();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			System.out.println("Error: La lista "+listaReproduccion.getName()+" no existe");
		}
		return contadorCanciones;
	}
}
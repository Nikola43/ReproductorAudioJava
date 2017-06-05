package Clases;

import Interfaces.Cancion;

import java.util.ArrayList;

public class LeerTeclasUsandoCPP
{

	//creamos un nuevo metodo nativo en c++
    native int leerTecla();
    native boolean hayTeclaPulsada();

    //Cargamos la libreria compilada 'leerTeclasCPP.dll' que contiene los 6
    static 
    {
        System.loadLibrary("leerTeclasCPP");
    }

    //Clase principal
    static public void main(String argv[]) 
    {
    	//Definicion de constantes con las teclas que usara el programa
    	final int ESC = 27; 

    	//Instanciamos un objeto tipo 'LeerTeclasUsandoCPP'
        LeerTeclasUsandoCPP teclas = new LeerTeclasUsandoCPP();

        //Creamos una variable para almacenar el codigo de la tecla pulsada
        int teclaPulsada = 0;

	System.out.println("Pulsa cualquier tecla");
        
        //Mientras la tecla pulsada sea distinto de ESC
        while( teclaPulsada != ESC )
        {
            if ( teclas.hayTeclaPulsada()  )
            {
            	//leemos la tecla usando el metodo escrito en c que esta dentro de la libreria que compilamos
            	teclaPulsada = teclas.leerTecla(); 

            	//Imprimimos el codigo de la tecla pulsada
            	System.out.println("\nCodigo ASCII: "+teclaPulsada+"    Tecla pulsada: "+(char)teclaPulsada);
            }   
        }
    }
}
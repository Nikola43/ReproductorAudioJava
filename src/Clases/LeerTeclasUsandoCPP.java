package Clases;

public class LeerTeclasUsandoCPP
{
    //Definimos los metodos que tendra  un nuevo metodo nativo en C++

    /* INTERFAZ
    Cabecera: JNIEXPORT jint JNICALL Java_LeerTeclasUsandoCPP_leerTecla(JNIEnv *, jobject)
    Descripcion: Lee la tecla pulsada por el usuario
    Entradas:
    Precondiciones:
    Salidas: Un entero
    Postcondiciones: Devolverá el codigo ASCII correspondiente a la tecla pulsada
    Entrada/Salida:
    */
    native int leerTecla();

    /* INTERFAZ
    Cabecera: JNIEXPORT jboolean JNICALL Java_LeerTeclasUsandoCPP_hayTeclaPulsada(JNIEnv *, jobject)
    Descripcion: Comprueba si se pulsa cualquier tecla del teclado
    Entradas:
    Precondiciones:
    Salidas: Un boolean
    Postcondiciones: Devolvera TRUE cuando se haya pulsado cualquier tecla, FALSE cuando no
    Entrada/Salida:
	*/
    native boolean hayTeclaPulsada();

    //Cargamos la libreria compilada 'leerTeclasCPP.dll'
    static
    {
        System.loadLibrary("leerTeclasCPP");
    }
}
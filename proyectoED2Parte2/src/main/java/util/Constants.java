
package util;

import TDAs.CircularDoublyLinkedList;

public class Constants {
    public static String rutaPreguntas;
    public static String rutaRespuestas;
    
    
    
    public static CircularDoublyLinkedList<String> cargarListaFotos(){
        CircularDoublyLinkedList<String> listaFotos = new CircularDoublyLinkedList<>();
        
        listaFotos.addLast("archivos/imagenes/computadora1.jpg");
        listaFotos.addLast("archivos/imagenes/computadora2.jpg");
        listaFotos.addLast("archivos/imagenes/computadora3.jpg");
        listaFotos.addLast("archivos/imagenes/computadora4.jpg");
        listaFotos.addLast("archivos/imagenes/computadora5.jpg");
        
        return listaFotos;
    }
}

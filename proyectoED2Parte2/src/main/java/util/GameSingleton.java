
package util;

import java.util.ArrayList;
import model.Respuesta;

public class GameSingleton {
    
    private static GameSingleton gameInstance;
    private ArrayList<String> preguntas; 
    private ArrayList<Respuesta> respuestas;
    
    
    
    private GameSingleton(ArrayList<String> preguntas, ArrayList<Respuesta> respuestas){
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }
    
    
    
    public static GameSingleton getInstance(ArrayList<String> listaPreguntas, ArrayList<Respuesta> listaRespuestas, boolean reset){
        if(gameInstance==null || reset){
            gameInstance = new GameSingleton(listaPreguntas,listaRespuestas);
        }
        return gameInstance;
    }

    public ArrayList<String> getPreguntas() {
        return preguntas;
    }

    public ArrayList<Respuesta> getRespuestas() {
        return respuestas;
    }
    
    
    
}

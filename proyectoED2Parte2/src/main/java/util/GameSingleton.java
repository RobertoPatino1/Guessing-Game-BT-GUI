
package util;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class GameSingleton {
    
    private static GameSingleton gameInstance;
    private ArrayList<String> preguntas; 
    private ArrayList<String> respuestas;
    
    
    
    private GameSingleton(ArrayList<String> preguntas, ArrayList<String> respuestas){
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }
    
    
    
    public static GameSingleton getInstance(ArrayList<String> listaPreguntas, ArrayList<String> listaRespuestas, boolean reset){
        if(gameInstance==null || reset){
            gameInstance = new GameSingleton(listaPreguntas,listaRespuestas);
        }
        return gameInstance;
    }
    
}


package util;

import java.util.List;
import model.Respuesta;

public class GameSingleton {
    
    private static GameSingleton gameInstance;
    private List<String> preguntas; 
    private List<Respuesta> respuestas;
    
    
    
    private GameSingleton(List<String> preguntas, List<Respuesta> respuestas){
        this.preguntas = preguntas;
        this.respuestas = respuestas;
    }
    
    
    
    public static GameSingleton getInstance(List<String> listaPreguntas, List<Respuesta> listaRespuestas, boolean reset){
        if(gameInstance==null || reset){
            gameInstance = new GameSingleton(listaPreguntas,listaRespuestas);
        }
        return gameInstance;
    }
    public static GameSingleton getInstance(){
        return gameInstance;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }
    
    
    
}


package util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.Respuesta;

public class Lector {
    
    
    public static ArrayList<String> cargarListaPreguntas(String path){
        ArrayList<String> preguntas = new ArrayList<>();
        try(BufferedReader lector = new BufferedReader(new FileReader(path))){
            String line;
            while((line = lector.readLine())!=null){
                preguntas.add(line);
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Questions file does not exists!");
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Unexpected exception");
        }
        
        return preguntas;
        
    }
    
    
    public static ArrayList<Respuesta> cargarListaRespuestas(String path){
        
        ArrayList<Respuesta> respuestas = new ArrayList<>();
        try(BufferedReader lector = new BufferedReader(new FileReader(path))){
            
            String line;
            while((line = lector.readLine())!=null){
                
                ArrayList<String> answers = new ArrayList<>();
                
                Collections.addAll(answers, line.split(" "));
                
                String animal = answers.remove(0);
                respuestas.add(new Respuesta(animal, answers));
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
            System.out.println("Answers file does not exists!");
            
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Unexpected exception");
        }
        
        return respuestas;
        
    }
    
    
    

    
}


package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
            System.out.println("File does not exists!");
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
            System.out.println("File alredy exists!");
        }catch(IOException e){
            e.printStackTrace();
        }catch(Exception e){
            System.out.println("Unexpected exception");
        }
        
        return respuestas;
        
    }
    
}

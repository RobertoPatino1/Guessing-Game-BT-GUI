
package model;

import java.util.ArrayList;

public class Respuesta {
    private String animal;
    private ArrayList<String> respuestas;   //Lista de las respuestas con si y no

    public Respuesta(String animal, ArrayList<String> respuestas) {
        this.animal = animal;
        this.respuestas = respuestas;
    }

    public String getAnimal() {
        return animal;
    }

    public ArrayList<String> getRespuestas() {
        return respuestas;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "animal=" + animal + ", respuestas=" + respuestas + '}';
    }
    
    
    
    
    
    
    
    
    
}

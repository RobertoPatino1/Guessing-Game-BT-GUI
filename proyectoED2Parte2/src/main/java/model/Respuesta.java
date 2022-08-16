
package model;

import java.util.List;

public class Respuesta {
    private String animal;
    private List<String> respuestas;   //Lista de las respuestas con si y no

    public Respuesta(String animal, List<String> respuestas) {
        this.animal = animal;
        this.respuestas = respuestas;
    }

    public String getAnimal() {
        return animal;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    @Override
    public String toString() {
        return "Respuesta{" + "animal=" + animal + ", respuestas=" + respuestas + '}';
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }
    
    
    
    
    
    
    
    
    
}

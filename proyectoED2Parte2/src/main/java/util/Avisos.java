
package util;

import javafx.scene.control.Alert;

public class Avisos {
    
    public static void avisoCamposIncompletos(){
        Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegurese de seleccionar todos los campos"); //FIXME
        alerta.setTitle("Error");
        alerta.setHeaderText("Ha ocurrido un error:");
        alerta.showAndWait();  
    }
    public static void avisoCamposErroneos(){
        Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegurese de ingresar caracteres numericos \nen el total de preguntas"); //FIXME
        alerta.setTitle("Error");
        alerta.setHeaderText("Ha ocurrido un error:");
        alerta.showAndWait();  
    }
    
    
    
}

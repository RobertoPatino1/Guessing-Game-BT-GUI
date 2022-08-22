/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Alan
 */
public class PantallaPreguntasController implements Initializable{
    
    @FXML
    private Label lblPregunta;
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    @FXML
    private Button btnDejarJugar;
    @FXML
    private VBox vboxCentro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
      
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("startMenu");
    }

    @FXML
    private void jugadorRespondeSi(ActionEvent event) {
        System.out.println("La respuesta es si (Se viaja a la izquierda del arbol)");
    }

    @FXML
    private void jugadorRespondeNo(ActionEvent event) {
        System.out.println("La respuesta es no (Se viaja a la derecha del arbol)");
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void mostrarResultados(){
        /*
        ###########################################################################
        METODO QUE LIMPIA LOS CONTENEDORES Y MUESTRA EN PANTALLA
        LAS POSIBLES RESPUESTAS (SE EJECUTA SI NO SE DIO CON LA RESPUESTA CORRECTA)
        ############################################################################
        */
    }
    
    
    
    
    
}

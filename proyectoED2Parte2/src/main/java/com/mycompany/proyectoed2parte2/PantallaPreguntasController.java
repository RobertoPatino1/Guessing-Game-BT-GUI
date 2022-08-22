/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ArbolDecision;
import util.GameSingleton;

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
    
    private ArbolDecision arbolJuego;
    
    private GameSingleton instaciaJuego = GameSingleton.getInstance();
    
    private ArrayList<String> respuestasJugador;
    
    private int count;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arbolJuego = new ArbolDecision(instaciaJuego.getPreguntas(),instaciaJuego.getRespuestas());    
        respuestasJugador = new ArrayList<>();
        count = 1;
        
    }
      
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("startMenu");
    }

    @FXML
    private void jugadorRespondeSi(ActionEvent event) {
        respuestasJugador.add("si");
        count++;
        
        if(count==instaciaJuego.getRespuestas().size()){
            System.out.println("SE ACABA EL JUEGO");
        }
        
    }

    @FXML
    private void jugadorRespondeNo(ActionEvent event) {
        respuestasJugador.add("no");
        count++;
        if(count==instaciaJuego.getRespuestas().size()){
            System.out.println("SE ACABA EL JUEGO");
        }
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

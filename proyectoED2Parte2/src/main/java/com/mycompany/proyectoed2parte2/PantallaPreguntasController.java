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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
      
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("startMenu");
    }
    
    
    
    
    
}

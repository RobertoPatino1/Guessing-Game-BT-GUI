/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PantallaJuegoInversoController implements Initializable {


    @FXML
    private Button btnDejarJugar;
    @FXML
    private VBox vboxCentro;
    @FXML
    private Label lblPregunta;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Button btnSi;
    @FXML
    private Button btnNo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void cancelar(ActionEvent event) {
    }

    @FXML
    private void jugadorRespondeSi(ActionEvent event) {
    }

    @FXML
    private void jugadorRespondeNo(ActionEvent event) {
    }

}

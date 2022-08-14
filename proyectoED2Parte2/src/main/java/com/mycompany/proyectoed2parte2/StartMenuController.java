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


public class StartMenuController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void jugar(ActionEvent event) {
    }

    @FXML
    private void cargarPreguntas(ActionEvent event) {
        /*
        Metodo que abre un fileChooser para agregar los archivos de texto con las preguntas
        */
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

}

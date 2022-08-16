/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;


public class StartMenuController implements Initializable {
public static  int id = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void jugar(ActionEvent event) {
        //Saltamos de ventana???
    }

    @FXML
    private void cargarPreguntas(ActionEvent event) throws IOException {

        try{
        FileChooser fileChooser = new FileChooser(); //Este nos permite abrir el explorador de archivo
        fileChooser.setTitle("Buscar foto");
       
        

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        

        List<File> txtFile = fileChooser.showOpenMultipleDialog(null);
        
        if(txtFile!=null){
            for(File f: txtFile){
               File txt = new File("file:"+f.getAbsolutePath()); 
                //Se copia la imagen
                Path from = Paths.get(f.toURI());
                Path to = Paths.get("archivos/"+id+"_"+f.getName());

                Files.copy(from, to);
                
                
            }
            id++;
            

            
        }
        

    
       }catch(FileAlreadyExistsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"El archivo que se ha seleccionado ya se encuentra almacenado, por favor seleccione otro"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");
            System.out.println(e.getClass());
            alerta.show();  
       }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }

}

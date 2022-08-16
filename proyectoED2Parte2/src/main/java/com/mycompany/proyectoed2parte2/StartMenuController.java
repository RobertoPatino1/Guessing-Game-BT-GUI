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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import model.Respuesta;
import util.Lector;


public class StartMenuController implements Initializable {
public static  int id = 0;
    @FXML
    private BorderPane borderPane;
    private ArrayList<String> listaPreguntas = new ArrayList<>();
    private ArrayList<String> listaRespuestas = new ArrayList<>();
    @FXML
    private Label lblTitulo;
    @FXML
    private VBox contenedor;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarListaArchivos();
    }    
    
    @FXML
    private void jugar(ActionEvent event) {
        actualizarListaArchivos();
        if(listaPreguntas.isEmpty()||listaRespuestas.isEmpty()){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"Por favor, asegurese de cargar sus preguntas y respuestas antes de jugar"); //FIXME
            alerta.setTitle("Error");
            alerta.show();  
        }else{
            escogerNombresArchivos();
        }
        

        
        
        
    }

    @FXML
    private void cargarPreguntas(ActionEvent event) throws IOException {
        Alert aviso = new Alert(Alert.AlertType.INFORMATION,"Asegurese de seleccionar primero el archivo de preguntas \ny luego el archivo de respuestas"); //FIXME
        aviso.setHeaderText("Importante: ");

        aviso.showAndWait();  
        try{
        FileChooser fileChooser = new FileChooser(); //Este nos permite abrir el explorador de archivo
        fileChooser.setTitle("Cargar archivos");
       
        

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        

        List<File> fileList = fileChooser.showOpenMultipleDialog(null);
        
        
        if(fileList!=null){

            Path fromPreguntas = Paths.get(fileList.get(0).toURI());
            Path toPreguntas = Paths.get("archivos/preguntas/"+id+"_"+fileList.get(1).getName());
            Path fromRespuestas = Paths.get(fileList.get(0).toURI());
            Path toRespuestas = Paths.get("archivos/respuestas/"+id+"_"+fileList.get(0).getName());
            
            Files.copy(fromPreguntas, toPreguntas);
            Files.copy(fromRespuestas, toRespuestas);

            id++;
            
            actualizarListaArchivos();
            
        }
        

    
       }catch(FileAlreadyExistsException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR,"El archivo que se ha seleccionado ya se encuentra almacenado, por favor seleccione otro"); //FIXME
            alerta.setTitle("Error");
            alerta.setHeaderText("Ha ocurrido un error:");

            alerta.show();  
       }
    }

    @FXML
    private void salir(ActionEvent event) {
        System.exit(0);
    }
    
    
    private void actualizarListaArchivos(){

        File[] preguntas = new File("archivos/preguntas/").listFiles();
        File[] respuestas = new File("archivos/respuestas/").listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file : preguntas) {
            if (file.isFile()) {
                if(!listaPreguntas.contains(file.getName()))
                    listaPreguntas.add(file.getName());
            }
        }
        for (File file : respuestas) {
            if (file.isFile()) {
                if(!listaRespuestas.contains(file.getName()))
                    listaRespuestas.add(file.getName());
            }
        }
    }
    
    private void escogerNombresArchivos(){
       Button iniciarPartida = new Button("Jugar!");
       iniciarPartida.setFont(new Font("Arial",15));


       Label lblPreguntas = new Label("Selecciona el archivo de preguntas!");
       Label lblRespuestas = new Label("Selecciona el archivo de respuestas!");

       lblPreguntas.setFont(new Font("Arial", 15));
       lblRespuestas.setFont(new Font("Arial", 15));

       contenedor.setPadding(new Insets(20));



        //Creamos los cb
        ComboBox<String> comboPreguntas = new ComboBox<>();

        ComboBox<String> comboRespuestas = new ComboBox<>();

        comboPreguntas.getItems().setAll(listaPreguntas);
        comboRespuestas.getItems().setAll(listaRespuestas);

        comboPreguntas.setPromptText("Preguntas: ");
        comboRespuestas.setPromptText("Respuestas: ");
        VBox.setMargin(comboPreguntas, new Insets(10, 0, 20, 0));
        VBox.setMargin(comboRespuestas, new Insets(10, 0, 20, 0));


        contenedor.getChildren().setAll(lblPreguntas,comboPreguntas,lblRespuestas,comboRespuestas,iniciarPartida);

        borderPane.setCenter(contenedor);


        iniciarPartida.setOnAction(e -> {
            String nombreArchivoPreguntas = comboPreguntas.getValue();
            String nombreArchivoRespuestas = comboRespuestas.getValue();

            if(nombreArchivoPreguntas==null || nombreArchivoRespuestas == null){
                Alert alerta = new Alert(Alert.AlertType.ERROR,"Asegurese de seleccionar todos los campos"); //FIXME
                alerta.setTitle("Error");
                alerta.setHeaderText("Ha ocurrido un error:");
                alerta.showAndWait();  
            }else{
                
                ArrayList<String> preguntas = Lector.cargarListaPreguntas("archivos/"+nombreArchivoPreguntas);
                ArrayList<Respuesta> respuestas = Lector.cargarListaRespuestas("archivos/"+nombreArchivoPreguntas);
                //Creamos el singleton con los archivos seleccionados
                

                //Caso contrario cambiamos de escena pasando como parametro lo recuperado


            }
        });
        
    }

}

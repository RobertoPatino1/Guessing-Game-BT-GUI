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
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import model.ArbolDecision;
import model.Respuesta;
import trees.BinaryTree;
import util.Avisos;
import util.Constants;
import util.GameSingleton;
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
    
    private ImageView imageView;
    private Label lblCountdown = new Label();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarListaArchivos();
        imageView = new ImageView();

        lblCountdown.setFont(new Font("Arial", 50));
    }    
    
    @FXML
    private void switchPantallaPreguntas(ActionEvent event) throws IOException {
        App.setRoot("PantallaPreguntas");
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
        ArrayList<File> listaArchivos = new ArrayList<>();
        Alert aviso = new Alert(Alert.AlertType.INFORMATION,"Primero deberá seleccionar el archivo de preguntas, \nluego el archivo de respuestas"); //FIXME
        aviso.setHeaderText("Importante: ");

        aviso.showAndWait();  
        
        try{

            FileChooser fileChooser = new FileChooser(); //Este nos permite abrir el explorador de archivos

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));


            fileChooser.setTitle("Cargar Preguntas");
            File archivoPreguntas = fileChooser.showOpenDialog(null);

            fileChooser.setTitle("Cargar Respuestas");
            File archivoRespuestas = fileChooser.showOpenDialog(null);

            listaArchivos.add(archivoPreguntas);
            listaArchivos.add(archivoRespuestas);
        

        

        if(!listaArchivos.isEmpty() && listaArchivos.size()>=2){

            Path fromPreguntas = Paths.get(listaArchivos.get(0).toURI());
            Path toPreguntas = Paths.get("archivos/preguntas/"+id+"_"+listaArchivos.get(0).getName());
            Path fromRespuestas = Paths.get(listaArchivos.get(1).toURI());
            Path toRespuestas = Paths.get("archivos/respuestas/"+id+"_"+listaArchivos.get(1).getName());
            
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
       Button juegoInverso = new Button("Jugar el modo inverso!");
       juegoInverso.setFont(new Font("Arial",15));
       Button btnRegresar = new Button("Volver al menú principal");
       btnRegresar.setFont(new Font("Arial",15));
       
       btnRegresar.setOnAction(ehh -> {
           try {
               App.setRoot("startMenu");
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       });

       Label lblPreguntas = new Label("Selecciona el archivo de preguntas!");
       Label lblRespuestas = new Label("Selecciona el archivo de respuestas!");
       Label lblCantidadPreguntas = new Label("Indique el numero máximo de preguntas a realizar!");

       lblPreguntas.setFont(new Font("Arial", 15));
       lblRespuestas.setFont(new Font("Arial", 15));
       lblCantidadPreguntas.setFont(new Font("Arial", 15));
       

       contenedor.setPadding(new Insets(20));
       
       
       //Se crea el textfield
        TextField txtCantidadPreguntas = new TextField();
        txtCantidadPreguntas.setMinWidth(40);
        txtCantidadPreguntas.setMaxWidth(40);
        
        //Creamos los cb
        ComboBox<String> comboPreguntas = new ComboBox<>();
        
        ComboBox<String> comboRespuestas = new ComboBox<>();
        
        comboPreguntas.getItems().setAll(listaPreguntas);
        System.out.println(listaPreguntas);
        
        comboRespuestas.getItems().setAll(listaRespuestas);

        comboPreguntas.setPromptText("Preguntas: ");
        comboRespuestas.setPromptText("Respuestas: ");
        VBox.setMargin(comboPreguntas, new Insets(10, 0, 20, 0));
        VBox.setMargin(comboRespuestas, new Insets(10, 0, 20, 0));
        VBox.setMargin(txtCantidadPreguntas, new Insets(10, 0, 20, 0));
        VBox.setMargin(iniciarPartida, new Insets(10, 0, 20, 0));
        VBox.setMargin(juegoInverso, new Insets(10, 0, 20, 0));
        
        contenedor.getChildren().setAll(lblPreguntas,comboPreguntas,lblRespuestas,comboRespuestas,lblCantidadPreguntas,txtCantidadPreguntas,iniciarPartida,juegoInverso,btnRegresar);

        borderPane.setCenter(contenedor);

        
        iniciarPartida.setOnAction(e -> {
            String nombreArchivoPreguntas = comboPreguntas.getValue();
            String nombreArchivoRespuestas = comboRespuestas.getValue();
            
            List<String> preguntas = Lector.cargarListaPreguntas("archivos/preguntas/"+nombreArchivoPreguntas);
            List<Respuesta> respuestas = Lector.cargarListaRespuestas("archivos/respuestas/"+nombreArchivoRespuestas);
            
            if(nombreArchivoPreguntas==null || nombreArchivoRespuestas == null || txtCantidadPreguntas.getText().equals("")){
                Avisos.avisoCamposIncompletos();
            }else{  
                try{
                    
                    int totalPreguntas = Integer.valueOf(txtCantidadPreguntas.getText());
                    
                    //Seccionando la lista de preguntas en funcion del total de preguntas que debe hacer la computadora
                    preguntas = preguntas.subList(0, totalPreguntas);
                    
                    //Solo debemos seccionar la lista de respuestas de si/no en la lista de respuestas que tenemos
                    for(Respuesta r: respuestas){
                        r.setRespuestas(r.getRespuestas().subList(0, totalPreguntas));
                    }
                    
                    
                }catch(NumberFormatException ex){
                    Avisos.avisoCamposErroneos();
                }catch(IndexOutOfBoundsException ex){
                    //En este caso no hay suficientes elementos en la lista, solo se escoje la lista tal y como es
                    System.out.println("No se hace nada con preguntas");
                    System.out.println("No se hace nada con las respuestas de cada respuesta correcta");
                }
                System.out.println(preguntas);
                System.out.println(respuestas);
                
                launchGame(preguntas, respuestas);
                
                Constants.rutaPreguntas = "archivos/preguntas/"+nombreArchivoPreguntas;
                Constants.rutaRespuestas = "archivos/respuestas/"+nombreArchivoRespuestas;
            }
        });
        juegoInverso.setOnAction(e -> {
            String nombreArchivoPreguntas = comboPreguntas.getValue();
            String nombreArchivoRespuestas = comboRespuestas.getValue();
            
            List<String> preguntas = Lector.cargarListaPreguntas("archivos/preguntas/"+nombreArchivoPreguntas);
            List<Respuesta> respuestas = Lector.cargarListaRespuestas("archivos/respuestas/"+nombreArchivoRespuestas);
            
            if(nombreArchivoPreguntas==null || nombreArchivoRespuestas == null || txtCantidadPreguntas.getText().equals("")){
                Avisos.avisoCamposIncompletos();
            }else{  
                try{
                    
                    int totalPreguntas = Integer.valueOf(txtCantidadPreguntas.getText());
                    
                    //Seccionando la lista de preguntas en funcion del total de preguntas que debe hacer la computadora
                    preguntas = preguntas.subList(0, totalPreguntas);
                    
                    //Solo debemos seccionar la lista de respuestas de si/no en la lista de respuestas que tenemos
                    for(Respuesta r: respuestas){
                        r.setRespuestas(r.getRespuestas().subList(0, totalPreguntas));
                    }
                    
                    
                }catch(NumberFormatException ex){
                    Avisos.avisoCamposErroneos();
                }catch(IndexOutOfBoundsException ex){
                    //En este caso no hay suficientes elementos en la lista, solo se escoje la lista tal y como es
                    System.out.println("No se hace nada con preguntas");
                    System.out.println("No se hace nada con las respuestas de cada respuesta correcta");
                }
                System.out.println(preguntas);
                System.out.println(respuestas);
                
                launchInvertedGame(preguntas, respuestas);
                
                Constants.rutaPreguntas = "archivos/preguntas/"+nombreArchivoPreguntas;
                Constants.rutaRespuestas = "archivos/respuestas/"+nombreArchivoRespuestas;
            }
        });
        
    }
    

    private void launchGame(List<String> preguntas, List<Respuesta> respuestas){
        //Creando el objeto Singleton
        GameSingleton.getInstance(preguntas,respuestas,true);

        contenedor.getChildren().clear();

        lblTitulo.setText("Piensa en un animal!");

        Button btnJugar = new Button("Iniciar la partida!");
        Label lblListo = new Label("Listo?");

        lblListo.setFont(new Font("Arial",20));
        lblListo.setPadding(new Insets(10, 10, 10, 10));
        btnJugar.setFont(new Font("Arial", 20));

        contenedor.getChildren().addAll(lblListo,btnJugar);


        btnJugar.setOnAction(eh -> {
            System.out.println("Se cambia de pantalla al juego");
            try {
                App.setRoot("PantallaPreguntas");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
    }
    private void launchInvertedGame(List<String> preguntas, List<Respuesta> respuestas){
        //Creando el objeto Singleton
        GameSingleton.getInstance(preguntas,respuestas,true);

        contenedor.getChildren().clear();

        lblTitulo.setText("Trata de llegar al animal que te mostraré!");

        Button btnJugar = new Button("Iniciar la partida!");
        Label lblListo = new Label("Listo?");

        lblListo.setFont(new Font("Arial",20));
        lblListo.setPadding(new Insets(10, 10, 10, 10));
        btnJugar.setFont(new Font("Arial", 20));

        contenedor.getChildren().addAll(lblListo,btnJugar);


        btnJugar.setOnAction(eh -> {
            System.out.println("Se cambia de pantalla al juego");
            try {
                App.setRoot("PantallaJuegoInverso");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
    }
    
    
 
    

}

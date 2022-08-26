/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import TDAs.CircularDoublyLinkedList;
import TDAs.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.ArbolDecision;
import model.Respuesta;
import util.Constants;
import util.GameSingleton;
import util.Lector;

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
    @FXML
    private Label lblAnimal;
    
    private int count;
    
    private ArbolDecision arbolJuego;
    
    private GameSingleton instaciaJuego = GameSingleton.getInstance();
    
    private ArrayList<String> respuestasJugador;
    private Iterator<String> it;
    
    private CircularDoublyLinkedList<String> listaFotos;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblAnimal.setText(generarAnimal());
        arbolJuego = new ArbolDecision(Lector.cargarListaPreguntas(Constants.rutaPreguntas),Lector.cargarListaRespuestas(Constants.rutaRespuestas));  
        respuestasJugador = new ArrayList<>();
        count = 0;
        mostrarPregunta(count);
        btnDejarJugar.setVisible(false);


        listaFotos = Constants.cargarListaFotos();
        
        it = listaFotos.iterator();
        cargarImagen(it.next());
    }    
    
    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        App.setRoot("startMenu");
    }

    @FXML
    private void jugadorRespondeSi(ActionEvent event) {
        respuestasJugador.add("si");
        count++;
        Integer c = count;
        Integer tmp = instaciaJuego.getPreguntas().size();
        if(c<tmp){
            cargarImagen(it.next());
            mostrarPregunta(c);
        }
        if(c.equals(tmp)){

            System.out.println(arbolJuego.mostrarPosiblesRespuestas(count, respuestasJugador));
            mostrarResultados();
        }
    }

    @FXML
    private void jugadorRespondeNo(ActionEvent event) {
        respuestasJugador.add("no");
        count++;
        Integer c2 = count;
        Integer tmp = instaciaJuego.getPreguntas().size();
        if(c2<tmp){
            cargarImagen(it.next());
            mostrarPregunta(c2);
        }
        if(c2.equals(tmp)){
            System.out.println("FIN DEL JUEGO");
            System.out.println(arbolJuego.mostrarPosiblesRespuestas(count, respuestasJugador));
            mostrarResultados();
            /*
            ##########################################
            VALIDAR EN FUNCION DEL TAMANIO DE LA LISTA DE POSIBLES RESPUESTAS:
            SI ES UNO PUEDE QUE SEA UNA RESPUESTA CERTERA, SI ES MAYOR A 1 SE PRESENTAN LAS
            RESPUESTAS POSIBLES
            ##########################################
            */
        }
    }
    
    
    
    private String generarAnimal(){
        Random ran = new Random();
        
        ArrayList<Respuesta> lista=Lector.cargarListaRespuestas(Constants.rutaRespuestas);

        int indice = ran.nextInt(lista.size());
        
        String animal= lista.get(indice).getAnimal();
        
        return animal;
    }
    
    
    private void mostrarPregunta(int idx){
        java.util.List<String> preguntas=instaciaJuego.getPreguntas();
        
        if(idx>preguntas.size()){
            System.out.println("Error en indice");
        }else{
            String pregunta= preguntas.get(idx);
            lblPregunta.setText(pregunta);
        }
    }
    
    
    private void cargarImagen(String nombreImagen){
        try{
            FileInputStream input = new FileInputStream(nombreImagen);
            Image foto = new Image(input);
            imgFoto.setImage(foto);
            
            
        }catch(FileNotFoundException e){
            System.out.println("No se encontro la foto");
        }
    }
    
    
    public void mostrarResultados(){
        
    }
}

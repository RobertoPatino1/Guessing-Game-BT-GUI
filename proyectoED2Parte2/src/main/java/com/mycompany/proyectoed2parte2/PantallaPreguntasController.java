/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import TDAs.CircularDoublyLinkedList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.ArbolDecision;
import util.Constants;
import util.GameSingleton;
import util.Lector;

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
    
    private Iterator<String> it;
    
    private CircularDoublyLinkedList<String> listaFotos;
    @FXML
    private ImageView imgvFoto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        arbolJuego = new ArbolDecision(Lector.cargarListaPreguntas(Constants.rutaPreguntas),Lector.cargarListaRespuestas(Constants.rutaRespuestas));  
        respuestasJugador = new ArrayList<>();
        count = 0;
        mostrarPregunta(count);
        btnDejarJugar.setVisible(false);
        System.out.println("ARBOL");
        System.out.println(arbolJuego.getArbol().inOrderRecursiveTraversal());
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
    

    private void mostrarPregunta(int idx){
        List<String> preguntas=instaciaJuego.getPreguntas();
        
        if(idx>preguntas.size()){
            System.out.println("Error en indice");
        }else{
            String pregunta= preguntas.get(idx);
            lblPregunta.setText(pregunta);
        }
    }
    
   

    
    private void mostrarResultados(){
        /*
        ###########################################################################
        METODO QUE LIMPIA LOS CONTENEDORES Y MUESTRA EN PANTALLA
        LAS POSIBLES RESPUESTAS 
        ############################################################################
        */
        
        Label lblRespuestaFinal = new Label();
        lblRespuestaFinal.setFont(new Font("Arial", 24));
        ImageView contenedor = new ImageView();
        
        setImagen(contenedor, "archivos/imagenes/computadoraRespuestaCorrecta.jpg");
        ArrayList<String> posiblesRespuestas = arbolJuego.mostrarPosiblesRespuestas(count, respuestasJugador);
        btnDejarJugar.setVisible(true);
        
        
        if(posiblesRespuestas==null){
            //No se encontraron respuestas
            lblPregunta.setText("Lo siento, no pude adivinar el animal\nen el que pensabas :( ");
            vboxCentro.getChildren().clear();
            cargarImagen("archivos/imagenes/sinSolucion.jpg");
            vboxCentro.getChildren().addAll(lblPregunta,imgvFoto,lblRespuestaFinal);
            

        }
        
        else if(posiblesRespuestas.size()==1){
            //Se encontro la respuesta unica de esa pregunta
            lblPregunta.setText("El animal en el que pensabas es: ");
            lblRespuestaFinal.setText(posiblesRespuestas.get(0));
            vboxCentro.getChildren().clear();
            cargarImagen("archivos/imagenes/respuestaCorrecta.jpg");
            vboxCentro.getChildren().addAll(lblPregunta,imgvFoto,lblRespuestaFinal);
            
        }else if(posiblesRespuestas.size()>1){
            //Se encontraron varias posibles respuestas
            lblPregunta.setText("No estoy muy seguro del animal en el que pensabas.\nPero sé que debe ser alguno de estos: ");
            
            lblRespuestaFinal.setText(posiblesRespuestas.toString());
            
            vboxCentro.getChildren().clear();
            
            cargarImagen("archivos/imagenes/variasSoluciones.jfif");
            vboxCentro.getChildren().addAll(lblPregunta,imgvFoto,lblRespuestaFinal);

        }
    }
    
    
    
    
    private void cargarImagen(String nombreImagen){
        try{
            FileInputStream input = new FileInputStream(nombreImagen);
            Image foto = new Image(input);
            imgvFoto.setImage(foto);
            
            
        }catch(FileNotFoundException e){
            System.out.println("No se encontro la foto");
        }
    }
    
    private void setImagen(ImageView contenedor,String nombreImagen){
        try{
            FileInputStream input = new FileInputStream(nombreImagen);
            Image foto = new Image(input);
            contenedor.setImage(foto);
        }catch(FileNotFoundException e){
            System.out.println("No se encontro la foto");
        }
    }
    
    
    
    
    
}

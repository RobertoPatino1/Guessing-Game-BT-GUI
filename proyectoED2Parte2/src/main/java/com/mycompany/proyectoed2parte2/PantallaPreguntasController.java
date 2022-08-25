/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyectoed2parte2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.ArbolDecision;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /*
        FIXMEEE: REEMPLAZAR LOS PARAMETROS DEL ARBOL DE DECISION
        */
        arbolJuego = new ArbolDecision(Lector.cargarListaPreguntas("archivos/preguntas/0_PREGUNTAS.txt"),Lector.cargarListaRespuestas("archivos/respuestas/0_RESPUESTAS.txt"));    
        respuestasJugador = new ArrayList<>();
        count = 0;
        
        System.out.println(arbolJuego.getArbol().inOrderRecursiveTraversal());
        mostrarPregunta(count);
        
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
            mostrarPregunta(c);
        }
        if(c.equals(tmp)){

            System.out.println(arbolJuego.mostrarPosiblesRespuestas(count, respuestasJugador));
            /*
            ##########################################
            VALIDAR EN FUNCION DEL TAMANIO DE LA LISTA DE POSIBLES RESPUESTAS:
            SI ES UNO PUEDE QUE SEA UNA RESPUESTA CERTERA, SI ES MAYOR A 1 SE PRESENTAN LAS
            RESPUESTAS POSIBLES
            ##########################################
            */
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
    

    @FXML
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
        LAS POSIBLES RESPUESTAS (SE EJECUTA SI NO SE DIO CON LA RESPUESTA CORRECTA)
        ############################################################################
        */
        

        
        Label lblRespuestaFinal = new Label();
        lblRespuestaFinal.setFont(new Font("Arial", 24));
        
        ArrayList<String> posiblesRespuestas = arbolJuego.mostrarPosiblesRespuestas(count, respuestasJugador);
        
        if(posiblesRespuestas==null){
            //No se encontraron respuestas
            lblPregunta.setText("Lo siento, no pude adivinar el animal\nen el que pensabas :( ");
            
            vboxCentro.getChildren().clear();
            vboxCentro.getChildren().addAll(lblPregunta);

        }
        
        else if(posiblesRespuestas.size()==1){
            //Se encontro la respuesta unica de esa pregunta
            lblPregunta.setText("El animal en el que pensabas es: ");
            lblRespuestaFinal.setText(posiblesRespuestas.get(0));
            vboxCentro.getChildren().clear();
            vboxCentro.getChildren().addAll(lblPregunta,lblRespuestaFinal);
            
        }else if(posiblesRespuestas.size()>1){
            //Se encontraron varias posibles respuestas
            lblPregunta.setText("No estoy muy seguro del animal en el que pensabas.\nPero sé que debe ser alguno de estos: ");
            
            lblRespuestaFinal.setText(posiblesRespuestas.toString());
            
            vboxCentro.getChildren().clear();
            vboxCentro.getChildren().addAll(lblPregunta,lblRespuestaFinal);
        }
    }
    
    
    
    
    
}

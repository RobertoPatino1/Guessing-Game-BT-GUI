/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import trees.BinaryTree;
import util.Constants;
import util.GameSingleton;
import util.Lector;

/**
 *
 * @author josel
 */
public class ArbolDecision {
    private BinaryTree arbol;
    private List<String> listaPreguntas = Lector.cargarListaPreguntas(Constants.rutaPreguntas);
    private List<Respuesta> listaRespuestas = Lector.cargarListaRespuestas(Constants.rutaRespuestas);
    
    
//    public ArbolDecision(){
//        this.arbol=new BinaryTree();
//        this.iniciarPreguntas();
//        this.iniciarRespuestas();
//    }
    
    public ArbolDecision(List<String> preguntas, List<Respuesta> respuestas){
        this.arbol=new BinaryTree();
        this.iniciarPreguntas(preguntas);
        this.iniciarRespuestas(respuestas);
    }

    public BinaryTree getArbol() {
        return arbol;
    }

    public void setArbol(BinaryTree arbol) {
        this.arbol = arbol;
    }
    
    
    
    public void iniciarPreguntas(List<String> preguntas){
//        ArrayList<String> preguntas=Lector.cargarListaPreguntas(Constants.rutaPreguntas);
        for(String pregunta:preguntas){
            this.arbol.setLastLeaves(pregunta);
        }
    }
    
    public void iniciarRespuestas(List<Respuesta> respuestas){
//        ArrayList<Respuesta> respuestas=Lector.cargarListaRespuestas(Constants.rutaRespuestas);
        for(Respuesta r:respuestas){
            this.setAnimal(r);
        }
    }
    
    
    public void setAnimal(Respuesta respuesta){
        List<String> lista=respuesta.getRespuestas();
        
        if(listaPreguntas.size()==respuesta.getRespuestas().size()){
            Stack<BinaryTree<String>> s = new Stack<>();
            s.push(arbol);
            int index=0;
            
            while(!s.isEmpty()){
                BinaryTree<String> tmp=s.pop();
                if(tmp.isLeaf() || tmp.getLeft()==null || tmp.getRight()==null){
                    if(lista.get(index).equalsIgnoreCase("si")){
                        BinaryTree<String> tmpLeft=new BinaryTree(respuesta.getAnimal());
                        tmp.setLeft(tmpLeft);
                    }
                    else if(lista.get(index).equalsIgnoreCase("no")){
                        BinaryTree<String> tmpRight=new BinaryTree(respuesta.getAnimal());
                        tmp.setRight(tmpRight);
                    }
                }
                else if(lista.get(index).equalsIgnoreCase("si")){
                    s.push(tmp.getLeft());
                    index++;
                }
                else if(lista.get(index).equalsIgnoreCase("no")){
                    s.push(tmp.getRight());
                    index++;
                }

            }    
        }

    }
    
    
    public String recorrerArbolRespuestas(ArrayList<String> listaRespuestas){

        String retorno = "";
        Stack<BinaryTree<String>> s = new Stack<>();
        s.push(arbol);
        int index=0;
        while(!s.isEmpty()){
            BinaryTree<String> tmp=s.pop();
            
            if(index==listaRespuestas.size()){
                retorno = "Estas pensando en un(a) " +tmp.getRootContent()+"!";

            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("si")){
                if(tmp.getLeft()!=null){
                    s.push(tmp.getLeft());
                    index++;                   
                }else{
                    retorno =  "No se pudo encontrar la respuesta";
                }

            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("no")){
                if(tmp.getRight()!=null){
                    s.push(tmp.getRight());
                    index++;              
                }else{
                    retorno =  "No se pudo encontrar la respuesta";
                }

            }
        
        }
        return retorno;
    }
    
        public BinaryTree<String> recorrerArbolRespuestasN(ArrayList<String> listaRespuestas){ 

        BinaryTree<String> retorno = new BinaryTree();
        Stack<BinaryTree<String>> s = new Stack<>();
        s.push(arbol);
        int index=0;
        while(!s.isEmpty()){
            BinaryTree<String> tmp=s.pop();
            
            if(index==listaRespuestas.size()){
                retorno =  tmp;

            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("si")){
                if(tmp.getLeft()!=null){
                    s.push(tmp.getLeft());
                    index++;                   

                }else{
                    return null;
                }
            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("no")){
                if(tmp.getRight()!=null){
                    s.push(tmp.getRight());
                    index++;              

                }else{
                    return null;
                }
            }
        }
        return retorno;
    }
    
    
    public ArrayList<String> mostrarPosiblesRespuestas(int cantidadPreguntasEscogidas,ArrayList<String> respuestasJugador){
        
        BinaryTree<String> subArbol=this.recorrerArbolRespuestasN(respuestasJugador);
        
        if(subArbol==null){
            return null;
        }
        
        int fondo=(listaPreguntas.size()+1)-cantidadPreguntasEscogidas;
        
        ArrayList<String> posiblesAnimales=new ArrayList();
        
        todasLasRespuestas(subArbol,fondo,posiblesAnimales);
        
        return posiblesAnimales;
    
    }
    
    
    private static void todasLasRespuestas(BinaryTree<String> subArbol,int nivel,ArrayList<String> lista){
        if(subArbol==null){
            return;
        }
        if(nivel==1&& subArbol.getRootContent()!=null){
            lista.add(subArbol.getRootContent());
        }
        else if(nivel>1){
            todasLasRespuestas(subArbol.getLeft(),nivel-1,lista);
            todasLasRespuestas(subArbol.getRight(),nivel-1,lista);
        }
    }
    
    
    public String recorrerArbolRespuestasInverso(ArrayList<String> listaRespuestas){

        String retorno = "";
        Stack<BinaryTree<String>> s = new Stack<>();
        s.push(arbol);
        int index=0;
        while(!s.isEmpty()){
            BinaryTree<String> tmp=s.pop();
            
            if(index==listaRespuestas.size()){
                retorno =tmp.getRootContent();

            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("si")){
                if(tmp.getLeft()!=null){
                    s.push(tmp.getLeft());
                    index++;                   
                }else{
                    retorno =  "No se pudo encontrar la respuesta";
                }

            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("no")){
                if(tmp.getRight()!=null){
                    s.push(tmp.getRight());
                    index++;              
                }else{
                    retorno =  "No se pudo encontrar la respuesta";
                }

            }
        
        }
        return retorno;
    }
    
}

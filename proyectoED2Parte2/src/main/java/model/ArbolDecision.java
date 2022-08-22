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
import util.Lector;

/**
 *
 * @author josel
 */
public class ArbolDecision {
    private BinaryTree arbol;
    private static final ArrayList<String> listaPreguntas = Lector.cargarListaPreguntas(Constants.rutaPreguntas);
    private ArrayList<Respuesta> listaRespuestas = Lector.cargarListaRespuestas(Constants.rutaRespuestas);
    
    
    public ArbolDecision(){
        this.arbol=new BinaryTree();
        this.iniciarPreguntas();
        this.iniciarRespuestas();
    }

    public BinaryTree getArbol() {
        return arbol;
    }

    public void setArbol(BinaryTree arbol) {
        this.arbol = arbol;
    }
    
    
    
    public void iniciarPreguntas(){
        ArrayList<String> preguntas=Lector.cargarListaPreguntas(Constants.rutaPreguntas);
        for(String pregunta:preguntas){
            this.arbol.setLastLeaves(pregunta);
        }
    }
    
    public void iniciarRespuestas(){
        ArrayList<Respuesta> respuestas=Lector.cargarListaRespuestas(Constants.rutaRespuestas);
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
                if(tmp.isLeaf()){
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

                }
            }
            else if(listaRespuestas.get(index).equalsIgnoreCase("no")){
                if(tmp.getRight()!=null){
                    s.push(tmp.getRight());
                    index++;              

                }   
            }
        }
        return retorno;
    }
    
    
    
    public static ArrayList<String> pedirRespuestas(){
        
        Scanner sc = new Scanner(System.in);
        ArrayList<String> respuestas = new ArrayList<>();
        
        for(String pregunta:listaPreguntas){
            
            System.out.println(pregunta);
            String respuesta = sc.nextLine();
     
            while(!respuesta.equalsIgnoreCase("si")&&!respuesta.equalsIgnoreCase("no")){
                System.out.println("Debe responder en forma de si o no.");
                System.out.println(pregunta);
                respuesta = sc.nextLine();
            }
            
            
            respuestas.add(respuesta);
           
        }
        
        return respuestas;
        
    }
    
}

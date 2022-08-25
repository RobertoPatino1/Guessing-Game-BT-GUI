/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.io.Serializable;

import java.util.Iterator;

import java.util.Comparator;


public class CircularDoublyLinkedList<E> implements List<E>,Iterable<E> {
    public CircularNode<E> first;
    
    public CircularDoublyLinkedList(){
        first=null;
    }
    
    public CircularNode<E> getCurrentNode(){
        return this.first;
        
    }
    
    @Override
    public boolean addFirst(E element) {
        CircularNode<E> nodo = new CircularNode<>(element);
        if(element==null)
            return false;
        else if(isEmpty()){
            first=nodo;
            first.setNext(first);
            first.setPrevious(first);
        }
        else{
            CircularNode<E> last=first.getPrevious();
            nodo.setNext(first);
            first.setPrevious(nodo);
            last.setNext(nodo);
            nodo.setPrevious(last);
            first=nodo;
        }
        return true;
    }
    
    public CircularNode<E> getFirst() {
        return first;
    }
    
    @Override
    public boolean addLast(E element) {
        CircularNode<E> nodo = new CircularNode<>(element);
        if(element==null)
            return false;
        else if(isEmpty()){
            first=nodo;
            first.setNext(first);
            first.setPrevious(first);
            
        }
        else{
            CircularNode<E> last=first.getPrevious();
            nodo.setNext(first);
            first.setPrevious(nodo);
            nodo.setPrevious(last);
            last.setNext(nodo);
        }
        return true;
    }

    
    @Override
    public boolean isEmpty() {
        return this.first==null;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        else if(first.getNext()==first){
            E tmp=first.getContent();
            first=null;
            return tmp;  
        }
        else{
            E tmp=first.getContent();
            CircularNode<E> last=first.getPrevious();
            first=first.getNext();
            first.setPrevious(last);
            last.setNext(first);
            return tmp;
        }
        
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            return null;
        }
        else if(first.getNext()==first){
            E tmp=first.getContent();
            first=null;
            return tmp;  
        }
        else{ 
            CircularNode<E> last=first.getPrevious();
            E tmp=last.getContent();
            last=last.getPrevious();
            first.setPrevious(last);
            last.setNext(first);
            return tmp;
        }
        
    }

    @Override
    public int size() {
        int counter=0;
        CircularNode<E> t=first;
        if(first!=null){
            
            do{
               counter++;
               t=t.getNext();
               
            
            }
            while(t!=first);
        }
        return counter;
     }

    @Override
    public void clear() {
        first=null;
    }

    @Override
    public void add(int index, E element) {
        if(index==0 && !isEmpty()){
            this.addFirst(element);
        }
        else if(!isEmpty() && element!=null && index<=size()){
            CircularNode<E> node= new CircularNode(element);
            int counter=0;
            CircularNode<E> pointer=first;
            while(index>counter){
                if(pointer.getNext()!=null){
                    pointer=pointer.getNext();
                }
                counter++;
            }
            if(counter==index){
                node.setPrevious(pointer.getPrevious());
                node.setNext(pointer);
                pointer.getPrevious().setNext(node);
                pointer.setPrevious(node);
            }
        }      
    }

    @Override
    public E remove(int index) {
        if(this.isEmpty()){
            return null;
        }
        if(index==0){
            E content=first.getContent();
            this.removeFirst();
            return content;
        }
        else if(!isEmpty() || index<=size()){
            int counter=0;
            CircularNode<E> node=this.first;
            CircularNode<E> previous=this.first;
            
            while(counter!=index){
                previous=node;
                node=node.getNext();
                counter++;
            }
            E content=node.getContent();
            previous.setNext(node.getNext());
            node.getNext().setPrevious(previous);
            return content;
        }
        return null;
    }

    @Override
    public E get(int index) {
        if(!isEmpty() && index==0){
            return first.getContent();
        }
        else if(!isEmpty() && index<=size()){
            CircularNode<E> pointer=first;
            int counter=0;
            while(counter!=index){
                pointer=pointer.getNext();
                counter++;
            }
            return pointer.getContent();
        }
        return null;
    }

    @Override
    public E set(int index, E element) {
        if(!isEmpty() && index==0){
            E content=first.getContent();
            first.setContent(element);
            return content;
        }
        else if(!isEmpty() && index<=size() && element!=null){
            CircularNode<E> pointer=first;
            int counter=0;
            while(counter!=index){
                pointer=pointer.getNext();
                counter++;
            }
            E content=pointer.getContent();
            pointer.setContent(element);
            return content;
        }
        return null;
    }
    
    
    public String toString() {
        String s = "";
        CircularNode<E> t=first;
        if(first!=null){
            
            do{
               s += t.getContent() + " ";
               t=t.getNext();
               
            
            }
            while(t!=first);
        }
        return s;
    }
    
    


    public CircularDoublyLinkedList<E> findSame(Comparator<E> cmp,E anotherElement){
        CircularDoublyLinkedList<E> result=new CircularDoublyLinkedList<E>();
        
        for(int i=0;i<this.size();i++){
            if(cmp.compare(this.get(i), anotherElement)==0){
                result.addLast(this.get(i));           
            }            
        }    
        return result;
    }
    
    public CircularDoublyLinkedList<E> findSmall(Comparator<E> cmp,E anotherElement){
        CircularDoublyLinkedList<E> result=new CircularDoublyLinkedList<E>();
        
        for(int i=0;i<this.size();i++){
            if(cmp.compare(this.get(i), anotherElement)>0){
                result.addLast(this.get(i));           
            }            
        }    
        return result;
    }
    
    public CircularDoublyLinkedList<E> findBig(Comparator<E> cmp,E anotherElement){
        CircularDoublyLinkedList<E> result=new CircularDoublyLinkedList<E>();
        
        for(int i=0;i<this.size();i++){
            if(cmp.compare(this.get(i), anotherElement)<0){
                result.addLast(this.get(i));           
            }            
        }    
        return result;
    }
    
    
    
    public Iterator<E> iterator(){
        Iterator<E> it = new Iterator<E>() {
            CircularNode<E> pointer = first;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public E next() {
                //Se recupera a E
                E content = pointer.getContent();
                //Se avanza el pointer
                pointer = pointer.getNext();
                return content;
            }
        };
        return it;
    }
    public Iterator<E> reversedIterator(){
        Iterator<E> it = new Iterator<E>() {
            CircularNode<E> pointer = first;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public E next() {
                //Se recupera a E
                E content = pointer.getContent();
                //Se avanza el pointer
                pointer = pointer.getPrevious();
                return content;
            }
        };
        return it;
    }
    
}

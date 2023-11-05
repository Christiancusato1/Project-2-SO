/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

import so2_project.Personajes;

/**
 *
 * @author giovannacianfaglione
 */
public class Queue<T> {
    
    private int size; 
    private Node pFirst; 
    private Node pLast; 

    public Queue() {
        this.size = 0; 
        this.pFirst = null; 
        this.pLast = null; 
    }
    
    
    public Node peek(){
        return this.pFirst; 
    }
    
    public boolean isEmpty(){
        return this.pFirst == null; 
    }
    
    public void enqueue(T data){// encolar (meter en la lista)
        // recordar hay que insertar al final de la cola
       Node newNode = new Node(data); 
       if(this.isEmpty()){
           this.pFirst = newNode; 
           this.pLast = newNode; 
           this.size ++; 
       }else{
           Node aux = this.pLast; 
           aux.setpNext(newNode);
           this.pLast = newNode; 
           this.size ++; 
       }
        
    }
    
    public Node dequeue(){// para eliminar un nodo en la cola
        // recordar se tiene que eliminar el primer nodo de la cola siempre
        Node aux = this.pFirst; 
        if(!this.isEmpty()){
            this.pFirst = this.pFirst.getpNext(); 
            this.size --; 
        }if(this.size == 0){
            this.pLast = null;
            this.pFirst = null; 
        }

        return aux; 
    }
    
    public void print(){
        if(this.isEmpty()){
            System.out.println("La cola está vacía!");
        }else{
            for (int i = 0; i < this.size; i++) {
                Node aux = this.peek(); 
                System.out.println(aux.getData());
                this.dequeue(); 
                this.enqueue((T)aux.getData());
            }
        }
    }

    
    public Queue makeCopy(){
        Queue copy = new Queue(); 
        if(!this.isEmpty()){
            for (int i = 0; i < this.size; i++) {
                Node aux = this.peek(); 
                copy.enqueue((Personajes)aux.getData());
                this.dequeue(); 
                this.enqueue((T)aux.getData());
            }
        }
        return copy; 
    }
    
    
    public void reverse(){// es de forma recursiva 
        if(!this.isEmpty()){
            Node aux = this.peek(); 
            this.dequeue(); 
            this.reverse(); 
            this.enqueue((T)aux.getData());
        }
        
    }
    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the pFirst
     */
    public Node getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(Node pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the pLast
     */
    public Node getpLast() {
        return pLast;
    }

    /**
     * @param pLast the pLast to set
     */
    public void setpLast(Node pLast) {
        this.pLast = pLast;
    }
    
    
    
    
}

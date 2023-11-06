/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

import Estructuras.Node;
import Estructuras.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import static so2_project.Main.admin;
import static so2_project.Main.w;

/**
 *
 * @author giovannacianfaglione
 * 
 */
public class Admin extends Thread{
    
    Main main = new Main(); 
    private Queue cola1Nintendo = new Queue(); 
    private Queue cola2Nintendo  = new Queue(); 
    private Queue cola3Nintendo  = new Queue(); 
    private Queue colaRefuerzoNintendo  = new Queue(); 
    private Queue cola1Capcon  = new Queue(); 
    private Queue cola2Capcon  = new Queue();
    private Queue cola3Capcon  = new Queue(); 
    private Queue colaRefuerzoCapcon  = new Queue();  
    private int contadorNintendo = 0; // ES PARA EL ID
    private int contadorCapcom = 0; // ES PARA EL ID
    private Queue ganadoresNintendo = new Queue();
    private Queue ganadoresCapcom = new Queue(); 
    public Semaphore mutex = new Semaphore(1); 
//    private String edo;
    
    
    
    
    @Override
    public void run(){
        while(true){
           
            
            try {
                
                main.admin.actualizar();
//                main.w.colaCapcom1.setText(main.admin.printCola(main.admin.getCola1Capcon()));
//                main.w.colaCapcom2.setText(main.admin.printCola(main.admin.getCola2Capcon()));
//                main.w.colaCapcom3.setText(main.admin.printCola(main.admin.getCola3Capcon()));
//                main.w.colaCapcomRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoCapcon()));
//
//                main.w.colaNintendo1.setText(main.admin.printCola(main.admin.getCola1Nintendo()));
//                main.w.colaNintendo2.setText(main.admin.printCola(main.admin.getCola2Nintendo()));
//                main.w.colaNintendo3.setText(main.admin.printCola(main.admin.getCola3Nintendo()));
//                main.w.colaNintendoRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoNintendo()));

                
                
                sleep(main.ia.getDayDuration()*2000);
                this.generatePersonajes();
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           
        
        
        
//       
        
//        try {
////            this.crearPersonajes(); // crea los primeros juegos 
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
     }
    
    public void actualizar() throws InterruptedException{
        
                main.w.colaCapcomRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoCapcon()));
                main.w.colaCapcom1.setText(main.admin.printCola(main.admin.getCola1Capcon()));
                main.w.colaCapcom2.setText(main.admin.printCola(main.admin.getCola2Capcon()));
                main.w.colaCapcom3.setText(main.admin.printCola(main.admin.getCola3Capcon()));
                main.w.colaCapcomRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoCapcon()));

                main.w.colaNintendoRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoNintendo()));
                main.w.colaNintendo1.setText(main.admin.printCola(main.admin.getCola1Nintendo()));
                main.w.colaNintendo2.setText(main.admin.printCola(main.admin.getCola2Nintendo()));
                main.w.colaNintendo3.setText(main.admin.printCola(main.admin.getCola3Nintendo()));
//                main.w.colaNintendoRefuerzo.setText(main.admin.printCola(main.admin.getColaRefuerzoNintendo()));
                
//                main.w.ganadorCapcom.setText(main.admin.printCola(main.admin.getGanadoresCapcom()));
//                main.w.ganadorNintendo.setText(main.admin.printCola(main.admin.getGanadoresNintendo()));
                
//                main.w.ganadorCapcom.setText(main.admin.printGanadores(main.admin.getGanadoresCapcom()));
//                main.w.ganadorNintendo.setText(main.admin.printGanadores(main.admin.getGanadoresNintendo()));

                
//                this.edo="Esperando";
//                this.colaRefuerzoSubir();
//                main.w.doing.setText(edo);
                
                
                
                
    }
    
    
    public void colaRefuerzoSubir() throws InterruptedException{
        Random random = new Random(); 
        double randomValue = random.nextDouble();
        
        if(randomValue<0.4){
            main.admin.mutex.acquire();
            if(!main.admin.colaRefuerzoCapcon.isEmpty()){
                Node aux=  main.admin.colaRefuerzoCapcon.dequeue();
                main.admin.cola1Capcon.enqueue(aux.getData());
            }
            
            if (!main.admin.colaRefuerzoNintendo.isEmpty()) {
                 Node aux1=  main.admin.colaRefuerzoNintendo.dequeue();
                main.admin.colaRefuerzoNintendo.enqueue(aux1.getData());
                main.admin.mutex.release();
                
            }
            
            
           
        }
       
        
    }
// public Personajes(int id, String empresa)
    
     public String printCola(Queue cola){
        String p = ""; 
        Node aux1 = cola.peek();
        String empresa = ""; 
        if(aux1 != null){
        empresa = ((Personajes)aux1.getData()).getEmpresa();} 
        String charToAdd = ""; 
        if(aux1 != null){
        if(empresa.equals("Nintendo")){
            charToAdd = "Z"; 
        }else{
            charToAdd = "S"; 
        }}
        
        if(cola.isEmpty()){
            System.out.println("La cola está vacía!");
        }else{
            for (int i = 0; i < cola.getSize(); i++) {
                Node aux = cola.peek(); 
                
                p += charToAdd+((Personajes)aux.getData()).getId() + "->";
               cola.dequeue();  
                cola.enqueue(((Personajes)aux.getData()));
            }
        }return p; 
    }
    
     
//     public String printGanadores(Queue cola){
//        
//        String p= "";
//      
//        if(cola.isEmpty()){
//            System.out.println("La cola está vacía!");
//        }else{
//            for (int i = 0; i < cola.getSize(); i++) {
//                Node aux = cola.peek(); 
//                
//                p += ((Personajes)aux.getData()).getId() + "->";
//               cola.dequeue();  
//                cola.enqueue(((Personajes)aux.getData()));
//            }
//        }return p; 
//    }
//     public void cambiarPrioridad(){
////         if(main.ia.)
//     }
    
    public void crearPersonajes() throws InterruptedException{
        System.out.println("FALGGGGG 1");
        //nintendo
        for (int i = 0; i < 10; i++) {
            Personajes p = new Personajes(getContadorNintendo(), "Nintendo"); 
//            this.contadorNintendo ++; 
            System.out.println(p.getNombre());
            System.out.println(p.getEmpresa());
            System.out.println(p.getPrioridad());
            main.admin.mutex.acquire(); 
            if (p.getPrioridad() == 1) {
                main.admin.getCola1Nintendo().enqueue(p);
            }else if(p.getPrioridad() == 2){
                main.admin.getCola2Nintendo().enqueue(p);
            }else{
                main.admin.getCola3Nintendo().enqueue(p); 
            }
            main.admin.mutex.release();
            main.admin.setContadorNintendo(this.getContadorNintendo() + 1); 
            
        }
        
        
        // Capcom 
         for (int i = 0; i < 10; i++) {
            Personajes pe = new Personajes(getContadorCapcom(), "Capcom");
//            this.contadorCapcom++; 
            main.admin.mutex.acquire();
            if (pe.getPrioridad() == 1) {
                main.admin.getCola1Capcon().enqueue(pe);
            }else if(pe.getPrioridad() == 2){
                main.admin.getCola2Capcon().enqueue(pe);
            }else{
                main.admin.getCola3Capcon().enqueue(pe); 
            }
            main.admin.mutex.release();
            main.admin.setContadorCapcom(this.getContadorCapcom() + 1); 
            
        }
//         mutex.release();
         // ahora revisamos si alguna lista esta vacia: 
         // capcom: 
//         mutex.acquire();
          System.out.println("COLA 1 NINTENDO: ");
         System.out.println(this.cola1Nintendo.getSize());
          System.out.println("COLA 2 NINTENDO: ");
         System.out.println(this.cola2Nintendo.getSize());
          System.out.println("COLA 3 NINTENDO: ");
         System.out.println(this.cola3Nintendo.getSize());
         
         System.out.println("COLA 1 CAPCOM: ");
         System.out.println(this.cola1Capcon.getSize());
          System.out.println("COLA 2 capcom: ");
         System.out.println(this.cola2Capcon.getSize());
          System.out.println("COLA 3 capcom: ");
         System.out.println(this.cola3Capcon.getSize());
       
                main.admin.mutex.acquire();
         this.setContadorCapcom(this.revisarColasVacias(getCola1Capcon(), 1, getContadorCapcom(), "Capcom"));
         this.setContadorCapcom(this.revisarColasVacias(getCola2Capcon(), 2, getContadorCapcom(), "Capcom"));
         this.setContadorCapcom(this.revisarColasVacias(getCola3Capcon(), 3, getContadorCapcom(), "Capcom"));
         
         // nintendo
         this.setContadorNintendo(this.revisarColasVacias(getCola1Nintendo(), 1, getContadorNintendo(), "Nintendo")); 
         this.setContadorNintendo(this.revisarColasVacias(getCola2Nintendo(), 2, getContadorNintendo(), "Nintendo")); 
         this.setContadorNintendo(this.revisarColasVacias(getCola3Nintendo(), 3, getContadorNintendo(), "Nintendo")); 
                 main.admin.mutex.release();
         System.out.println("DESPUES DE LLENAR: ");
         System.out.println("COLA 1 NINTENDO: ");
         System.out.println(this.cola1Nintendo.getSize());
          System.out.println("COLA 2 NINTENDO: ");
         System.out.println(this.cola2Nintendo.getSize());
          System.out.println("COLA 3 NINTENDO: ");
         System.out.println(this.cola3Nintendo.getSize());
          System.out.println("COLA 1 CAPCOM: ");
         System.out.println(this.cola1Capcon.getSize());
          System.out.println("COLA 2 capcom: ");
         System.out.println(this.cola2Capcon.getSize());
          System.out.println("COLA 3 capcom: ");
         System.out.println(this.cola3Capcon.getSize());
       
         

        
    }
    
    public int revisarColasVacias(Queue cola, int prioridad, int contadorEmpresa, String empresa) throws InterruptedException{
//      mutex.acquire();
        System.out.println("FLAGGGG 3!");
        if(cola.isEmpty()){

            for (int i = 0; i < 5; i++) {
                Personajes p = new Personajes(contadorEmpresa, empresa); 
                p.setPrioridad(prioridad);
                cola.enqueue(p);
                contadorEmpresa ++; 
                System.out.println("COLA QUE ENTRA PARA LLENAR: ");
                System.out.println(prioridad);
                System.out.println(empresa);
                System.out.println("CONTADOR EMPRESA: ");
                System.out.println(contadorEmpresa);
            }
//            mutex.release();
        }return contadorEmpresa; 
    }
    
    public Torneo getTorneo() throws InterruptedException{
        Torneo torneo = new Torneo();
        main.admin.mutex.acquire();
        System.out.println("Coloa capcom antes");
        System.out.println(admin.printCola(admin.cola1Capcon));

        while(torneo.getpCapcom() == null){
        if (!this.getCola1Capcon().isEmpty()) {
            torneo.setpCapcom((Personajes)this.getCola1Capcon().dequeue().getData());
        System.out.println("Coloa capcom Despues de quitar");
        System.out.println(admin.printCola(admin.cola1Capcon));
        }else if (!this.getCola2Capcon().isEmpty()) {
            torneo.setpCapcom((Personajes)this.getCola2Capcon().dequeue().getData());
        System.out.println("Coloa capcom Despues de quitar");
        System.out.println(admin.printCola(admin.cola1Capcon));
        }else if(!this.getCola3Capcon().isEmpty()){
            torneo.setpCapcom((Personajes)this.getCola3Capcon().dequeue().getData());
        System.out.println("Coloa capcom Despues de quitar");
        System.out.println(admin.printCola(admin.cola1Capcon));
        }}
        
        while(torneo.getpNintendo()== null){
        if (!this.getCola1Nintendo().isEmpty()) {
            torneo.setpNintendo((Personajes)this.getCola1Nintendo().dequeue().getData());
           
        }else if(!this.getCola1Nintendo().isEmpty()){
           torneo.setpNintendo((Personajes)this.getCola2Nintendo().dequeue().getData()); 
        }else if(!this.getCola3Nintendo().isEmpty()){
            torneo.setpNintendo((Personajes)this.getCola3Nintendo().dequeue().getData());
        }}
        
        
        main.admin.mutex.release();
         return torneo; 
    }
 
    public void generatePersonajes() throws InterruptedException{
        Random random = new Random(); 
        double randomValue = random.nextDouble();
        if(randomValue<0.8){
            Personajes p = new Personajes(main.admin.getContadorNintendo(), "Nintendo"); 
            main.admin.mutex.acquire();
            if(p.getPrioridad() == 1 ){
                if (main.admin.getCola1Nintendo().getpLast() != null) {
                    if (((Personajes)main.admin.getCola1Nintendo().getpLast().getData()).getId() != p.getId()) {
                       main.admin.getCola1Nintendo().enqueue(p); 
                    }
                }else{
                    main.admin.getCola1Nintendo().enqueue(p);
                }
//                if (((Personajes)main.admin.getCola1Nintendo().getpLast().getData()).getId() != p.getId() && main.admin.getCola1Nintendo().getpLast() != null) {
//                  main.admin.getCola1Nintendo().enqueue(p); 
//                }
//                main.admin.getCola1Nintendo().enqueue(p);
            }else if(p.getPrioridad() == 2){
                if (main.admin.getCola2Nintendo().getpLast() != null) {
                    if (((Personajes)main.admin.getCola2Nintendo().getpLast().getData()).getId() != p.getId()) {
                        main.admin.getCola2Nintendo().enqueue(p);
                    }
                }else{
                    main.admin.getCola2Nintendo().enqueue(p);
                }
                
//                if (((Personajes)main.admin.getCola2Nintendo().getpLast().getData()).getId() != p.getId() && main.admin.getCola2Nintendo().getpLast() != null) {
//                  main.admin.getCola2Nintendo().enqueue(p);
//                }
//                main.admin.getCola2Nintendo().enqueue(p);
            }else{
                if (main.admin.getCola3Nintendo().getpLast() != null) {
                    if (((Personajes)main.admin.getCola3Nintendo().getpLast().getData()).getId() != p.getId()) {
                        main.admin.getCola3Nintendo().enqueue(p);
                    }
                }else{
                    main.admin.getCola3Nintendo().enqueue(p);
                }
//                if (((Personajes)main.admin.getCola3Nintendo().getpLast().getData()).getId() != p.getId() && main.admin.getCola3Nintendo().getpLast() != null) {
//                   main.admin.getCola3Nintendo().enqueue(p);
//                }
//                main.admin.getCola3Nintendo().enqueue(p);
            }
//            main.admin.mutex.release();
            int a = main.admin.getContadorNintendo(); 
            main.admin.setContadorNintendo(a + 1); 
            Personajes p2 = new Personajes(main.admin.getContadorCapcom(), "Capcom"); 
//            main.admin.mutex.acquire();
            if(p2.getPrioridad() ==1){
                if(main.admin.getCola1Capcon().getpLast() != null){
                  if (((Personajes)main.admin.getCola1Capcon().getpLast().getData()).getId() != p2.getId()){
                     main.admin.getCola1Capcon().enqueue(p2);   
                  }  
                }else{
                      main.admin.getCola1Capcon().enqueue(p2); 
                  }
//                if (((Personajes)main.admin.getCola1Capcon().getpLast().getData()).getId() != p2.getId() && main.admin.getCola1Capcon().getpLast() != null ) {
//                  main.admin.getCola1Capcon().enqueue(p2);  
//                }
                 
            }else if(p2.getPrioridad() == 2){
                if(main.admin.getCola2Capcon().getpLast() != null){
                    if (((Personajes)main.admin.getCola2Capcon().getpLast().getData()).getId() != p2.getId()){
                      main.admin.getCola2Capcon().enqueue(p2);   
                    }
                }else{
                        main.admin.getCola2Capcon().enqueue(p2); 
                    }
//                if (((Personajes)main.admin.getCola2Capcon().getpLast().getData()).getId() != p2.getId() && main.admin.getCola2Capcon().getpLast() != null) {
//                  main.admin.getCola2Capcon().enqueue(p2);  
//                }
//                main.admin.getCola2Capcon().enqueue(p2); 
            }else{
                if(main.admin.getCola3Capcon().getpLast() != null){
                    if(((Personajes)main.admin.getCola3Capcon().getpLast().getData()).getId() != p2.getId()){
                       main.admin.getCola3Capcon().enqueue(p2);  
                    }
//                    
                }else{
                        main.admin.getCola3Capcon().enqueue(p2); 
                    }
//                if (((Personajes)main.admin.getCola3Capcon().getpLast().getData()).getId() != p2.getId() && main.admin.getCola3Capcon().getpLast() != null) {
//                  main.admin.getCola3Capcon().enqueue(p2);  
//                }
//                main.admin.getCola3Capcon().enqueue(p2); 
            }
            main.admin.mutex.release(); 
            int b = main.admin.getContadorCapcom(); 
            main.admin.setContadorCapcom(b + 1); 

        }

    }
    
    
    
    /**
     * @return the cola1Nintendo
     */
    public Queue getCola1Nintendo() {
        return cola1Nintendo;
    }

    /**
     * @param cola1Nintendo the cola1Nintendo to set
     */
    public void setCola1Nintendo(Queue cola1Nintendo) {
        this.cola1Nintendo = cola1Nintendo;
    }

    /**
     * @return the cola2Nintendo
     */
    public Queue getCola2Nintendo() {
        return cola2Nintendo;
    }

    /**
     * @param cola2Nintendo the cola2Nintendo to set
     */
    public void setCola2Nintendo(Queue cola2Nintendo) {
        this.cola2Nintendo = cola2Nintendo;
    }

    /**
     * @return the cola3Nintendo
     */
    public Queue getCola3Nintendo() {
        return cola3Nintendo;
    }

    /**
     * @param cola3Nintendo the cola3Nintendo to set
     */
    public void setCola3Nintendo(Queue cola3Nintendo) {
        this.cola3Nintendo = cola3Nintendo;
    }

    /**
     * @return the colaRefuerzoNintendo
     */
    public Queue getColaRefuerzoNintendo() {
        return colaRefuerzoNintendo;
    }

    /**
     * @param colaRefuerzoNintendo the colaRefuerzoNintendo to set
     */
    public void setColaRefuerzoNintendo(Queue colaRefuerzoNintendo) {
        this.colaRefuerzoNintendo = colaRefuerzoNintendo;
    }

    /**
     * @return the cola1Capcon
     */
    public Queue getCola1Capcon() {
        return cola1Capcon;
    }

    /**
     * @param cola1Capcon the cola1Capcon to set
     */
    public void setCola1Capcon(Queue cola1Capcon) {
        this.cola1Capcon = cola1Capcon;
    }

    /**
     * @return the cola2Capcon
     */
    public Queue getCola2Capcon() {
        return cola2Capcon;
    }

    /**
     * @param cola2Capcon the cola2Capcon to set
     */
    public void setCola2Capcon(Queue cola2Capcon) {
        this.cola2Capcon = cola2Capcon;
    }

    /**
     * @return the cola3Capcon
     */
    public Queue getCola3Capcon() {
        return cola3Capcon;
    }

    /**
     * @param cola3Capcon the cola3Capcon to set
     */
    public void setCola3Capcon(Queue cola3Capcon) {
        this.cola3Capcon = cola3Capcon;
    }

    /**
     * @return the colaRefuerzoCapcon
     */
    public Queue getColaRefuerzoCapcon() {
        return colaRefuerzoCapcon;
    }

    /**
     * @param colaRefuerzoCapcon the colaRefuerzoCapcon to set
     */
    public void setColaRefuerzoCapcon(Queue colaRefuerzoCapcon) {
        this.colaRefuerzoCapcon = colaRefuerzoCapcon;
    }

    /**
     * @return the contadorNintendo
     */
    public int getContadorNintendo() {
        return contadorNintendo;
    }

    /**
     * @param contadorNintendo the contadorNintendo to set
     */
    public void setContadorNintendo(int contadorNintendo) {
        this.contadorNintendo = contadorNintendo;
    }

    /**
     * @return the contadorCapcom
     */
    public int getContadorCapcom() {
        return contadorCapcom;
    }

    /**
     * @param contadorCapcom the contadorCapcom to set
     */
    public void setContadorCapcom(int contadorCapcom) {
        this.contadorCapcom = contadorCapcom;
    }

    /**
     * @return the ganadoresNintendo
     */
    public Queue getGanadoresNintendo() {
        return ganadoresNintendo;
    }

    /**
     * @param ganadoresNintendo the ganadoresNintendo to set
     */
    public void setGanadoresNintendo(Queue ganadoresNintendo) {
        this.ganadoresNintendo = ganadoresNintendo;
    }

    /**
     * @return the ganadoresCapcom
     */
    public Queue getGanadoresCapcom() {
        return ganadoresCapcom;
    }

    /**
     * @param ganadoresCapcom the ganadoresCapcom to set
     */
    public void setGanadoresCapcom(Queue ganadoresCapcom) {
        this.ganadoresCapcom = ganadoresCapcom;
    }


    
    
}

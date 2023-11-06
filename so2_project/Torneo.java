/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

/**
 *
 * @author giovannacianfaglione
 */
public class Torneo {
    private Personajes pNintendo;
    private Personajes pCapcom;
    private boolean ganadores;
    private Personajes ganador;
//    private admin admin

    public Torneo() {
    }
            
    
    
//    public void crearTorneo(){
//        String tipoDeTorneo; 
//        Torneo torneo = main.admin.getTorneo(); 
//        this.contadorParejas ++; 
//         Random random = new Random(); 
//        double randomValue = random.nextDouble();
//        if(randomValue <0.4){
//            tipoDeTorneo = "ganadores"; 
//            
//            
//        }
//        
//    }
    
    

    public Personajes getpNintendo() {
        return pNintendo;
    }

    public void setpNintendo(Personajes pNintendo) {
        this.pNintendo = pNintendo;
    }

    public Personajes getpCapcom() {
        return pCapcom;
    }

    public void setpCapcom(Personajes pCapcom) {
        this.pCapcom = pCapcom;
    }
    
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package so2_project;

import Interfaz.VideoStart;
import Interfaz.Window;

/**
 *
 * @author giovannacianfaglione
 */
public final class Main {
    public static Admin admin= new Admin();
    public static IA ia= new IA(3);
    public static Window w;
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
//        VideoStart v= new VideoStart();
//        v.setVisible(true);
        w  = new Window(); 
        w.setVisible(true); 
        

//        System.out.println("hola");

//        Window c = new Window();
//        c.setVisible(true); 
        

        admin.crearPersonajes();
        w.colaCapcom1.setText(admin.printCola(admin.getCola1Capcon()));
        w.colaCapcom2.setText(admin.printCola(admin.getCola2Capcon()));
        w.colaCapcom3.setText(admin.printCola(admin.getCola3Capcon()));
        w.colaCapcomRefuerzo.setText(admin.printCola(admin.getColaRefuerzoCapcon()));
        
        w.colaNintendo1.setText(admin.printCola(admin.getCola1Nintendo()));
        w.colaNintendo2.setText(admin.printCola(admin.getCola2Nintendo()));
        w.colaNintendo3.setText(admin.printCola(admin.getCola3Nintendo()));
        w.colaNintendoRefuerzo.setText(admin.printCola(admin.getColaRefuerzoNintendo()));
        
        ia.start();
        admin.start();
//        
        
        
//        System.out.println("cree");
//        System.out.println(admin.getCola1Capcon().getpFirst().getData());
        
//       
        
        // TODO code application logic here
    }
    
}

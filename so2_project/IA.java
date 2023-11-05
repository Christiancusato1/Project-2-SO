/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

import Estructuras.Node;
import Estructuras.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

//import static so2_project.Main.w;
/**
 *
 * @author giovannacianfaglione
 */
public class IA extends Thread {

    private int contadorParejas = 0;
    private Main main = new Main();
    private int dayDuration;
    private int contadorTorneos = 1;
    private String edo;

    public IA(int dayDuration) {
        this.dayDuration = dayDuration;

    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Torneo torneo = main.ia.finalizarTorneo();

                ImageIcon imageIconCapcom = new ImageIcon(torneo.getpCapcom().getPathImage());
                ImageIcon imageIconNintendo = new ImageIcon(torneo.getpNintendo().getPathImage());
                getMain().w.PeleaCapcom.setIcon(imageIconCapcom);
                getMain().w.idPersonajeCapcom.setText("S" + torneo.getpCapcom().getId());
                getMain().w.nombrePersonajeCapcom.setText(torneo.getpCapcom().getNombre());

                getMain().w.PeleaNintendo.setIcon(imageIconNintendo);
                getMain().w.idPersonajeNintendo.setText("Z" + torneo.getpNintendo().getId());
                getMain().w.nombrePersonajeNintendo.setText(torneo.getpNintendo().getNombre());

                this.checkCounter(); //si ya pasaron 2 parejas el genera una pareja de jugadore snintendo y capcom
//            this.finalizarTorneo(); //para selccionar al ganador o empateo o no aplica 
                main.ia.sumadorCOntadoresPersonajes();
                main.ia.contadorTorneos++;

//            if(main.ia.contadorTorneos == 8){
////                main.ia.subirPrioridades(); 
//                main.ia.contadorTorneos = 1; 
//            }
//                main.w.capcomWL.setIcon(null);
//                main.w.nintendoWL.setIcon(null);
                ImageIcon mentira = new ImageIcon("sr//Imagenes//trofeo.png");
                ImageIcon falso = new ImageIcon("src//Imagenes//Sworrd.png");
       
                this.edo = "Esperando";
                main.w.doing.setText(edo);
                main.w.nintendoWL.setIcon(mentira);
                main.w.capcomWL.setIcon(mentira);
                main.w.poderCapcom.setIcon(falso);
                main.w.poderNintendo.setIcon(falso);
                
                
                
                sleep(main.ia.getDayDuration() * 700);

            } catch (InterruptedException ex) {
                Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void checkCounter() throws InterruptedException {
        if (getContadorParejas() == 2) {
            getMain().admin.generatePersonajes();
            this.setContadorParejas(0);
        }
    }

    public String crearTipoDeTorneo() {
        String tipoDeTorneo;
        this.setContadorParejas(this.getContadorParejas() + 1);
        Random random = new Random();
        double randomValue = random.nextDouble();
        if (randomValue < 0.4) {
            tipoDeTorneo = "ganadores";

        } else if (randomValue < 0.67) {
            tipoDeTorneo = "empate";
        } else {
            tipoDeTorneo = "refuerzo";

        }
        return tipoDeTorneo;

    }

    public Torneo finalizarTorneo() throws InterruptedException {
        
//        this.edo = "Esperando";
//        main.w.doing.setText(edo);
        Torneo torneo = main.admin.getTorneo();
        String tipoDeTorneo = this.crearTipoDeTorneo();
        
//        poderCapcom

        ImageIcon imageGanador = new ImageIcon("src//Imagenes//trofeo.png");
        ImageIcon imageKO = new ImageIcon("src//Imagenes//ko.png");
        ImageIcon empate = new ImageIcon("src//Imagenes//guantes.png");
        ImageIcon refuerzos = new ImageIcon("src//Imagenes//pesas.png");
        
        ImageIcon poder1 = new ImageIcon("src//Imagenes//Haduken.png");
        ImageIcon poder2 = new ImageIcon("src//Imagenes//Sword.png");
        ImageIcon poder3 = new ImageIcon("src//Imagenes//TriFuerza.png");
        ImageIcon poder4 = new ImageIcon("src//Imagenes//shoryuken.png");
        main.admin.mutex.acquire();
        if (tipoDeTorneo.equals("ganadores")) {
//             sleep(main.ia.getDayDuration()*300);
            torneo.getpNintendo().updatePower();
            torneo.getpCapcom().updatePower();
            this.edo = "Decidiendo";
            main.w.doing.setText(edo);
            sleep(main.ia.getDayDuration() * 200);
            this.edo = "Ganador";
            main.w.doing.setText(edo);
            
            

            if (torneo.getpNintendo().getPower().equals("Master Sword") && torneo.getpCapcom().getPower().equals("Shoryuken")) {
                
                torneo.getpNintendo().setGanador(true);
                main.w.poderNintendo.setIcon(poder2);
                main.w.nintendoWL.setIcon(imageGanador);
                
              
                torneo.getpCapcom().setGanador(false);
                main.w.capcomWL.setIcon(imageKO);
                main.w.poderCapcom.setIcon(poder4);
                main.admin.getGanadoresNintendo().enqueue(torneo.getpNintendo());
                
                main.w.ganadorCapcom.setText(main.admin.printCola(main.admin.getGanadoresCapcom()));
                main.w.ganadorNintendo.setText(main.admin.printCola(main.admin.getGanadoresNintendo()));
                
                main.w.cantGanadoresCapcom.setText(Integer.toString(main.admin.getGanadoresCapcom().getSize()));
                main.w.cantGanadoresNintendo.setText(Integer.toString(main.admin.getGanadoresNintendo().getSize()));
                

            } else if (torneo.getpNintendo().getPower().equals("Tri Fuerza") && torneo.getpCapcom().getPower().equals("Haduken")) {
                
                torneo.getpNintendo().setGanador(true);
                main.w.nintendoWL.setIcon(imageGanador);
                main.w.poderNintendo.setIcon(poder3);
                
                torneo.getpCapcom().setGanador(false);
                main.w.capcomWL.setIcon(imageKO);
                main.w.poderCapcom.setIcon(poder1);
                main.admin.getGanadoresNintendo().enqueue(torneo.getpNintendo());
                
                main.w.ganadorCapcom.setText(main.admin.printCola(main.admin.getGanadoresCapcom()));
                main.w.ganadorNintendo.setText(main.admin.printCola(main.admin.getGanadoresNintendo()));
                
                main.w.cantGanadoresCapcom.setText(Integer.toString(main.admin.getGanadoresCapcom().getSize()));
                main.w.cantGanadoresNintendo.setText(Integer.toString(main.admin.getGanadoresNintendo().getSize()));
                
            } else if (torneo.getpNintendo().getPower().equals("Tri Fuerza") && torneo.getpCapcom().getPower().equals("Shoryuken")) {
                
                torneo.getpNintendo().setGanador(false);
                main.w.nintendoWL.setIcon(imageKO);
                main.w.poderNintendo.setIcon(poder3);
                
                torneo.getpCapcom().setGanador(true);
                main.w.capcomWL.setIcon(imageGanador);
                main.w.poderCapcom.setIcon(poder4);
                main.admin.getGanadoresCapcom().enqueue(torneo.getpCapcom());
                
                main.w.ganadorCapcom.setText(main.admin.printCola(main.admin.getGanadoresCapcom()));
                main.w.ganadorNintendo.setText(main.admin.printCola(main.admin.getGanadoresNintendo()));
                
                main.w.cantGanadoresCapcom.setText(Integer.toString(main.admin.getGanadoresCapcom().getSize()));
                main.w.cantGanadoresNintendo.setText(Integer.toString(main.admin.getGanadoresNintendo().getSize()));
                
            } else if (torneo.getpNintendo().getPower().equals("Master Sword") && torneo.getpCapcom().getPower().equals("Haduken")) {
                
                torneo.getpNintendo().setGanador(false);
                main.w.nintendoWL.setIcon(imageKO);
                main.w.poderNintendo.setIcon(poder2);
                
                torneo.getpCapcom().setGanador(true);
                main.w.capcomWL.setIcon(imageGanador);
                main.w.poderCapcom.setIcon(poder1);
                main.admin.getGanadoresCapcom().enqueue(torneo.getpCapcom());
                
                main.w.ganadorCapcom.setText(main.admin.printCola(main.admin.getGanadoresCapcom()));
                main.w.ganadorNintendo.setText(main.admin.printCola(main.admin.getGanadoresNintendo()));
                
                main.w.cantGanadoresCapcom.setText(Integer.toString(main.admin.getGanadoresCapcom().getSize()));
                main.w.cantGanadoresNintendo.setText(Integer.toString(main.admin.getGanadoresNintendo().getSize()));
                
                
               
                
            }
            sleep(main.ia.getDayDuration() * 200);

//                private String[] powersCapcom = {"Haduken","Shoryuken"};
//    private String[] powersNintendo= {"Master Sword","Tri Fuerza"};
        } else if (tipoDeTorneo.equals("empate")) {
            torneo.getpCapcom().setPrioridad(1);
            
            main.admin.getCola1Capcon().enqueue(torneo.getpCapcom());
//             torneo.getpCapcom().setPrioridad(1);
            torneo.getpNintendo().setPrioridad(1);
            main.admin.getCola1Nintendo().enqueue(torneo.getpNintendo());
//             torneo.getpNintendo().setPrioridad(1);
             this.edo = "Decidiendo";
            main.w.doing.setText(edo);
            sleep(main.ia.getDayDuration() * 200);
            main.w.nintendoWL.setIcon(empate);
            main.w.capcomWL.setIcon(empate);
            
            this.edo = "Empate";
            main.w.doing.setText(edo);
            sleep(main.ia.getDayDuration() * 200);
             
        } else {
            main.admin.getColaRefuerzoCapcon().enqueue(torneo.getpCapcom());
            main.admin.getColaRefuerzoNintendo().enqueue(torneo.getpNintendo());
             this.edo = "Decidiendo";
            main.w.doing.setText(edo);
            sleep(main.ia.getDayDuration() * 200);
            
            main.w.nintendoWL.setIcon(refuerzos);
            main.w.capcomWL.setIcon(refuerzos);
            this.edo = "Refuerzo";
            main.w.doing.setText(edo);
            sleep(main.ia.getDayDuration() * 200);

        }
        
        main.admin.mutex.release();
        return torneo;
    }

    public void sumadorCOntadoresPersonajes() {
        Node aux;
        for (int i = 0; i < main.admin.getCola2Capcon().getSize(); i++) {
            aux = main.admin.getCola2Capcon().getpFirst();
            ((Personajes) aux.getData()).setContadorPrioridad(((Personajes) aux.getData()).getContadorPrioridad() + 1);
            if (((Personajes) aux.getData()).getContadorPrioridad() == 8) {
                ((Personajes) aux.getData()).setContadorPrioridad(0);
                main.admin.getCola1Capcon().enqueue(aux.getData());

                main.admin.getCola2Capcon().dequeue();

            } else {
                main.admin.getCola2Capcon().dequeue();
                main.admin.getCola2Capcon().enqueue(aux.getData());
            }
        }
        for (int i = 0; i < main.admin.getCola3Capcon().getSize(); i++) {
            aux = main.admin.getCola3Capcon().getpFirst();
            ((Personajes) aux.getData()).setContadorPrioridad(((Personajes) aux.getData()).getContadorPrioridad() + 1);
            if (((Personajes) aux.getData()).getContadorPrioridad() == 8) {
                ((Personajes) aux.getData()).setContadorPrioridad(0);
                main.admin.getCola2Capcon().enqueue(aux.getData());

                main.admin.getCola3Capcon().dequeue();

            } else {
                main.admin.getCola3Capcon().dequeue();
                main.admin.getCola3Capcon().enqueue(aux.getData());
            }
        }
        Node aux2;
        for (int i = 0; i < main.admin.getCola2Nintendo().getSize(); i++) {
            aux2 = main.admin.getCola2Nintendo().getpFirst();
            ((Personajes) aux2.getData()).setContadorPrioridad(((Personajes) aux2.getData()).getContadorPrioridad() + 1);
            if (((Personajes) aux2.getData()).getContadorPrioridad() == 8) {
                ((Personajes) aux2.getData()).setContadorPrioridad(0);
                main.admin.getCola1Nintendo().enqueue(aux2.getData());

                main.admin.getCola2Nintendo().dequeue();

            } else {
                main.admin.getCola2Nintendo().dequeue();
                main.admin.getCola2Nintendo().enqueue(aux2.getData());
            }
        }
        for (int i = 0; i < main.admin.getCola3Nintendo().getSize(); i++) {
            aux2 = main.admin.getCola3Nintendo().getpFirst();
            ((Personajes) aux2.getData()).setContadorPrioridad(((Personajes) aux2.getData()).getContadorPrioridad() + 1);
            if (((Personajes) aux2.getData()).getContadorPrioridad() == 8) {
                ((Personajes) aux2.getData()).setContadorPrioridad(0);
                main.admin.getCola2Nintendo().enqueue(aux2.getData());

                main.admin.getCola3Nintendo().dequeue();

            } else {
                main.admin.getCola3Nintendo().dequeue();
                main.admin.getCola3Nintendo().enqueue(aux2.getData());
            }
        }

    }

//     public Queue subirPrioridadColas(Queue colaPrioridad, Queue colaAuxiliar) throws InterruptedException{
//         
//         if (!colaPrioridad.isEmpty()) {
//             Node aux = colaPrioridad.getpFirst(); 
//             for (int i = 0; i < colaPrioridad.getSize(); i++) {
////                 ((Personajes)aux.getData()).subirPrioridad(); 
//                 colaAuxiliar.enqueue(aux.getData());
//                 colaPrioridad.dequeue(); 
////                 colaAuxiliar.enqueue(aux.getData());
////                 aux = aux.getpNext(); 
//             
//         }
//           
//         }return colaAuxiliar; 
//     }
//         
//     
//     public void cambiarDeCola(Queue auxiliar, Queue cola1, Queue cola2) throws InterruptedException{
//         
//         Node aux = auxiliar.getpFirst(); 
//         for (int i = 0; i < auxiliar.getSize(); i++) {
//             if (((Personajes)aux.getData()).getPrioridad() == 1) {
//                 cola1.enqueue(aux.getData());
//                 auxiliar.dequeue(); 
//             }else{
//                 cola2.enqueue(aux.getData());
//                 auxiliar.dequeue(); 
//             }
//         }
//       
//     }
//     
//     
//     public void cambiarDeCVerdadera(Queue auxiliarCapcom, Queue auxiliarNintendo){
//         if(!auxiliarCapcom.isEmpty()){
//          Node aux = auxiliarCapcom.getpFirst(); 
//         for (int i = 0; i < auxiliarCapcom.getSize(); i++) {
//             if (((Personajes)aux.getData()).getPrioridad() == 1) {
//                 main.admin.getCola1Capcon().enqueue(aux.getData());
//                 auxiliarCapcom.dequeue(); 
//             }else{
//                 main.admin.getCola2Capcon().enqueue(aux.getData());
//                 auxiliarCapcom.dequeue();
//             }
//         }}
//         
//         if(!auxiliarNintendo.isEmpty()){
//          Node aux2 = auxiliarNintendo.getpFirst(); 
//         for (int i = 0; i < auxiliarCapcom.getSize(); i++) {
//             if (((Personajes)aux2.getData()).getPrioridad() == 1) {
//                 main.admin.getCola1Nintendo().enqueue(aux2.getData());
//                 auxiliarNintendo.dequeue(); 
//             }else{
//                 main.admin.getCola2Nintendo().enqueue(aux2.getData());
//                 auxiliarNintendo.dequeue(); 
//             }
//         }}
//         
//     }
//     
//     
//       
//         public void subirPrioridades() throws InterruptedException{
//             getMain().admin.mutex.acquire();
//             Queue colaAuxiliarCapcon = new Queue(); 
//              Queue colaAuxiliarNintendo = new Queue();
//              
//              
//              
//             colaAuxiliarCapcon = main.ia.subirPrioridadColas(getMain().admin.getCola2Capcon(), colaAuxiliarCapcon);
//             colaAuxiliarCapcon = main.ia.subirPrioridadColas(getMain().admin.getCola3Capcon(), colaAuxiliarCapcon);
////             main.ia.cambiarDeCola(colaAuxiliarCapcon, getMain().admin.getCola1Capcon(), getMain().admin.getCola2Capcon());
//             
//             
//             
//             
//             colaAuxiliarNintendo = main.ia.subirPrioridadColas(getMain().admin.getCola2Nintendo(), colaAuxiliarNintendo);
//             colaAuxiliarNintendo = main.ia.subirPrioridadColas(getMain().admin.getCola3Nintendo(), colaAuxiliarNintendo);
////             main.ia.cambiarDeCola(colaAuxiliarNintendo, getMain().admin.getCola1Nintendo(), getMain().admin.getCola2Nintendo());
//
//            main.ia.cambiarDeCVerdadera(colaAuxiliarCapcon, colaAuxiliarNintendo);
//             getMain().admin.mutex.release();
//         }
// 
    /**
     * @return the contadorParejas
     */
    public int getContadorParejas() {
        return contadorParejas;
    }

    /**
     * @param contadorParejas the contadorParejas to set
     */
    public void setContadorParejas(int contadorParejas) {
        this.contadorParejas = contadorParejas;
    }

    /**
     * @return the contadorTorneos
     */
    public int getContadorTorneos() {
        return contadorTorneos;
    }

    /**
     * @param contadorTorneos the contadorTorneos to set
     */
    public void setContadorTorneos(int contadorTorneos) {
        this.contadorTorneos = contadorTorneos;
    }

}

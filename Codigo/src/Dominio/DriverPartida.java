package Dominio;

import java.util.Scanner;

import ClasesExtra.*;

/**
 *
 * @author Joan
 */
public class DriverPartida {

    public static void main(String[] args) {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Problema prueba = new Problema();
        CtrlProblemas cp = new CtrlProblemas();
        while (estado != fin) {
            System.out.println("Partida:");
            System.out.println("1. Jugar");
            System.out.println("2. Exit");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    Partida partida = new Partida();
                    char color;
                    System.out.println("Ha elegido: Jugar");
                    System.out.println("Lista de los problemas registrados:");
                    cp.printProblemas();
                    System.out.println("Introduzca el índice del problema que desea jugar: ");
                    int problema = sc.nextInt();
                    sc.nextLine();
                    Pair <String, Integer> o = cp.seleccionProblema(problema);
                    Problema p = new Problema(o.getKey(), o.getValue());
                    p.setNumMovimientos(o.getValue());
                    System.out.println(o.getValue());
                    p.fenToMatrix(o.getKey());
                    p.printTablero();
                    System.out.println("Introduzca opción que desea jugar: ");
                    System.out.println("1. JugadorVsJugador"); 
                    System.out.println("2. JugadorVsMaquina1");
                    System.out.println("3. Maquina1VsMaquina1");
                    System.out.println("4. Maquina1VsMaquina2");
                    System.out.println("5. Maquina2VSMaquina2");
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    switch (opcion) {
                        case 1:
                            System.out.println("JugadorVsJugador");
                            Usuario us1 = new Usuario();
                            Usuario us2 = new Usuario();
                            System.out.println("Jugador1, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                us1 = new Usuario(false,"Jose","password");
                                us2 = new Usuario(true,"Maria","password");
                            } else if (color == 'b') {
                                us1 = new Usuario(true,"Jose","password");
                                us2 = new Usuario(false,"Maria","password");
                            } else {
                                System.out.println("Error, vuelva a intentarlo");
                                break;
                            }
                            partida = new Partida(us1, us2, p);
                            String c;
                            if (us2.getColor()) {
                                c = "blancas.";
                            } else {
                                c = "negras";
                            }
                            System.out.println("Partida creada, jugador2 juega con " + c);
                            partida.playJugadores();
                            break;
                        case 2:
                            System.out.println("JugadorVsMaquina1");
                            Usuario usjm = new Usuario();
                            Maquina m = new Maquina();
                            System.out.println("Jugador, ¿color? (n/b)");
                            color = sc.next().charAt(0);
                            sc.nextLine();
                            if (color == 'n') {
                                usjm = new Usuario(false,"Jose","password");
                                m = new Maquina(true, "m1",1,p.getNumMovimientos());
                            } else if (color == 'b') {
                                usjm = new Usuario(true,"Jose","password");
                                m = new Maquina(false,"m1", 1, p.getNumMovimientos());
                            } else {
                                System.out.println("Error, vuelva a intentarlo");
                                break;
                            }
                            partida = new Partida(usjm, m, p);
                            String c2;
                            if (usjm.getColor()) {
                                c2 = "blancas.";
                            } else {
                                c2 = "negras";
                            }
                            System.out.println("Partida creada, Jugador1 juega con " + c2);
                            partida.playJugadorVSMaquina();
                            break;
                        case 3: 
                            System.out.println("Maquina1VsMaquina1");
                            Maquina m1 = new Maquina(true,"m1",1,p.getNumMovimientos());
                            Maquina m2 = new Maquina(false,"m2",1,p.getNumMovimientos());
                            System.out.println("Color de cada maquina escogido aleatoriamente");
                            partida = new Partida(m1, m2, p);
                            System.out.println("Partida creada correctamente");
                            partida.playMaquinaVSMaquina(true);
                            System.out.println("Añadir otro problema? (y/n)");
                            char k = sc.next().charAt(0);
                            sc.nextLine();
                            boolean masProblems = false;
                            if (k == 'y') masProblems = true;
                            while (masProblems) {
                                cp.printProblemas();
                                System.out.println("Introduzca el índice del problema que desea jugar: ");
                                int problemaK = sc.nextInt();
                                sc.nextLine();
                                Pair <String, Integer> oK = cp.seleccionProblema(problemaK);
                                Problema pK = new Problema(oK.getKey(), oK.getValue());
                                pK.setNumMovimientos(oK.getValue());
                                
                                
                                pK.fenToMatrix(oK.getKey());
                                pK.printTablero();
                                System.out.println("Maquina1VsMaquina1");
                                Maquina m1K = new Maquina(true,"m1",1,pK.getNumMovimientos());
                                Maquina m2K = new Maquina(false,"m2",1,pK.getNumMovimientos());
                                System.out.println("Color de cada maquina escogido aleatoriamente");
                                Partida partidaK = new Partida(m1K, m2K, pK);
                                System.out.println("Partida creada correctamente");
                                partidaK.playMaquinaVSMaquina(true);
                                System.out.println("Añadir otro problema? (y/n)");
                                char K = sc.next().charAt(0);
                                sc.nextLine();
                                if (k == 'y') masProblems = true;
                                else masProblems = false;
                            }
                            break;
                        case 4: 
                            System.out.println("Maquina1VsMaquina2");
                            System.out.println("No implementado aún; segunda entrega");
                            break;
                        case 5: 
                            System.out.println("Maquina2VsMaquina2");
                            System.out.println("No implementado aún; segunda entrega");
                            break;
                    }
                    break;
                case 2:
                    fin = 2;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }
    }
}

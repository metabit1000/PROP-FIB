package Dominio;

import ClasesExtra.*;
import Dominio.fichas.Ficha;
import java.util.Scanner;
import java.lang.System; //para nanotime()

/**
 *
 * @author Joan
 */
public class Partida {
    private Problema p = new Problema();
    private long time;
    private Jugador player1;
    private Jugador player2;
    private boolean turno;

    public Partida() {}
    
    public Partida(Usuario j1,Usuario j2,Problema p) {
        //creadora de partida para dos jugadores usuarios
        player1 = j1;
        player2 = j2;
        this.p = p;
    }
    
    public Partida(Usuario j1,Maquina m,Problema  p) {
        //creadora de partida para dos jugadores: un usuario y una maquina
        player1 = j1;
        player2 = m ; 
        this.p = p;
    }
    
    public Partida(Maquina m1, Maquina m2, Problema p) {
        //creadora de partida para dos jugadores maquina
        player1 = m1;
        player2 = m2;
        this.p = p;
    }
    
    public void playJugadores() {
        //juegan dos jugadores usuario, para ello, cada vez que el jugador con color igual al color al que empieza el problema mueve una ficha correctamente, 
        //incrementa cont (se cuenta como un movimiento gastado dentro de numero de movimientos de dicho problema). Si el jugador no logra ganar al otro jugador 
        //con ese numero de movimientos, se considerará que no ha ganado la partida, en caso contrario, ganará la partida y se subirá al ranking 
        //junto con el tiempo empleado en cada uno de sus movimientos.
        int cont = 0;
        String coordenada1,coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        long t1 = 0;
        long t2 = 0;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        boolean pt = p.getTurno();
        while (cont < (p.getNumMovimientos()) && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            Pair <Coordenada,Coordenada> moves  = new Pair();
            if (turno){
                long startTime1 = System.nanoTime();    
                moves = player1.getNextMove(p);
                t1 += (System.nanoTime() - startTime1);
                if (turno == pt) ++cont;
            }
            else {
                long startTime2 = System.nanoTime();   
                moves = player2.getNextMove(p);
                t2 = (System.nanoTime() - startTime2);
                if (turno == pt) ++cont;
            }
            int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
            if (res == 0) {
                if (p.checkmate(turno)){
                    p.printTablero();
                    win = true;
                    long k;
                    if (turno) k = t1;
                    else k = t2;
                    System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                    String winner;
                    if (turno == player1.getColor()) winner = player1.getNombre();
                    else winner = player2.getNombre();
                    if (!p.existeix(winner)) p.introducirElemento(winner, k);
                    else p.actualizarRanking(player1.getNombre(), k);
                }
                turno = !turno;
            }
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }
    
    public void playJugadorVSMaquina() {
        //juega un usuario contra la maquina (minimax), para ello, cada vez que elusuario mueve una ficha correctamente, 
        //incrementa cont (se cuenta como un movimiento gastado dentro de numero de movimientos de dicho problema). Si el jugador no logra ganar al algoritmo
        //con ese numero de movimientos, se considerará que no ha ganado la partida, en caso contrario, ganará la partida y se subirá al ranking 
        //junto con el tiempo empleado en cada uno de sus movimientos.
        int cont = 0;
        String coordenada1,coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        long t1 = 0;
        long t2 = 0;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        boolean pt = p.getTurno();
        while (cont < p.getNumMovimientos() && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            p.printTablero();
            Pair <Coordenada,Coordenada> moves  = new Pair();
            boolean T = player2.getColor();
            if (player1.getColor()) {
                if (T == turno) {
                    moves = player2.getNextMove(p);
                    p.moveFicha(moves.getKey().coordToString(), moves.getValue().coordToString());
                    turno = !turno;
                }
                else {
                    if (turno) {
                        long startTime1 = System.nanoTime();
                        moves = player1.getNextMove(p);
                        t1 += (System.nanoTime() - startTime1);
                        if (turno == pt) ++cont;
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        //if (turno == pt) ++cont;
                        //System.out.println(cont);
                        if (p.checkmate(turno)){
                            p.printTablero();
                            win = true;
                            long k;
                            if (turno) k = t1;
                            else k = t2;
                            System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                            String winner;
                            if (turno == player1.getColor()) winner = player1.getNombre();
                            else winner = player2.getNombre();
                            if (!p.existeix(winner)) p.introducirElemento(winner, k);
                            else p.actualizarRanking(player1.getNombre(), k);
                        }
                        turno = !turno;
                    }
                }
            }
            else {
                if (T == !turno) {
                    if (!turno) {
                        long startTime1 = System.nanoTime();
                        moves = player1.getNextMove(p);
                        t1 += (System.nanoTime() - startTime1);
                        if (turno == pt) ++cont;
                    }
                    else moves = player2.getNextMove(p);
                    int res = mover(turno,moves.getKey().coordToString(),moves.getValue().coordToString());
                    if (res == 0) {
                        if (turno == pt) ++cont;
                        if (p.checkmate(turno)){
                            p.printTablero();
                            win = true;
                            long k;
                            if (turno) k = t2;
                            else k = t1;
                            System.out.println("Fin del juego. Ganan las "+t+" En un tiempo de "+k/1000000000+" segundos.");
                            p.actualizarRanking(player1.getNombre(), k);
                        }
                        turno = !turno;
                    }
                }
                else {
                    moves = player2.getNextMove(p);
                    p.moveFicha(moves.getKey().coordToString(), moves.getValue().coordToString());
                    if (turno == pt) ++cont;
                    turno = !turno;
                }
            }
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
    }
    
    public int playMaquinaVSMaquina(boolean validar) {
        //juegan dos maquinas, para ello, cada vez que la maquina con color igual al color al que empieza el problema mueve una ficha, 
        //incrementa cont (se cuenta como un movimiento gastado dentro de numero de movimientos de dicho problema). Si la maquina no logra ganar al otro jugador 
        //con ese numero de movimientos, se considerará que no ha ganado la partida, en caso contrario, ganará la partida
        int cont = 0;
        String coordenada1, coordenada2;
        Scanner sc = new Scanner(System.in);
        String c;
        boolean win = false;
        if (p.getTurno()) c = "blancas.";
        else c = "negras.";
        System.out.println("En este problema, empiezan las "+c);
        turno = p.getTurno();
        boolean pt = p.getTurno();
        int compare;
        if (!validar) compare = p.getNumMovimientos();
        else compare = 50;
        p.printTablero();
        while (cont < compare && !win) {
            String t;
            if (turno) t = "blancas.";
            else t = "negras.";
            System.out.println("El turno es de las "+ t);
            System.out.println("Por favor, haga su movimiento");
            Pair <Coordenada,Coordenada> moves  = new Pair();
            if (turno) {
                moves = player1.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }
                
            }
            else {
                moves = player2.getNextMove(p);
                if (turno == pt) {
                    ++cont;
                }
            }
            p.moveFicha(moves.getKey().coordToString(),moves.getValue().coordToString());
            if (p.checkmate(turno)){
                win = true;
                System.out.println("Fin del juego. Ganan las "+t);
            }
            p.printTablero();
            turno = !turno;
        }
        if (!win) {
            p.printTablero();
            System.out.println("Se ha excedido el número de movimientos del problema.");
        }
        return cont;
    }
    
    public int mover(boolean color,String cord1,String cord2 ){
        //para la ficha con color color (true = blanco, false = negro), en coordenada de origen cord1 y coordenada de destino cord2,
        //devuelve -1 si al mover esa ficha el jugador está en jaque o si esa posición es inválida. devolverá 0 en caso de ser una posicion correcta.
        Coordenada c1 = new Coordenada();
        c1.stringToCoord(cord1);
        Coordenada c2 = new Coordenada();
        c2.stringToCoord(cord2);
        Ficha o = p.getFicha(c2);
        if(color == p.getFicha(c1).getColor() && p.moveFicha(cord1,cord2)){
            if (p.mate(!p.getFicha(c2).getColor())) {
                System.out.println("Estás en jaque. Vuelve a intentarlo.");
                p.undoFicha(c2.coordToString(),c1.coordToString(),o);
                return -1;
            }
            else {
                return 0;
            }
        }
        else {
            System.out.println("Vuelva a intentarlo");
            return -1;
        }  
    }
}

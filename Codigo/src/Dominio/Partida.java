package Dominio;

import ClasesExtra.Coordenada;
import ClasesExtra.Pair;
import Dominio.fichas.Ficha;
import java.util.ArrayList;

/**
 *
 * @author Joan
 */
public class Partida {
    private Problema p = new Problema();
    private Jugador player1;
    private Jugador player2;

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

    public boolean getTurnoInicial() {
        return p.getTurno(); //turno inicial del problema
    }
    
    public int getNumMovimientos() {
        return p.getNumMovimientos();  //num movimientos del problema
    }
    
    public ArrayList<Coordenada> posiblesMovimientos(Coordenada c) {
        ArrayList<Coordenada> res = p.getFicha(c).posiblesMovimientos(p, c);
        return res;
    }
    
    public Problema getProblema() {
        return p;
    }
    
    public void setProblema(Problema p) {
        this.p = p;
    }
    
    public String getNombreJugador1() {
        return player1.getNombre();
    }
    
    public String getNombreJugador2() {
        return player2.getNombre();
    }
    
    public boolean checkMate(boolean turno) {
        return p.checkmate(turno);
    }
    
    public boolean getColor(Coordenada c) {
        return p.getFicha(c).getColor();
    }
    
    public void actualizarRanking(String nombre,double tiempo) {
        if (p.existeix(nombre)) p.actualizarRanking(nombre, tiempo);
        else p.introducirElemento(nombre,tiempo);
    }
    
    public int moverFicha(boolean color,Coordenada cordInicio,Coordenada cordFinal){ //color es el turno
        Ficha o = p.getFicha(cordFinal);
        int res = 0; //todo correcto
        if(color == p.getFicha(cordInicio).getColor() && p.moveFicha(cordInicio,cordFinal)){
            if (p.mate(!p.getFicha(cordFinal).getColor())) {
                p.undoFicha(cordFinal,cordInicio,o);
                res = -1; //"Estás en jaque. Vuelve a intentarlo."
            }
        } 
        else res = -2;  //No es tu turno
        return res;
    }
    
    public Pair<Coordenada,Coordenada> moverFichaMaquina() {
        Maquina m = (Maquina)player2;  //para corregir el error de que usuario no tiene getNextMove()
        Pair<Coordenada,Coordenada> res = m.getNextMove(p); 
        p.moveFicha(res.getKey(), res.getValue()); //lo aplico en dominio
        return res; //devuelvo el mejor movimiento para la capa de presentacion
    }
    
   
   /* 
    public int playMaquinaVSMaquina(boolean validar) {
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
    */
    
}

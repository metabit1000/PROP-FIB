package Dominio.fichas;

import Dominio.Problema;
import ClasesExtra.Coordenada;
import java.util.*;

/**
 *
 * @author Àlex
 */
public class DriverKnight {
    public void testConstructor() {}
    
    public void testPosiblesMovimientos() {}
    
    public static void main (String [] args) { 
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        Knight prueba = new Knight();
        char color;
        Problema p = new Problema();
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. PosiblesMovimientos");
            System.out.println("3. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    System.out.println("Ha elegido: Constructor");
                    System.out.println("Introduzca un color (negro /blanco): ");
                    color = sc.next().charAt(0);
                    sc.nextLine();
                    if (color == 'n') prueba = new Knight(false,'n');
                    else if (color == 'b') prueba = new Knight(true,'N'); 
                    else {
                        System.out.println("Error, vuelva a intentarlo");
                        break;
                    } 
                    System.out.println("Ficha creada correctamente");
                    break;
                case 2:
                    Integer x,y;
                    p = new Problema();
                    System.out.println("Ha elegido: PosiblesMovimientos");
                    try {
                        if (prueba.getColor()) System.out.println("Puede usar cualquier coordenada del tablero"); //hago que salte excepcion!
                    } catch (NullPointerException e) {
                        System.out.println("Debe crear antes la ficha que quiere introducir en el tablero");
                        break; //para que no siga el codigo
                    }
                    System.out.println("Introduzca una coordenada del tablero(x): ");
                    x = sc.nextInt();
                    System.out.println("Introduzca una coordenada del tablero(y): ");
                    y = sc.nextInt();
                    Coordenada c = new Coordenada(x,y);
                    try {
                        p.setFicha(c,prueba);
                        p.printTablero();
                    } catch (ArrayIndexOutOfBoundsException e2){
                        System.out.println("Coordenadas fuera del rango del tablero, vuelva a probar.");
                    }
                    ArrayList<Coordenada> res = prueba.posiblesMovimientos(p,c);
                    for(int i=0;i<res.size();i++) {
                        res.get(i).printxy();
                    }
                    break;
                case 3: 
                    fin = 3;
                    System.out.println("Gracias. Que tenga un buen día.");
                    break;
            }
        }

    }
}

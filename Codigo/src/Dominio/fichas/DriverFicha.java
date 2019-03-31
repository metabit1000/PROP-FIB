package Dominio.fichas;

import ClasesExtra.Coordenada;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Àlex
 */
public class DriverFicha {
    public void testConstructor() {}
    public void testGetCoordenada() {}
    public void testGetColor() {}
    public void testsetPosicion() {}
    //testPosiblesMovimientos en cada subclase
    public static void main (String [] args) throws IOException {
        int estado = 0;
        int fin = 1000; //por poner algo...
        Scanner sc = new Scanner(System.in);
        while (estado != fin) {
            System.out.println("Menú:");
            System.out.println("1. Constructor");
            System.out.println("2. GetPosicion");
            System.out.println("3. GetColor");
            System.out.println("4. SetColor");
            System.out.println("5. SetPosicion");
            System.out.println("6. Salir");
            System.out.println("Introduzca un número: ");
            estado = sc.nextInt();
            switch (estado) {
                case 1:
                    Boolean color;
                    String res;
                    int res2;
                    Coordenada c = new Coordenada();
                    System.out.println("Ha elegido: Constructor");
                    System.out.println("Introduzca un color (false = negro /blanco = true): ");
                    color = sc.nextBoolean();
                    System.out.println("Introduzca un coordenada(x): ");
                    res2 = sc.nextInt();
                    c.setX(res2);
                    System.out.println("Introduzca un coordenada(y): ");
                    res2 = sc.nextInt();
                    c.setY(res2);
                    Peon prueba = new Peon(color,c); //como ejemplo peon, por ser abstracta
                    System.out.println("Ficha creada correctamente");
                    break;
                case 2:
                    System.out.println("Ha elegido: GetPosicion");
                    Peon prueba2 = new Peon(false,new Coordenada(1,1));
                    Coordenada c2 = prueba2.getPosicion();
                    System.out.println("La posicion de una ficha peon cualquiera es: ");
                    c2.printxy();
                    break;
                case 3: 
                    System.out.println("Ha elegido: GetColor");
                    Peon prueba3 = new Peon(false,new Coordenada(1,1));
                    Boolean c3 = prueba3.getColor();
                    System.out.println("El color de una ficha cualquiera es: " + c3);
                    break;
                case 4:
                    System.out.println("Ha elegido: SetColor");
                    Peon prueba4 = new Peon(false,new Coordenada(1,1));
                    System.out.println("Introduzca un color (false = negro /blanco = true) : ");
                    Boolean c4 = sc.nextBoolean();
                    System.out.println("Se ha obtenido correctamente");
                    break;
                case 5:
                    System.out.println("Ha elegido: SetPosicion");
                    Peon prueba5 = new Peon(false,new Coordenada(1,1));
                    System.out.println("Introduzca un coordenada(x): ");
                    int res5 = sc.nextInt();
                    Coordenada c5 = new Coordenada();
                    c5.setX(res5);
                    System.out.println("Introduzca un coordenada(y): ");
                    int res6 = sc.nextInt();
                    c5.setY(res6);
                    prueba5.setPosicion(c5);
                    System.out.println("Se ha obtenido correctamente");
                    break;
                case 6: 
                    fin = 6;
                    System.out.println("Gracias por utilizar nuestros servicios. Que tenga un buen día.");
                    break;
            }
        }
    }
}

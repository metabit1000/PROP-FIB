package Dominio.fichas;

import ClasesExtra.Coordenada;
import Dominio.Problema;
import java.util.ArrayList;

/**
 *
 * @author Àlex
 */
public class Bishop extends Ficha{
    public Bishop() {}
    
    public Bishop(boolean color,char c) {
        super(color,c);
    }
    
    @Override
    public ArrayList<Coordenada> posiblesMovimientos(Problema p, Coordenada c) {
        ArrayList<Coordenada> res = new ArrayList();
        int x = c.getX();
        int y = c.getY();
        
        if (p.esValid(c) && p.getFicha(c) != null && p.getFicha(c).getColor() == color) {
            /* ARRIBA/IZQUIERDA (pensando en las blancas) */
            for (int i = 1; i <= 7; ++i) {
                c = new Coordenada(x - i,y - i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                            res.add(c);
                    }
            }
            /* ABAJO/IZQUIERDA */
            for (int i = 1; i <= 7; ++i) {
                c = new Coordenada(x + i,y - i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                            res.add(c);
                    }
            }
            /* ARRIBA/DERECHA */
            for (int i = 1; i <= 7; ++i) {
                c = new Coordenada(x + i,y + i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                            res.add(c);
                    }
            }
            /* ABAJO/DERECHA */
            for (int i = 1; i <= 7; ++i) {
                c = new Coordenada(x - i,y + i);
                    if (p.esValid(c)) {
                        if (p.getFicha(c) == null || (p.getFicha(c) != null && p.getFicha(c).getColor() != color))
                            res.add(c);
                    }
            }
        }
        return res;
    }  
}
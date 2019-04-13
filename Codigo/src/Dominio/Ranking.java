package Dominio;
import java.util.*;
/**
 *
 * @author Jordi
 */
public class Ranking {
    private  Map<String,Double> rank = new HashMap<>();
    
    public Ranking() {}
    
        public void imprimir(){
              Iterator iterator=rank.keySet().iterator();
              while(iterator.hasNext()){
                  Object key = iterator.next();
                  System.out.println("Clave : " + key + "  Valor : "+ rank.get(key));
              }
        }
        
     public void ordenar(){
         List<Map.Entry<String, Double>> list = new LinkedList<>(rank.entrySet());

        Collections.sort(list, (Map.Entry<String, Double> m1, Map.Entry<String, Double> m2) -> (m1.getValue()).compareTo(m2.getValue()));

        Map<String, Double> result = new LinkedHashMap<>();
        list.forEach((entry) -> {
            result.put(entry.getKey(), entry.getValue());
        });
        rank= result;
    
     }

    public void getclasificacion(){
        
        imprimir();
        
        
    }
    
    
    public void setelemento(String  nombre , double tiempo){
          rank.put(nombre , tiempo );
          ordenar();
    }
    
    public void eliminarUsuario(String  nombre ){
        rank.remove(nombre);
    }
    public void setactualizar(String nombre , double tiempo ){
        //comprobar que esté dentro de Rank
          Iterator iterator=rank.keySet().iterator();
           Object key = iterator.next();
           double n = rank.get(key);
            if (tiempo < n ){
              eliminarUsuario(nombre);
        setelemento(nombre ,tiempo);  
           
            }
       
    }
}
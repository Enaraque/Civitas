/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
/**
 *
 * @author enriquearaqueespinosa
 */
public class Tablero {
    private ArrayList<Casilla> casillas;
    private boolean porSalida;
    
    public Tablero() {
        casillas = new ArrayList<Casilla>();
        Casilla nueva = new Casilla("Salida");
        casillas.add(nueva);
        porSalida = false;
    }
    
    private boolean correcto(int numCasilla) {
        boolean ok = false;
        if (numCasilla < casillas.size()) {
            ok = true;
        }
        return ok;
    }
    
    boolean computarPasoPorSalida() {
        boolean aux = porSalida;
        porSalida = false;
        return aux;
    }
    
    void añadeCasilla(Casilla casilla) {
        casillas.add(casilla);
    }
    
    public Casilla getCasilla(int numCasilla) {
        Casilla c = null;
        if (correcto(numCasilla)) {
            c = casillas.get(numCasilla);
        }
        return c;
    }
    
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    } 
    
    int nuevaPosicion(int actual, int tirada) {
        int posicion = (actual+tirada) % casillas.size();
        if (posicion != actual + tirada) {
            porSalida = true;
        }
        return posicion;
    }
    
    public int getNumCasillas(){
        return casillas.size();
    }
    
    
}

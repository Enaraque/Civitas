/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author enriquearaqueespinosa & jorgeLopezRemacho
 */
public class MazoSorpresas {
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    
    private void init() {
        sorpresas = new ArrayList<Sorpresa>();
        barajada = false;
        usadas = 0;
    } 
    
    MazoSorpresas(boolean depurar) {
        debug = depurar;
        init();
        if (debug) {
            Diario.getInstance().ocurreEvento("Se ha activado el modo depuración del mazo de sorpresas.");
        }
    }
    
    MazoSorpresas() {
        init();
        debug = false;
    }
    
    void alMazo(Sorpresa s) {
        if (!barajada) {
            sorpresas.add(s);
        }
    }
    
    Sorpresa siguiente() {
        if (!barajada || usadas == sorpresas.size()) {
            usadas = 0;
            barajada = true;
            if (!debug) {
                Collections.shuffle(sorpresas);
            }     
        }
        usadas++;
        Sorpresa carta = sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(carta);
        
        return carta;
    }
    
    //Añadidas adicionalmente
    Sorpresa getSorpresa( int get) {
        return sorpresas.get(get);
    }
    
    int getSize() {
        return sorpresas.size();
    }
    
            
}

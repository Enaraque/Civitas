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
public class CasillaSorpresa extends Casilla{
    private MazoSorpresas mazo;
    
    public CasillaSorpresa() {
        super("Sin nombre.");
        this.mazo = new MazoSorpresas();
    }
    
    public CasillaSorpresa(String unNombre, MazoSorpresas mazo) {
        super(unNombre);
        this.mazo = mazo;
    }
    
    @Override
    public String toString() {
        return  ("SORPRESA");
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        this.recibeJugador_sorpresa(actual, todos);
    }
    
    void recibeJugador_sorpresa(int actual, ArrayList<Jugador> todos){
        Sorpresa sorpresa = this.mazo.siguiente();
        Diario.getInstance().ocurreEvento("El jugador "+todos.get(actual).getNombre()
                +" ha caido en la casilla " + this.toString());
        sorpresa.aplicarAJugador(actual, todos);
    }
}

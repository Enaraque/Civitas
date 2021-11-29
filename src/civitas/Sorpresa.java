/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author jorgeLopezRemacho
 */

public abstract class Sorpresa {

    /**
    * Aplica esta carta sorpresa a algun jugador.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);    
    
    /**
    * Añade a Diario eventos asociados con la aplicacion
    * de cartas sorpresa.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    abstract void informe(int actual, ArrayList<Jugador> todos);
    
    /**
    * Devuelve informacion sobre la sorpresa.
    * En este caso, su seccion de texto.
    *
    * @return String con info legible de la Sorpresa.
    */
    abstract public String toString();
}

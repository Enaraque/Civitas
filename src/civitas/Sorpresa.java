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

public class Sorpresa {
    
    TipoSorpresa tipo;
    private String texto;
    private int valor;
    
    /**
    * Constructor de instancias de la clase Sorpresa.
    * 
    * @param tipo Tipo de sorpresa.
    * @param texto Texto que describe la accion de la carta Sorpresa.
    * @param valor Cantidad de dinero que afecta la accion.
    */
    Sorpresa(TipoSorpresa tipo, String texto, int valor){
        this.tipo = tipo;
        this.texto = texto;
        this.valor = valor;
    }
    
    /**
    * Aplica esta carta sorpresa a algun jugador.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(this.tipo == TipoSorpresa.PAGARCOBRAR){
            aplicarAJugador_pagarCobrar(actual,todos);
        }
        else{
            aplicarAJugador_porCasaHotel(actual,todos);
        }
    }
    
    /**
    * Aplica sobre un jugador Sorpresas del tipo pagar/cobrar.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        todos.get(actual).modificaSaldo(this.valor);
    }
    
    /**
    * Aplica sobre un jugador Sorpresas del tipo porCasaHotel.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        todos.get(actual).modificaSaldo(this.valor*(todos.get(actual).cantidadCasasHoteles()));
    }
    
    /**
    * Añade a Diario eventos asociados con la aplicacion
    * de cartas sorpresa.
    * 
    * @param actual Indice del jugador sobre el cual se aplica.
    * @param todos ArrayList con los jugadores de la partida.
    */
    private void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando la siguiente sorpresa a "
                +todos.get(actual).toString()+": "+this.toString());
    }
    
    /**
    * Devuelve informacion sobre la sorpresa.
    * En este caso, su seccion de texto.
    *
    * @return String con info legible de la Sorpresa.
    */
    public String toString(){
        return texto;
    }
}

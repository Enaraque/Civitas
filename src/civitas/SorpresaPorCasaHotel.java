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
public class SorpresaPorCasaHotel extends Sorpresa{
    private int valor;
    
    public SorpresaPorCasaHotel(String texto, int valor){
        super(texto);
        this.valor = valor;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        aplicarAJugador_porCasaHotel(actual,todos);
    }
    
    private void aplicarAJugador_porCasaHotel(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        todos.get(actual).modificaSaldo(this.valor*(todos.get(actual).cantidadCasasHoteles()));
    }
    
    @Override
    public String toString(){
        return texto;
    }
}

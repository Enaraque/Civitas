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
    private String texto;
    private int valor;
    
    SorpresaPorCasaHotel(String texto, int valor) {
        this.texto = texto;
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
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Aplicando la siguiente sorpresa a "
                +todos.get(actual).toString()+": "+this.toString());
    }
    
    @Override
    public String toString(){
        return texto;
    }
}

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
public class SorpresaPagarCobrar extends Sorpresa{
    private String texto;
    private int valor;
    
    SorpresaPagarCobrar(String texto, int valor) {
        this.texto = texto;
        this.valor = valor;
    }
    
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        aplicarAJugador_pagarCobrar(actual,todos);
    }
    
    private void aplicarAJugador_pagarCobrar(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        todos.get(actual).modificaSaldo(this.valor);
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

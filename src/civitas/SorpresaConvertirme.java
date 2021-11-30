/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author jorgelerre
 */
public class SorpresaConvertirme extends Sorpresa{

    public SorpresaConvertirme(String texto){
        super(texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        aplicarAJugador_convertirme(actual,todos);
    }
    
    private void aplicarAJugador_convertirme(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        JugadorEspeculador especulador = todos.get(actual).convertir();
        todos.set(actual, especulador);
    }
    
    
    @Override
    public String toString(){
        return texto;
    }
}



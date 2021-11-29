/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;


/**
 *
 * @author enriquearaqueespinosa & jorgeLopezRemacho
 */
public class Casilla {   
    private String nombre;

    public Casilla (String unNombre) {
        this.nombre = unNombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento(todos.get(actual).getNombre()+" ha caido en la casilla: "+ this.toString());
    }
    
    public String toString() {
        return  (nombre);
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);   
    }
}
    


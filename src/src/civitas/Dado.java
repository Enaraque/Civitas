/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.Random;

/**
 *
 * @author jorgeLopezRemacho
 */
class Dado {
  static final private Dado instance = new Dado();
  
  static final private int ULTIMORESULTADOINICIAL = 0;
  static final private int VALORDADOMINIMO = 1;
  static final private int VALORDADOMAXIMO = 6;
  
  private Random random;
  private int ultimo_resultado;
  private boolean debug;
  
  static public Dado getInstance() {
    return instance;
  }
  
  private Dado () {
    random = new Random();
    ultimo_resultado = ULTIMORESULTADOINICIAL;
    debug=false;
  }
  
  int tirar(){
    
    
    if(debug){
      ultimo_resultado = VALORDADOMINIMO;
    }
    else{
      //nextInt puede generar ceros, por lo que necesitamos evitar dicho caso sumando 1 al final.
      ultimo_resultado = random.nextInt(VALORDADOMAXIMO) + VALORDADOMINIMO;
    }
    
    return ultimo_resultado;
  }
  
  int quienEmpieza (int n){
    //Como trabajamos con ArrayList, el valor generado sera entre el jugador 0 y el n-1.
    return random.nextInt(n);
  }
  
  void setDebug(boolean d){
    debug = d;
    if(debug){
        Diario.getInstance().ocurreEvento("Modo de depuración del dado activado.");
    }
    else{
        Diario.getInstance().ocurreEvento("Modo de depuración del dado desactivado.");
    }
  }
  
  int getUltimoResultado(){
    return ultimo_resultado;
  }
  
}
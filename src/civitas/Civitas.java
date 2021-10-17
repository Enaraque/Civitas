/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

/**
 *
 * @author jorgeLopezRemacho
 */
public class Civitas {

    static void tirarDado(int n_tiradas, boolean modo){
        int[] recuento_dado = {0,0,0,0,0,0};
        
        Dado.getInstance().setDebug(modo);
        for(int i=0; i < n_tiradas; i++){
            recuento_dado[Dado.getInstance().tirar()-1]++;  //-1 para ajustar los valores (1-6) al vector (0-5)
        }
        
        System.out.println("Resultados de "+n_tiradas+" tiradas con debug = "+modo );
        for(int i=0; i<6; i++){
            System.out.println((i+1)+"->"+recuento_dado[i]+" veces.");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Comprobacion 1: Metodo quienEmpieza()
        System.out.println("\nComprobacion 1: Metodo \"quienEmpieza\" de la clase Dado.");
        int[] recuento = {0,0,0,0};
        int n_tiradas = 100;
        for (int i = 0; i < n_tiradas; i++){
            recuento[Dado.getInstance().quienEmpieza(4)]++;
        }
        System.out.println("Resultados de "+n_tiradas+" tiradas del metodo quienEmpieza:");
        for(int i=0; i<4; i++){
            System.out.println("Jugador "+(i+1)+"->"+recuento[i]+" veces.");
        }
        
        //Comprobacion 2: Dado
        System.out.println("\nComprobacion 2.1: Dado sin debug.");
        tirarDado(600, false);
        System.out.println("\nComprobacion 2.2: Dado con debug.");
        tirarDado(100, true);

        //Comprobacion 3: getUltimoResultado() de Dado.
        System.out.println("\nComprobacion 3: getUltimoResultado() de Dado.");
        
        int ultima_tirada;
        Dado.getInstance().setDebug(false);
        
        System.out.println("Tirando dado...");
        Dado.getInstance().tirar();
        ultima_tirada = Dado.getInstance().getUltimoResultado();
        System.out.println("Valor de la ultima tirada:"+ultima_tirada+".");
        
        //Comprobacion 4: Tipos enumerados
        System.out.println("\nComprobacion 4: Mostrar tipos enumerados.");
        
        EstadoJuego ej = EstadoJuego.INICIO_TURNO;
        TipoCasilla tc = TipoCasilla.CALLE;
        TipoSorpresa ts = TipoSorpresa.PAGARCOBRAR;
        
        System.out.println(ej+"\t"+tc+"\t"+ts);
        
        //Comprobacion 5: Clase Tablero y Clase Casilla
        
        System.out.println("\nComprobacion 5: Pruebas de Tablero y Casilla.");
        Tablero t = new Tablero();
        
        t.añadeCasilla(new Casilla("Gran Via de Colon", 400, 200, 100));
        t.añadeCasilla(new Casilla("Carretera de Cordoba", 100, 50, 25));
        t.añadeCasilla(new Casilla("Paseo de los Tristes", 375, 200, 125));
        t.añadeCasilla(new Casilla("Camino de Ronda", 200, 100, 50));
        t.añadeCasilla(new Casilla("Pedro Antonio de Alarcon", 300, 150, 75));
        t.añadeCasilla(new Casilla("Paseo del Salon", 500, 225, 110));
        t.añadeCasilla(new Casilla("Avenida de Andalucia", 150, 75, 35));
        t.añadeCasilla(new Casilla("Avenida de la Constitucion", 450, 225, 110));
        
        for(int i=0; i < t.getNumCasillas(); i++){
            System.out.println(t.getCasilla(i).toString());
        }
        
        //Comprobacion 6: Pruebas sobre getters de Casilla.
        
        System.out.println("\nComprobacion 6: Pruebas sobre getters de Casilla.");
        int calle_mas_cara = 1;
        int calle_mas_barata = 1;   //La salida es la casilla 0, y vamos a ignorarla para realizar este calculo.
        float suma_total = 0.0f;
        
        
        for(int i=2; i < t.getNumCasillas(); i++){
            if(t.getCasilla(i).getPrecioCompra()<t.getCasilla(calle_mas_barata).getPrecioCompra()){
                calle_mas_barata = i;
            }
            
            if(t.getCasilla(i).getPrecioCompra()>t.getCasilla(calle_mas_cara).getPrecioCompra()){
                calle_mas_cara = i;
            }
            
            suma_total += t.getCasilla(i).getPrecioCompra();
        }
        
        System.out.println("La casilla mas cara es "+t.getCasilla(calle_mas_cara).getNombre()+".");
        System.out.println("La casilla mas barata es "+t.getCasilla(calle_mas_barata).getNombre()+".");
        System.out.println("El precio de la casilla media es de "+(suma_total/t.getNumCasillas())+".");
        
        //Comprobacion 7: Clase Diario
        System.out.println("\nComprobacion 7: Clase Diario.");
        Diario.getInstance().ocurreEvento("Leyendo eventos registrados en el Diario.");
        while(Diario.getInstance().eventosPendientes()){
            System.out.println(Diario.getInstance().leerEvento());
        }
        
        //Comprobacion 8: Tiradas en el tablero
        System.out.println("\nComprobacion 8: Tiradas en el tablero.");
        System.out.println("Numero de casillas en el tablero = "+t.getNumCasillas());
        
        int casilla_actual = 0;
        
        System.out.println("Posicion de comienzo= "+casilla_actual);
        
        for(int i = 1; i <= 10; i++){
            casilla_actual = t.nuevaPosicion(casilla_actual, Dado.getInstance().tirar());
            System.out.println("Tirada "+i+"="+Dado.getInstance().getUltimoResultado()+".\tPosicion "+i+"="+casilla_actual+".");
        }

        //Esto es una prueba
    }
    
}
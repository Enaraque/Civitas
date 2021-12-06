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
public class CasillaCalle extends Casilla{
    static float FACTORALQUILERCALLE = 1.0f;
    static float FACTORALQUILERCASA = 1.0f;
    static float FACTORALQUILERHOTEL = 4.0f;
    static Jugador PROPIETARIONULO = null;
    
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    private Jugador Propietario;
    
    public CasillaCalle() {
        super("Sin nombre.");
        this.precioCompra = 0;
        this.precioEdificar = 0;
        this.precioBaseAlquiler = 0;
        this.numCasas = this.numHoteles = 0;
        this.Propietario = PROPIETARIONULO;
    }
    
    public CasillaCalle (String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase) {
        super(unNombre);
        this.precioCompra = unPrecioCompra;
        this.precioEdificar = unPrecioEdificar;
        this.precioBaseAlquiler = unPrecioAlquilerBase;
    }
    
    public boolean esEsteElPropietario(Jugador jugador){
        return jugador == this.Propietario;
    }
    
    public float getPrecioAlquilerCompleto() {
        return precioBaseAlquiler * (FACTORALQUILERCALLE + FACTORALQUILERCASA*numCasas + FACTORALQUILERHOTEL*numHoteles);
    }
        
    public float getPrecioCompra() {
        return precioCompra;
    }
        
    public float getPrecioEdificar() {
        return precioEdificar;
    }
    
    public int getNumCasas() {
        return numCasas;
    } 
    
    public int getNumHoteles() {
        return numHoteles;
    }
    
    public boolean tienePropietario(){
        boolean propietario = false;
        
        if(Propietario != PROPIETARIONULO){
            propietario = true;
        }
     
        return propietario;
    }
    
    void tramitarAlquiler(Jugador jugador){
        if(this.tienePropietario() && !this.esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(this.getPrecioAlquilerCompleto());
            this.Propietario.recibe(this.getPrecioAlquilerCompleto());
            Diario.getInstance().ocurreEvento(jugador.getNombre() + " paga " 
                    + this.getPrecioAlquilerCompleto() + " de alquiler a " + this.Propietario.getNombre());
        }
    }
    
    @Override
    public String toString() {
        return  ("CALLE "+ super.toString() + ".\n\t"
                + "Precios: Compra:"+ precioCompra+", Edificar: "+ precioEdificar+", Alquiler base: "+ precioBaseAlquiler+".\n\t" 
                + "Edificaciones: Casas: "+ numCasas +", Hoteles: "+ numHoteles);
    }
    
    public int cantidadCasasHoteles(){
        return numCasas+numHoteles;
    }
        
    boolean comprar(Jugador jugador){
        this.Propietario = jugador;
        jugador.paga(this.precioCompra);
        return true;
    }
    
    boolean construirCasa(Jugador jugador){
        jugador.paga(this.getPrecioEdificar());
        this.numCasas++;
        return true;
    }
    
    boolean construirHotel(Jugador jugador){
        jugador.paga(this.getPrecioEdificar());
        this.numHoteles++;
        return true;
    }
    
    @Override
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        this.recibeJugador_calle(actual, todos);
    }
    
    void recibeJugador_calle(int actual, ArrayList<Jugador> todos){
        this.informe(actual, todos);
        Jugador j_actual = todos.get(actual);
        if(!this.tienePropietario()){
            j_actual.puedeComprarCasilla();
        }
        else{
            tramitarAlquiler(j_actual);
        }
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        boolean exito = false;
        
        if(this.esEsteElPropietario(jugador) && n <= this.numCasas){
            numCasas-=n;
            exito = true;
        }
        
        return exito;
    }   

    void actualizaPropiedadesPorConversion(JugadorEspeculador jugador) {
        this.Propietario = jugador;
    }
}

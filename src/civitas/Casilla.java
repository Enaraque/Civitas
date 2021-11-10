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
    static float FACTORALQUILERCALLE = 1.0f;
    static float FACTORALQUILERCASA = 1.0f;
    static float FACTORALQUILERHOTEL = 4.0f;
    static Jugador PROPIETARIONULO = null;
    
    private TipoCasilla tipo;
    private String nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    private Jugador Propietario;
    private MazoSorpresas mazo;
    
    
    public int cantidadCasasHoteles(){
        return numCasas+numHoteles;
    }
    
    private void init(){
        this.tipo = TipoCasilla.DESCANSO;
        this.nombre = "Sin nombre.";
        this.precioCompra = 0;
        this.precioEdificar = 0;
        this.precioBaseAlquiler = 0;
        this.numCasas = this.numHoteles = 0;
        this.Propietario = PROPIETARIONULO;
        this.mazo = new MazoSorpresas();
        
    }
    public Casilla (String unNombre) {
        init();
        this.tipo = TipoCasilla.DESCANSO;
        this.nombre = unNombre;
    }
    
    public Casilla (String unNombre, float unPrecioCompra,
            float unPrecioEdificar, float unPrecioAlquilerBase) {
        
        init();
        
        this.tipo= TipoCasilla.CALLE;
        this.nombre = unNombre;
        this.precioCompra = unPrecioCompra;
        this.precioEdificar = unPrecioEdificar;
        this.precioBaseAlquiler = unPrecioAlquilerBase;
    }
    
    public Casilla (String unNombre, MazoSorpresas mazo) {
        init();
        
        this.tipo= TipoCasilla.SORPRESA;
        this.nombre = unNombre;
        this.mazo = mazo;
    }
    
    public boolean esEsteElPropietario(Jugador jugador){
        return jugador == this.Propietario;
    }
    
    
    public float getPrecioAlquilerCompleto() {
        return precioBaseAlquiler * (FACTORALQUILERCALLE + FACTORALQUILERCASA*numCasas + FACTORALQUILERHOTEL*numHoteles);
    }
    
    public String getNombre() {
        return nombre;
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
        
        if(this.tipo == TipoCasilla.CALLE){
            if(Propietario != PROPIETARIONULO){
                propietario = true;
            }
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
    
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento(todos.get(actual).getNombre()+" ha caido en la casilla: "+ this.toString());
    }
    
    public String toString() {
        return  (tipo +" "+ nombre+".\n\t"
                + "Precios: Compra:"+ precioCompra+", Edificar: "+ precioEdificar+", Alquiler base: "+ precioBaseAlquiler+".\n\t" 
                + "Edificaciones: Casas: "+ numCasas +", Hoteles: "+ numHoteles);
    }

    
    //A hacer para la sesion siguiente (3)
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
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
        if(this.tipo == TipoCasilla.CALLE){
            this.recibeJugador_calle(actual, todos);
        }
        else if(this.tipo == TipoCasilla.SORPRESA){
            this.recibeJugador_sorpresa(actual, todos);
        }
        else{
            this.informe(actual, todos);
        }
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
    
    void recibeJugador_sorpresa(int actual, ArrayList<Jugador> todos){
        Sorpresa sorpresa = this.mazo.siguiente();
        Diario.getInstance().ocurreEvento("El jugador "+todos.get(actual).getNombre()
                +" recibe la sorpresa siguiente:\n" + sorpresa.toString());
        sorpresa.aplicarAJugador(actual, todos);
    }
    
    boolean derruirCasas(int n, Jugador jugador){
        boolean exito = false;
        
        if(this.esEsteElPropietario(jugador) && n <= this.numCasas){
            numCasas-=n;
            exito = true;
        }
        
        return exito;
    }
}
    


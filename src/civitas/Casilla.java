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
        }
    }
    
    
    void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento(todos.get(actual).getNombre()+" ha caido en la casilla: "+ this.toString());
    }
    
    public String toString() {
        return  (tipo +" "+ nombre+".Precios: Compra:"+ precioCompra+", Edificar: "
                + precioEdificar+", Alquiler base: "+ precioBaseAlquiler+", Casas: "+ numCasas +", Hoteles: "+ numHoteles);
    }

    
    //A hacer para la sesion siguiente (3)
    boolean comprar(Jugador jugador){
        return false;
    }
    
    boolean construirCasa(Jugador jugador){
        return false;
    }
    
    boolean construirHotel(Jugador jugador){
        return false;
    }
    
    void recibeJugador(int actual, ArrayList<Jugador> todos){
    
    }
    
    void recibeJugador_calle(int actual, ArrayList<Jugador> todos){
    
    }
    void recibeJugador_sorpresa(int actual, ArrayList<Jugador> todos){
    
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
    
}

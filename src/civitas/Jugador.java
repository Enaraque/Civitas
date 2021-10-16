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
public class Jugador implements Comparable<Jugador>{
    protected static final int CASASMAX = 4;
    protected static final int HOTELESMAX = 4;
    protected static final int CASASPORHOTEL = 4;
    protected static final float PASOPORSALIDA = 1000.0f;
    private static final float SALDOINICIAL = 7500.0f;
    
    private String nombre;
    private int casillaActual;
    private boolean puedeComprar;
    private float saldo;
    private ArrayList<Casilla> propiedades;

    /**
    * Constructor de la clase Jugador
    * Crea una instancia de Jugador con sus parametros iniciales por defecto.
    *
    * @param nombre Nombre del jugador.
    */
    Jugador(String nombre){
        this.nombre = nombre;
        this.casillaActual = 0;
        this.saldo = SALDOINICIAL;
        this.puedeComprar = false;
        this.propiedades = new ArrayList<Casilla>();
    }
    
    /**
    * Constructor de copia de la clase Jugador
    * Crea una instancia de Jugador con los parametros
    *
    * @param otro Jugador ya creado sobre el cual copiar crear la nueva instancia.
    */
    protected Jugador(Jugador otro){
        this.nombre = otro.nombre;
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = otro.propiedades;
    }
    
    /**
    * Devuelve el valor de la constante de clase CASASPORHOTEL.
    *
    * @param void
    * @return Numero de casas necesarias para construir un hotel
    */
    int cantidadCasasHoteles(){
        return Jugador.CASASPORHOTEL;
    }
    
    //Interfaz
    @Override
    public int compareTo(Jugador otro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * Calcula si el jugador está en bancarrota o no.
    * Un jugador se declara en bancarrota si su saldo es menor que 0.
    *
    * @param void
    * @return True si y solo si está en bancarrota.
    */
    boolean enBancarrota(){
        return this.getSaldo() < 0;
    }
    
    /**
    * Consulta si la propiedad con indice ip existe 
    * Un jugador se declara en bancarrota si su saldo es menor que 0.
    *
    * @param un valor
    * @return True si y solo si está en bancarrota.
    */
    public boolean existeLaPropiedad(int ip){
        assert(ip >= 0);
        return this.getPropiedades().size() > ip;
    }
    
    private int getCasasMax(){
        return Jugador.CASASMAX;
    }
    
    private int getHotelesMax(){
        return Jugador.HOTELESMAX;
    }
    
    private float getPremioPasoSalida(){
        return Jugador.PASOPORSALIDA;
    }
    
    int getCasasPorHotel(){
        return Jugador.CASASPORHOTEL;
    }
    
    int getCasillaActual(){
        return this.casillaActual;
    }
    
    protected String getNombre(){
        return nombre;
    }
    
    protected ArrayList<Casilla> getPropiedades(){
        return this.propiedades;
    }
    
    boolean getPuedeComprar(){
        return this.puedeComprar;
    }
    
    protected float getSaldo(){
        return this.saldo;
    }
    
    boolean modificaSaldo(float cantidad){
        this.saldo += cantidad;
        return true;
    }
    
    boolean moverACasilla(int numCasilla){
        this.casillaActual = numCasilla;
        this.puedeComprar = false;
        Diario.getInstance().ocurreEvento(this.nombre+" se mueve a la casilla "+numCasilla+".");
        return true;
    }
    
    boolean paga(float cantidad){
        return this.modificaSaldo(-cantidad);
    }
    
    boolean pagaAlquiler(float cantidad){
        return this.paga(cantidad);
    }
    
    boolean pasaPorSalida(){
        this.recibe(this.getPremioPasoSalida());
        Diario.getInstance().ocurreEvento(this.nombre+" ha cobrado por pasar por la salida "+this.getPremioPasoSalida());
        return true;
    }
    
    boolean puedeComprarCasilla(){
        boolean aux = this.puedeComprar;
        puedeComprar = true;
        return aux;
    }
    
    private boolean puedoEdificarCasa(Casilla propiedad){
        return (propiedad.esEsteElPropietario(this) && 
                puedoGastar(propiedad.getPrecioEdificar()) &&
                propiedad.getNumCasas() < this.getCasasMax());
    }
    
    private boolean puedoEdificarHotel(Casilla propiedad){
        return (propiedad.esEsteElPropietario(this) && 
                puedoGastar(propiedad.getPrecioEdificar()) &&
                propiedad.getNumHoteles() < this.getHotelesMax() &&
                propiedad.getNumCasas() >= this.getCasasPorHotel()) ;
    }
    
    private boolean puedoGastar(float precio){
        return this.getSaldo()>=precio;
    }
    
    boolean recibe(float cantidad){
        return this.modificaSaldo(cantidad);
    }
    
    boolean tieneAlgoQueGestionar(){
        return this.getPropiedades().size() > 0;
    }
    
    //Por implementar en la siguiente sesion (3)
    boolean comprar(Casilla titulo){
        return false;
    }
    
    boolean construirCasa(int ip){
        return false;
    }
    
    boolean construirHotel(int ip){
        return false;
    }
}

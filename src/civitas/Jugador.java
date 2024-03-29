

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
    private ArrayList<CasillaCalle> propiedades;

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
        this.propiedades = new ArrayList<CasillaCalle>();
    }
    
    /**
    * Constructor de copia de la clase Jugador
    * Crea una instancia de Jugador con los parametros
    *
    * @param otro Jugador ya creado sobre el cual copiar crear la nueva instancia.
    */
    protected Jugador(Jugador otro){
        this.nombre = new String(otro.nombre);
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
        this.propiedades = new ArrayList<CasillaCalle>(otro.propiedades);
    }
    
    /**
    * Devuelve la suma del número de las casas y hoteles construidos.
    *
    * @return Numero de casas y hoteles del jugador.
    */
    int cantidadCasasHoteles(){
        int casasHoteles = 0;
        
        for(int i = 0; i < this.propiedades.size(); i++){
            casasHoteles+=this.propiedades.get(i).cantidadCasasHoteles();
        }
        
        return casasHoteles;
    }
    
    //Interfaz
    @Override
    public int compareTo(Jugador otro) {
        //-1 si es menor que otro. 0 si son iguales. 1 si es mayor
        return Float.valueOf(this.getSaldo()).compareTo(Float.valueOf(otro.getSaldo()));
    }
    
    /**
    * Calcula si el jugador está en bancarrota o no.
    * Un jugador se declara en bancarrota si su saldo es menor que 0.
    *
    * @return True si y solo si está en bancarrota.
    */
    boolean enBancarrota(){
        return this.getSaldo() < 0;
    }
    
    /**
    * Comprueba que el índice ip es un índice válido dentro de las
    * propiedades del jugador (ArrayList <Casilla> propiedades).
    *
    * @param ip un entero no negativo.
    * @return True si y solo si ip es un indice válido.
    */
    protected boolean existeLaPropiedad(int ip){
        assert(ip >= 0);
        return this.getPropiedades().size() > ip;
    }
    
    /**
    * Devuelve el valor de la constante de clase CASASMAX.
    *
    * @return Numero de casas maximo que se pueden edificar
    * en una casilla.
    */
    protected int getCasasMax(){
        return Jugador.CASASMAX;
    }
    
    /**
    * Devuelve el valor de la constante de clase HOTELESMAX.
    *
    * @return Numero de hoteles maximo que se pueden edificar
    * en una casilla.
    */
    protected int getHotelesMax(){
        return Jugador.HOTELESMAX;
    }
    
    /**
    * Devuelve el valor de la constante de clase PASOPORSALIDA.
    *
    * @return Cantidad de dinero que se otorga a un jugador al
    * pasar por la casilla de salida.
    */
    protected float getPremioPasoSalida(){
        return Jugador.PASOPORSALIDA;
    }
    
    /**
    * Devuelve el valor de la constante de clase CASASPORHOTEL.
    * 
    * @return Numero de casas que se sustituyen por un hotel.
    */
    int getCasasPorHotel(){
        return Jugador.CASASPORHOTEL;
    }
    
    /**
    * Devuelve la posicion del tablero en la que se encuentra
    * el jugador.
    *
    * @return Casilla actual.
    */
    public int getCasillaActual(){
        return this.casillaActual;
    }
    
    /**
    * Devuelve el nombre del jugador.
    *
    * @return String con el nombre del jugador.
    */
    public String getNombre(){
        return nombre;
    }
    
    /**
    * Devuelve el ArrayList de propiedades del jugador.
    *
    * @return ArrayList de Casillas con las casillas que posee el jugador.
    */
    //Cambio la visibilidad protected a public para poder hacer actualiza() en vistaTextuak.
    public ArrayList<CasillaCalle> getPropiedades(){
        return this.propiedades;
    }
    
    /**
    * Calcula si el jugador puede comprar la casilla en la que
    * está situado.
    *
    * @return true si y solo si puede comprar la casilla actual.
    */
    boolean getPuedeComprar(){
        return this.puedeComprar;
    }
    
    /**
    * Devuelve el saldo del jugador.
    *
    * @return saldo del jugador.
    */
    public float getSaldo(){
        return this.saldo;
    }
    
    /**
    * Modifica el saldo del jugador sumandole cierta cantidad.
    * 
    * @param cantidad Cantidad a sumar al saldo del jugador.
    * @return true si (y solo si) la operacion se completo correctamente.
    */
    boolean modificaSaldo(float cantidad){
        this.saldo += cantidad;
        return true;
    }
    
    /**
    * Mueve al jugador a la nueva casilla especificada.
    * 
    * @param numCasilla Nueva casilla del jugador.
    * @return true si (y solo si) la operacion se completo correctamente.
    */
    boolean moverACasilla(int numCasilla){
        this.casillaActual = numCasilla;
        this.puedeComprar = false;
        Diario.getInstance().ocurreEvento(this.nombre+" se mueve a la casilla "+numCasilla+".");
        return true;
    }
    
    /**
    * Sustrae del saldo del jugador la cantidad especificada.
    * 
    * @param cantidad Cantidad a pagar por el jugador.
    * @return true si (y solo si) la operacion se completo correctamente.
    */
    boolean paga(float cantidad){
        return this.modificaSaldo(-cantidad);
    }
    
    /**
    * Sustrae del saldo del jugador la cantidad especificada.
    * Actualmente es exactamente igual que el metodo paga.
    * 
    * @param cantidad Cantidad a pagar por el jugador.
    * @return true si (y solo si) la operacion se completo correctamente.
    */
    boolean pagaAlquiler(float cantidad){
        return this.paga(cantidad);
    }
    
    /**
    * Realiza los cambios precisos sobre el jugador por 
    * pasar por la casilla de Salida.
    * 
    * @return true si (y solo si) la operacion se completo correctamente.
    */
    boolean pasaPorSalida(){
        this.recibe(this.getPremioPasoSalida());
        Diario.getInstance().ocurreEvento(this.nombre+" ha cobrado por pasar por la salida "+this.getPremioPasoSalida());
        return true;
    }
    
    /**
    * Fija el atributo puedeComprar a true y devuelve el
    * valor de este atributo.
    *
    * @return el nuevo valor de puedeComprar.
    */
    boolean puedeComprarCasilla(){
        boolean aux = this.puedeComprar;
        puedeComprar = true;
        return aux;
    }
    
    /**
    * Comprueba si se puede construir una casa en la 
    * propiedad especificada.
    * 
    * @param propiedad Propiedad sobre la cual se quiere 
    * hacer la comprobacion.
    * @return true si se puede construir la casa, false en
    * otro caso.
    */
    boolean puedoEdificarCasa(CasillaCalle propiedad){
        return (puedoGastar(propiedad.getPrecioEdificar()) &&
                propiedad.getNumCasas() < this.getCasasMax());
    }
    
    /**
    * Comprueba si se puede construir un hotel en la 
    * propiedad especificada.
    * 
    * @param propiedad Propiedad sobre la cual se quiere 
    * hacer la comprobacion.
    * @return true si se puede construir el hotel, false en
    * otro caso.
    */
    boolean puedoEdificarHotel(CasillaCalle propiedad){
        boolean puedoEdificar = false;
        float precio = propiedad.getPrecioEdificar();
        
        if(this.puedoGastar(precio))
            if(propiedad.getNumHoteles() < this.getHotelesMax())
                if(propiedad.getNumCasas() >= this.getCasasPorHotel())
                    puedoEdificar = true;
        
        return puedoEdificar;
    }
    
    /**
    * Comprueba si un jugador puede permitirse comprar algo.
    * 
    * @param precio El precio de lo que se quiera comprar.
    * @return true si puede comprarlo, false en otro caso.
    */
    private boolean puedoGastar(float precio){
        return this.getSaldo()>=precio;
    }
    
    /**
    * Añade al saldo del jugador cierta cantidad de dinero.
    * 
    * @param cantidad Cantidad a añadir.
    * @return true si se completo la accion con exito, false
    * en otro caso.
    */
    boolean recibe(float cantidad){
        return this.modificaSaldo(cantidad);
    }
    
    /**
    * Comprueba si un jugador posee propiedades.
    * 
    * @return true si tiene propiedades, false si no.
    */
    boolean tieneAlgoQueGestionar(){
        return this.getPropiedades().size() > 0;
    }
    
    /**
    * Devuelve informacion sobre el jugador.
    *
    * @return String con el nombre del jugador.
    */
    public String toString(){
        String info;
        info = "JUGADOR: " + nombre + ". Saldo: " + saldo + ". Con un total de: "
                + propiedades.size() + " propiedades. Casilla actual: " +this.casillaActual;
        return info;
    }
    
    //Por implementar en la siguiente sesion (3)
    boolean comprar(CasillaCalle titulo){
        boolean result = false;
        
        if(this.puedeComprar){
            float precio = titulo.getPrecioCompra();
            if(this.puedoGastar(precio)){
                result = titulo.comprar(this);
                this.getPropiedades().add(titulo);
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+" compra la propiedad "+titulo.getNombre());
                this.puedeComprar = false;
            }
            else{
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+" no tiene saldo para comprar la propiedad "+titulo.getNombre());
            }
        }
        return result;
    }
    
    boolean construirCasa(int ip){
        boolean result = false;
        
        if(this.existeLaPropiedad(ip)){
            CasillaCalle propiedad = this.getPropiedades().get(ip);
            
            if(this.puedoEdificarCasa(propiedad)){
                result = propiedad.construirCasa(this); 
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+
                " construye una casa en la propiedad "+this.getPropiedades().get(ip).getNombre());
            }
            else{
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+ " NO ha podido construir una casa en la propiedad "
                    + this.getPropiedades().get(ip).getNombre());
            }
        }
        return result;
    }
    
    boolean construirHotel(int ip){
        boolean result = false;
        if(this.existeLaPropiedad(ip)){
            CasillaCalle propiedad = this.getPropiedades().get(ip);
            
            if(this.puedoEdificarHotel(propiedad)){
                result = propiedad.construirHotel(this);
                propiedad.derruirCasas(CASASPORHOTEL, this);
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+
                " construye un hotel en la propiedad "+this.getPropiedades().get(ip).getNombre());
            }
            else{
                Diario.getInstance().ocurreEvento("El jugador "+this.getNombre()+
                " no ha podido construir un hotel en la propiedad "+this.getPropiedades().get(ip).getNombre());
            }
        }
        return result;
    }
    
    protected JugadorEspeculador convertir(){
        JugadorEspeculador especulador = new JugadorEspeculador(this);
        return especulador;
    }
}


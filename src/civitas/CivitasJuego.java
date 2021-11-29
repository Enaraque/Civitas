/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author enriquearaqueespinosa
 */
public class CivitasJuego {
    protected static final int NUMAXJUGADORES = 4;
    protected static final int SORPRESAPAGARCOBRAR1 = 50;
    protected static final int SORPRESAPAGARCOBRAR2 = 225;
    protected static final int SORPRESAPAGARCOBRAR3 = 570;
    protected static final int SORPRESAPORCASAHOTEL1 = 50;
    protected static final int SORPRESAPORCASAHOTEL2 = 75;
    
    
    
    private int indiceJugadorActual;
    private MazoSorpresas mazo;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private EstadoJuego estado;
    private GestorEstados gestor;
    
    public CivitasJuego(ArrayList<String> nombres, boolean debug) {
        
        jugadores = new ArrayList<Jugador>();
        for (int i = 0; i < NUMAXJUGADORES; i++) {
            Jugador aux = new Jugador(nombres.get(i));
            jugadores.add(aux);
        }
       
        gestor = new GestorEstados();
        estado = gestor.estadoInicial();        
        Dado.getInstance().setDebug(debug);
        indiceJugadorActual = Dado.getInstance().quienEmpieza(NUMAXJUGADORES);
        mazo = new MazoSorpresas(debug);
        
        tablero = new Tablero();
        inicializarTablero(mazo);
        inicializarMazoSorpresas();
    }
    
    private void inicializarTablero(MazoSorpresas mazo) {
        CasillaCalle casilla1 = new CasillaCalle("Calle Periodista Daniel Saucedo Aranda", 320, 192, 96);
        tablero.añadeCasilla(casilla1);
        
        CasillaCalle casilla2 = new CasillaCalle("Calle Periodista Luis Seco de Lucena", 325, 195, 97);
        tablero.añadeCasilla(casilla2);
        
        CasillaSorpresa sorpresa1 = new CasillaSorpresa("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa1);
        
        CasillaCalle casilla3 = new CasillaCalle("Calle Periodista Fernando Gómez de la Cruz", 340, 270, 135);
        tablero.añadeCasilla(casilla3);
        
        CasillaCalle casilla4 = new CasillaCalle("Av. Francisco Ayala", 400, 240, 120);
        tablero.añadeCasilla(casilla4);
        
        CasillaCalle casilla5 = new CasillaCalle("Calle Obispo Hurtado", 435, 261, 130);
        tablero.añadeCasilla(casilla5);
        
        CasillaCalle casilla6 = new CasillaCalle("Calle Azorín", 460, 276, 138);
        tablero.añadeCasilla(casilla6);
        
        CasillaSorpresa sorpresa2 = new CasillaSorpresa("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa2);
        
        CasillaCalle casilla7 = new CasillaCalle("Calle Casillas de Prats", 490, 294, 147);
        tablero.añadeCasilla(casilla7);
        
        Casilla descanso = new Casilla("PARKING");
        tablero.añadeCasilla(descanso);
        
        CasillaCalle casilla8 = new CasillaCalle("Ancha de Gracia", 545, 327, 164);
        tablero.añadeCasilla(casilla8);
        
        CasillaCalle casilla9 = new CasillaCalle("Calle Recogidas", 620, 372, 186);
        tablero.añadeCasilla(casilla9);
        
        CasillaSorpresa sorpresa3 = new CasillaSorpresa("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa3);
        
        CasillaCalle casilla10 = new CasillaCalle("Calle Martinez de la Rosa", 750, 450, 225);
        tablero.añadeCasilla(casilla10);
        
        CasillaCalle casilla11 = new CasillaCalle("Calle Pedro Antonio de Alarcón", 900, 540, 270);
        tablero.añadeCasilla(casilla11);
        
        CasillaCalle casilla12 = new CasillaCalle("Calle Oficios", 1150, 690, 345);
        tablero.añadeCasilla(casilla12);
        
        CasillaCalle casilla13 = new CasillaCalle("Paseo de los Tristes", 1350, 810, 405);
        tablero.añadeCasilla(casilla13);
        
        CasillaSorpresa sorpresa4 = new CasillaSorpresa("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa4);
        
        CasillaCalle casilla14 = new CasillaCalle("Acera del Darro", 1500, 1080, 540);
        tablero.añadeCasilla(casilla14); 
    }
    
    private void inicializarMazoSorpresas() {
        String cobrar1 = "Al ir al supermercado a comprar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR1 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar1 = new SorpresaPagarCobrar(cobrar1, SORPRESAPAGARCOBRAR1);
        mazo.alMazo(sorpresaPagarCobrar1);
        
        String cobrar2 = "Al ir al supermercado a comprar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR3 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar2 = new SorpresaPagarCobrar(cobrar2, SORPRESAPAGARCOBRAR3);
        mazo.alMazo(sorpresaPagarCobrar2);
        
        String pagarCasaHotel1 = "Has empezado el dia con una llamada telefonica. Te han dicho que los\n"+
                " conductos de extracción de humos de tus edificios son obsoletos y deben ser renovados.\n"
                + "Paga " + SORPRESAPORCASAHOTEL2 + " por cada edificio que tengas.\n";
        SorpresaPorCasaHotel sorpresaPorCasaHotel1 = new SorpresaPorCasaHotel(pagarCasaHotel1, -SORPRESAPORCASAHOTEL2);
        mazo.alMazo(sorpresaPorCasaHotel1);
        
        String pagar1 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente \n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR2 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar3 = new SorpresaPagarCobrar(pagar1, -SORPRESAPAGARCOBRAR2);
        mazo.alMazo(sorpresaPagarCobrar3);
        
        String pagarCasaHotel2 = "¡Terremoto! Todos tus edificios han sufrido daños y debes repararlos para que no se caigan.\n"
                + "Paga " + SORPRESAPORCASAHOTEL1 + " por cada edificio que tengas.\n";
        SorpresaPorCasaHotel sorpresaPorCasaHotel2 = new SorpresaPorCasaHotel(pagarCasaHotel2, -SORPRESAPORCASAHOTEL1);
        mazo.alMazo(sorpresaPorCasaHotel2);
        
        String cobrar3 = "Al ir al supermercado a compar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR2 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar4 = new SorpresaPagarCobrar(cobrar3, SORPRESAPAGARCOBRAR2);
        mazo.alMazo(sorpresaPagarCobrar4);
        
        String pagar2 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente,\n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR1 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar5 = new SorpresaPagarCobrar(pagar2, -SORPRESAPAGARCOBRAR1);
        mazo.alMazo(sorpresaPagarCobrar5);
        
        String cobrarCasaHotel1 = "¡Hoy es tu día de de suerte! El gobierno municipal ha emitido una ordenanza que \n"
                + "hace que pagues menos IBI en todas tus propiedades.\n" + "Cobra " + SORPRESAPORCASAHOTEL2 + " por cada " 
                + "edificio que tengas.";
        SorpresaPorCasaHotel sorpresaPorCasaHotel3 = new SorpresaPorCasaHotel(cobrarCasaHotel1, SORPRESAPORCASAHOTEL2);
        mazo.alMazo(sorpresaPorCasaHotel3);
        
        String pagar3 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente \n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR3 + ".\n";
        SorpresaPagarCobrar sorpresaPagarCobrar6 = new SorpresaPagarCobrar(pagar3, -SORPRESAPAGARCOBRAR3);
        mazo.alMazo(sorpresaPagarCobrar6);
        
        String cobrarCasaHotel2 = "Un jeque ha venido a Granada ¡y te ha alquilado todas tus propiedades para él y sus siervos!\n"
                + "Cobra " + SORPRESAPORCASAHOTEL1 + " por cada edificio que tengas.\n";
        SorpresaPorCasaHotel sorpresaPorCasaHotel4 = new SorpresaPorCasaHotel(cobrarCasaHotel2, SORPRESAPORCASAHOTEL1);
        mazo.alMazo(sorpresaPorCasaHotel4);    
    }
    
    public Jugador getJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }
    
    public int getIndiceJugadorActual() {
        return indiceJugadorActual;
    }
    
    public Tablero getTablero() {
        return tablero;
    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    //cambiado a public para el controlador
    public void pasarTurno() {
        int pasar =  (getIndiceJugadorActual()+1) % jugadores.size();
        indiceJugadorActual = pasar;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion) {
        estado = gestor.siguienteEstado(jugadores.get(getIndiceJugadorActual()), estado, operacion);
    }
    
    public boolean construirCasa(int ip) {
        boolean exito = jugadores.get(getIndiceJugadorActual()).construirCasa(ip);
        
        return exito;
    }
    
    public boolean construirHotel(int ip) {
        boolean exito = jugadores.get(getIndiceJugadorActual()).construirHotel(ip);
        
        return exito;
    }
    
    public boolean finalDelJuego() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).enBancarrota()) {
                return true;
            }
        }
        return false;
    }
    
    //puesto publi para utilizarlo en vistaTextual y en el controlador
    public ArrayList<Jugador> ranking() {
        Collections.sort(jugadores, Collections.reverseOrder());
        return jugadores;
    }
    
    private void contabilizarPasosPorSalida() {
        if (tablero.computarPasoPorSalida()) {
            jugadores.get(getIndiceJugadorActual()).pasaPorSalida();
        }
    }
    
    //hecho pubico para utilizarlo en controlador
    public void avanzaJugador() {
        int posicionActual, posicionNueva, tirada;
        Jugador jugadorActual = this.getJugadorActual();
        Casilla casillaNueva;
        //Calculamos la casilla a la que avanza el jugador
        posicionActual = jugadorActual.getCasillaActual();
        tirada = Dado.getInstance().tirar();
        posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        
        //Actualizamos el estado de Tablero y Jugador
        casillaNueva = tablero.getCasilla(posicionNueva);
        this.contabilizarPasosPorSalida();
        jugadorActual.moverACasilla(posicionNueva);
        
        casillaNueva.recibeJugador(indiceJugadorActual, jugadores);
    }
    
    public OperacionJuego siguientePaso() {
        Jugador jugadorActual = getJugadorActual();
        OperacionJuego operacion = gestor.siguienteOperacion(jugadorActual,estado);
        
        if(operacion == OperacionJuego.PASAR_TURNO){
            this.pasarTurno();
            this.siguientePasoCompletado(operacion);
        }
        else if(operacion == OperacionJuego.AVANZAR){
            this.avanzaJugador();
            this.siguientePasoCompletado(operacion);
        }
        return operacion;
    }
    
    public boolean comprar() {
        boolean result;
        
        Jugador jugadorActual = this.getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        CasillaCalle casillaActual = (CasillaCalle)tablero.getCasilla(numCasillaActual);
        result = jugadorActual.comprar(casillaActual);
        
        return result;
    }
    
    /*
    public static void main(String[] args) {
        ArrayList<String> nombres = new ArrayList<>();
            nombres.add("jugador1");
            nombres.add("jugador2");
            nombres.add("jugador3");
            nombres.add("jugador4");
            
        CivitasJuego prueba = new CivitasJuego(nombres, true);
        prueba.getJugadores().get(0).modificaSaldo(450);
        prueba.getJugadores().get(1).modificaSaldo(500);
        prueba.getJugadores().get(2).modificaSaldo(100);
        prueba.getJugadores().get(3).modificaSaldo(2);
        prueba.ranking();
        
        //Comprobando ranking();
        for (int i=0; i < NUMAXJUGADORES; i++) {
            System.out.println("el jugador " + prueba.getJugadores().get(i) + " es el numero " + (i+1) + " con " + prueba.getJugadores().get(i).getSaldo());         
        }
        
        //Comprobadno el tablero y el mazo.
        for (int i = 0; i < prueba.getTablero().getNumCasillas(); i++) {
            System.out.println(prueba.getTablero().getCasilla(i).toString());
        }
        
        for (int i = 0; i < prueba.mazo.getSize(); i++) {
            System.out.println(prueba.mazo.getSorpresa(i).toString());
        }
        
        //jugador actual y pasar turno
        for (int i = 0; i < NUMAXJUGADORES*2; i++) {
            Jugador señor = prueba.getJugadorActual();
            System.out.println("El jugador de nombre "+ señor.getNombre()+ " tiene el turno siendo el jugador número " + prueba.getIndiceJugadorActual());
            prueba.pasarTurno();
        }
        System.out.println("El jugador actual es "+ prueba.getJugadorActual().getNombre());
        
        //siguiente paso       
        prueba.siguientePasoCompletado(OperacionJuego.AVANZAR);
        System.out.println("El estado del jugador " + prueba.getJugadorActual().getNombre()+ " es " + prueba.estado);
       
        //pasar por salida
        double dinerAntes = prueba.getJugadorActual().getSaldo();
        prueba.getTablero().nuevaPosicion(14, 10);
        prueba.contabilizarPasosPorSalida();
        if (prueba.getJugadorActual().getSaldo() == dinerAntes+1000) {
            System.out.println("Ha contabilizado bien :)");
        }
        else {
            System.out.println("No contabilizado bien :(");
        }
        
        //mover a casilla (clase jugador)
        System.out.println("La casilla actual es " + prueba.getJugadorActual().getCasillaActual()+ "    ");
        prueba.getJugadorActual().moverACasilla(12);
        System.out.println("La casilla actual es " + prueba.getJugadorActual().getCasillaActual()+ "    ");
        
        //Paga alquiler de clase jugador (tambien compruebo si funciona el metodo paga);
        
        System.out.println("El saldo del jugador actual es de " + prueba.getJugadorActual().getSaldo());
        prueba.getJugadorActual().pagaAlquiler(300);
        System.out.println("El saldo del jugador actual es de " + prueba.getJugadorActual().getSaldo());
        
        //final del juego
        prueba.getJugadores().get(0).paga(prueba.getJugadores().get(0).getSaldo()+1);
        System.out.println(prueba.getJugadores().get(0).getSaldo());
        
        if(prueba.finalDelJuego()) {
            System.out.println("EL JUEGO HA TERMINADO");
        }
        else {
            System.out.println("EL JUEGO SIGUE EN MARCHA");
        }
        
     
    }
*/
}

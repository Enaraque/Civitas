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
    protected static final int SORPRESAPORCASAHOTEL1 = 550;
    protected static final int SORPRESAPORCASAHOTEL2 = 780;
    
    
    
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
        Casilla casilla1 = new Casilla("Calle Periodista Daniel Saucedo Aranda", 320, 192, 96);
        tablero.añadeCasilla(casilla1);
        
        Casilla casilla2 = new Casilla("Calle Periodista Luis Seco de Lucena", 325, 195, 97);
        tablero.añadeCasilla(casilla2);
        
        Casilla sorpresa1 = new Casilla("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa1);
        
        Casilla casilla3 = new Casilla("Calle Periodista Fernando Gómez de la Cruz", 340, 270, 135);
        tablero.añadeCasilla(casilla3);
        
        Casilla casilla4 = new Casilla("Av. Francisco Ayala", 400, 240, 120);
        tablero.añadeCasilla(casilla4);
        
        Casilla casilla5 = new Casilla("Calle Obispo Hurtado", 435, 261, 130);
        tablero.añadeCasilla(casilla5);
        
        Casilla casilla6 = new Casilla("Calle Azorín", 460, 276, 138);
        tablero.añadeCasilla(casilla6);
        
        Casilla sorpresa2 = new Casilla("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa2);
        
        Casilla casilla7 = new Casilla("Calle Casillas de Prats", 490, 294, 147);
        tablero.añadeCasilla(casilla7);
        
        Casilla descanso = new Casilla("PARKING");
        tablero.añadeCasilla(descanso);
        
        Casilla casilla8 = new Casilla("Ancha de Gracia", 545, 327, 164);
        tablero.añadeCasilla(casilla8);
        
        Casilla casilla9 = new Casilla("Calle Recogidas", 620, 372, 186);
        tablero.añadeCasilla(casilla9);
        
        Casilla sorpresa3 = new Casilla("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa3);
        
        Casilla casilla10 = new Casilla("Calle Martinez de la Rosa", 750, 450, 225);
        tablero.añadeCasilla(casilla10);
        
        Casilla casilla11 = new Casilla("Calle Pedro Antonio de Alarcón", 900, 540, 270);
        tablero.añadeCasilla(casilla11);
        
        Casilla casilla12 = new Casilla("Calle Oficios", 1150, 690, 345);
        tablero.añadeCasilla(casilla12);
        
        Casilla casilla13 = new Casilla("Paseo de los Tristes", 1350, 810, 405);
        tablero.añadeCasilla(casilla12);
        
        Casilla sorpresa4 = new Casilla("SORPRESA", mazo);
        tablero.añadeCasilla(sorpresa4);
        
        Casilla casilla14 = new Casilla("Acera del Darro", 1800, 1080, 540);
        tablero.añadeCasilla(casilla14); 
    }
    
    private void inicializarMazoSorpresas() {
        String cobrar1 = "Al ir al supermercado a comprar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR1 + ".\n";
        Sorpresa sorpresaPagarCobrar1 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, cobrar1, SORPRESAPAGARCOBRAR1);
        mazo.alMazo(sorpresaPagarCobrar1);
        
        String cobrar2 = "Al ir al supermercado a comprar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR3 + ".\n";
        Sorpresa sorpresaPagarCobrar2 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, cobrar2, SORPRESAPAGARCOBRAR3);
        mazo.alMazo(sorpresaPagarCobrar2);
        
        String pagarCasaHotel1 = "Has empezado el dia con una llamada telefonica. Te han dicho que hay\n"
                + "obras por culpa de los conductos de extracción de humos en los edificios\n"
                + "Paga " + SORPRESAPORCASAHOTEL2 + ".\n";
        Sorpresa sorpresaPorCasaHotel1 = new Sorpresa(TipoSorpresa.PORCASAHOTEL, pagarCasaHotel1, -SORPRESAPORCASAHOTEL2);
        mazo.alMazo(sorpresaPorCasaHotel1);
        
        String pagar1 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente \n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR2 + ".\n";
        Sorpresa sorpresaPagarCobrar3 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, pagar1, -SORPRESAPAGARCOBRAR2);
        mazo.alMazo(sorpresaPagarCobrar3);
        
        String pagarCasaHotel2 = "Has empezado el día con una llamanda telefónica. Te han dicho que hay\n"
                + " obras por culpa de los conductos de extracción de humos en los edificios.\n"
                + "Paga " + SORPRESAPORCASAHOTEL1 + ".\n";
        Sorpresa sorpresaPorCasaHotel2 = new Sorpresa(TipoSorpresa.PORCASAHOTEL, pagarCasaHotel2, -SORPRESAPORCASAHOTEL1);
        mazo.alMazo(sorpresaPorCasaHotel2);
        
        String cobrar3 = "Al ir al supermercado a compar leche compraste un boleto de loteria,\n"
                + "hoy lo has comprobado y ¡el premio es tuyo!\n" + "Cobra " + SORPRESAPAGARCOBRAR2 + ".\n";
        Sorpresa sorpresaPagarCobrar4 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, cobrar3, SORPRESAPAGARCOBRAR2);
        mazo.alMazo(sorpresaPagarCobrar4);
        
        String pagar2 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente,\n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR1 + ".\n";
        Sorpresa sorpresaPagarCobrar5 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, pagar2, -SORPRESAPAGARCOBRAR1);
        mazo.alMazo(sorpresaPagarCobrar5);
        
        String cobrarCasaHotel1 = "¡Hoy es tu día de de suerte! Has ido al banco y por ser tan buen cliente\n"
                + "han decidido hacerte un regalo.\n" + "Cobra " + SORPRESAPORCASAHOTEL2 + ".\n";
        Sorpresa sorpresaPorCasaHotel3 = new Sorpresa(TipoSorpresa.PORCASAHOTEL, cobrarCasaHotel1, SORPRESAPORCASAHOTEL2);
        mazo.alMazo(sorpresaPorCasaHotel3);
        
        String pagar3 = "Vas a ir de viaje en coche a casa de tus padres pero no arranca. Desgraciadamente \n"
                + "tienes que llevarlo al taller.\n" + "Pagas " + SORPRESAPAGARCOBRAR3 + ".\n";
        Sorpresa sorpresaPagarCobrar6 = new Sorpresa(TipoSorpresa.PAGARCOBRAR, pagar3, -SORPRESAPAGARCOBRAR3);
        mazo.alMazo(sorpresaPagarCobrar6);
        
        String cobrarCasaHotel2 = "¡Hoy es tu día de de suerte! Has ido al banco y por ser tan buen cliente\n"
                + "han decidido hacerte un regalo.\n" + "Cobra " + SORPRESAPORCASAHOTEL1 + ".\n";
        Sorpresa sorpresaPorCasaHotel4 = new Sorpresa(TipoSorpresa.PORCASAHOTEL, cobrarCasaHotel2, SORPRESAPORCASAHOTEL1);
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
    
    private void pasarTurno() {
        int pasar =  (getIndiceJugadorActual()+1) % jugadores.size();
        indiceJugadorActual = pasar;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion) {
        estado = gestor.siguienteEstado(jugadores.get(getIndiceJugadorActual()), estado, operacion);
    }
    
    public boolean construirCasa(int ip) {
        jugadores.get(getIndiceJugadorActual()).construirCasa(ip);
        return true;
    }
    
    public boolean construirHotel(int ip) {
        jugadores.get(getIndiceJugadorActual()).construirHotel(ip);
        return true;
    }
    
    public boolean finalDelJuego() {
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).enBancarrota()) {
                return true;
            }
        }
        return false;
    }
    
    //mal hecho, sin terminar;
    private ArrayList<Jugador> ranking() {
        Collections.sort(jugadores);
        return jugadores;
    }
    
    private void contabilizarPasosPorSalida() {
        if (tablero.computarPasoPorSalida()) {
            jugadores.get(getIndiceJugadorActual()).pasaPorSalida();
        }
    }
    
    //metodos a implementar en la práctica siguiente.
    /*
    private void avanzaJugador() {
        
    }
    
    public OperacionJuego siguientePaso() {
        
    }
    
    public boolean comprar() {
    
    }
    */
    
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
            System.out.println("el jugador " + prueba.getJugadores().get(i) + "es el numero " + i + " con " + prueba.getJugadores().get(i).getSaldo());         
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
}

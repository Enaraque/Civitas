/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author enriquearaqueespinosa
 */
import java.util.ArrayList;
import civitas.CivitasJuego;
import vistaTextualCivitas.VistaTextual;
import controladorCivitas.Controlador;

public class JuegoTexto {
    public static void main(String[] args) {
        String jugador1 = "Gador", jugador2 = "Christian", jugador3 = "Jorge", jugador4 = "Enrique";
        ArrayList<String> jugadores = new ArrayList<>();
        jugadores.add(jugador1);
        jugadores.add(jugador2);
        jugadores.add(jugador3);
        jugadores.add(jugador4);
        
        CivitasJuego juego = new CivitasJuego(jugadores, false);
        VistaTextual vista = new VistaTextual(juego);
        Controlador controlador = new Controlador(juego, vista);
        controlador.juega();
    }
}

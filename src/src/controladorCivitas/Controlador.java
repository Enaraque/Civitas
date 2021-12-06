/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladorCivitas;

import civitas.CivitasJuego;
import civitas.OperacionJuego;
import civitas.OperacionInmobiliaria;
import civitas.GestionInmobiliaria;
import vistaTextualCivitas.Vista;

/**
 *
 * @author enriquearaqueespinosa
 */
public class Controlador {
    private CivitasJuego juego;
    private Vista vista;
    
     public Controlador(CivitasJuego juego, Vista vista) {
        this.juego = juego;
        this.vista = vista;
    }
    
    public void juega() {
        vista.mostrarEventos();
        
        while(!juego.finalDelJuego()) {
            vista.actualiza();
            vista.pausa();
            OperacionJuego aux = juego.siguientePaso();
            vista.mostrarSiguienteOperacion(aux);
            
            if (aux != OperacionJuego.PASAR_TURNO) {
                vista.mostrarEventos();
            }
            
            if(!juego.finalDelJuego()) {
                switch (aux) {
                    case COMPRAR :
                        Respuesta resp = vista.comprar();
                        if (resp == Respuesta.SI) {
                            juego.comprar();
                        }
                        juego.siguientePasoCompletado(aux);
                        break;
                    case GESTIONAR :
                        OperacionInmobiliaria opIn = vista.elegirOperacion();
                        if (opIn != OperacionInmobiliaria.TERMINAR) {
                            int propiedadElegida = vista.elegirPropiedad();
                            GestionInmobiliaria gestIn = new GestionInmobiliaria(opIn, propiedadElegida);
                            if (gestIn.getOperacion() == OperacionInmobiliaria.CONSTRUIR_CASA) {
                                juego.construirCasa(gestIn.getPropiedad());
                            }
                            else {
                                juego.construirHotel(gestIn.getPropiedad());
                            }
                        }
                        else {
                            juego.siguientePasoCompletado(aux);
                        }
                        break;                        
                }
            }
            else {
                vista.actualiza();
            }
        }
    }
}

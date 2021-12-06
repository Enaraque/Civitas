/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import GUI.CapturaNombres;
import GUI.CivitasView;
import controladorCivitas.Controlador;
import java.util.ArrayList;

/**
 *
 * @author jorgelerre
 */
public class TestP5 {
    public static void main(String[] args) {
        /*
        Para probar si se hace bien la conversión te aconsejamos que crees una clase de prueba llamada
        TestP4, con un método main que cree un Jugador, le asocie una propiedad, y luego lo convierta a
        JugadorEspeculador y lo muestre. Comprueba que la propiedad que se le asocia cambia de
        propietario al hacer la conversión. ¿Qué debería pasar si tratáramos de convertir a un
        JugadorEspeculador? Debes redefinir el método de conversión de la forma que consideres más
        adecuada.
        */
        final boolean MODODEBUG = false;
        CivitasView vista = new CivitasView();
        ArrayList<String> jugadores = new ArrayList<>();
        
        CapturaNombres captura1 = new CapturaNombres(vista, true);
        jugadores = captura1.getNombres();
        
        CivitasJuego modelo = new CivitasJuego(jugadores, MODODEBUG);
        Controlador controlador = new Controlador(modelo, vista);
        vista.SetCivitasJuego(modelo);
        
        controlador.juega();
    }
}

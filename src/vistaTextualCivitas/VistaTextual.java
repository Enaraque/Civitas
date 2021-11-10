package vistaTextualCivitas;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.OperacionJuego;
import controladorCivitas.Respuesta;
import civitas.OperacionInmobiliaria;
import civitas.Jugador;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;



public class VistaTextual implements Vista {
  
    private int ULTIMO_EVENTO_MOSTRADO;
    private static String separador = "=====================";
  
    private Scanner in;

    CivitasJuego juegoModel;

    public VistaTextual (CivitasJuego juegoModel) {
      in = new Scanner (System.in);
      this.juegoModel=juegoModel;
      ULTIMO_EVENTO_MOSTRADO = 0;
    }



    public  void pausa() {
       System.out.print ("\nPulsa ENTER para continuar:");
       in.nextLine();
    }

    int leeEntero (int max, String msg1, String msg2) {
        Boolean ok;
        String cadena;
        int numero = -1;
        do {
            System.out.print (msg1);
            cadena = in.nextLine();
            try {  
                numero = Integer.parseInt(cadena);
                ok = true;
            } catch (NumberFormatException e) { // No se ha introducido un entero
                System.out.println (msg2);
                ok = false;  
            }
            if (ok && (numero < 0 || numero >= max)) {
                System.out.println (msg2);
                ok = false;
            }
          } while (!ok);

        return numero;
    }

    int menu (String titulo, ArrayList<String> lista) {
        String tab = "  ";
        int opcion;
        System.out.println (titulo);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println (tab+i+"-"+lista.get(i));
        }

        opcion = leeEntero(lista.size(),
                            "\n"+tab+"Elige una opción: ",
                            tab+"Valor erróneo");
        return opcion;
    }

    public void actualiza() {
        if (juegoModel.finalDelJuego()) {
            juegoModel.ranking();
            System.out.println("EL JUGADOR GANADOR ES: " + juegoModel.getJugadores().get(0).toString());

             for (int i = 1; i < juegoModel.getJugadores().size(); i++) {
                System.out.println(juegoModel.getJugadores().get(i).toString() + "\n es el número " + (i+1));
            }
        }
        else {
            System.out.println(juegoModel.getJugadorActual().toString()+ ".");
            int n_propiedades = juegoModel.getJugadorActual().getPropiedades().size();
            if(n_propiedades > 0){
                System.out.println("Propiedades del jugador:");
                for (int i = 0; i < n_propiedades; i++) {
                    System.out.println("\t" + juegoModel.getJugadorActual().getPropiedades().get(i).toString() + "\n");
                }
            }
        }
    }

    public Respuesta comprar() {        
        Respuesta resp;
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("NO");
        opciones.add("SI");
        String texto = new String("¿Desea comprar la calle?");
        int elegido = menu(texto, opciones);
        
        if (elegido == 0) {
            resp = Respuesta.NO;
        }
        else {
            resp = Respuesta.SI;
        }
        
        return resp;
    }

    public OperacionInmobiliaria elegirOperacion() {
        OperacionInmobiliaria resp;
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("CONSTRUIR_CASA");
        opciones.add("CONSTRUIR_HOTEL");
        opciones.add("TERMINAR");
        String texto = "¿Qué tipo de gestión inmobiliaria desea hacer?";
        int elegido = menu(texto, opciones);
        
        if (elegido == 0) {
            resp = OperacionInmobiliaria.CONSTRUIR_CASA;
        }
        else if (elegido == 1) {
            resp = OperacionInmobiliaria.CONSTRUIR_HOTEL;
        }
        else {
            resp = OperacionInmobiliaria.TERMINAR;
        }
        
        return resp;    
    }

    public int elegirPropiedad() {
        //necesito volver a pedirle a jugador las propiedades pero al estar protected no puedo
        ArrayList <String> opciones = new ArrayList<String>();
        for (int i = 0; i < juegoModel.getJugadorActual().getPropiedades().size(); i++) {
            String propiedad = juegoModel.getJugadorActual().getPropiedades().get(i).toString();
            opciones.add(propiedad);
        }        
        String texto = "¿Sobre qué propiedad desea hacer la gestión?";
        int elegido = menu(texto, opciones);
        
        return elegido;
    }

    public void mostrarSiguienteOperacion(OperacionJuego operacion) {
        System.out.println("La siguiente operación que se va a realizar es " + operacion);
    }

    public void mostrarEventos() {
        if(Diario.getInstance().eventosPendientes())
            System.out.println("No hay eventos que mostrar");
        else{
            int cont = 0;
            do{
                System.out.println("Evento número " + cont + ":      " + Diario.getInstance().leerEvento());
                cont++;
            }while(Diario.getInstance().eventosPendientes());
        }
    }
}

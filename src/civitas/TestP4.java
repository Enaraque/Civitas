/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author jorgelerre
 */
public class TestP4 {
    public static void main(String[] args) {
        /*
        Para probar si se hace bien la conversión te aconsejamos que crees una clase de prueba llamada
        TestP4, con un método main que cree un Jugador, le asocie una propiedad, y luego lo convierta a
        JugadorEspeculador y lo muestre. Comprueba que la propiedad que se le asocia cambia de
        propietario al hacer la conversión. ¿Qué debería pasar si tratáramos de convertir a un
        JugadorEspeculador? Debes redefinir el método de conversión de la forma que consideres más
        adecuada.
        */
        
        //Creamos un jugador y una calle
        Jugador jugador = new Jugador("Jorge");
        CasillaCalle calle1 = new CasillaCalle("Calle Comandante Cousteau", 100, 100, 50);
        
        
        System.out.println("Creación de objetos Jugador y CasillaCalle.");
        System.out.println(jugador.toString());
        System.out.println(calle1.toString());
        
        //El jugador compra la casilla
        jugador.puedeComprarCasilla();
        jugador.comprar(calle1);
        
        System.out.println("\n\nCompra de "+calle1.getNombre()+" por parte de "+jugador.getNombre());
        System.out.println(jugador.toString());
        System.out.println(calle1.toString());
        
        //El jugador se convierte en JugadorEspeculador
        JugadorEspeculador jugador_nuevo = jugador.convertir();
        System.out.println("\n\nEl jugador "+jugador.getNombre()+" se convierte en JugadorEspeculador.");
        System.out.println("Jugador nuevo: " +jugador_nuevo.toString());
        if(calle1.esEsteElPropietario(jugador_nuevo))
            System.out.println("El nuevo objeto JugadorEspeculador es el propietario de "+calle1.getNombre()+".");
        else if(calle1.esEsteElPropietario(jugador))
            System.out.println("El objeto Jugador antiguo sigue siendo el propietario de "+calle1.getNombre()+".");
        
        //Probamos que es seguro el constructor de copia de JugadorEspeculador.
        JugadorEspeculador otro_nuevo = jugador_nuevo.convertir();
        System.out.println("Jugador nuevo tras segunda conversión:\n" +otro_nuevo.toString());
    }
}

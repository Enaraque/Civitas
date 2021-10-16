/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;


/**
 *
 * @author enriquearaqueespinosa
 */
public class Casilla {
    static float FACTORALQUILERCALLE = 1.0f;
    static float FACTORALQUILERCASA = 1.0f;
    static float FACTORALQUILERHOTEL = 4.0f;
    
    private TipoCasilla tipo;
    private String nombre;
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    public Casilla (TipoCasilla unTipo, String unNombre,
            float unPrecioCompra, float unPrecioEdificar, float unPrecioAlquilerBase) {
        
        tipo= unTipo;
        nombre = unNombre;
        precioCompra = unPrecioCompra;
        precioEdificar = unPrecioEdificar;
        precioBaseAlquiler = unPrecioAlquilerBase;
        numCasas = numHoteles = 0;   
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
    
    public String toString() {
        return  (tipo +" "+ nombre+".Precios: Compra:"+ precioCompra+", Edificar: + "
                + precioEdificar+", Alquiler base: "+ precioBaseAlquiler+", Casas: "+ numCasas +", Hoteles: "+ numHoteles);
    }
    
}

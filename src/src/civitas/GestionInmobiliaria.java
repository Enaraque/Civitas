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
public class GestionInmobiliaria {
    private int propiedad;
    private OperacionInmobiliaria operacion;
    
    public GestionInmobiliaria(OperacionInmobiliaria gest, int ip) {
        operacion = gest;
        propiedad = ip;       
    }
    
    public OperacionInmobiliaria getOperacion() {
        return operacion;
    }
    
    public int getPropiedad() {
        return propiedad;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author jorgelerre
 */
public class JugadorEspeculador extends Jugador{
    protected static final int FACTORESPECULADOR = 2;
    
    protected JugadorEspeculador(Jugador otro){
        super(otro);
        if(otro.getClass() != JugadorEspeculador.class){
            actualizaPropiedadesPorConversion();
        }
    }
    
    private void actualizaPropiedadesPorConversion(){
        for(int i = 0; i < this.getPropiedades().size(); i++){
            this.getPropiedades().get(i).actualizaPropiedadesPorConversion(this);
        }
    }
    
    @Override
    public String toString(){
        String info = "$$$$JUGADOR ESPECULADOR$$$$\n"+super.toString();
        return info;
    }
    
    @Override
    boolean paga(float cantidad){
        return this.modificaSaldo(-cantidad/FACTORESPECULADOR);
    }
    
    boolean puedoGastar(float precio){
        return this.getSaldo() >= precio/FACTORESPECULADOR;
    }
    
    @Override
    protected int getCasasMax(){
        return super.getCasasMax()*FACTORESPECULADOR;
    }
    
    @Override
    protected int getHotelesMax(){
        return super.getHotelesMax()*FACTORESPECULADOR;
    }
    
    @Override
    protected JugadorEspeculador convertir(){
        return this;
    }
    
}


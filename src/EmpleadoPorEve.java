// CLASE EMPLEADO POR EVENTO
// ALONDRA MYRIEL GUTIERREZ VILLEGAS
// MATERIA  POO
import java.io.Serializable;
public class EmpleadoPorEve extends Empleado implements Deducible, Serializable{
     private double sueldoEv;
    private double impuesto;

    public EmpleadoPorEve(String nombre, String direccion, String rfc, String cargo, double sueldoEv) {
        super(nombre, direccion, rfc, cargo);
       this.sueldoEv = sueldoEv;
       this.impuesto = sueldoEv*0.07;
    }

    public void setSueldo(double sueldoEv) {
        this.sueldoEv = sueldoEv;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getSueldo() {
        return sueldoEv;
    }

    public double getImpuesto() {
        return sueldoEv*0.06;
    }
    
    @Override
    public double calcularSueldoCobrar(){
    double sueldoBruto = sueldoEv + calcularPrestaciones() -calcularDeducibles();
    return sueldoBruto - calcularImpuesto(sueldoBruto);
    }
    
    public double calcularPrestaciones(){
    return 0;
    }
    
    public double calcularDeducibles(){
    double impu = impuesto;
    return impu;
    }
    
    @Override
    public double calcularImpuesto(double cantidad){
    return cantidad*TasaImpuesto/100;
    }
    
    @Override
    public String toString(){
    String cadena = super.toString() + "\nSueldo por Evento: "+sueldoEv+ "\nDEDUCIBLES: "+"\nPrestaciones Totales: "+calcularPrestaciones()+"\nImpuestos:"+impuesto+"\nDeducibles Totales: "+calcularDeducibles();
    return cadena;
    }
}// fin de la clase

/*
CLASE EMPLEADO POR HORA
ALONDRA MYRIEL GUTIERREZ VILLEGAS GRUPO: XF   MATERIA: POO

 */
import java.io.Serializable;
public class EmpleadoPorHora extends Empleado implements Deducible, Serializable{
private double sueldoBase;
private int HorasTra;
private double Bono;
private double impuesto;
private double sueldo;



    public EmpleadoPorHora(String nombre, String direccion, String rfc, String cargo, double sueldo, int HorasTra) {
        super(nombre, direccion, rfc, cargo);
        this.sueldo = sueldo;
        this.HorasTra = HorasTra;
        this.sueldoBase = sueldo*HorasTra;
        this.Bono = sueldoBase*0.06;
        this.impuesto = sueldoBase*0.05;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public int getHorasTra() {
        return HorasTra;
    }

    public void setHorasTra(int HorasTra) {
        this.HorasTra = HorasTra;
    }

    public double getBono() {
        return Bono;
    }

    public void setBono(double Bono) {
        this.Bono = Bono;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(double impuesto) {
        this.impuesto = impuesto;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

 
    @Override
    public double calcularSueldoCobrar(){
    double sueldoBruto = sueldoBase + calcularPrestaciones() -calcularDeducibles();
    return sueldoBruto - calcularImpuesto(sueldoBruto);
    }
    
    public double calcularPrestaciones(){
    return Bono*1;
    }
    
    public double calcularDeducibles(){
    return impuesto*1;
    }
    
    @Override
    public double calcularImpuesto(double cantidad){
    return cantidad*TasaImpuesto/100;
    }
    
    
    @Override
    public String toString(){
    String cadena = super.toString() + "\nDEDUCIBLES:"+"\nImpuesto: "+impuesto+"\nDeducibles Totales: "+calcularDeducibles() + "\nSueldo por hora: "+sueldo+"\nHoras trabajadas: " 
            +HorasTra+"\nSueldo base del empleado: "+sueldoBase+"\nPRESTACIONES:"+"\nBono por productividad: "+Bono+"\nPrestaciones Totales: "+calcularPrestaciones();
            
    return cadena;
    }
    


}  


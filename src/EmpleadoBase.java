/*
CLASE EMPLEADO BASE
ALONDRA MYRIEL GUTIERREZ VILLEGAS
MATERIA: POO
GRUPO: XF
 */
import java.io.Serializable;
public class EmpleadoBase extends Empleado implements Deducible, Serializable  {
    private double sueldoBase;
    private double ayudaRenta; //esta es una prestacion
    private double seguroMedico;// este es un deducible
    private double Prima;
    private double Pension;
    private double Antiguedad;
    private double sindicato;
    private double segurodeVida;
    private int fechadeIngreso;
    
    

// constructores
    public EmpleadoBase(String nombre, String direccion, String rfc, String cargo, double sueldoBase, int fechadeIngreso){
        super(nombre, direccion, rfc, cargo);
        this.sueldoBase = sueldoBase; // es el 5 del sueldo base como ayuda de rena
        this.ayudaRenta = sueldoBase*0.05;
        this.seguroMedico = sueldoBase*0.07;
        this.Prima = sueldoBase*0.05;
        this.Pension = sueldoBase*0.09;
        this.Antiguedad = sueldoBase*0.03;
        this.sindicato = sueldoBase*0.05;
        this.segurodeVida = sueldoBase*0.10;
        
    }
 
    //set y get

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    public double getAyudaRenta() {
        return ayudaRenta;
    }

    public void setAyudaRenta(int porcAyudaRenta) {
        this.ayudaRenta = sueldoBase*porcAyudaRenta/100;
    }

    public double getSeguroMedico() {
        return seguroMedico;
    }

    public void setSeguroMedico(int porGastosMedicos) {
      this.seguroMedico = sueldoBase*porGastosMedicos/100;
        
    }

    public double getPrima() {
        return Prima;
    }

    public void setPrima(double Prima) {
        this.Prima = Prima;
    }

    public double getPension() {
        return Pension;
    }

    public void setPension(double Pension) {
        this.Pension = Pension;
    }

    public double getAntiguedad() {
        return Antiguedad;
    }

    public void setAntiguedad(double Antiguedad) {
        this.Antiguedad = Antiguedad;
    }

    public double getSindicato() {
        return sindicato;
    }

    public void setSindicato(double sindicato) {
        this.sindicato = sindicato;
    }

    public double getSegurodeVida() {
        return segurodeVida;
    }

    public void setSegurodeVida(double segurodeVida) {
        this.segurodeVida = segurodeVida;
        
    }

    public double getFechadeIngreso() {
        return fechadeIngreso;
    }

    public void setFechadeIngreso(int fechadeIngreso) {
        this.fechadeIngreso = fechadeIngreso;
    }
    // otros metodos
    
    @Override
    public double calcularSueldoCobrar()
    {
        double sueldoBruto = sueldoBase + calcularPrestaciones() - calcularDeducibles();
        return sueldoBruto + calcularImpuesto(sueldoBruto);
    }
    public double calcularPrestaciones()
    {
        return Prima+segurodeVida+Pension+ayudaRenta;
        
    }
    public double calcularDeducibles()
    {
        return Pension+sindicato+Antiguedad ;
    }
    
     @Override
    public double calcularImpuesto(double cantidad){
    return cantidad*TasaImpuesto/100;
    }
 
    @Override
    public String toString(){
    String cadena = super.toString() +"\nFECHA DE INGRESO:" +fechadeIngreso+ "años" + "\nAntiguedad: "
      +Antiguedad+"\nSUELDO BASE A PAGAR: " +sueldoBase + "\nPRIMAS VACACIONALES: " +Prima +"\n AYUDA RENTA: " + ayudaRenta + "\nSEGURO MEDICO: "
      +seguroMedico + "\nPENSION: " + Pension+ "\nSINDICATO: " + sindicato + "\nSEGURO DE VIDA: " + segurodeVida+
            "\nDEDUCIBLES: "+calcularDeducibles()+"\nSUELDO FINAL( - impuestos): "+ calcularSueldoCobrar();
   return cadena;     
    }
          
}// fin de la clase

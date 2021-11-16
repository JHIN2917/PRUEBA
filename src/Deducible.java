/*
esta interface DEDUCIBLE PARA LA APLICACION APP EMPLEADO

 */

/**
 *
 * @author ALONDRA MYRIEL GUTIERREZ VILLEGAS
 */

public interface Deducible  {
    //no tiene variables de instancia
    // no tienen variables de clase
    
    //puede contener algunas constantes
    final double TasaImpuesto = 20;
    // no tiene constructores
    //no tiene metodos set y gte
    // no tiene metodo toString
    //lista de metodos abstractos
    
    
    
    
    public abstract double calcularImpuesto(double cantidad);
    
}

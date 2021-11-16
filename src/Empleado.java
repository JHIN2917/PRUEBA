/*
 ALONDRA MYRIEL GUTIERREZ VILLEGAS
CLASE EMPLEADO
 */
import java.io.Serializable;
abstract public class Empleado implements Serializable{
    //variables de instancia
    private String nombre;
    private String direccion;
    private String rfc;
    private String cargo;
    
    //variables de clase
    private static int numEmpleados = 0;
    private static String empresa = "Cuidado con el gato SA de CV ";
    
    //constructores
    public Empleado(String nombre, String direccion, String rfc, String cargo){
            this.nombre = nombre;
            this.direccion = direccion;
            this.rfc = rfc;
            this.cargo = cargo;
            numEmpleados++;        
    
     }                
// metodos set y

    public String getCargo() {
        return nombre;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public static int getNumEmpleados() {
        return numEmpleados;
    }

    public static void setNumEmpleados(int numEmpleados) {
        Empleado.numEmpleados = numEmpleados;
    }

    public static String getEmpresa() {
        return empresa;
    }

    public static void setEmpresa(String empresa) {
        Empleado.empresa = empresa;
    }
   
    //otros metodos
    @Override 
    public String toString()
    {
        String cadena = "Empleado\n Nombre:" + nombre +"\n RFC:" + rfc + "\nDireccion:" + direccion + "\nCARGO:" + cargo;
        return cadena;
    }
    public abstract double calcularSueldoCobrar();
}

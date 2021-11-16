// APLICACION QUE MUESTRA LA NOMICA DE DISTINTOS TIPOS DE EMPLEADOS
// QUE PERMITE, CREAR, MODIFICAR, BUSCAR, MOSTRAR Y ELIMINAR UN REGISTRO DE CADA EMPLEADO
// Con ahora escribe y crea un archivo donde se guardan todos los datos que se ingresa en la aplicacion.
// ALONDRA MYRIEL GUTIERREZ VILLEGAS MATERIA: POO  GRUPO: XF
// 17/ 06/ 2021


import javax.swing.JOptionPane;
import java.io.*;
public class AppEmpleado{
    
    static int numEmpleados = 0;// variable pasa saber el numero actual de objetos que se tienen
    static Object[] menuEmpleados = {"Empleados BASE", "Empleados HORA", "Empleados EVENTUALES", "Salir"};
public static void main(String [] ars)
{
     // crear el arreglo para almacenar las figuras
         Empleado[] listaEmpleados; // arreglo polimorfico
        JOptionPane.showMessageDialog(null,"Hola Bienvenido a la aplicacion de tu nomina!");
        Object[] menuPrincipal = {"Crear registro",  "Modificar registro","Buscar registro", "Mostrar registro","Eliminar registro", "Salir"}; 
        String opcion;
        File archivo;
        archivo = identificaArchivo();
        numEmpleados = getSize(archivo);
        listaEmpleados = leerArchivo(archivo);
        
        do{
            opcion = leerOpcion("Menu: ", menuPrincipal);
            switch(opcion){ 
                case "Crear registro":
                    crearEmpleado(listaEmpleados); 
                break;
                
                case "Modificar registro": 
                    Object[] menuModificacion = {"Modificar empleado hora", "Modificar empleado base","Modificar empleado eventual","Salir"};
                    String opcionx = leerOpcion("Que empleado se modificará: ", menuModificacion);
                    switch(opcionx){
                        case "Modificar empleado base":
                            modificarEmpleadoB(listaEmpleados);
                        break;
                        
                        case "Modificar empleado hora":
                            modificarEmpleadoH(listaEmpleados);
                        break;
                        
                        case "Modificar empleado eventual":
                            modificarEmpleadoE(listaEmpleados);
                        break;
                        
                        case "Salir":
                            JOptionPane.showMessageDialog(null,"Regresando al menú anterior");
                        break;
                    }
                
                     break;
                
                case "Eliminar registro":
                    eliminarEmpleado(listaEmpleados); //elimina empleados
                break;
                
                case "Buscar registro":
                    buscarEmpleado(listaEmpleados); //busca registro o empleado
                break;
                
                case "Mostrar registro":
                    mostrarEmpleado(listaEmpleados); //muestra los empleados
                break;
                
        
            }
        }while(opcion.compareTo("Salir") != 0);
        guardarDatos(archivo, listaEmpleados);
     
}// fin del main
    public static void guardarDatos(File arch,  Empleado[] listaEmpleados) {
        FileOutputStream FOS = null;
        ObjectOutputStream OOS = null;
        try {
            FOS = new FileOutputStream(arch.getName());
              OOS = new ObjectOutputStream(FOS);
            for (int c = 0; c < listaEmpleados.length; c++) {
                if (listaEmpleados[c] != null) {
                    OOS.writeObject(listaEmpleados[c]);
                }
            }
        } catch (FileNotFoundException vE) {

            System.out.println( "El archivo no existe");

        } catch (IOException vE) {

            System.out.println(  "Error en el archivo");

        } finally {

            try {
                if (FOS != null) {
                    OOS.close();
                    FOS.close();
                }
            } catch (IOException vE) {
                System.out.println( "Error en el archivo");
            }

        }// fin de finally
        
    }// fin de guardarDatos
    
    
  public static int getSize(File arch) {
        FileInputStream FIS = null;
        ObjectInputStream OIS = null;
        int cont = 0;
        try {
            FIS = new FileInputStream(arch);
            OIS = new ObjectInputStream (FIS);
            Object dato = null;
            do {
                dato = OIS.readObject();
               
                if (dato != null) {
                    cont++;
                }
            } while (dato != null);
           
        } // fin de try
        catch (ClassNotFoundException vE)
        { System.out.println("La clase de objeto no existe"); }
        catch (FileNotFoundException vA) {
            System.out.println( "El archivo no existe");
        } catch (EOFException vO) {
            System.out.println( "Se ha llegado al final del archivo");
        } catch (IOException vI) {
            System.out.println( "Error al acceder al archivo...");
        } finally {

            try {
                if (FIS != null) {
                    OIS.close();
                    FIS.close();
                }
            } catch (IOException vE) {
                System.out.println( "Error en el archivo");
            }
            return cont;
        }
    } // fin de getSize

    public static  Empleado[] leerArchivo(File arch)
    {
        FileInputStream FIS = null;
        ObjectInputStream OIS = null;
         Empleado[] lista = null;
        int cont = getSize(arch);
        try 
            {FIS = new FileInputStream(arch);
                OIS = new ObjectInputStream(FIS);
                lista = new  Empleado[cont + 20];
                cont = 0;
                 Empleado dato = null;
                do 
                {
                    dato = ( Empleado) OIS.readObject();
                    if (dato != null)
                    {
                        lista[cont] = dato;
                        cont++;
                    }
                }while (dato != null);
        }// fin de try
        catch (ClassNotFoundException vE)
        { System.out.println("La clase Empleados no existe no existe");}
        catch (FileNotFoundException vA) {
            System.out.println( "El archivo no existe");
        }catch (EOFException vO) {
           System.out.println( "Se ha llegado al final del archivo");
        }catch (IOException vI) {
            System.out.println("Error al acceder al archivo...");
        }finally {

            try {
                if (FIS != null) {
                    OIS.close();
                    FIS.close();
                }// fin de try
            } catch (IOException vE) {
                System.out.println( "Error en el archivo");
            }
        } 
        return lista;
    } // fin de leer archivo
    public static File identificaArchivo() {
        File arch = null;
        char resp;
        do {
            String nombreArchivo =JOptionPane.showInputDialog("Nombre del archivo: ");
            arch = new File(nombreArchivo);
            if (arch.exists()) {
                if (arch.isFile()) {
                    resp = leerRespuestaSiNo("El archivo ya existe,Deseas continuar S/N?");
                    if (resp == 'N' || resp == 'n') {
                        arch = null;
                    } else {
                        JOptionPane.showMessageDialog(null, "Ese nombre de archivo es un directorio");
                        
                    }
                }

            }

        } while (arch == null);
        return arch;
    } // fin de identifica archivo
    
     public static char leerRespuestaSiNo(String mensaje) {
        char resp = '\0';
        do {
            String cad = JOptionPane.showInputDialog(mensaje);
            resp = cad.charAt(0);
        } while (resp != 'S' && resp != 's' && resp != 'N' && resp != 'n');
        return resp;
    } //fin de leer respuesta si no
    
    //Con este metodo elegiremos que persona es la que quermos crear
public static void crearEmpleado(Empleado[] lista){
        String opc;
       
        do{
            opc = leerOpcion("Elige el tipo de empleados que deseas registrar ", menuEmpleados);
            switch(opc){
                case "Empleados BASE": 
                    lista[numEmpleados] = crearEmpleadoBase();
                    numEmpleados++;
                break;
                
                case "Empleados HORA":
                    lista[numEmpleados] = crearEmpleadoHora();
                    numEmpleados++;
                break;
            
                case "Empleados EVENTUALES":
                    lista[numEmpleados] = crearEmpleadoEventual();
                    numEmpleados++;
                break;
                         
                default: JOptionPane.showMessageDialog(null, "Regresando al menu anterior");
            }
        }while(opc.compareTo("Salir")!= 0);
    
}
        

public static void modificarEmpleadoB(Empleado[] lista){
        int pos = buscarEmpleado(lista);
        try{
        if(lista[pos]instanceof EmpleadoBase){
            EmpleadoBase EMPLEADO = (EmpleadoBase)lista[pos];
            JOptionPane.showMessageDialog(null, "Hola, por favor vuelva a ingresar los nuevos datos");
                    EMPLEADO.setNombre(leerString("Ingrese el nombre del nuevo empleado"));
                    EMPLEADO.setDireccion(leerString("ingrese la dirección del nuevo empleado"));
                    EMPLEADO.setRfc(leerString("ingrese el RFC"));
                    EMPLEADO.setCargo(leerString("ingrese el nuevo cargo"));
                    EMPLEADO.setFechadeIngreso(leerEntero("Ingrese la fecha de ingreso"));
                    EMPLEADO.setSueldoBase(leerDouble("Cual es el sueldo base del empleado"));        
        }
        }catch(ArrayIndexOutOfBoundsException nxd){
             JOptionPane.showMessageDialog(null,"Estas regresando al menu anterior...");
        }
    }//fin metodo

public static void modificarEmpleadoH(Empleado[] lista){
        int pos = buscarEmpleado(lista);
        try{
        if(lista[pos]instanceof EmpleadoPorHora){
            EmpleadoPorHora EMPLEADO = (EmpleadoPorHora)lista[pos];
            JOptionPane.showMessageDialog(null, "Hola, por favor vuelva a ingresar los nuevos datos");
                    EMPLEADO.setNombre(leerString("Ingrese el nombre del nuevo empleado"));
                    EMPLEADO.setDireccion(leerString("Ingrese la direccion"));
                    EMPLEADO.setRfc(leerString("ingrese el RFC"));
                    EMPLEADO.setCargo(leerString("ingrese el nuevo cargo"));
                    EMPLEADO.setSueldo(leerDouble("Cual es el sueldo por hora"));
                    EMPLEADO.setHorasTra(leerEntero("Cuantas horas tarbaja el nuevo empleado"));
        }
        }catch(ArrayIndexOutOfBoundsException nxd){
             JOptionPane.showMessageDialog(null,"Estas regresando al menu anterior...");
        }
    }//fin metodo

public static void modificarEmpleadoE(Empleado[] lista){
        int pos = buscarEmpleado(lista);
        try{
        if(lista[pos]instanceof EmpleadoPorEve){
            EmpleadoPorEve EMPLEADO = (EmpleadoPorEve)lista[pos];
            JOptionPane.showMessageDialog(null, "Hola, por favor vuelva a ingresar los nuevos datos");
                    EMPLEADO.setNombre(leerString("Ingrese nombre del empleado"));
                    EMPLEADO.setDireccion(leerString("Ingrese la direccion"));
                    EMPLEADO.setRfc(leerString("Ingrese el RFC"));
                    EMPLEADO.setCargo(leerString("ingrese el nuevo cargo"));
                    EMPLEADO.setSueldo(leerDouble("Cual es el sueldo por hora"));
        }
        }catch(ArrayIndexOutOfBoundsException nxd){
             JOptionPane.showMessageDialog(null,"Estas regresando al menu anterior...");
        }
    }//fin metodo

public static EmpleadoBase crearEmpleadoBase(){ 
        EmpleadoBase EMPLEADO = null; 
        double sueldoB;
        String nombre,direccion,rfc,cargo;
        int fechaI;
        JOptionPane.showMessageDialog(null, "Hola, por favor ingrese los datos que se solicitan");
                nombre = leerString("Ingrese el nombre del empleado");
                direccion = leerString("Ingrese la direccion del empleado");
                rfc = leerString("Ingrese el RFC del empleado");
                cargo = leerString("Ingrese el cargo del empleado");
                sueldoB = leerDouble("Ingrese el sueldo base del empleado");
                fechaI = leerEntero("Ingrese la fecha de ingreso del empleado");
                EMPLEADO= new EmpleadoBase(nombre,direccion,rfc,cargo,sueldoB,fechaI);
                return EMPLEADO;
}

public static EmpleadoPorHora crearEmpleadoHora(){ 
        EmpleadoPorHora EMPLEADO = null; 
        double sueldo;
        int hora;
        String nombre,direccion,rfc,cargo;
        JOptionPane.showMessageDialog(null, "Hola, por favor ingrese los datos que se solicitan");
                nombre = leerString("Ingrese el nombre del empleado");
                direccion= leerString("Ingrese la direccion del empleado");
                rfc = leerString("Ingrese el RFC del empleado");
                cargo = leerString("Ingrese el cargo o puesto empleado");
                sueldo = leerDouble("Ingrese el sueldo por hora del empleado");
                hora = leerEntero("Ingrese el numero de horas trabajadas del empleado");
                EMPLEADO = new EmpleadoPorHora(nombre,direccion,rfc,cargo,sueldo,hora);
                return EMPLEADO;
}

public static EmpleadoPorEve crearEmpleadoEventual(){
        EmpleadoPorEve EMPLEADO = null; 
        double sueldo;
        String nombre,direccion,rfc,cargo;
        JOptionPane.showMessageDialog(null, "Hola, por favor ingrese los datos que se solicitan");
                nombre = leerString("Ingrese el nombre del empleado");
                direccion = leerString("Ingrese la direccion del empleado");
                rfc = leerString("Ingrese el RFC del empleado");
                cargo = leerString("Ingrese el cargo o puesto empleado");
                sueldo = leerDouble("Ingrese el sueldo por obra del empleado");
                EMPLEADO= new EmpleadoPorEve(nombre,direccion,rfc,cargo,sueldo);
                return EMPLEADO;
}

public static void eliminarEmpleado(Empleado[] lista){
    JOptionPane.showMessageDialog(null, "Desea eliminar el empleado?");
        int pos = buscarEmpleado(lista);
        for(int c = pos; c<numEmpleados-1; c++){
        lista[c] = lista[c+1];
        }
        numEmpleados--;
        
        JOptionPane.showMessageDialog(null,"solicitud exitosa");
    }//fin metodo

public static int buscarEmpleado(Empleado[] lista){ 
        String cadena = generaLista(lista);
        int numF = leerEntero("Introduzca el número de empleado: \n" + cadena);
        return numF-1;
    }//fin metodo

public static String generaLista(Empleado[] lista){
        String cadena = "LISTA DE EMPLEADOS REGISTRADOS \n";
        for(int i = 0; i <numEmpleados; i++){
            if(lista[i]instanceof EmpleadoBase){
                EmpleadoBase X = (EmpleadoBase)lista[i];
                cadena = cadena + (i + 1 ) + ".-" + X.getNombre() +  "\nSueldo base = " + X.getSueldoBase() +  "\nFecha Ingreso = " + X.getFechadeIngreso() +  "\nAntiguedad= " + X.getAntiguedad() + "\nPrimas Vacacionales= " + X.getPrima() +  "\nSeguro de vida= " + X.getSegurodeVida() +  "\nSeguro Medico= " + X.getSeguroMedico() +  "\nAyuda renta= " + X.getAyudaRenta() +  "\nPension= " + X.getPension() + "\nSindicato= " + X.getSindicato()+"\n";
                                                
                                                
            }
            if(lista[i]instanceof EmpleadoPorHora){
                EmpleadoPorHora X = (EmpleadoPorHora)lista[i];
                cadena = cadena + (i + 1) + ".-" + X.getNombre() +  "\nSueldo por hora= " + X.getSueldo()+ "\nHoras trabajadas = " + X.getHorasTra()+
                                            "\nSueldo base = " + X.getSueldoBase()+  "\nBono productivo = " + X.getBono()+ "\nImpuesto = " + X.getImpuesto()+"\n";
            }
            
            if(lista[i]instanceof EmpleadoPorEve){
                EmpleadoPorEve X = (EmpleadoPorEve)lista[i];
                cadena = cadena + (i + 1) + ".-" + X.getNombre() +  "\nSaldo por obra = " + X.getSueldo()+ "\nImpuesto = " + X.getImpuesto()+"\n"; 
            }

            }
        
        return cadena;
    }//fin del metodo

public static void mostrarEmpleado(Empleado[] lista){ //Metodo que muestra empleados
        String opc;
        do{
            opc = JOptionPane.showInputDialog(null,"\n¿Que empleados deseas mostrar?"+"\n1.Empleados base"+"\n2.Empleados hora"+"\n3.Empleados eventuales"+"\nSalir");
            switch(opc){
                case  "Empleados base":
                    for(int i = 0; i < numEmpleados; i++){
                        if(lista[i] instanceof EmpleadoBase){
                            JOptionPane.showMessageDialog(null, lista[i]);
                        }
                    }
                break;
                
                case "Empleados hora":
                    for(int i = 0; i < numEmpleados; i++){
                        if(lista[i] instanceof EmpleadoPorHora){
                            JOptionPane.showMessageDialog(null, lista[i]);
                        }
                    }
                break;
                
                case "Empleados eventuales":
                    for(int i = 0; i < numEmpleados; i++){
                        if(lista[i] instanceof EmpleadoPorEve){
                            JOptionPane.showMessageDialog(null, lista[i]);
                        }
                    }
                break;
                
                
            }
            
        }while(opc.compareTo("Salir") != 0);
    }

public static String leerString(String msg){ 
String temp = JOptionPane.showInputDialog(msg);
return temp;
}
public static String leerOpcion(String mensaje, Object[] opciones){ 
        String opc = (String)JOptionPane.showInputDialog(null, mensaje, "LISTA DE OPCIONES",
                        JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return opc;
    } 
   
     //Con este metodo podremos leer todos los double que lleguemos a ingresar
    public static double leerDouble(String mensaje) 
    {
        String temp = JOptionPane.showInputDialog(mensaje);
        double valor = 0;
        try {
            valor = Double.parseDouble(temp);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error solo datos numericos");
            leerDouble(mensaje);
        }//con este try catch nos aseguraremos de que todos los datos sean 
        //numericos y no de otro tipo

        return valor;
    }// fin del metodo

    //con este metodo podremos leer cualquier numero de tipo entero que se ingrese
    public static int leerEntero(String mensaje) 
    {
        String temp = JOptionPane.showInputDialog(mensaje);
        int valor = 0;
        try {
            valor = Integer.parseInt(temp);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error solo datos numericos");
            leerEntero(mensaje);
        }//con este try catch nos aseguraremos de que todos los datos sean 
        //numericos y no de otro tipo
        return valor;
    } //Fin leerEntero

    //Con este metodo podremos leer cualquier long que ingresemos
    public static long leerLong(String mensaje)
    {
        String temp = JOptionPane.showInputDialog(mensaje);
        long valor = 0;
        try {
            valor = Long.parseLong(temp);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error solo datos numericos");
            leerLong(mensaje);
        }//con este try catch nos aseguraremos de que todos los datos sean 
        //numericos y no de otro tipo
        return valor;
    }//Fin leerLong


}// fin de la aplicacion
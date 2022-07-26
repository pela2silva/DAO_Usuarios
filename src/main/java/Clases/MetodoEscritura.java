
package Clases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MetodoEscritura extends Thread{
 
    private List<Usuarios> user;
    private int opcion;
    private String rutaTxt, rutaDat;

    public List<Usuarios> getUser() {
        return user;
    }

    public void setUser(List<Usuarios> user) {
        this.user = user;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public String getRutaTxt() {
        return rutaTxt;
    }

    public void setRutaTxt(String rutaTxt) {
        this.rutaTxt = rutaTxt;
    }

    public String getRutaDat() {
        return rutaDat;
    }

    public void setRutaDat(String rutaDat) {
        this.rutaDat = rutaDat;
    }
    
    // Archivo txt
    
    public void escribirTxt() {
        
        List<String> escritura = this.user.stream().map(x->x.getNombre().toUpperCase()+ ", "
                + x.getApellido().toUpperCase() + "\n").collect(Collectors.toList());
        try {
            
            FileWriter fw = new FileWriter(this.rutaTxt,true);
            BufferedWriter bw = new BufferedWriter (fw);
            
            for (String e : escritura) {
                 
                bw.write(e);
            }
            System.out.println("## Se creo el archivo txt ##");
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("## Error de carga ##");
            e.printStackTrace(System.out);
        }
    }// Fin metodo escribirTxt
    
    
    public void escribirDat(){
        
        try {
            FileOutputStream fo = new FileOutputStream(this.rutaDat);
            ObjectOutputStream oo = new ObjectOutputStream (fo);

            oo.writeObject(this.user);
            
            oo.close();
            fo.close();

        } catch (IOException ex) {
            System.out.println("##  Error de carga archivo dat  ##");
            ex.printStackTrace(System.out);
        }
    } //Fin metodo escrituraDat
    
    // Metodo para la carga de la ruta
    
    public void cargarRutaTxt(String texto) {
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        String retorno = sn.nextLine();
        this.rutaTxt = retorno;
    }

    public void cargarRutaDat(String texto) {
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        String retorno = sn.nextLine();
        this.rutaDat = retorno;
    }

    public static int cargarOpcion(String texto) {
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        int retorno = sn.nextInt();
        return retorno;
    }
    
    
    public void crearFile (){
        try {
            File archivo = new File("usuarios.dat");
            if (archivo.exists()) {
                System.out.println("No es necesario hacer la creacion del archivo");
            } else {
                archivo.createNewFile();
            }
            System.out.println("Se creo el archivo");
        } catch (IOException ex) {
            System.out.println("Error en la creacion del archivo");
            ex.printStackTrace(System.out);
        }
    } // Fin del metodo crearFile
    
@Override
    public void run(){
        
        switch(this.opcion){
            case 2:
                escribirTxt();
                System.out.println("Se escribieron los datos txt de los Usuarios");
                break;
            case 3:
                escribirDat();
                System.out.println("Se escribieron los registros dat de los usuarios");
                break;
            default:
                System.out.println("Se ingreso una opcion erronea !");                
        }
    } // Fin run
} // FIN DE LA CLASE

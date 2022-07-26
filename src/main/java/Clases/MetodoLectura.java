
package Clases;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Scanner;


public class MetodoLectura extends Thread {
    
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
  
    

public void lecturaTxt (){
    
        try {
            FileReader fr = new FileReader(this.rutaTxt);
            BufferedReader bf = new BufferedReader(fr);

            String linea = bf.readLine();

            while (linea != null) {
                System.out.println(linea);
                linea = bf.readLine();
            }
            bf.close();
            fr.close();
        } catch (IOException ex) {
            System.out.println("## Error al leer el txt ##");
            ex.printStackTrace(System.out);
        }
    } //Fin metodo lectura txt


    public void lecturaDat() {

        try {
            FileInputStream fi = new FileInputStream(this.rutaDat);
            ObjectInputStream oi = new ObjectInputStream(fi);

            List<Usuarios> lista = (List<Usuarios>) oi.readObject();
            lista.forEach(System.out::println);
            
            oi.close();
            fi.close();
            
        } catch (IOException ex) {
            System.out.println("##  Error de lecura ##");
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            System.out.println("##  Error de tipo  ##");
            e.printStackTrace(System.out);
        }
    } // Fin metodo lecturaDat


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
    
    @Override
    public void run() {

        switch (this.opcion) {

            case 4:
                lecturaTxt();
                break;
            case 5:
                lecturaDat();
                break;
            default:
                System.out.println("Se ingreso una opcion erronea !");
        }
    } // Fin del run
} // Fin clase

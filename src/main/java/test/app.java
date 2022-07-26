
package test;

import Clases.MetodoEscritura;
import Clases.MetodoLectura;
import Clases.Usuarios;
import DAO.DAOUsuariosImp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import static test.app.cargaLista;

public class app {

    public static void main(String[] args) {

        List<Usuarios> registraUsuarios = new ArrayList<>();
        
        DAOUsuariosImp ejecucion = new DAOUsuariosImp();

        System.out.println("===========================================");
        System.out.println("##        MENUES PROGRAMA USUARIOS       ##");
        System.out.println("===========================================" + "\n");
        System.out.println("1 - REGISTRAR - LEER - MODIFICAR - BORRAR");
        System.out.println("2 - CONSULTAR - GRABAR/LEER .txt y .dat" + "\n");
        int opcionMenu = cargarMenu("## MENU:  ");

        if (opcionMenu == 1) {

            try {

                int opcion;
                String opcionSi = "Si";
                boolean ban = true;
                String cargarNombre;
                Scanner sn = new Scanner(System.in);

                while (ban) {

                    System.out.println("  ###  MENU de MANTENIMIENTO ###  ");
                    System.out.println("1 - Alta de USUARIO");
                    System.out.println("2 - Listado de USUARIOS");
                    System.out.println("3 - Modifica Telefono de USUARIO por Nombre");
                    System.out.println("4 - Borra USUARIO");
                    System.out.println("0 - SALIR");

                    opcion = cargarOpcion("INGRESE UNA OPCION");

                    switch (opcion) {
                        case 1:
                            cargaLista(ejecucion.leer());
                            cargaLista(registraUsuarios);
                            ejecucion.registrar(registraUsuarios);
                            break;
                        case 2:
                            ejecucion.leer();
                            break;
                        case 3:
                            System.out.println("## Modifica TELEFONO ## Ingrese NOMBRE del USUARIO: ");
                            cargarNombre = sn.nextLine();
                            System.out.println("Esta seguro de modificar el TELEFONO de " + cargarNombre + "??!!");
                            opcionSi = cargarTexto("## (SI) MODIFICAR || (NO) SALIR  ##");
                            if (verificador(opcionSi)) {
                                System.out.println("## Ingrese NUEVO numero de TELEFONO: ");
                                String telNuevo = sn.nextLine();
                                ejecucion.modificar(new Usuarios(cargarNombre, "", "", telNuevo));
                            } else {
                                System.out.println("Se finaliza la carga !! ");
                            }
                            ;
                            break;
                        case 4:
                            System.out.println("## Ingrese el Nombre a BORRAR: ");
                            cargarNombre = sn.nextLine();
                            //buscaNom(ejecucion.leer(), cargarNombre);
                            System.out.println("Esta seguro de borrar al USUARIO " + cargarNombre + "??!!");
                            opcionSi = cargarTexto("##  (SI) BORRAR || (NO) SALIR  ##");
                            if (verificador(opcionSi)) {
                                ejecucion.borrar(new Usuarios(cargarNombre, "", "", ""));
                            } else {
                                System.out.println("Se finaliza la carga !! ");
                            }
                            ;
                            break;
                        case 0:
                            System.out.println("Gracias por usar el programa");
                            ban = false;
                            break;
                        default:
                            System.out.println("La opcion ingresada es incorrecta");
                    } // Fin del switch
                } // Fin del while
            } catch (ClassNotFoundException ex) {
                System.out.println("Error de conexion");
                ex.printStackTrace(System.out);
            } catch (SQLException ex) {
                System.out.println("Error de app");
                ex.printStackTrace(System.out);
            }
        } else if (opcionMenu == 2) {

            try {

                int opcion;
                boolean ban = true;

                while (ban) {

                    System.out.println("  ###  MENU DE OPCIONES ###  ");
                    System.out.println("1 - Lista Ordenada por Apellido");
                    System.out.println("2 - Guarda Usuarios en un txt");
                    System.out.println("3 - Guarda Usuarios en un dat");
                    System.out.println("4 - Recupera Usuarios del txt");
                    System.out.println("5 - Recupera Informacion del dat");
                    System.out.println("6 - Consulta de Usuarios por ID");
                    System.out.println("0 - SALIR");

                    opcion = cargarOpcion("INGRESE UNA OPCION");

                    switch (opcion) {
                        case 1:
                            ordenaApellido(ejecucion.leer());
                            break;
                        case 2:
                            MetodoEscritura hiloUno = new MetodoEscritura();
                            hiloUno.setOpcion(opcion);
                            hiloUno.cargarRutaTxt("## Ingrese la ubicacion para guardar los Usuarios en archivo txt ##");
                            hiloUno.setUser(ejecucion.leer());
                            hiloUno.start();
                            break;
                        case 3:
                            MetodoEscritura hiloDos = new MetodoEscritura();
                            hiloDos.setOpcion(opcion);
                            hiloDos.cargarRutaDat("##  Ingrese la ubicacion para guardar los usuarios en archivo dat  ##");
                            hiloDos.crearFile();
                            hiloDos.setUser(ejecucion.leer());
                            hiloDos.start();
                            break;
                        case 4:
                            MetodoLectura hiloTres = new MetodoLectura();
                            hiloTres.setOpcion(opcion);
                            hiloTres.cargarRutaTxt("## Ingrese nombre archivo txt a leer ##");
                            hiloTres.getRutaTxt();
                            hiloTres.start();
                            break;
                        case 5:
                            MetodoLectura hiloCuatro = new MetodoLectura();
                            hiloCuatro.setOpcion(opcion);
                            hiloCuatro.cargarRutaDat("## Ingrese nombre archivo dat a recuperar  ##");
                            hiloCuatro.getUser();
                            hiloCuatro.start();
                            break;
                        case 6:
                            int cargarId;
                            System.out.println("## Ingrese el ID a consultar: ");
                            Scanner sn = new Scanner(System.in);
                            cargarId = sn.nextInt();
                            buscaId(ejecucion.leer(), cargarId);
                            System.out.println("-----------------------------------------------------------------------------------------------------");
                            break;
                        case 0:
                            System.out.println("## Gracias por usar el programa !!! ##");
                            ban = false;
                            break;
                        default:
                            System.out.println("La opcion ingresada es incorrecta");
                    } // Fin del switch
                } // Fin del while
            } catch (ClassNotFoundException ex) {
                System.out.println("Error de conexion");
                ex.printStackTrace(System.out);
            } catch (SQLException ex) {
                System.out.println("Error de app");
                ex.printStackTrace(System.out);
            }
        }
    } // Fin de la main
    
                /*         M E T O D O S         */
    public static void ordenaApellido (List<Usuarios> lista){
        System.out.println("-------------------------------------------------------------------------");
        lista.stream().sorted((Usuarios o1, Usuarios o2)-> o1.getApellido().toLowerCase().compareTo(o2.getApellido().toLowerCase())).forEach(System.out::println);
    } // Fin ordenaApellido

    public static void buscaId(List<Usuarios> lista, int idUser) {
        
        for (Usuarios usuarios : lista) {
            if (usuarios.getId() == idUser) {
                System.out.println("-----------------------------------------------------------------------------------------------------");
                System.out.println("## Los datos del id ingresado son: " + " Id " +usuarios.getId() + " - "+ usuarios.getApellido() + ", "
                        + usuarios.getNombre() + " - Telefono: " + usuarios.getTelefono() + " - Email: " + usuarios.getEmail());
            }
        }
    } // Fin buscaId

    public static int cargarMenu(String texto) {
        int retorno = -1;
        boolean ban = true;

        while (ban) {
            try {
                Scanner sn = new Scanner(System.in);
                System.out.println(texto);
                retorno = sn.nextInt();
                if (retorno == 1 || retorno == 2) {
                    return retorno;
                } else {
                    System.out.println("Ingrese un 1 o un 2");
                    retorno = sn.nextInt();
                }
                
                ban = false;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un caracter numerico");
                e.printStackTrace(System.out);
            }
        }
        return retorno;
    } // Fin cargarOpcion
    
    public static int cargarOpcion(String texto) {
        int retorno = -1;
        boolean ban = true;

        while (ban) {
            try {
                Scanner sn = new Scanner(System.in);
                System.out.println(texto);
                retorno = sn.nextInt();
                ban = false;
            } catch (InputMismatchException e) {
                System.out.println("Ingrese un caracter numerico");
                e.printStackTrace(System.out);
            }
        }
        return retorno;
    } // Fin cargarOpcion

    public static String cargarTexto(String texto) {
        Scanner sn = new Scanner(System.in);
        System.out.println(texto);
        String retorno = sn.nextLine();
        return retorno;
    } // Fin cargarTexto
    
    public static boolean verificador (String texto){
        return texto.matches("^(S|s){1}+(I|i){1}$");
    } // Fin verificador
    
    
    public static void cargaLista(List<Usuarios> lista) {

        String opcion;
        
        while (true) {
            opcion = cargarTexto("## (SI) CARGAR o (NO) SALIR ##");
            if (verificador(opcion)) {
                
                String nom = (cargarTexto("Ingrese el NOMBRE del USUARIO: "));
                String ape = (cargarTexto("Ingrese el APELLIDO del USUARIO: "));
                                           
                boolean ban = lista.stream().anyMatch(x -> x.getNombre().toLowerCase().equalsIgnoreCase(nom)
                        && x.getApellido().toLowerCase().equalsIgnoreCase(ape));

                if (ban) {
                    System.out.println("*******************");
                    System.out.println("Ya esta cargado !! ");
                    System.out.println("*******************");
                    break;
                } else {
                    System.out.println("Siga con la carga...");
                }
                
                String mail = (cargarTexto("Ingrese el MAIL del USUARIO: "));
                String tel = (cargarTexto("Ingrese el TELEFONO del USUARIO: "));
                lista.add(new Usuarios(nom,ape,mail,tel));
            } else if (opcion.equalsIgnoreCase("no")) {
                System.out.println("Se finaliza la carga !! ");
                break;
            } 
        }
    } // Fin cargaLista
} // Fin de la clase app

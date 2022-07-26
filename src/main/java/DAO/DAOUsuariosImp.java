
package DAO;

import Clases.Usuarios;
import Interfaces.DAOUsuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOUsuariosImp extends Conexion implements DAOUsuarios {
    
    private final String REGISTRO = "INSERT INTO Usuarios(nombre,apellido,email,telefono) VALUES(?,?,?,?)";
    private final String MODIFICAR="UPDATE Usuarios SET telefono=? WHERE nombre=?";
    private final String ELIMINAR="DELETE FROM Usuarios WHERE nombre=?";
    private final String RECUPERAR ="SELECT * FROM Usuarios";

    @Override
    public void registrar(List<Usuarios> lista) throws ClassNotFoundException, SQLException {
        
        
        this.conectar();
        PreparedStatement accion = this.conexión.prepareStatement(REGISTRO);
        
        
        for (Usuarios usuarios : lista) {

            accion.setString(1, usuarios.getNombre());
            accion.setString(2, usuarios.getApellido());
            accion.setString(3, usuarios.getEmail());
            accion.setString(4, usuarios.getTelefono());
            accion.executeUpdate();
        }
        System.out.println("## SE REGISTRARON LOS DATOS EN LA TABLA ##");
        accion.close();
        this.conexión.close();
    }

    
    @Override
    public List<Usuarios> leer() throws ClassNotFoundException, SQLException {
        List<Usuarios> lista = new ArrayList<>();
        this.conectar();
        Statement accion = this.conexión.createStatement();
        ResultSet resultado = accion.executeQuery(RECUPERAR);

        while (resultado.next()) {
            lista.add(
                    new Usuarios(
                            resultado.getInt(1),
                            resultado.getString(2),
                            resultado.getString(3),
                            resultado.getString(4),
                            resultado.getString(5))
            );
        }
        lista.forEach(System.out::println);
        return lista;
    }

    @Override
    public void modificar(Usuarios usuarios) throws ClassNotFoundException, SQLException {

        this.conectar();
        PreparedStatement accion = this.conexión.prepareStatement(MODIFICAR);
        
        accion.setString(1, usuarios.getTelefono());
        accion.setString(2, usuarios.getNombre());
        accion.executeUpdate();
        
        accion.close();
        this.conexión.close();
        
        System.out.println("## SE REALIZO LA MODIFICACION DEL REGISTRO ##");
    }
    
    @Override
    public void borrar(Usuarios usuarios) throws ClassNotFoundException, SQLException {

        this.conectar();
        PreparedStatement accion = this.conexión.prepareStatement(ELIMINAR);
        
        accion.setString(1, usuarios.getNombre());
        accion.executeUpdate();
        
        accion.close();
        this.conexión.close();
        
        System.out.println("## SE ELIMINO EL REGISTRO CORRECTAMENTE ##");
    }
    
    
} // FIN DE LA CLASE

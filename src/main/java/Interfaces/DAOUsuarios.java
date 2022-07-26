
package Interfaces;

import Clases.Usuarios;
import java.sql.SQLException;
import java.util.List;

public interface DAOUsuarios {
    
    public void registrar (List<Usuarios> lista) throws ClassNotFoundException,SQLException;
    public List <Usuarios> leer () throws ClassNotFoundException,SQLException;
    public void modificar(Usuarios usuarios)throws ClassNotFoundException,SQLException;
    public void borrar (Usuarios usuarios)throws ClassNotFoundException,SQLException;
}

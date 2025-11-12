package dao;

import modelo.Usuario;
import modelo.ConexionBD;
import java.sql.*;

public class UsuarioDAO {
    public boolean validar(String correo, String clave) {
        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM usuarios WHERE correo=? AND clave=?");
            ps.setString(1, correo);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registrar(Usuario u) {
        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO usuarios(nombre, correo, clave) VALUES (?, ?, ?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setString(3, u.getClave());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.mycompany.mavenproject3.dao;

import com.mycompany.mavenproject3.model.Usuario;
import com.mycompany.mavenproject3.util.ConexionBD;
import java.sql.*;

public class UsuarioDAO {
    public Usuario validar(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setNombre(rs.getString("nombre"));
                    return u;
                }
            }
        }
        return null;
    }
}

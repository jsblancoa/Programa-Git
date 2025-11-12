package dao;

import modelo.ItemCarrito;
import modelo.Producto;
import modelo.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO {

    public void agregarItem(int idUsuario, int idProducto, int cantidad) {
        try {
            Connection con = ConexionBD.getConexion();
            // Si ya existe el item, actualizar cantidad
            PreparedStatement psCheck = con.prepareStatement(
                "SELECT id, cantidad FROM carrito WHERE id_usuario=? AND id_producto=?");
            psCheck.setInt(1, idUsuario);
            psCheck.setInt(2, idProducto);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int existCant = rs.getInt("cantidad");
                PreparedStatement psUpd = con.prepareStatement(
                    "UPDATE carrito SET cantidad=? WHERE id=?");
                psUpd.setInt(1, existCant + cantidad);
                psUpd.setInt(2, id);
                psUpd.executeUpdate();
            } else {
                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carrito(id_usuario, id_producto, cantidad) VALUES (?, ?, ?)");
                ps.setInt(1, idUsuario);
                ps.setInt(2, idProducto);
                ps.setInt(3, cantidad);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemCarrito> listar(int idUsuario) {
        List<ItemCarrito> lista = new ArrayList<>();
        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement(
                "SELECT c.id, c.id_producto, c.cantidad, p.nombre, p.precio FROM carrito c JOIN productos p ON c.id_producto=p.id WHERE c.id_usuario=?");
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemCarrito it = new ItemCarrito();
                it.setId(rs.getInt("id"));
                it.setIdProducto(rs.getInt("id_producto"));
                it.setCantidad(rs.getInt("cantidad"));
                lista.add(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void eliminarItem(int id) {
        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM carrito WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vaciarCarrito(int idUsuario) {
        try {
            Connection con = ConexionBD.getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM carrito WHERE id_usuario=?");
            ps.setInt(1, idUsuario);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

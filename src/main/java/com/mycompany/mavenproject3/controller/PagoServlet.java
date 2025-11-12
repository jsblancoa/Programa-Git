package controlador;

import modelo.ConexionBD;
import dao.CarritoDAO;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class PagoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String correo = (String) session.getAttribute("correo");
        if (correo == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // Simulación: obtener idUsuario (suponer 1)
        int idUsuario = 1;
        String nombreTarjeta = request.getParameter("nombreTarjeta");
        String numeroTarjeta = request.getParameter("numeroTarjeta");
        String fecha = request.getParameter("fecha");
        String cvv = request.getParameter("cvv");

        // En entorno real: validar pago con pasarela. Aquí simulamos éxito.
        try {
            Connection con = ConexionBD.getConexion();
            // Crear pedido
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO pedidos (id_usuario, total, estado) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
            // Calcular total consultando carrito
            double total = 0.0;
            PreparedStatement psTotal = con.prepareStatement(
                "SELECT c.cantidad, p.precio FROM carrito c JOIN productos p ON c.id_producto=p.id WHERE c.id_usuario=?");
            psTotal.setInt(1, idUsuario);
            ResultSet rs = psTotal.executeQuery();
            while (rs.next()) {
                total += rs.getInt("cantidad") * rs.getDouble("precio");
            }
            ps.setInt(1, idUsuario);
            ps.setDouble(2, total);
            ps.setString(3, "CONFIRMADO");
            ps.executeUpdate();
            ResultSet keys = ps.getGeneratedKeys();
            int idPedido = -1;
            if (keys.next()) idPedido = keys.getInt(1);

            // Mover ítems del carrito a detalle_pedidos (simple ejemplo)
            PreparedStatement psItems = con.prepareStatement(
                "INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad) SELECT ?, id_producto, cantidad FROM carrito WHERE id_usuario=?");
            psItems.setInt(1, idPedido);
            psItems.setInt(2, idUsuario);
            psItems.executeUpdate();

            // Vaciar carrito
            CarritoDAO carritoDAO = new CarritoDAO();
            carritoDAO.vaciarCarrito(idUsuario);

            // Redirigir a confirmación
            request.setAttribute("idPedido", idPedido);
            RequestDispatcher rd = request.getRequestDispatcher("confirmacion.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("carrito?accion=listar");
        }
    }
}

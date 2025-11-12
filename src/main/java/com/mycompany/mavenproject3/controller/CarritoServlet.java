package controlador;

import dao.CarritoDAO;
import modelo.ItemCarrito;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;

public class CarritoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        String correo = (String) session.getAttribute("correo");
        if (correo == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        // En una implementación real, buscar idUsuario por correo; aquí asumimos id 1 para ejemplo
        int idUsuario = 1;

        CarritoDAO dao = new CarritoDAO();
        if ("listar".equals(accion) || accion == null) {
            List<ItemCarrito> items = dao.listar(idUsuario);
            request.setAttribute("items", items);
            RequestDispatcher rd = request.getRequestDispatcher("carrito.jsp");
            rd.forward(request, response);
        } else if ("agregar".equals(accion)) {
            int idProducto = Integer.parseInt(request.getParameter("idProducto"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            dao.agregarItem(idUsuario, idProducto, cantidad);
            response.sendRedirect("catalogo.jsp");
        } else if ("eliminar".equals(accion)) {
            int idItem = Integer.parseInt(request.getParameter("id"));
            dao.eliminarItem(idItem);
            response.sendRedirect("carrito?accion=listar");
        } else if ("vaciar".equals(accion)) {
            dao.vaciarCarrito(idUsuario);
            response.sendRedirect("carrito?accion=listar");
        }
    }
}

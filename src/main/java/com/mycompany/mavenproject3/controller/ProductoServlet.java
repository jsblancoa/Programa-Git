package com.mycompany.mavenproject3.controller;

import com.mycompany.mavenproject3.dao.ProductoDAO;
import com.mycompany.mavenproject3.model.Producto;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Productos")
public class ProductoServlet extends HttpServlet {
    private ProductoDAO dao = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String accion = req.getParameter("accion");
        try {
            if ("nuevo".equals(accion)) {
                req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
            } else if ("editar".equals(accion)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Producto p = dao.obtenerPorId(id);
                req.setAttribute("producto", p);
                req.getRequestDispatcher("editProduct.jsp").forward(req, resp);
            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(req.getParameter("id"));
                dao.eliminar(id);
                resp.sendRedirect("Productos");
            } else {
                List<Producto> lista = dao.listar();
                req.setAttribute("lista", lista);
                req.getRequestDispatcher("productos.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String precioStr = req.getParameter("precio");
        String cantidadStr = req.getParameter("cantidad");
        String idStr = req.getParameter("id");

        double precio = 0;
        int cantidad = 0;
        try {
            if (precioStr != null && !precioStr.isEmpty()) {
                precio = Double.parseDouble(precioStr);
            }
            if (cantidadStr != null && !cantidadStr.isEmpty()) {
                cantidad = Integer.parseInt(cantidadStr);
            }

            if (idStr == null || idStr.isEmpty()) {
                Producto p = new Producto();
                p.setNombre(nombre);
                p.setPrecio(precio);
                p.setCantidad(cantidad);
                dao.insertar(p);
            } else {
                Producto p = new Producto();
                p.setId(Integer.parseInt(idStr));
                p.setNombre(nombre);
                p.setPrecio(precio);
                p.setCantidad(cantidad);
                dao.actualizar(p);
            }
            resp.sendRedirect("Productos");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

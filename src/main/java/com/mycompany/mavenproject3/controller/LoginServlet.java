package com.mycompany.mavenproject3.controller;

import com.mycompany.mavenproject3.dao.UsuarioDAO;
import com.mycompany.mavenproject3.model.Usuario;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("usuario");
        String password = req.getParameter("password");

        try {
            Usuario u = usuarioDAO.validar(username, password);
            if (u != null) {
                HttpSession sesion = req.getSession();
                sesion.setAttribute("usuario", u);
                resp.sendRedirect("panel.jsp");
            } else {
                req.setAttribute("mensaje", "Usuario o contraseña incorrectos");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

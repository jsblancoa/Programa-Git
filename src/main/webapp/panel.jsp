<%@page contentType="text/html;charset=UTF-8"%>
<%@page import="com.mycompany.mavenproject3.model.Usuario" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Panel</title></head>
<body>
    <h2>Bienvenido, <%= u.getNombre() %></h2>
    <a href="Productos">Ver productos</a> |
    <a href="Logout">Cerrar sesión</a>
</body>
</html>

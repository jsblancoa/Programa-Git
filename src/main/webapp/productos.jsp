<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.mavenproject3.model.Producto" %>
<%
    List<Producto> lista = (List<Producto>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
<head><title>Productos</title></head>
<body>
    <h2>Productos</h2>
    <a href="Productos?accion=nuevo">Agregar producto</a>
    <table border="1" style="width:80%; margin-top:10px;">
        <tr><th>ID</th><th>Nombre</th><th>Precio</th><th>Cantidad</th><th>Acciones</th></tr>
        <%
            if (lista != null) {
                for (Producto p : lista) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getPrecio() %></td>
            <td><%= p.getCantidad() %></td>
            <td>
                <a href="Productos?accion=editar&id=<%= p.getId() %>">Editar</a> |
                <a href="Productos?accion=eliminar&id=<%= p.getId() %>" onclick="return confirm('Eliminar?')">Eliminar</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>

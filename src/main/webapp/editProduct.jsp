<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.mycompany.mavenproject3.model.Producto" %>
<%
    Producto p = (Producto) request.getAttribute("producto");
    if (p == null) {
        response.sendRedirect("Productos");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Editar Producto</title></head>
<body>
    <h2>Editar producto</h2>
    <form action="Productos" method="post">
        <input type="hidden" name="id" value="<%= p.getId() %>">
        Nombre: <input type="text" name="nombre" value="<%= p.getNombre() %>" required><br>
        Precio: <input type="number" step="0.01" name="precio" value="<%= p.getPrecio() %>" required><br>
        Cantidad: <input type="number" name="cantidad" value="<%= p.getCantidad() %>" required><br><br>
        <input type="submit" value="Actualizar">
    </form>
    <a href="Productos">Volver</a>
</body>
</html>

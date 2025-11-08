<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Agregar Producto</title></head>
<body>
    <h2>Agregar producto</h2>
    <form action="Productos" method="post">
        Nombre: <input type="text" name="nombre" required><br>
        Precio: <input type="number" step="0.01" name="precio" required><br>
        Cantidad: <input type="number" name="cantidad" required><br><br>
        <input type="submit" value="Guardar">
    </form>
    <a href="Productos">Volver</a>
</body>
</html>

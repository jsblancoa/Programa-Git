<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><title>Login - PCInnovation</title></head>
<body>
    <h2>Iniciar sesión</h2>
    <form action="Login" method="post">
        Usuario: <input type="text" name="usuario" required><br><br>
        Contraseña: <input type="password" name="password" required><br><br>
        <input type="submit" value="Ingresar">
    </form>

    <p style="color:red;">${mensaje}</p>
</body>
</html>

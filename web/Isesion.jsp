<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java" %>
<%
    HttpSession sesion = request.getSession(true);
    if(sesion.getAttribute("id") != null){
        if((Integer)sesion.getAttribute("permiso") == 1){
                response.sendRedirect("VistaAdministrador.jsp");
                }
        if((Integer)sesion.getAttribute("permiso") == 0){
            response.sendRedirect("vistausuario.jsp");
            }
        }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minimum-scale=1" />
    <title>Iniciar Sesión</title>
    <link rel="icon" type="image/png" href="img/favicon.ico">
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="login" background="img/Fondo.jpg">
    <header>
        <div class="cabecera">
            <img src="img/corazon.png" class="logo">
            <span>
                <nav class="navigation">
                <ul class="show">
                    <li>
                        <a href="index.html">Volver</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <br>
    <div class="fondoForm">
        <form name="iniciar" method="POST" action="iniciarsesion" class="form">
            <div class='control'>
                <h1>
                    Iniciar Sesión
                </h1>
            </div>
            <div class='control block-cube block-input'>
                <input name='email' placeholder='Correo electronico' type='email'>
                <div class='bg-top'>
                    <div class='bg-inner'></div>
                </div>
                <div class='bg-right'>
                    <div class='bg-inner'></div>
                </div>
                <div class='bg'>
                    <div class='bg-inner'></div>
                </div>
            </div>
            <div class='control block-cube block-input'>
                <input name='pass' placeholder='Contraseña' type='password'>
                <div class='bg-top'>
                    <div class='bg-inner'></div>
                </div>
                <div class='bg-right'>
                    <div class='bg-inner'></div>
                </div>
                <div class='bg'>
                    <div class='bg-inner'></div>
                </div>
            </div>
            <button class='btn block-cube block-cube-hover' type='button' onclick="iniciarsesion()">
                <div class='bg-top'>
                  <div class='bg-inner'></div>
                </div>
                <div class='bg-right'>
                  <div class='bg-inner'></div>
                </div>
                <div class='bg'>
                  <div class='bg-inner'></div>
                </div>
                <div class='text'>
                  Iniciar Sesión
                </div>
            </button>
            <br>
            <a href="Registrarse.html">¿No tienes cuenta? Regístrate aquí</a>
            <div class='credits'>
                Página con fines educativos, Creada por Elizalde Hernández Alan, García García Aram Jesua, Gutierrez Bueno Elizabeth Andrea y Vasco Giraldo Juan Esteban
            </div>
        </form>
        <script src="js/validar.js"></script>
    </div>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="Control.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java" %>
<%  
    HttpSession sesion = request.getSession(true);
    try{
    if(sesion.getAttribute("id")== null || ((Integer)sesion.getAttribute("permiso"))== 1){
    %>
    
    <jsp:forward page="error.jsp">
        <jsp:param name="Error" value="Es obligatorio identificarse" />
    </jsp:forward>
    <%
    }}catch(Exception e){
        System.out.println(e.getMessage());
        %>
    
    <jsp:forward page="error.jsp">
        <jsp:param name="Error" value="Es obligatorio identificarse" />
    </jsp:forward>
    <%
    }   
        MUsuario u= AccionesUsuario.ConsultarUsuario((Integer)sesion.getAttribute("id"));
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
                        <a href="cuenta.jsp">Volver</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <br>
    <div class="fondoForm">
        <form name="modificarusu" method="GET" action="ModificarUsuario" class='form'>
            <div class='control'>
                <h1>
                    Actualizar/Modificar Cuenta
                </h1>
            </div>
            <div class='control block-cube block-input'>
                <input type="text" placeholder="Ingresa tu nombre" name="nombre" value="<%=u.getNombre() %>" required>
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
                <input type="text" placeholder="Ingresa tu apellido paterno" name="appat" value="<%=u.getAppat() %>" required>
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
                <input type="text" placeholder="Ingresa tu apellido materno" name="apmat" value="<%=u.getApmat() %>" required>
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
                <input type="text" name="fecha" placeholder="Ingresa tu Fecha de Nacimiento DD/MM/AAAA" value="<%=u.getFecha() %>" required>
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
                <input type="number" placeholder="Numero Celular" name="telcel" maxlength="10" value="<%=u.getCelular() %>" required>
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
                <input type="number" placeholder="Numero Particular" name="telpar" value="<%=u.getTelefono() %>" required>
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
                <input name='email' placeholder='Correo electronico' value="<%=u.getEmail() %>" type='email'>
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
                <input name='pass' placeholder='Contraseña' type='password' value="<%=u.getPassword()%>">
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
                <input type="password" placeholder="Confirma contraseña" name="confpass" required>
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
            <button class='btn block-cube block-cube-hover' type='button' onclick="modificarusua()">
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
            Actualizar Cuenta
          </div>
        </button>
            <!--<br>
        <br>
        <br>
        <div class='credits'>
            Página con fines educativos, Creada por Elizalde Hernández Alan, García García Aram Jesua, Gutierrez Bueno Elizabeth Andrea y Vasco Giraldo Juan Esteban
        </div>-->
        </form>
    </div>
    <script src="js/validar.js"></script>
</body>
</html>
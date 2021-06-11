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
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cuenta</title>
    <link rel="stylesheet" href="css/masterstyle.css">
    <link rel="shortcut icon" href="img/favicon.ico">
</head>

<body background="img/Fondo.jpg">
    <header>
        <div class="cabecera">
            <a href="index.html"><img src="img/corazon.png" class="logo"></a>
            <span>
                <nav class="navigation">
                <ul class="show">
                    
                    <li>
                        <a href="Productosusuario.jsp">VOLVER</a>
                    </li>
                    <li>
                        <a href="CerrarSesion">CERRAR SESIÓN</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <div class="beta aux2">
        <h1>Bienvenido a su cuenta</h1>
        <h2>Datos personales:</h2>
        <h3> Nombre: <%=u.getNombre() %></h3>
        <h3>Apellido paterno: <%=u.getAppat() %></h3>
        <h3>Apellido Materno: <%=u.getApmat() %></h3>
        <h3>Fecha de nacimiento: <%=u.getFecha() %></h3>
        <h3>Telefono celular: +55 <%=u.getCelular() %> </h3>
        <h3>Telefono Particular: +55 <%=u.getTelefono() %></h3>
        <h3>Correo: <%=u.getEmail() %></h3>
    </div>
    <div class="principal">
        <div class="aux">
            <a href="EliminarUsuario">
                <div class="button-container-1">
                    <div class="button-container-1">
                        <span class="mas">BEN & JERRY'S</span> <button id='work' type="button" name="Hover">Eliminar Mi Cuenta</button>
                    </div>
                </div>
            </a>
        </div>
        <div class="aux">
            <a href="modificarcuenta.jsp">
                <div class="button-container-1">
                    <div class="button-container-1"> <span class="mas">BEN & JERRY'S</span> <button id='work' type="button" name="Hover">Modificar Cuenta</button> </div>
                </div>
            </a>
        </div>
        <div class="aux">
            <a href="Tarjetas.jsp">
                <div class="button-container-1">
                    <div class="button-container-1"> <span class="mas">BEN & JERRY'S</span> <button id='work' type="button" name="Hover">Mis Tarjetas de credito</button> </div>
                </div>
            </a>
        </div>
        <footer>
            <p>Página con fines educativos, Creada por Elizalde Hernández Alan, García García Aram Jesua, Gutierrez Bueno Elizabeth Andrea y Vasco Giraldo Juan Esteban</p>
        </footer>
</body>
</html>
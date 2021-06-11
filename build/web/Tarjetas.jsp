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
        List<MTarjeta> lista = AccionesTarjeta.buscartarjetasusuario((Integer)sesion.getAttribute("id"));
    %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BIENVENIDO</title>
    <link rel="stylesheet" href="css/styletable.css">
    <link rel="icon" type="image/png" href="img/favicon.ico">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
</head>
<body background="img/Fondo.jpg">
    <header>
        <div class="cabecera">
            <img src="img/corazon.png" class="logo">
            <span>
                <nav class="navigation">
                <ul class="show">
                    <li>
                        <a href="cuenta.jsp">VOLVER</a>
                    </li>
                    <li>
                        <a href="CerraSesion">CERRAR SESIÓN</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <div class="TextoSombreadomamalon1">
        <h1>Mis Tarjetas</h1>
        <a href="anadirtarjeta.jsp">
            <button class="btn btn1 center">Agregar Una Nueva Tarjeta</button>
        </a>
    </div>
    <br>
    <br>
    <div class="principal">
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Numero de Tarjeta</th>
                        <th>Fecha de Expiración</th>
                        <th>Código</th>
                        <th>Tipo</th>
                        <th>Actualizar datos</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for(MTarjeta t: lista){
                    %>
                    <tr>
                        <td><%=t.getNumero() %></td>
                        <td><%=t.getFecha() %></td>
                        <td><%=t.getCodigo() %></td>
                        <td><%=t.getTipo_des() %> </td>
                        <td><a href="Modificartarjeta.jsp?id=<%=t.getId()%>">Modificar</a></td>
                    </tr>
                    <% 
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
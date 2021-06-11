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
        List<MCompra> lista = AccionesCompra.consultarcomprasUsu((Integer)sesion.getAttribute("id"));
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
    <div class="TextoSombreadomamalon1">
        <h1>Mis compras</h1>
    </div>
    <br>
    <br>
    <div class="principal">
        <div class="container">
            <table>
                <thead>
                    <tr>
                        <th>Id Recibo</th>
                        <th>Fecha</th>
                        <th>Forma de pago</th>
                        <th>Total de la compra</th>
                        <th>Detalles de la compra</th>
                    </tr>
                </thead>
                <tbody>
                   
                        <%     
                            for(MCompra mc: lista){
                        %>
                     <tr>    
                        <td><%=mc.getId() %></td>
                        <td><%=mc.getFecha() %></td>
                        <td><%=mc.getForma_des() %></td>
                        <td><%=mc.getTotal() %>MXN</td>
                        <td><a href="detallescompra.jsp?id=<%=mc.getId() %>">Detalles</a></td>
                    </tr>    
                        <% 
                            }
                        %>
                    
                </tbody>
            </table>
        </div>
    </div>
    <footer>
        Página con fines educativos, Creada por Elizalde Hernández Alan, García García Aram Jesua, Gutierrez Bueno Elizabeth Andrea y Vasco Giraldo Juan Esteban
    </footer>
</body>
</html>

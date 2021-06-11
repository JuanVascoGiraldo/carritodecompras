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

        List<Catalogo> lista = AccionesCatalogo.Tarjeta();

    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Productos</title>
    <link rel="icon" type="image/png" href="img/favicon.ico">
    <link rel="stylesheet" href="css/AgProd.css">
</head>
<body background="img/Fondo.jpg">
    <header>
        <div class="cabecera">
            <img src="img/corazon.png" class="logo">
            <span>
                <nav class="navigation">
                <ul class="show">
                    <li>
                        <a href="Tarjetas.jsp">VOLVER</a>
                    </li>
                    <li>
                        <a href="CerrarSesion">CERRAR SESIÓN</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <div class="principal">
        <br>
        <form action="AnadirTarjeta" name="anadirtar">
            <h1>Agregar Tarjeta</h1>
            <input type="number" placeholder="Numero de Tarjeta" name="numero" required>
            <input type="text" name="fechaexp" placeholder="Ingresa la fecha de expiración ej 02/21" required>
            <input type="number" placeholder="Codigo de seguridad" name="codigo" required>
            <div class="box">
                <select name="tipo">
                <option selected disabled>Tipo de Tarjeta</option>
                <% for(Catalogo c: lista){ %>
                <option value="<%=c.getId() %>"><%=c.getDescripcion()%></option>
                <% }%>
              </select>
            </div>
            <br>
            <button onclick="agregartar()" type="button">Agregar Tarjeta</button>
        </form>
    </div>
    <script src="js/validar.js"></script>
</body>
</html>
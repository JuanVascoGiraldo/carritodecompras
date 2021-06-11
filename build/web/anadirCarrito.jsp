<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="Control.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java" %>
<%  
    try{
    HttpSession sesion = request.getSession(true);
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
        String NUMEROS = "[0-9]*";
        int id = 0;
        if( request.getParameter("id") != null){
            Pattern pattern = Pattern.compile(NUMEROS);
            Matcher matcher = pattern.matcher(request.getParameter("id"));        
            boolean r = matcher.matches();
            if(r == true){
                id = Integer.parseInt(request.getParameter("id"));
            }
        }
        if(id == 0){
            %>
        <jsp:forward page="error.jsp">
            <jsp:param name="Error" value="Es obligatorio identificarse" />
        </jsp:forward>
    <%
        }
        MProducto p = AccionesProducto.buscarProducto(id);
        
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Añadir al carrito</title>
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
    <div class="principal">
        <br>
        <form action="AnadirCarrito" name="anadircarrito">
            <h1>Agregar producto al carrito</h1>
            <h3>ID:</h3>
            <input value="<%=p.getId()%>" type="number" name="id" max="99999999" readonly="readonly">
            <h3>Precio:</h3>
            <input value="<%=p.getPrecio()%>" type="number" name="Precio" max="99999999" readonly="readonly">
            <h3>Stock:</h3>
            <input value="<%=p.getCantidad()%>" type="number" name="stock" max="99999999" readonly="readonly">
            <h3>¿Cuantas unidades quieres añadir a tu carrito?</h3>
            <input type="number" placeholder="Cantidad deseada" name="cantidad" required>
            <br>
            <button onclick="anadircarrit()" type="button">Agregar Producto</button>
        </form>
    </div>
    <script src="js/validar.js"></script>
</body>
</html>
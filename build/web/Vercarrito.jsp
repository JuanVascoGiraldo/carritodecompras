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
        List<DCompra> lista = (List<DCompra>)sesion.getAttribute("lista");
    %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi carrito</title>
    <link rel="stylesheet" href="css/styleProductos.css">
    <link rel="icon" type="image/png" href="img/favicon.ico">
    <script src="js/myscript.js"></script>
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
    <h1 class="center">Mi carrito</h1>
    <br>
    <div class="principal">
        <% if(lista.size() > 0){ 
            for(DCompra dc: lista){
        %>
        <div class="contenedor">
            <h3>Sabor: <%=dc.getProductos().getDes_sabor()%> </h3>
            <br>
            <h3>Presentación: <%=dc.getProductos().getDes_presentacion()%></h3>
            <br>
            <h3>Tamaño: <%=dc.getProductos().getDes_tamano()%></h3>
            <br>
            <h3>Descuento: <%=dc.getProductos().getDes_descuento()%> %</h3>
            <br>
            <h3>Precio: <%=dc.getProductos().getPrecio()%> MXN</h3>
            <br>
            <h3>Subtotal: <%=dc.getTotal()%> MXN</h3>
            <br>
            <h3>Cantidad: <%=dc.getCantidad()%></h3>
            <br>
            <a href="modificarproductocarrito.jsp?id=<%=dc.getProductos().getId()%>&&cantidad=<%=dc.getCantidad()%>">
                <button class="btn btn1">Modificar</button>
            </a>
            <a href="EliminarCarrito?id=<%=dc.getProductos().getId()%>">
                <button class="btn btn1">Eliminar</button>
            </a>
        </div>
        <% } %>
        <div class="center">
            <a href="RegistrarCompra?metodo=2">
                <button class="btn btn1">Pagar en efectivo</button>
            </a>
            <a href="PagarTarjeta.jsp">
                <button class="btn btn1">Pagar con tarjeta</button>
            </a>
        </div>
        <% 
            }else{
                %>
            <div class="contenedor">
                <h3>No tienes Ningun Producto Registrado</h3>
            </div>
        <% } %>
    </div>
</body>
</html>
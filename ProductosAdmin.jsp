<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="Control.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true" language="java" %>
<%  
    try{
    HttpSession sesion = request.getSession(true);
    if(sesion.getAttribute("id")== null || ((Integer)sesion.getAttribute("permiso"))== 0){
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
        String NUMEROS = "[0-2]";
        int fil = 0;
        if( request.getParameter("fil") != null){
            Pattern pattern = Pattern.compile(NUMEROS);
            Matcher matcher = pattern.matcher(request.getParameter("fil"));        
            boolean r = matcher.matches();
            if(r == true){
                fil = Integer.parseInt(request.getParameter("fil"));
            }
        }
        List<MProducto> lista = AccionesProducto.buscarAllProducto();

    %>
    <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos Admin</title>
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
                        <a href="CerrarSesion">CERRAR SESIÓN</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <h1 class="center">Productos</h1>
    <br>
    <div class="centrar">
        <a href="agregarproducto.jsp" class="btn-neon">
            <span id="span1"></span>
            <span id="span2"></span>
            <span id="span3"></span>
            <span id="span4"></span> Agregar Producto
        </a>
    </div>
    <br>
    <form>
        <div class="box">
                <select name="area" onChange="location = form.area.options[form.area.selectedIndex].value;">
                    <option selected disabled>Filtrar por Stock</option>
                    <option value="ProductosAdmin.jsp?fil=0">Ninguna</option>
                    <option value="ProductosAdmin.jsp?fil=1">Con Stock</option>
                    <option value="ProductosAdmin.jsp?fil=2">Sin Stock</option>
                </select>
            </div>
    </form>
    <br>
    <div class="principal">
        <% for( MProducto p: lista){ 
            if(p.getCantidad() > 0 && fil != 2){
        %>
        <div class="contenedor">
            <h3>ID:<%=p.getId()%> </h3>
            <br>
            <h3>Precio: <%=p.getPrecio()%> MXN</h3>
            <br>
            <h3>Stock: <%=p.getCantidad()%> Unidades</h3>
            <br>
            <h3>Sabor: <%=p.getDes_sabor()%> </h3>
            <br>
            <h3>Presentación: <%=p.getDes_presentacion()%></h3>
            <br>
            <h3>Tamaño: <%=p.getDes_tamano()%></h3>
            <br>
            <h3>Descuento: <%=p.getDes_descuento()%> %</h3>
            <br>
            <div class="sombreado">
                <a href="modificarProducto.jsp?id=<%=p.getId()%>">
                    <img src="img/edit.png" width="60px">
                </a>
                <a href="EliminarProducto?id=<%=p.getId()%>">
                    <img src="img/Cross.png" width="50px">
                </a>
            </div>
        </div>
        <% } 
            if(p.getCantidad() == 0 && fil != 1){
        %>
        <div class="contenedor2">
            <h3>ID:<%=p.getId()%> </h3>
            <br>
            <h3>Precio:<%=p.getPrecio()%>MXN</h3>
            <br>
            <h3>Stock:<%=p.getCantidad()%> Unidades</h3>
            <br>
            <h3>Sabor:<%=p.getDes_sabor()%> </h3>
            <br>
            <h3>Presentación:<%=p.getDes_presentacion()%></h3>
            <br>
            <h3>Tamaño:<%=p.getDes_tamano()%></h3>
            <br>
            <h3>Descuento:<%=p.getDes_descuento()%> %</h3>
            <br>
            <div class="sombreado">
                <a href="modificarProducto.jsp?id=<%=p.getId()%>">
                    <img src="img/edit.png" width="60px">
                </a>
                <a href="EliminarProducto?id=<%=p.getId()%>">
                    <img src="img/Cross.png" width="50px">
                </a>
            </div>
        </div>
        <%  } } %>
    </div>
    <div class="creditos">
        <a target="_blank" href="undefined/icons/set/close-window">Cerrar ventana icon</a> icono de <a target="_blank" href="">Icons8</a>
        <div>Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
    </div>
</body>
</html>

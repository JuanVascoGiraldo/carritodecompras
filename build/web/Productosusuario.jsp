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
        String NUMEROS = "[0-3]";
        String NUMEROS2 = "[0-9]*";
        int fil = 0;
        if( request.getParameter("fil") != null){
            Pattern pattern = Pattern.compile(NUMEROS);
            Matcher matcher = pattern.matcher(request.getParameter("fil"));        
            boolean r = matcher.matches();
            if(r == true){
                fil = Integer.parseInt(request.getParameter("fil"));
            }
        }
        int par = 0;
        if( request.getParameter("par") != null){
            Pattern pattern = Pattern.compile(NUMEROS2);
            Matcher matcher = pattern.matcher(request.getParameter("par"));        
            boolean r = matcher.matches();
            if(r == true){
                par = Integer.parseInt(request.getParameter("par"));
            }
        }
        List<MProducto> lista = AccionesProducto.buscarAllProductoconStock();
        List<Catalogo> sabores = AccionesCatalogo.Sabores();
        List<Catalogo> presentacion = AccionesCatalogo.Presentacion();
        List<Catalogo> tamano = AccionesCatalogo.Tamano();
    %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos Usuario</title>
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
                        <a href="cuenta.jsp">MI CUENTA</a>
                    </li>
                    <li>
                        <a href="Vercarrito.jsp">MI CARRITO</a>
                    </li>
                    <li>
                        <a href="VerCompras.jsp">MIS COMPRAS</a>
                    </li>
                    <li>
                        <a href="CerrarSesion">CERRAR SESIÓN</a>
                    </li>
                </ul>
                </nav>
            </span>
        </div>
    </header>
    <form>
    <div class="center2">
        <h1 class="center">Filtrar Productos</h1>
        <br>
        <div class="centrar">
                <div class="box">
                    <select name="area" onChange="location = form.area.options[form.area.selectedIndex].value;">
                        <option selected disabled>Por Sabor:</option>
                     <%  
                    for (Catalogo c: sabores){
                %>
                        <option value="Productosusuario.jsp?fil=1&&par=<%=c.getId()%>" ><%=c.getDescripcion()%> </option>
                <%  
                }
                %>
                    </select>
                </div>
                <div class="box">
                    <select name="area1" onChange="location = form.area1.options[form.area1.selectedIndex].value;">
                        <option selected disabled>Por Presentación:</option>
                <%  
                        for (Catalogo c: presentacion){
                    %>
                        <option value="Productosusuario.jsp?fil=2&&par=<%=c.getId()%>"><%=c.getDescripcion()%> </option>
                    <%  
                    }
                %>
                    </select>
                </div>
                <div class="box">
                    <select name="area2" onChange="location = form.area2.options[form.area2.selectedIndex].value;">
                        <option selected disabled>Por Tamaño:</option>
                    <%  
                        for (Catalogo c: tamano){
                    %>
                        <option value="Productosusuario.jsp?fil=3&&par=<%=c.getId()%>"><%=c.getDescripcion()%> </option>
                    <%  
                    }
                    %>
                    </select>
                </div>
                <div class="box">
                    <select name="area3" onChange="location = form.area3.options[form.area3.selectedIndex].value;">
                        <option selected disabled>Ninguna</option>
                        <option value="Productosusuario.jsp?fil=0&&par=0">Ninguna</option>
                    </select>
                </div>
        </div>
    </div>
    </form>
    <br>
    <h1 class="center">Productos</h1>
    <br>
    <div class="principal">
        <% 
        for(MProducto p: lista){
            if( fil != 0 && fil != 2 && fil != 3 && par == p.getSabor()){
         %>   
        <div class="contenedor">
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
            <a href="anadirCarrito.jsp?id=<%=p.getId()%>">
                <button class="btn btn1">Añadir al carrito</button>
            </a>
        </div>
        <% }
            if( fil != 0 && fil != 1 && fil != 3 && par == p.getPresentacion()){
         %>   
        <div class="contenedor">
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
            <a href="anadirCarrito.jsp?id=<%=p.getId()%>">
                <button class="btn btn1">Añadir al carrito</button>
            </a>
        </div>
        <% }
            if( fil != 0 && fil != 1 && fil != 2 && par == p.getTamano()){
         %>   
        <div class="contenedor">
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
            <a href="anadirCarrito.jsp?id=<%=p.getId()%>">
                <button class="btn btn1">Añadir al carrito</button>
            </a>
        </div>
        <% }
            if( fil== 0 && par == 0){
         %>   
        <div class="contenedor">
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
            <a href="anadirCarrito.jsp?id=<%=p.getId()%>">
                <button class="btn btn1">Añadir al carrito</button>
            </a>
        </div>
        <%  
                }
            } 
        %>
    </div>
    </div>
    <div class="creditos">
        <a target="_blank" href="undefined/icons/set/close-window">Cerrar ventana icon</a> icono de <a target="_blank" href="">Icons8</a>
        <div>Icons made by <a href="https://www.freepik.com" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a></div>
    </div>
</body>
</html>

<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="Control.*"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
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
        int id = 0;
        String NUMEROS = "[0-9]*";
        if( request.getParameter("id") != null){
            Pattern pattern = Pattern.compile(NUMEROS);
            Matcher matcher = pattern.matcher(request.getParameter("id"));        
            boolean r = matcher.matches();
            if(r == false){
                %>
                <jsp:forward page="error.jsp">
                    <jsp:param name="Error" value="Es obligatorio identificarse" />
                </jsp:forward>
                <%
            }
        }
        id = Integer.parseInt(request.getParameter("id"));
        MProducto p= AccionesProducto.buscarProducto(id);
        List<Catalogo> sabores = AccionesCatalogo.Sabores();
        List<Catalogo> descuento = AccionesCatalogo.Descuento();
        List<Catalogo> presentacion = AccionesCatalogo.Presentacion();
        List<Catalogo> tamano = AccionesCatalogo.Tamano();
        
    %>
    <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Productos</title>
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
                        <a href="ProductosAdmin.jsp">VOLVER</a>
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
        <form action="ModificarProducto" name="modipro">
            <h1>Modificar producto</h1>
            <input value="<%=p.getId()%>" type="number" name="id" max="99999999" readonly="readonly">
            <div class="box">
                <select name="sabor">
            <option selected disabled>Sabor</option>
            <%  
                        for (Catalogo c: sabores){
                    %>
                    <option value="<%=c.getId()%>" <% if(c.getId() == p.getSabor()){%> selected<% }%> ><%=c.getDescripcion()%> </option>
                    <%  
                    }
                    %>
          </select>
            </div>
            <div class="box">
                <select name="presentacion">
                <option selected disabled>Presentación</option>
                <%  
                        for (Catalogo c: presentacion){
                    %>
                    <option value="<%=c.getId()%>" <% if(c.getId() == p.getPresentacion()){%> selected<% }%>><%=c.getDescripcion()%> </option>
                    <%  
                    }
                    %>
              </select>
            </div>
            <div class="box">
                <select name="tamano">
                <option selected disabled>Tamaño</option>
                <%  
                        for (Catalogo c: tamano){
                    %>
                    <option value="<%=c.getId()%>" <% if(c.getId() == p.getTamano()){%> selected<% }%> ><%=c.getDescripcion()%> </option>
                    <%  
                    }
                    %>
              </select>
            </div>
            <div class="box">
                <select name="descuento">
                <option selected disabled>Descuento</option>
                <%  
                        for (Catalogo c: descuento){
                    %>
                   <option value="<%=c.getId()%>" <% if(c.getId() == p.getDescuento()){%> selected<% }%>><%=c.getPorcentaje()%>%</option>
                    <%  
                    }
                    %>
              </select>
            </div>
            <input type="number" placeholder="Precio(MXN)" name="precio" value="<%=p.getPresentacion() %>" required>
            <input type="number" placeholder="Número de unidades disponibles(stock)" name="cantidad" value="<%=p.getCantidad() %>" required>
            <br>
            <button onclick=" modificarpro()" type="button">Modificar Producto</button>
        </form>
        <script src="js/validar.js"></script>
    </div>
</body>
</html>
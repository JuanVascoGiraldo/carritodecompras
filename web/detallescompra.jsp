<%@page import="java.util.List"%>
<%@page import="Modelo.*"%>
<%@page import="Control.*"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
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
        MCompra mc = AccionesCompra.consultarcomprasUsu((Integer)sesion.getAttribute("id"), id);
        List<DCompra> lista = AccionesCompra.consultardetallecompra(id);
    %>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ver Compra</title>
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
                        <a href="VerCompras.jsp">VOLVER</a>
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
        <h1>Detalles De La Compra</h1>
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
                    </tr>
                </thead>
                <tbody>
                     <tr>    
                        <td><%=mc.getId() %></td>
                        <td><%=mc.getFecha() %></td>
                        <td><%=mc.getForma_des() %></td>
                        <td><%=mc.getTotal() %>MXN</td>
                    </tr>    
                        <% 
                        %>
                    
                </tbody>
            </table>
            <br>
            <br>
            <table>
                <thead>
                    <tr>
                        <th>ID Producto</th>
                        <th>Sabor</th>
                        <th>Presentación</th>
                        <th>Tamaño</th>
                        <th>Descuento</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Subtotal del Pedido</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                       for(DCompra dc: lista){
                    %>
                    <tr>
                        <td><%=dc.getProductos().getId() %></td>
                        <td><%=dc.getProductos().getDes_sabor() %></td>
                        <td><%=dc.getProductos().getDes_presentacion() %></td>
                        <td><%=dc.getProductos().getDes_tamano() %></td>
                        <td><%=dc.getProductos().getDes_descuento() %> %</td>
                        <td><%=dc.getProductos().getPrecio() %>MXN</td>
                        <td><%=dc.getCantidad() %></td>
                        <td><%=dc.getTotal() %></td>
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

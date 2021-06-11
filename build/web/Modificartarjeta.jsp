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
        MTarjeta t= AccionesTarjeta.buscarTargetabyID(id);
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
        <form action="ModificarTarjeta" name="moditar">
            <h1>Modificar Tarjeta</h1>
            <input type="number" name="id" value="<%=t.getId()%>" readonly="readonly">
            <input type="number" placeholder="Numero de Tarjeta" name="numero" value="<%=t.getNumero()%>" required>
            <input type="text" name="fechaexp" placeholder="Ingresa la fecha de expiración ej 02/21" value="<%=t.getFecha() %>" required>
            <input type="number" placeholder="Codigo de seguridad" name="codigo" value="<%=t.getCodigo()%>" required>
            <div class="box">
                <select name="tipo">
                <option selected disabled>Tipo de Tarjeta:</option>
                <% for(Catalogo c: lista){  %>
                <option value="<%=c.getId() %>" <% if(c.getId() == t.getTipo()){%>selected<% } %> ><%=c.getDescripcion()%></option>
                <% }%>
              </select>
            </div>
            <br>
            <button onclick="modificartar()" type="button">Modificar Tarjeta</button>
        </form>
    </div>
    <script src="js/validar.js"></script>
</body>
</html>
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
        if(lista.size() == 0){
            %>
    
            <jsp:forward page="error.jsp">
                <jsp:param name="Error" value="Es obligatorio identificarse" />
            </jsp:forward>
    <%
        }
        List<MTarjeta> listas = AccionesTarjeta.buscartarjetasusuario((Integer)sesion.getAttribute("id"));
    %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagar Con Tarjeta</title>
    <link rel="stylesheet" href="css/masterstyle.css">
    <link rel="icon" type="image/png" href="img/favicon.ico">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
</head>

<body background="img/fondoOp.jpg">
    <header>
        <div class="cabecera">
            <img src="img/corazon.png" class="logo">
            <span>
                <nav class="navigation">
                <ul class="show">
                    <li>
                        <a href="Vercarrito.jsp">VOLVER</a>
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
        <h1>Selecciona la tarjeta con la que deseas pagar</h1>
    </div>
    <br>
    <br>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        /* The Modal (background) */
        
        .modal {
            display: none;
            /* Hidden by default */
            position: fixed;
            /* Stay in place */
            z-index: 1;
            /* Sit on top */
            padding-top: 100px;
            /* Location of the box */
            left: 0;
            top: 0;
            width: 100%;
            /* Full width */
            height: 100%;
            /*
    Full height */
            overflow: auto;
            /* Enable scroll if needed */
            background-color: rgb(0, 0, 0);
            /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4);
            /* Black w/ opacity */
        }
        /* Modal Content */
        
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            text-align: center;
        }
        /* The Close Button */
        
        .close {
            color: #aaaaaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        
        .close:hover,
        .close:focus {
            color: #000;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    </head>

    <body>
        <div class="principal">
            <form name="tarje">
            <div class="alfa">
                
                <div class="box">
                    <select name="tar">
                <option selected disabled>Selecciona tu tarjeta:</option>
                <%  for(MTarjeta t: listas){        %>
                <option><%= t.getNumero()%></option>
                <%      }   %>
              </select> <br><br>
                    <button id="myBtn" class="btn btn1" type="button">Confirmar</button>
                </div>
            </div>
            <!-- The Modal -->
            <div id="myModal" class="modal">

                <!-- Modal content -->
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <h2>Tu compra se esta procesando...</h2>
                    <img src="img/gatitoTrabajando.jpg"><br><br><br>
                    <a href="RegistrarCompra?metodo=1">
                        <button class="btn btn1" type="button">Presiona aquí para continuar</button>
                    </a>
                </div>

            </div>
            </form>
            <script>
                // Get the modal
                var modal = document.getElementById("myModal");

                // Get the button that opens the modal
                var btn = document.getElementById("myBtn");

                // Get the <span> element that closes the modal
                var span = document.getElementsByClassName("close")[0];

                // When the user clicks the button, open the modal 
                btn.onclick = function() {
                    var select = document.tarje.tar;
                    var tar = select.options[select.selectedIndex].value;
                    if(tar === "Selecciona tu tarjeta:" ){
                        alert("Selecciona una tarjeta");
                    }else{
                        modal.style.display = "block";
                    }

                }

                // When the user clicks on <span> (x), close the modal
                span.onclick = function() {
                    modal.style.display = "none";
                }

                // When the user clicks anywhere outside of the modal, close it
                window.onclick = function(event) {
                    if (event.target == modal) {
                        modal.style.display = "none";
                    }
                }
            </script>
            <footer>
                <p>Página con fines educativos, Creada por Elizalde Hernández Alan, García García Aram Jesua, Gutierrez Bueno Elizabeth Andrea y Vasco Giraldo Juan Esteban</p>
            </footer>
    </body>

</html>
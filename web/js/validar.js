function iniciarsesion(){
    var nombre= document.iniciar.email.value;
    var pass = document.iniciar.pass.value;
    let expresioncorreo = /^\w+@\w+\.+[a-z]+$/;
    let expresioncontra =  /^[a-zA-Z0-9 ]+$/;
    var allvalid = true;

    if(nombre === "" || pass === "" ){
        allvalid = false;
        alert("llena todo los campos vacios");
    }
    else if(nombre.length > 30 || pass.length > 30){
        allvalid = false;
        alert("Ingresa menos caracteres en los campos");
    }
    else if(!expresioncorreo.test(nombre)){
        allvalid = false;
        alert("El correo ingresado no es válido, escriba por favor uno con la estructura: texto@texto.texto");
    }else if(!expresioncontra.test(pass)){
        allvalid = false;
        alert("Ingresa solo letras y numero en el campo de contraseña");
    }

    if(allvalid === true){
        document.iniciar.submit();
    }
}

function registrase(){
    var nombre = document.registrar.nombre.value;
    var paterno = document.registrar.appat.value;
    var materno = document.registrar.apmat.value;
    var fecha = document.registrar.fecha.value;
    var par = document.registrar.telpar.value;
    var celular = document.registrar.telcel.value;
    var email = document.registrar.email.value;
    var pass = document.registrar.pass.value;
    var confpass = document.registrar.confpass.value;
    let expresioncorreo = /^\w+@\w+\.+[a-z]+$/;
    let expresioncontra =  /^[a-zA-Z0-9 ]+$/;
    let expresiononlytext = /^[a-zA-Z ]+$/; 
    let expresionfecha = /^([012][1-9]|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
    let expresiononlynumber = /^([0-9])*$/;
    var allvalid = true;

    if(nombre === "" || paterno === "" || materno === "" || fecha=== "" || par=== "" || celular=== "" || email=== ""
    || pass=== "" || confpass === ""){
        alert("Llena los campos vacios");
        allvalid = false;
    }
    else if(nombre.length > 20 || paterno.length > 20 || materno.length > 20 || fecha.length > 10 || par.length > 10 
        || celular.length > 10 || pass.length > 30 ||  email.length > 30 ){
        alert("Ingresa menos caracteres en los campos de texto");
        allvalid = false;
    }
    else if(!expresiononlytext.test(nombre)){
        alert("Ingresa solo letras en el nombre");
        allvalid = false;
    }
    else if(!expresiononlytext.test(paterno)){
        alert("Ingresa solo letras en el apellido paterno");
        allvalid = false;
    }
    else if(!expresiononlytext.test(materno)){
        alert("Ingresa solo letras en el apellido materno");
        allvalid = false;
    }
    else if(!expresionfecha.test(fecha)){
        alert("El formato de fecha esta mal debe de ser 02/05/2021");
        allvalid = false;
    }else if(!expresiononlynumber.test(par)){
        alert("ingresa solamente numeros en el campo de telefono fijo");
        allvalid = false;
    }else if(!expresiononlynumber.test(celular)){
        alert("ingresa solamente numeros en el campo de celular");
        allvalid = false;
    }
    else if(!expresioncorreo.test(email)){
        alert("El correo ingresado no es válido, escriba por favor uno con la estructura: texto@texto.texto");
        allvalid = false;
    }
    else if(!expresioncontra.test(pass)){
        alert("Ingresa solo numeros y letras en el campo de contraseña");
        allvalid = false;
    }
    else if(!expresioncontra.test(confpass)){
        alert("Ingresa solo numeros y letras en el campo de confirmar contraseña");
        allvalid = false;
    }
    else if(pass !== confpass){
        alert("La confirmación de la contraseña no es igual a la contraseña, ingrese los mismos caractéres de la contraseña, por favor.");
        allvalid = false;
    }
    if(allvalid === true){
        document.registrar.submit();
    }
}

function anadirproducto(){
    var select = document.agregarpro.sabor;
    var sabor = select.options[select.selectedIndex].value;
    var select1 = document.agregarpro.tamano;
    var tamano = select1.options[select1.selectedIndex].value;
    var select2 = document.agregarpro.descuento;
    var descuento = select2.options[select2.selectedIndex].value;
    var select3 = document.agregarpro.presentacion;
    var presentacion = select3.options[select3.selectedIndex].value;
    var precio = document.agregarpro.precio.value;
    var cantidad = document.agregarpro.cantidad.value;
    let expresiononlynumber = /^([0-9])*$/;
    let expresiononlynumber1 = /^\d*\.?\d+$/;
    var allvalid = true;

    if(precio === "" || cantidad === "" || sabor === "Sabor" || presentacion === "Presentacion" || tamano === "Tamaño"
    || descuento === "Descuento"){
        allvalid = false;
        alert("Llena los campos vacios");
    }
    else if(precio.length > 8 || cantidad.length > 8){
        alert("Llena los campos vacios");
        allvalid = false;
    }else if(!expresiononlynumber1.test(precio)){
        alert("Ingresa solo numeros en el campo de precio");
        allvalid = false;
    }else if(!expresiononlynumber.test(cantidad)){
        alert("Ingresa solo numeros en el campo de Stock");
        allvalid = false;
    }

    if(allvalid == true){
        document.agregarpro.submit();
    }
}

function modificarusua(){
    var nombre = document.modificarusu.nombre.value;
    var paterno = document.modificarusu.appat.value;
    var materno = document.modificarusu.apmat.value;
    var fecha = document.modificarusu.fecha.value;
    var par = document.modificarusu.telpar.value;
    var celular = document.modificarusu.telcel.value;
    var email = document.modificarusu.email.value;
    var pass = document.modificarusu.pass.value;
    var confpass = document.modificarusu.confpass.value;
    let expresioncorreo = /^\w+@\w+\.+[a-z]+$/;
    let expresioncontra =  /^[a-zA-Z0-9 ]+$/;
    let expresiononlytext = /^[a-zA-Z ]+$/; 
    let expresionfecha = /^([012][1-9]|3[01])(\/)(0[1-9]|1[012])\2(\d{4})$/;
    let expresiononlynumber = /^([0-9])*$/;
    var allvalid = true;

    if(nombre === "" || paterno === "" || materno === "" || fecha=== "" || par=== "" || celular=== "" || email=== ""
    || pass=== "" || confpass === ""){
        alert("Llena los campos vacios");
        allvalid = false;
    }
    else if(nombre.length > 20 || paterno.length > 20 || materno.length > 20 || fecha.length > 10 || par.length > 10 
        || celular.length > 10 || pass.length > 30 ||  email.length > 30 ){
        alert("Ingresa menos caracteres en los campos de texto");
        allvalid = false;
    }
    else if(!expresiononlytext.test(nombre)){
        alert("Ingresa solo letras en el nombre");
        allvalid = false;
    }
    else if(!expresiononlytext.test(paterno)){
        alert("Ingresa solo letras en el apellido paterno");
        allvalid = false;
    }
    else if(!expresiononlytext.test(materno)){
        alert("Ingresa solo letras en el apellido materno");
        allvalid = false;
    }
    else if(!expresionfecha.test(fecha)){
        alert("El formato de fecha esta mal debe de ser 02/05/2021");
        allvalid = false;
    }else if(!expresiononlynumber.test(par)){
        alert("ingresa solamente numeros en el campo de telefono fijo");
        allvalid = false;
    }else if(!expresiononlynumber.test(celular)){
        alert("ingresa solamente numeros en el campo de celular");
        allvalid = false;
    }
    else if(!expresioncorreo.test(email)){
        alert("El correo ingresado no es válido, escriba por favor uno con la estructura: texto@texto.texto");
        allvalid = false;
    }
    else if(!expresioncontra.test(pass)){
        alert("Ingresa solo numeros y letras en el campo de contraseña");
        allvalid = false;
    }
    else if(!expresioncontra.test(confpass)){
        alert("Ingresa solo numeros y letras en el campo de confirmar contraseña");
        allvalid = false;
    }
    else if(pass !== confpass){
        alert("La confirmación de la contraseña no es igual a la contraseña, ingrese los mismos caractéres de la contraseña, por favor.");
        allvalid = false;
    }
    if(allvalid === true){
        document.modificarusu.submit();
    }
}

function modificarpro(){
    var select = document.modipro.sabor;
    var sabor = select.options[select.selectedIndex].value;
    var select1 = document.modipro.tamano;
    var tamano = select1.options[select1.selectedIndex].value;
    var select2 = document.modipro.descuento;
    var descuento = select2.options[select2.selectedIndex].value;
    var select3 = document.modipro.presentacion;
    var presentacion = select3.options[select3.selectedIndex].value;
    var precio = document.modipro.precio.value;
    var cantidad = document.modipro.cantidad.value;
    let expresiononlynumber = /^([0-9])*$/;
    let expresiononlynumber1 = /^\d*\.?\d+$/;
    var allvalid = true;

    if(precio === "" || cantidad === "" || sabor === "Sabor" || presentacion === "Presentacion" || tamano === "Tamaño"
    || descuento === "Descuento"){
        allvalid = false;
        alert("Llena los campos vacios");
    }
    else if(precio.length > 8 || cantidad.length > 8){
        alert("Llena los campos vacios");
        allvalid = false;
    }else if(!expresiononlynumber1.test(precio)){
        alert("Ingresa solo numeros en el campo de precio");
        allvalid = false;
    }else if(!expresiononlynumber.test(cantidad)){
        alert("Ingresa solo numeros en el campo de Stock");
        allvalid = false;
    }

    if(allvalid == true){
        document.modipro.submit();
    }
}

function agregartar(){
    var numero = document.anadirtar.numero.value;
    var fecha = document.anadirtar.fechaexp.value;
    var codigo =  document.anadirtar.codigo.value;
    var select = document.anadirtar.tipo;
    var tipo = select.options[select.selectedIndex].value;
    let expresiononlynumber = /^([0-9])*$/;
    let expresionfecha = /^(0[1-9]|1[012])(\/)(\d{2})$/;
    var allvalid = true;

    if(numero === "" || fecha === "" || codigo === "" || tipo === "Tipo de Tarjeta"){
        allvalid = false;
        alert("Llena los campos vacios");
    }else if(numero.length != 16){
        allvalid = false;
        alert("Ingresa exactamente 16 dijitos en el numero de la tarjeta");
    }else if(codigo.length != 4){
        allvalid = false;
        alert("Ingresa 4 numeros en el codigo de la tarejta");
    }else if(!expresiononlynumber.test(numero)){
        allvalid = false;
        alert("Ingresa solo numeros en el campo de numero");
    }else if(!expresiononlynumber.test(codigo)){
        allvalid = false;
        alert("Ingresa solo numeros en el campo de codigo");
    }else if(!expresionfecha.test(fecha)){
        allvalid = false;
        alert("Formato incorrecto el formato debe ser 06/21");
    }
    
    if(allvalid == true){
        document.anadirtar.submit();
    }
}


function modificartar(){
    var numero = document.moditar.numero.value;
    var fecha = document.moditar.fechaexp.value;
    var codigo =  document.moditar.codigo.value;
    var select = document.moditar.tipo;
    var tipo = select.options[select.selectedIndex].value;
    let expresiononlynumber = /^([0-9])*$/;
    let expresionfecha = /^(0[1-9]|1[012])(\/)(\d{2})$/;
    var allvalid = true;

    if(numero === "" || fecha === "" || codigo === "" || tipo === "Tipo de Tarjeta"){
        allvalid = false;
        alert("Llena los campos vacios");
    }else if(numero.length != 16){
        allvalid = false;
        alert("Ingresa exactamente 16 dijitos en el numero de la tarjeta");
    }else if(codigo.length != 4){
        allvalid = false;
        alert("Ingresa 4 numeros en el codigo de la tarejta");
    }else if(!expresiononlynumber.test(numero)){
        allvalid = false;
        alert("Ingresa solo numeros en el campo de numero");
    }else if(!expresiononlynumber.test(codigo)){
        allvalid = false;
        alert("Ingresa solo numeros en el campo de codigo");
    }else if(!expresionfecha.test(fecha)){
        allvalid = false;
        alert("Formato incorrecto el formato debe ser 06/21");
    }
    
    if(allvalid == true){
        document.moditar.submit();
    }
}

function anadircarrit(){
    var cantidad = document.anadircarrito.cantidad.value;
    var stock = document.anadircarrito.stock.value;
    let expresiononlynumber = /^([0-9])*$/;
    var allvalid = true;

    if(cantidad === "" || stock === ""){
        allvalid = false;
        alert("Llena los campos vacios");
    }else if(!expresiononlynumber.test(cantidad) || !expresiononlynumber.test(stock)){
        allvalid = false;
        alert("Ingresa Solo numeros en catidad");
    }else if(cantidad >> stock){
        allvalid = false;
        alert("No hay suficientes unidades disponibles ingresa una cantidad mas pequeña");
    }
    if(allvalid == true){
        document.anadircarrito.submit();
    }
}

function modCarrito(){
    var cantidad = document.ModCar.cantidad.value;
    var stock = document.ModCar.stock.value;
    let expresiononlynumber = /^([0-9])*$/;
    var allvalid = true;

    if(cantidad === "" || stock === ""){
        allvalid = false;
        alert("Llena los campos vacios");
    }else if(!expresiononlynumber.test(cantidad) || !expresiononlynumber.test(stock)){
        allvalid = false;
        alert("Ingresa Solo numeros en catidad");
    }else if(cantidad >> stock){
        allvalid = false;
        alert("No hay suficientes unidades disponibles ingresa una cantidad mas pequeña");
    }
    if(allvalid == true){
        document.ModCar.submit();
    }
}
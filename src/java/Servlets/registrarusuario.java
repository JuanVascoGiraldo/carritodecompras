
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Modelo.*;
import Control.*;


public class registrarusuario extends HttpServlet {

    private static final String FECHA= "^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";
    private static final String NUMEROS = "[0-9]*";
    private static final String CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String LETRASYNUMEROS = "^[_A-Za-z0-9_-]{3,30}$";
    private static final String LETRAS = "[a-zA-z ]*";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if(request.getParameter("nombre") != null && request.getParameter("appat") != null &&
                request.getParameter("apmat") != null && request.getParameter("fecha") != null &&
                request.getParameter("telcel") != null && request.getParameter("telpar") != null &&
                request.getParameter("email") != null && request.getParameter("pass") != null ){
                
                Pattern pattern = Pattern.compile(CORREO);
                Matcher matcher = pattern.matcher(request.getParameter("email"));        
                boolean r = matcher.matches();
                pattern = Pattern.compile(LETRASYNUMEROS);
                matcher = pattern.matcher(request.getParameter("pass"));        
                boolean r2 = matcher.matches();
                pattern = Pattern.compile(LETRAS);
                matcher = pattern.matcher(request.getParameter("nombre"));        
                boolean r3 = matcher.matches();
                pattern = Pattern.compile(LETRAS);
                matcher = pattern.matcher(request.getParameter("appat"));        
                boolean r4 = matcher.matches();
                pattern = Pattern.compile(LETRAS);
                matcher = pattern.matcher(request.getParameter("apmat"));        
                boolean r5 = matcher.matches();
                pattern = Pattern.compile(FECHA);
                matcher = pattern.matcher(request.getParameter("fecha"));        
                boolean r6 = matcher.matches();
                pattern = Pattern.compile(NUMEROS);
                matcher = pattern.matcher(request.getParameter("telcel"));        
                boolean r7 = matcher.matches();
                pattern = Pattern.compile(NUMEROS);
                matcher = pattern.matcher(request.getParameter("telpar"));        
                boolean r8 = matcher.matches();
                
                if(r == true && r2 == true && r3 == true && r4 == true &&
                        r5 == true && r6 == true &&  r7 == true && r8 == true ){
                    String nombre, appat, apmat, email, pass, fecha;
                    Long telcel, telpar;
                    nombre = request.getParameter("nombre");
                    appat =  request.getParameter("appat");
                    apmat = request.getParameter("apmat") ;
                    email = request.getParameter("email");
                    pass = request.getParameter("pass") ;
                    fecha= request.getParameter("fecha") ;
                    telcel = Long.parseLong(request.getParameter("telcel"));
                    telpar =  Long.parseLong(request.getParameter("telpar"));
                    MUsuario usu = new MUsuario();
                    usu.setNombre(nombre);
                    usu.setApmat(apmat);
                    usu.setAppat(appat);
                    usu.setEmail(email);
                    usu.setPassword(pass);
                    usu.setFecha(fecha);
                    usu.setCelular(telcel);
                    usu.setTelefono(telpar);
                    usu.setRol(0);
                    int estatus = AccionesUsuario.AgregarUsuario(usu);
                    if(estatus > 0){
                        String link = "iniciarsesion?email="+email+"&&pass="+pass ;
                        response.sendRedirect(link);
                    }else{
                        response.sendRedirect("error.jsp");
                    }
                }else{
                    response.sendRedirect("error.jsp");
                } 
            }else{
                response.sendRedirect("error.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

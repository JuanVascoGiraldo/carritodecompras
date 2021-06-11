
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.*;
import Modelo.*;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ModificarTarjeta extends HttpServlet {

    private static final String FECHA= "^(0[1-9]|1[0-2])(\\/|-)(\\d{2})$";
    private static final String NUMEROSCVV = "[0-9]{4,4}";
    private static final String NUMEROS = "[0-9]*";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("id") != null && sesion.getAttribute("permiso") != null  ){
                if((Integer)sesion.getAttribute("permiso") == 0){
                    if(request.getParameter("numero") != null && request.getParameter("fechaexp") != null
                            && request.getParameter("codigo") != null && request.getParameter("tipo") != null
                            && request.getParameter("id") != null){
                        Pattern pattern = Pattern.compile(NUMEROS);
                        Matcher matcher = pattern.matcher(request.getParameter("numero"));        
                        boolean r = matcher.matches();
                        pattern = Pattern.compile(NUMEROSCVV);
                        matcher = pattern.matcher(request.getParameter("codigo"));        
                        boolean r2 = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("tipo"));        
                        boolean r3 = matcher.matches();
                        pattern = Pattern.compile(FECHA);
                        matcher = pattern.matcher(request.getParameter("fechaexp"));        
                        boolean r4 = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("id"));        
                        boolean r5 = matcher.matches();
                        if(r == true && r2 == true  && r3 == true  && r4 == true && r5 == true ){
                            long numero = Long.parseLong(request.getParameter("numero"));
                            int codigo = Integer.parseInt(request.getParameter("codigo"));
                            int tipo = Integer.parseInt(request.getParameter("tipo"));
                            String fechaexp = request.getParameter("fechaexp");
                            int id = Integer.parseInt(request.getParameter("id"));
                            MTarjeta t = new MTarjeta();
                            t.setCodigo(codigo);
                            t.setFecha(fechaexp);
                            t.setNumero(numero);
                            t.setTipo(tipo);
                            t.setId(id);
                            int Estatus = AccionesTarjeta.ModificarTarjeta(t);
                            if(Estatus > 0){
                                response.sendRedirect("Tarjetas.jsp");
                            }else{
                                response.sendRedirect("error.jsp");
                            }
                        }
                        else{
                            response.sendRedirect("error.jsp");
                        }
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

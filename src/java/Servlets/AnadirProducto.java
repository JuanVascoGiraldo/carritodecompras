
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


public class AnadirProducto extends HttpServlet {
    
    private static final String NUMEROS = "[0-9]*";
    private static final String NUMEROSD = "^[0-9]+([.][0-9]+)?$";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("id") != null && sesion.getAttribute("permiso") != null ){
                if((Integer)sesion.getAttribute("permiso") == 1){
                    if(request.getParameter("sabor") != null && request.getParameter("tamano") != null &&
                            request.getParameter("presentacion") != null && request.getParameter("descuento") != null &&
                            request.getParameter("precio") != null && request.getParameter("cantidad") != null){
                        Pattern pattern = Pattern.compile(NUMEROS);
                        Matcher matcher = pattern.matcher(request.getParameter("sabor"));        
                        boolean r = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("tamano"));        
                        boolean r2 = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("presentacion"));        
                        boolean r3 = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("descuento"));        
                        boolean r4 = matcher.matches();
                        pattern = Pattern.compile(NUMEROSD);
                        matcher = pattern.matcher(request.getParameter("precio"));        
                        boolean r5 = matcher.matches();
                        pattern = Pattern.compile(NUMEROS);
                        matcher = pattern.matcher(request.getParameter("cantidad"));        
                        boolean r6 = matcher.matches();
                        if(r==true && r2==true && r3==true && r4==true && r5==true && r6==true){
                            int sabor = Integer.parseInt(request.getParameter("sabor"));
                            int tamano = Integer.parseInt(request.getParameter("tamano"));
                            int presentacion = Integer.parseInt(request.getParameter("presentacion"));
                            int descuento = Integer.parseInt(request.getParameter("descuento"));
                            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                            Double precio = Double.parseDouble(request.getParameter("precio"));
                            MProducto p = new MProducto();
                            p.setSabor(sabor);
                            p.setTamano(tamano);
                            p.setPresentacion(presentacion);
                            p.setDescuento(descuento);
                            p.setCantidad(cantidad);
                            p.setPrecio(precio);
                            int Estatus = AccionesProducto.agregarProducto(p);
                            if(Estatus >0 ){
                                response.sendRedirect("ProductosAdmin.jsp?fil=0");
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

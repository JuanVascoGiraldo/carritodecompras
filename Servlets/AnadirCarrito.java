
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Control.*;
import Modelo.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AnadirCarrito extends HttpServlet {
    
    private static final String NUMEROS = "[0-9]*";
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("id") != null && sesion.getAttribute("lista") != null ){
                if(request.getParameter("id") != null &&  request.getParameter("cantidad") != null){
                    Pattern pattern = Pattern.compile(NUMEROS);
                    Matcher matcher = pattern.matcher(request.getParameter("id"));        
                    boolean r = matcher.matches();
                    pattern = Pattern.compile(NUMEROS);
                    matcher = pattern.matcher(request.getParameter("cantidad"));        
                    boolean r2 = matcher.matches();
                    if(r == true && r2 == true){
                        MProducto p = AccionesProducto.buscarallProducto(Integer.parseInt(request.getParameter("id")));
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        List<DCompra> lista =  (List<DCompra>)sesion.getAttribute("lista");
                        DCompra dc = new DCompra();
                        int i = 0;
                        for (DCompra c: lista){
                            if(c.getProductos().getId() == p.getId()){
                                cantidad += c.getCantidad();
                                lista.remove(i);
                                break;
                            }
                            i++;
                        }
                        dc.setProductos(p);
                        Double total = p.getPrecio()*cantidad;
                        Double descuento = (p.getDes_descuento() * total/ 100);
                        dc.setCantidad(cantidad);
                        dc.setTotal(total-descuento);
                        lista.add(dc);
                        sesion.setAttribute("lista", lista);
                        response.sendRedirect("Vercarrito.jsp");
                    }
                    else{
                        response.sendRedirect("error.jsp");
                    }
                }else{
                    response.sendRedirect("error.jsp");
                }
            }
            else{
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


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
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarCompra extends HttpServlet {
    
    private static final String NUMEROS = "[1-2]";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("id") != null && sesion.getAttribute("lista") != null){
                if(request.getParameter("metodo") != null){
                    Pattern pattern = Pattern.compile(NUMEROS);
                    Matcher matcher = pattern.matcher(request.getParameter("metodo"));        
                    boolean r = matcher.matches();
                    boolean r2 = false;
                    int forma = 0;
                    if(r == true){
                        List<Catalogo> ca = AccionesCatalogo.Pago();
                        forma = Integer.parseInt(request.getParameter("metodo"));
                        for(Catalogo cat: ca){
                            if(cat.getId() == forma){
                                r2 = true;
                                break;
                            }
                        }
                    }
                    if(r2 == true){
                        Calendar fecha = java.util.Calendar.getInstance();
                        String fech=fecha.get(java.util.Calendar.DATE) + "/"
                            + (fecha.get(java.util.Calendar.MONTH)+1) + "/" 
                                    + fecha.get(java.util.Calendar.YEAR);
                        Double total = 0.0;
                        List<DCompra> lista = (List<DCompra>)sesion.getAttribute("lista");
                        for(DCompra c : lista){
                            total += c.getTotal();                        
                        }
                        int id_usu = (Integer)sesion.getAttribute("id");
                        MCompra mc = new MCompra();
                        mc.setFecha(fech);
                        mc.setForma(forma);
                        mc.setTotal(total);
                        mc.setUsuario(id_usu);
                        boolean estatus = AccionesCompra.RegistrarCompra(mc, lista);
                        if(estatus == true){
                            int esta = 0;
                            for(DCompra dc: lista){
                                esta = AccionesProducto.actualizarStock(dc.getProductos().getId(), dc.getCantidad());
                                if(esta <1){
                                    break;
                                }
                            }
                            if(esta > 0){
                                lista.clear();
                                sesion.setAttribute("lista", lista);
                                response.sendRedirect("Vercarrito.jsp");
                            }
                            else{
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


package Servlets;

import Control.AccionesUsuario;
import Modelo.DCompra;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class iniciarsesion extends HttpServlet {
 private static final String CORREO = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String LETRASYNUMEROS = "^[_A-Za-z0-9_-]{3,30}$";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession sesion = request.getSession(true);
            if(sesion.getAttribute("id") == null){
            String correo, contra;
            correo = request.getParameter("email");
            contra = request.getParameter("pass");
            if(correo != null && correo != null){
                Pattern pattern = Pattern.compile(CORREO);
                Matcher matcher = pattern.matcher(correo);        
                boolean r = matcher.matches();
                pattern = Pattern.compile(LETRASYNUMEROS);
                matcher = pattern.matcher(contra);        
                boolean r2 = matcher.matches();
                if(r == true && r2 == true){
                    List <Integer> respuesta = AccionesUsuario.iniciarsesion(correo, contra);
                    if(respuesta.size() != 0){
                        int entro= respuesta.get(0);
                        int permiso=respuesta.get(1);
                        int id= respuesta.get(2);
                        //redireccionar si coincide o no coincide la contraseña
                        if(entro == 1){
                            sesion.setAttribute("id", id);
                            sesion.setAttribute("permiso", permiso);
                            
                            //si coincide
                            //redireccionar segun la vista
                            if(permiso == 1){
                                response.sendRedirect("ProductosAdmin.jsp");
                                //redireccionar vista adminitrador
                            }
                            else{
                                List<DCompra> lista = new ArrayList<DCompra>();
                                sesion.setAttribute("lista", lista);
                                response.sendRedirect("Productosusuario.jsp");
                                //redireccionar vista usuario
                            }
                        }
                        else{
                            // no coincide
                            response.sendRedirect("Isesion.jsp");
                            //volver a inicio de sesion
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
        }else{
            if((Integer)sesion.getAttribute("permiso") == 1){
                response.sendRedirect("ProductosAdmin.jsp");
                }
            if((Integer)sesion.getAttribute("permiso") == 0){
                response.sendRedirect("");
                }
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

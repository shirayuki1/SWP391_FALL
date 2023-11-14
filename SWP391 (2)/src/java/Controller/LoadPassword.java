/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class LoadPassword  extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             out.print("<form action=\"ChangePassword\" method=\"POST\" id=\"changepasswords\" onsubmit=\"return validateForm()\">\n"
                      + "  <div class=\"row\">\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Mật khẩu cũ</label>\n"
                    + "      <input class=\"form-control\" type=\"password\" name=\"password\">\n"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">Mật khẩu mới</label>\n"
                    + "      <input class=\"form-control\" type=\"password\" required name=\"newpassword\" id=\"newpassword\">\n"
                    + "      <span id=\"firstnameError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">nhập lại mật khẩu</label>\n"
                    + "      <input class=\"form-control\" type=\"password\" required name=\"comfirm\" id=\"comfirm\">\n"
                    + "      <span id=\"lastnameError\" class=\"error\"></span>"
                    + "    </div>\n");
           out.print("<button class=\"btn btn-save\" type=\"submit\">save</button>\n");
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

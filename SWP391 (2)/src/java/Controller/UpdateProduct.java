 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ProductDAO;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */

public class UpdateProduct extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProduct at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String id = request.getParameter("masp");
        String name = request.getParameter("tensp");
        
        String size = request.getParameter("sizesp");
        String giasp = request.getParameter("giasp");
        String discountsp = request.getParameter("discountsp");
        String categorysp = request.getParameter("categorysp");
        String material = request.getParameter("materialsp");
        String quantity = request.getParameter("quantity");
        String img = request.getParameter("image");
        String oldimg = request.getParameter("oldimage");
        System.out.println(img + "UpdateProducts");
        int isAvailable = 0;
        if(Integer.parseInt(quantity) > 0){
            isAvailable = 1;
        }
        
          if(Integer.parseInt(quantity) <0){
            quantity = "0";
        }
        ProductDAO pdo = new ProductDAO();
        if(img.equals("")){ //nếu không đổi img thì lấy oldimg
            pdo.updateProduct(id, size, name, giasp, discountsp, categorysp, quantity, isAvailable, material, oldimg);
        }else{
            pdo.updateProduct(id, size, name, giasp, discountsp, categorysp, quantity, isAvailable, material, img);
        }
//        pdo.updateSP(id, size, name, giasp, discountsp, categorysp,quantity, isAvailable, material);

        
        response.sendRedirect("ManagerProductController");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CategoryDAO;
import Dao.ProductDAO;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Admin
 */


public class ManagerProductController extends HttpServlet {

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
        ProductDAO pdo = new ProductDAO();
        CategoryDAO cdo = new CategoryDAO();
        List<Product> productList = pdo.getAllProducts();
        List<Category> categoryList = cdo.getAll();
        request.setAttribute("list", productList);
        String messAddCategory = (String) request.getAttribute("messAddCategory");
        String messAddMaterial = (String) request.getAttribute("messAddMaterial");
        request.setAttribute("messAddCategory", messAddCategory);
        request.setAttribute("messAddMaterial", messAddMaterial);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
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
       ProductDAO pdo = new ProductDAO();
        CategoryDAO cdo = new CategoryDAO();
        List<Product> productList = pdo.getAll();
        List<Category> categoryList = cdo.getAll();
        request.setAttribute("list", productList);
        String messAddCategory = (String) request.getAttribute("messAddCategory");
        
        request.setAttribute("messAddCategory", messAddCategory);
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("managerproduct.jsp").forward(request, response);
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

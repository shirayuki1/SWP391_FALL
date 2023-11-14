/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AccountDAO;
import Dao.CategoryDAO;
import Dao.MaterialDAO;
import Dao.OrderDAO;
import Dao.ProductDAO;
import Dao.RequestDAO;
import Dao.UserDAO;
import Model.Account;
import Model.Category;
import Model.Material;
import Model.Order1;
import Model.Product;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "ManageOrderController", urlPatterns = {"/ManageOrderController"})
public class ManageOrderController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDAO rdo = new RequestDAO();
        MaterialDAO mdo = new MaterialDAO();
        AccountDAO ado = new AccountDAO();
        OrderDAO or = new OrderDAO();
        List<Order1> accList = or.getAll();
       
       String user = request.getParameter("user");
        System.out.println(user);
        
        if(user.equals("manager")){
            request.setAttribute("listAcc", accList);
           response.setContentType("text/html;charset=UTF-8");
           request.getRequestDispatcher("managermanageOrder.jsp").forward(request, response);   
        }else{
            request.setAttribute("listAcc", accList);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
        }
       
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
      PrintWriter out = response.getWriter();
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String quantity = request.getParameter("quantity");
//        String status = request.getParameter("status");
//        String price = request.getParameter("price");
//        String categoryID = request.getParameter("categoryID");
//        ProductDAO pdo = new ProductDAO();
        
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

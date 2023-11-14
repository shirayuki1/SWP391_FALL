/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AccountDAO;
import Dao.MaterialDAO;
import Dao.OrderDAO;
import Dao.RequestDAO;
import Model.Account;
import Model.Material;
import Model.OrderDetailUser;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ViewMyOrder", urlPatterns = {"/ViewMyOrder"})
public class ViewMyOrder extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          
            
            OrderDAO oda = new OrderDAO();
            
            
            String customerID = request.getParameter("customerID");
            int customerIDInt = Integer.parseInt(customerID);
            
            List<OrderDetailUser> orderList = oda.getOrderDetailUser(customerIDInt);
            
            
            
            request.setAttribute("listAcc", orderList);
            response.setContentType("application/json");
            request.getRequestDispatcher("viewOrder.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ViewMyOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewMyOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String quantity = request.getParameter("quantity");
//        String status = request.getParameter("status");
//        String price = request.getParameter("price");
//        String categoryID = request.getParameter("categoryID");
//        ProductDAO pdo = new ProductDAO();
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
 
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AccountDAO;
import Dao.CustomerDAO;
import Dao.OrderDAO;
import Model.Customer;
import Model.Product;
import Model.Promotion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CheckoutController extends HttpServlet {

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
            HashMap<Product, Integer> maps = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
          
            double totalPrice = 0;
            for (Product i : maps.keySet()) {
                totalPrice += i.getPrice() * (100 - i.getDiscount()) / 100 * maps.get(i);
            }
            request.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher("Checkout.jsp").forward(request, response);
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
   
        String email = request.getParameter("email");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String customerId = request.getParameter("customerID");
        int customerID ;
       AccountDAO accountDAO = new AccountDAO();
       if(customerId == null){
             customerID = accountDAO.insertAccount(email, lastname, phone, address);
        }
       else {
           int customerIdInt = Integer.parseInt(customerId);
           customerID = customerIdInt;
       }
        HashMap<Product, Integer> maps = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
        int totalitems = maps.size();
        OrderDAO orderDao = new OrderDAO();
        Promotion pro = (Promotion) request.getSession().getAttribute("pro");
        Integer orderID  = orderDao.insert(customerID,pro != null ? pro.getId() : null);
        orderDao.insertOderDetail(maps, orderID);
        orderDao.insertOderStatus(orderID);
        String url = request.getContextPath();
        response.sendRedirect(url);
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

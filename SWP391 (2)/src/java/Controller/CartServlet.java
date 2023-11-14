/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.ProductDAO;
import Dao.PromotionDAO;
import Model.Product;
import Model.Promotion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 *
 * @author kienb
 */
public class CartServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            String pid = request.getParameter("pid");
            String url = "";
            Product product = null;
            ProductDAO productDAO = new ProductDAO();
            if (pid != null) {
                product = productDAO.getProductByID(pid);
            }
            int quantity = request.getParameter("num") != null ? Integer.parseInt(request.getParameter("num")) : 1;
            HashMap<Product, Integer> maps = (HashMap<Product, Integer>) request.getSession().getAttribute("cart");
            if (maps == null) {
                maps = new HashMap<>();
            }
            if (action.equals("view")) {
                double totalPrice = 0;
                int totalProduct = 0;
                for (Product i : maps.keySet()) {
                    totalProduct += maps.get(i);
                    totalPrice += i.getPrice() * (100 - i.getDiscount()) / 100 * maps.get(i);
                }
                PromotionDAO promotionDAO = new PromotionDAO();
                Promotion pro = promotionDAO.getPromotion(totalProduct);
                if(pro != null){
                    request.getSession().setAttribute("pro", pro);
                }
                else {
                    request.getSession().removeAttribute("pro");
                }
                request.setAttribute("p", pro);
                request.setAttribute("totalPrice", totalPrice);
                request.getRequestDispatcher("ViewCart.jsp").forward(request, response);
                return;
            } else if (action.equals("add")) {
                if (maps.containsKey(product)) {
                    maps.put(product, quantity + maps.get(product));
                } else {
                    maps.put(product, quantity);
                }
                url = request.getHeader("referer");
                //nếu user mới vừa login
                if(url.equals("http://localhost:8080/SWP391/UserServlet?action=login")){
                    url = "ShowProductsServlet";
                }
            } else if (action.equals("delete")) {
                maps.remove(product);
                url = request.getHeader("referer");
            } else if (action.equals("update")) {
                maps.put(product, quantity);
                url = request.getHeader("referer");
            } else if (action.equals("checkout")) {
                
            }
            request.getSession().setAttribute("cart", maps);
            response.sendRedirect(url);
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

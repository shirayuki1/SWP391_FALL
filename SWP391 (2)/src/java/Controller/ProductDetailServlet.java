/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CategoryDAO;
import Dao.CommentDAO;
import Dao.ProductDAO;
import Model.Category;
import Model.Comment;
import Model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ProductDetailServlet extends HttpServlet {

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
       HttpSession session = request.getSession();
       //lấy hai productID để so sánh
        String productID = request.getParameter("productID");
        String id2 = request.getParameter("id2");    
       
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO cdo = new CategoryDAO();
        Product product = null;
        List<Product> list = new ArrayList<>();
        
        //Cho hai product vào list để đưa tới detail
        product = productDAO.getProductByID(productID);
        list.add(product);
        if(id2!=null){
            Product product2 = productDAO.getProductByID(id2);
            list.add(product2);
        }
        
        //Lấy sản phẩm cùng loại
        System.out.println(product + "ProductDettailServlet");
        String cateID = String.valueOf(product.getCategory().getId());
       
        List<Product> productCategory = productDAO.getProductByCategory(cateID);
        
        session.setAttribute("productList", list);
        request.setAttribute("productCategory", productCategory);
        List<Category> categoryList = cdo.getAll();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        request.setAttribute("listCate", gson.toJson(categoryList));
        
        //Lấy feedback của sản phẩm
        CommentDAO comDAO = new CommentDAO();
        int count = comDAO.countProductComment(productID);
        List<Comment> feedbacks = comDAO.getAllProductComment(productID);
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("count", count);
        request.setAttribute("nowProductID", productID);
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("productdetails.jsp");
        dispatcher.forward(request, response);
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

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
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DELL
 */
public class ProductSearchServlet extends HttpServlet {
     private ProductDAO productDAO;
      @Override
    public void init() throws ServletException {
        super.init();
        productDAO = new ProductDAO();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
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
         String action = request.getParameter("action");
         if(action != null ){
             if (action.equals("searchByName")){
                 String productName = request.getParameter("productName");
                 if(productName != null & !productName.isEmpty()){
                     List<Product> products = productDAO.searchProductsByName(productName);
                     HttpSession session = request.getSession();
                     session.setAttribute("searchResultsByName", products);
                     response.sendRedirect("searchProducts.jsp");
                       return;
                 }else response.sendRedirect("ShowProductsServlet");
                 
             } else if (action.equals("searchByPriceRange")) {
                  String minPriceStr = request.getParameter("minPrice");
                String maxPriceStr = request.getParameter("maxPrice");
                if (minPriceStr != null && !minPriceStr.isEmpty() && maxPriceStr != null && !maxPriceStr.isEmpty()) {
                    int minPrice = Integer.parseInt(minPriceStr);
                    int maxPrice = Integer.parseInt(maxPriceStr);
//                    BigDecimal maxPrice = new BigDecimal(maxPriceStr);
                    List<Product> products;

                    HttpSession session = request.getSession();
                    if (session.getAttribute("searchResultsByName") != null) {
                        List<Product> searchResults = (List<Product>) session.getAttribute("searchResultsByName");
                        products = productDAO.searchProductsByPriceRange(searchResults, minPrice, maxPrice);
                    } if  (session.getAttribute("searchResultsMaterial") != null) {
                        List<Product> searchResults = (List<Product>) session.getAttribute("searchResultsMaterial");
                        products = productDAO.searchProductsByPriceRange(searchResults, minPrice, maxPrice);
                    } 
                    if (session.getAttribute("searchResultsCategory") != null) {
                        List<Product> searchResults = (List<Product>) session.getAttribute("searchResultsCategory");
                        products = productDAO.searchProductsByPriceRange(minPrice, maxPrice);
                    }else{
                        List<Product> searchResults = (List<Product>) session.getAttribute("searchResultsByName");
                        products = productDAO.searchProductsByPriceRange(minPrice, maxPrice);
                    }
                    
                    
                    

                    session.setAttribute("searchResultsByPrice", products);
                    session.setAttribute("minPrice", minPrice);
                    session.setAttribute("maxPrice", maxPrice);
                    response.sendRedirect("searchProductsByPrice.jsp");
                    return;
             }else response.sendRedirect("searchProducts.jsp");
            }else if (action.equals("searchByCategory")) {
              String categoryName = request.getParameter("categoryName");
                if (categoryName != null && !categoryName.isEmpty()) {
                    List<Product> products = productDAO.searchProductsByCategory(categoryName);
                    HttpSession session = request.getSession();
                    session.setAttribute("searchResultsCategory", products);
//                    request.getRequestDispatcher("searchByCategory.jsp").forward(request, response);
                    response.sendRedirect("searchByCategory.jsp");

                    return;
                }
            }else if (action.equals("searchByMeterial")){
                 String materialName = request.getParameter("materialName");
                 if (materialName != null && !materialName.isEmpty()) {
                     List<Product> products = productDAO.searchProductsByMaterial(materialName);
                     HttpSession session = request.getSession(); 
                     session.setAttribute("searchResultsMaterial", products);
                     response.sendRedirect("searchByMaterial.jsp");
                 }
            }
             
         }
         
    }

 
  
}

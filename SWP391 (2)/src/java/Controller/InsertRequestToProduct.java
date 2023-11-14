/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ProductDAO;
import Dao.RequestDAO;
import Model.Request;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@WebServlet(name = "InsertRequestToProduct", urlPatterns = {"/InsertRequestToProduct"})
public class InsertRequestToProduct extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertRequestToProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertRequestToProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String customerID = request.getParameter("customerID");
        String name = request.getParameter("name");
        String quantity = request.getParameter("quantity");
        String cateID = request.getParameter("categoryID");
        String material = request.getParameter("materialID");
        String size = request.getParameter("size");
        String price = request.getParameter("price").trim();
        
        String code = generateProductCode("LCD", 1000);
        RequestDAO rdo = new RequestDAO();
        
        //Update status của request
        int reqID = Integer.parseInt(request.getParameter("ID"));
        Request req = new Request();
        req = rdo.getRequestByID(String.valueOf(reqID));
        System.out.println(req.toString() + "InsertReqTOProductServlet");
        float reqPrice = Float.parseFloat(request.getParameter("price"));
        int reqStaffID = req.getStaffID();
        int reqStatus = Integer.parseInt(request.getParameter("status")) + 1;
        rdo.updateRequest(reqID, reqPrice, reqStaffID, reqStatus);
        
        request.setAttribute("message", "Create new product from Request Successful!");
        rdo.insertIntoProduct(name, code, material, size, price, cateID, quantity);
       request.setAttribute("customerID", customerID);
        request.getRequestDispatcher("ViewMyRequest").forward(request, response);
    }

    private String generateProductCode(String prefix, int maxNumber) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(maxNumber) + 1;  // Generate a random number between 1 and maxNumber

        // Use String.format to ensure leading zeros for a fixed width (e.g., LCD001, LCD002)
        String productCode = String.format("%s%03d", prefix, randomNumber);

        return productCode;
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

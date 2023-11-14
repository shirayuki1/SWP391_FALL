/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.CategoryDAO;
import Dao.MaterialDAO;
import Dao.ProductDAO;
import Dao.RequestDAO;
import Model.Category;
import Model.Material;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "CreateRequest", urlPatterns = {"/CreateRequest"})
public class CreateRequest extends HttpServlet {

     private static final String UPLOAD_DIRECTORY = "upload";
    private static final String DEFAULT_FILENAME = "default.file";

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
            CategoryDAO cdo = new CategoryDAO();
            List<Category> list = cdo.getAll();
            String customerID = request.getParameter("customerID");
            System.out.println(customerID + "CreateRequest -------------------------------------");
            if(customerID == null || customerID.equals("null")){
                String redirectURL = "UserServlet?action=showLoginForm";
                response.sendRedirect(redirectURL);
            }else{
                request.setAttribute("list", list);
            
                MaterialDAO materialDAO = new MaterialDAO();
                List<Material> listMaterial = materialDAO.getAll();
                request.setAttribute("list", list);
                request.setAttribute("listMaterial", listMaterial);
                request.getRequestDispatcher("createRequest.jsp").forward(request, response);
            }
            
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String name = request.getParameter("productName");
        String customerID = request.getParameter("customerID");
        String staffID = request.getParameter("staffID");
        String quantity = request.getParameter("productQuantity");
        String material = request.getParameter("productMaterial");
        String category = request.getParameter("productCategory");
        String size = request.getParameter("productSize");
        String price = request.getParameter("productPrice");
        RequestDAO rdo = new RequestDAO();
        int length = getServletContext().getRealPath("/").length();
        String uploadPath = new StringBuilder(getServletContext().getRealPath("/")).delete(length - 10,length - 4).toString() + File.separator + "img";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
            
        request.setAttribute("message", "Request has been sent");
        rdo.insert(customerID, name, size, material, price, staffID, quantity, category);
        
        doGet(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.ProductDAO;
import Dao.PromotionDAO;
import Dao.RequestDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateRequest", urlPatterns = {"/UpdateRequest"})
public class UpdateRequest extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final RequestDAO objectDao = new RequestDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdatePromotion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePromotion at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        int ID = Integer.parseInt(request.getParameter("ID"));
        float price = Float.parseFloat(request.getParameter("price"));
        int staffID = Integer.parseInt(request.getParameter("staffID"));
        
        int status = Integer.parseInt(request.getParameter("status")) + 1;
        System.out.println(status);
//        if (value < 0){
//            request.setAttribute("messUpdatePromotion", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Giá trị'.");
//            request.getRequestDispatcher("promotion").forward(request, response);
//        }else {
            objectDao.updateRequest(ID, price, staffID, status);
            request.setAttribute("messUpdatePromotion", "Cập nhật promotion thành công!");
            request.getRequestDispatcher("ManageRequestController").forward(request, response);
//        }
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
       request.setCharacterEncoding("UTF-8");
       response.setCharacterEncoding("UTF-8");
        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float price = Float.parseFloat(request.getParameter("price"));
        int staffID = Integer.parseInt(request.getParameter("staffID"));
        int mateID = Integer.parseInt(request.getParameter("materialID"));
        String mateName = request.getParameter("materialName");
        int status = Integer.parseInt(request.getParameter("status"));
        System.out.println(status);
        String size = request.getParameter("size");
        int accID = Integer.parseInt(request.getParameter("accountID"));
        String accName = request.getParameter("accountName");
        String staffName = request.getParameter("staffName");
        
        
        request.setAttribute("ID", ID);
        request.setAttribute("name", name);
        request.setAttribute("quantity", quantity);
        request.setAttribute("price", price);
        request.setAttribute("staffID", staffID);
        request.setAttribute("size", size);
        request.setAttribute("materialID", mateID);
        request.setAttribute("materialName", mateName);
        request.setAttribute("status", status);
        request.setAttribute("accID", accID);
        request.setAttribute("accName", accName);
        request.setAttribute("staffName", staffName);
        request.getRequestDispatcher("updateRequest.jsp").forward(request, response);
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

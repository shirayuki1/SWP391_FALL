/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.PromotionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "UpdatePromotion", urlPatterns = {"/UpdatePromotion"})
public class UpdatePromotion extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final PromotionDAO objectDao = new PromotionDAO();
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
        int promotionID = Integer.parseInt(request.getParameter("promotionID"));
        String promotionName = request.getParameter("promotionName");
        Date dateStart = Date.valueOf(request.getParameter("dateStart"));
        Date dateEnd = Date.valueOf(request.getParameter("dateEnd"));
        boolean promotionStatus = true;
        Date currentDate = Date.valueOf(LocalDate.now());
        if(dateEnd.before(currentDate)){
            promotionStatus = false;
        }
        int value = Integer.parseInt(request.getParameter("value"));
        int accID = Integer.parseInt(request.getParameter("accountID"));
        
        if (dateStart.after(dateEnd)) {
            request.setAttribute("messUpdatePromotion", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Ngày bắt đầu' và 'Ngày kết thúc'.");
            request.getRequestDispatcher("promotion").forward(request, response);
        }else if(currentDate.after(dateEnd)){
            request.setAttribute("messUpdatePromotion", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Ngày kết thúc' và 'Ngày hiện tại'.");
            request.getRequestDispatcher("promotion").forward(request, response);
        }else if (value < 0){
            request.setAttribute("messUpdatePromotion", "Cập nhật thất bại! Vui lòng kiểm tra lại 'Giá trị'.");
            request.getRequestDispatcher("promotion").forward(request, response);
        }else {
            objectDao.updatePromotion(promotionID, promotionName, dateStart, dateEnd, promotionStatus, value, accID);
            request.setAttribute("messUpdatePromotion", "Cập nhật promotion thành công!");
            request.getRequestDispatcher("promotion").forward(request, response);
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
       request.setCharacterEncoding("UTF-8");
        int promotionID = Integer.parseInt(request.getParameter("promotionID"));
        String promotionName = request.getParameter("promotionName");
        Date dateStart = Date.valueOf(request.getParameter("dateStart"));
        Date dateEnd = Date.valueOf(request.getParameter("dateEnd"));
        boolean promotionStatus = Boolean.valueOf(request.getParameter("promotionStatus"));
        int value = Integer.parseInt(request.getParameter("value"));
        int accID = Integer.parseInt(request.getParameter("accountID"));
        String accName = request.getParameter("accountName");


        request.setAttribute("promotionID", promotionID);
        request.setAttribute("promotionName", promotionName);
        request.setAttribute("dateStart", dateStart);
        request.setAttribute("dateEnd", dateEnd);
        request.setAttribute("promotionStatus", promotionStatus);
        request.setAttribute("value", value);
        request.setAttribute("accID", accID);
        request.setAttribute("accName", accName);
        request.getRequestDispatcher("updatePromotion.jsp").forward(request, response);
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

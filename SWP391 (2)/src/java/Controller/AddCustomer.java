/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.UserDAO;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
public class AddCustomer extends HttpServlet {

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
        try  {
            /* TODO output your page here. You may use following sample code. */
           String email = request.getParameter("email");
            System.out.println(email);
            UserDAO dao = new UserDAO();
             boolean result = dao.addstaff(email);
             if (result) {

                List<Account> dto = dao.getListCustomer();
                request.setAttribute("listcustomer", dto);
                request.setAttribute("messtrue", "tài khoản dã thêm lại thành công");
                request.getRequestDispatcher("managercustomer.jsp").forward(request, response);

//                // xoá EmployeeID bên bảng Shifts
//                WorkSheetDAO dao2 = new WorkSheetDAO();
//                boolean result2 = dao2.RemoveUserFromShifts(employeeID);
//                //

            } else {
                
               List<Account> dto = dao.getListCustomer();
                request.setAttribute("listcustomer", dto);
                request.setAttribute("messefalse", "tài khoản vẫn đang hoạt động");
                request.getRequestDispatcher("managercustomer.jsp").forward(request, response);
            }
        }catch (Exception e) {
            log("Error at DeleteController" + e.toString());
        } finally {
             request.getRequestDispatcher("managercustomer.jsp").forward(request, response);
           
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

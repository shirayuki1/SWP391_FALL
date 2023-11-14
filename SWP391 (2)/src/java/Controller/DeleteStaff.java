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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author DELL
 */
public class DeleteStaff extends HttpServlet {
  
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       
           try{
            /* TODO output your page here. You may use following sample code. */
                 String email = request.getParameter("email");
              HttpSession session = request.getSession();
                UserDAO dao = new UserDAO();            
                boolean checkDelete = dao.delete(email);
                    if (checkDelete) {
                List<Account> dto = dao.getListStaff();
               request.setAttribute("listuser", dto);
             request.setAttribute("messok", "Xóa nhân viên thành công");
             
                    }    else {
                
                List<Account> dto = dao.getListStaff();
                request.setAttribute("listuser", dto);
                request.setAttribute("messerror", "Nhân viên đã nghỉ làm");
                request.getRequestDispatcher("ManagerUser.jsp").forward(request, response);
            }
                    
//                // xoá EmployeeID bên bảng Shifts
//                WorkSheetDAO dao2 = new WorkSheetDAO();
//                boolean result2 = dao2.RemoveUserFromShifts(employeeID);
//                //

            
        }catch (Exception e) {
            log("Error at DeleteController" + e.toString());
        } finally {
             request.getRequestDispatcher("managerstaff.jsp").forward(request, response);
           
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStaff.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(DeleteStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
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



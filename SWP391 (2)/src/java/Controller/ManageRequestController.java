/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AccountDAO;
import Dao.CategoryDAO;
import Dao.MaterialDAO;
import Dao.ProductDAO;
import Dao.RequestDAO;
import Model.Account;
import Model.Category;
import Model.Material;
import Model.Product;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManageRequestController", urlPatterns = {"/ManageRequestController"})
public class ManageRequestController extends HttpServlet {

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
       RequestDAO rdo = new RequestDAO();
        MaterialDAO mdo = new MaterialDAO();
        AccountDAO ado = new AccountDAO();
        List<Account> accList = new ArrayList<>();
        List<Request> requestList = rdo.getAll();
        List<Material> mateList = mdo.getAll();
        
        //lấy user từ session
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("userlogin");
        String user = account.getRoleid().getName();
        System.out.println(user + "ManageRequestController");
        if(user.equals("manager")){
             for (Request req : requestList) {
                Account acc = ado.getAccountByID(String.valueOf(req.getUserID()));
                accList.add(acc);
            }
            request.setAttribute("list", requestList);
            request.setAttribute("listMaterial", mateList);
            request.setAttribute("listAcc", accList);
            response.setContentType("application/json");
            request.getRequestDispatcher("managermanageRequest.jsp").forward(request, response);
        }else{
            for (Request req : requestList) {
                Account acc = ado.getAccountByID(String.valueOf(req.getUserID()));
                accList.add(acc);
            }
            request.setAttribute("list", requestList);
            request.setAttribute("listMaterial", mateList);
            request.setAttribute("listAcc", accList);
            response.setContentType("application/json");
            request.getRequestDispatcher("manageRequest.jsp").forward(request, response);
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
      PrintWriter out = response.getWriter();
//        String id = request.getParameter("id");
//        String name = request.getParameter("name");
//        String quantity = request.getParameter("quantity");
//        String status = request.getParameter("status");
//        String price = request.getParameter("price");
//        String categoryID = request.getParameter("categoryID");
//        ProductDAO pdo = new ProductDAO();
        
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

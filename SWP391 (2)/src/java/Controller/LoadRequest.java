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
import Model.Product;
import Model.Request;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoadRequest", urlPatterns = {"/LoadRequest"})
public class LoadRequest extends HttpServlet {

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
            String id = request.getParameter("id");
            RequestDAO rdo = new RequestDAO();
            Request r = rdo.getRequestByID(id);
            MaterialDAO mdo = new MaterialDAO();
            List<Material> listMate = mdo.getAll();
          
            /* TODO output your page here. You may use following sample code. */
            out.println("<form action=\"UpdateRequest\" method=\"POST\" id=\"updatesp\">\n"
                    + "                            <div class=\"row\">\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Mã sản phẩm </label>\n"
                    + "                                    <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"masp\" value=\"" + r.getId()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Tên sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"tensp\" value=\"" + r.getName()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Hình dáng và kích thước</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"sizesp\" value=\"" + r.getSize()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Giá bán</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"giasp\" value=\"" + r.getPrice() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Số lượng</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"quantity\" value=\"" + r.getQuantity()+ "\" >\n"
                    + "                                </div>\n"
                    + "                            <BR>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Nguyen lieu</label>\n"
                    + "                                    <select class=\"form-control\" name=\"materialsp\">\n");
                                                            
                                                            for(Material i: listMate){
                                                                if (i.getId()== r.getMaterial().getId()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                    + "                            </div>\n"
                    + "                            <BR>\n"
                                                                    
                    + "                            <BR>\n"
                    + "                            <BR>\n"
                    + "                            <button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n"
                    + "                            <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"#\">Hủy bỏ</a>\n"
                    + "                            <BR>\n"
                    + "                        </form>");
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

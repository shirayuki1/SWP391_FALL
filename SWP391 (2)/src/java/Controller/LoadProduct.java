/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Category;
import Model.Material;
import Model.Product;
import Dao.CategoryDAO;
import Dao.ProductDAO;
import Dao.MaterialDAO;
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

public class LoadProduct extends HttpServlet {

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
            ProductDAO pdo = new ProductDAO();
             Product p = pdo.getProductByID(id);
            CategoryDAO cdo = new CategoryDAO();
            List<Category> listCate = cdo.getAll();
            MaterialDAO mdo = new MaterialDAO();
            List<Material> listMate = mdo.getAll();
          
            /* TODO output your page here. You may use following sample code. */
            out.println("<form action=\"UpdateProduct\" method=\"POST\" id=\"updatesp\">\n"
                    + "                            <div class=\"row\">\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Mã sản phẩm </label>\n"
                    + "                                    <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"masp\" value=\"" + p.getProductID() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Tên sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"tensp\" value=\"" + p.getName()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Hình dáng sản phẩm</label>\n"
                    + "                                    <input class=\"form-control\" type=\"text\" required name=\"sizesp\" value=\"" + p.getSize()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Giá bán</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"giasp\" value=\"" + p.getPrice() + "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Số lượng</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\"required name=\"quantity\" value=\"" + p.getStock()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label class=\"control-label\">Discount</label>\n"
                    + "                                    <input class=\"form-control\" type=\"number\" step=\"0.01\" name=\"discountsp\" value=\"" + p.getDiscount()+ "\" >\n"
                    + "                                </div>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Danh mục</label>\n"
                    + "                                    <select class=\"form-control\" name=\"categorysp\">\n");
                                                            
                                                            for(Category i: listCate){
                                                                if (i.getId() == p.getCategory().getId()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getName() + "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                                                                        
                    + "                            </div>\n"
                    + "                            <BR>\n"
                    + "                                <div class=\"form-group col-md-6\">\n"
                    + "                                    <label for=\"exampleSelect1\" class=\"control-label\">Nguyen lieu</label>\n"
                    + "                                    <select class=\"form-control\" name=\"materialsp\">\n");
                                                            
                                                            for(Material i: listMate){
                                                                if (i.getId()== p.getMaterial()) {
                                                                    out.println("<option selected value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                } else {
                                                                    out.println("<option value=\"" + i.getId() + "\">" + i.getOriginal()+ "</option>");
                                                                }
                                                            }
                                                            out.println("                                    </select>\n");
                                                            out.println("                                </div>\n"
                    + "                            </div>\n"
                    
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\"></label>\n"
                    + "     <img  src=\"img/" + p.getImage()+ "\" id=\"avatarPreview\" alt=\"User cc\" width=\"100px\">"
                    +"     <input type=\"file\" name=\"image\" id=\"newAvatar\" onchange=\"previewImage(this) ;\">"
                    +"      <input type=\"hidden\" name=\"oldimage\" value=\"" + p.getImage() + "\">\n"
                            + "    </div>\n"    
                    + "  </div>\n");
                    out.print(
                   "<script>\n" +

                   "function previewImage(input) {\n" +
                   "    var avatarPreview = document.getElementById('avatarPreview');\n" +
                       "var oldImageValue = document.querySelector(\"input[name='oldimage']\").value;"+
                   "    if (input.files && input.files[0]) {\n" +
                   "        var reader = new FileReader();\n" +
                   "        reader.onload = function (e) {\n" +
                   "            avatarPreview.src = e.target.result;\n" +
                   "        };\n" +
                   "        reader.readAsDataURL(input.files[0]);\n" +
                   "    } else {\n" +
                   "      avatarPreview.src=oldImageValue  "+
                   "    }\n" +
                   "}\n" +
                   "</script>"

                    + "                            <button class=\"btn btn-save\" type=\"submit\">Lưu lại</button>\n"
                    + "                            <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"ManagerProductController\">Hủy bỏ</a>\n"
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

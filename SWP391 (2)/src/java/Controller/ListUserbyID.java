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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "ListUserbyID", urlPatterns = {"/ListUserbyID"})
public class ListUserbyID extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("id");
            UserDAO dao = new UserDAO();
            Account e = dao.getUserByID(id);
            List<String> list = dao.getEmail();
            String[] array = new String[list.size()];
             array = list.toArray(array);
            String currentemail = e.getEmail();
            List<String> listt = dao.getPhone();
            String[] arrayy = new String[listt.size()];
            arrayy = listt.toArray(arrayy);
             String currentphone = e.getPhone();            /* TODO output your page here. You may use following sample code. */
            out.print("<form action=\"updateuser\" method=\"POST\" id=\"updateusers\" onsubmit=\"return validateForm()\">\n"
                  + "  <div class=\"row\">\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">ID</label>\n"
                    + "      <input readonly=\"true\" class=\"form-control\" type=\"text\" name=\"id\"  value=\"" + e.getId() + "\" >\n"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">tên đầu</label>\n"
                    + "      <input class=\"form-control\" type=\"text\" required name=\"firstname\" id=\"firstname\" value=\"" + e.getFirstname()+ "\" >\n"
                     + "      <span id=\"firstnameError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">tên sau</label>\n"
                    + "      <input class=\"form-control\" type=\"text\" required name=\"lastname\" id=\"lastname\" value=\"" + e.getLastname()+ "\" >\n"
                     + "      <span id=\"lastnameError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">địa chỉ</label>\n"
                    + "      <input class=\"form-control\" type=\"text\" name=\"address\" id=\"address\" value=\"" + e.getAddress()+ "\" >\n"
                       + "      <span id=\"addressError\" class=\"error\"></span>"      
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label class=\"control-label\">ngày sinh</label>\n"
                    + "      <input  class=\"form-control\" type=\"date\" name=\"birthday\" id=\"birthday\" value=\"" + e.getBirthday() + "\" >\n"
                      + "      <span id=\"birthdayError\" class=\"error\"></span>"
                    + "    </div>\n"
                    + "    <div class=\"form-group col-md-6\">\n"
                    + "      <label for=\"exampleSelect1\" class=\"control-label\">giới tính</label>\n"
                    + "      <select class=\"form-control\" name=\"gender\">\n");
                if (e.getGender()== true) {
                out.println("<option value=\"" + true + "\">" + "Nam" + "</option>");
                out.println("<option value=\"" + false + "\">" + "Nữ" + "</option>");
                 } else {
                out.println("<option value=\"" + false + "\">" + "Nữ" + "</option>");
                out.println("<option value=\"" + true + "\">" + "Nam" + "</option>");
               }
            out.println("      </select>\n");
            out.println();
            out.println(" </div>\n"  
                  
                    + "  <div class=\"form-group col-md-6\">\n"
                      + "<label for=\"exampleSelect1\" class=\"control-label\">Chức vụ</label>\n"
                      + "<select class=\"form-control\" name=\"role\">\n");            
           if (e.getRoleid().getName().equalsIgnoreCase("staff")) {
                out.println("<option value=\"" + 3 + "\">" + "staff" + "</option>");
             
             }
          out.println("      </select>\n");
             out.println("    </div>\n"
             + "    <div class=\"form-group col-md-6\">\n"
             + "      <label class=\"control-label\">Email</label>\n"
             + "      <input class=\"form-control\" type=\"text\" name=\"email\" id=\"email\" value=\"" + e.getEmail()+ "\" >\n"
             + "      <span id=\"emailError\" class=\"error\"></span>"
             + "    </div>\n"
             + "    <div class=\"form-group col-md-6\">\n"
             + "      <label class=\"control-label\">Password </label>\n"
             + "      <input  class=\"form-control\" type=\"text\" name=\"password\" id=\"password\" value=\"" + e.getPassword()+ "\" >\n"
              + "      <span id=\"passwordError\" class=\"error\"></span>"   
             + "    </div>\n"
             + "    <div class=\"form-group col-md-6\">\n"
             + "      <label class=\"control-label\">số điện thoại</label>\n"
             + "      <input  class=\"form-control\" type=\"text\" name=\"phone\" id=\"phone\" value=\"" + e.getPhone()+ "\" >\n"
             + "      <span id=\"phoneError\" class=\"error\"></span>"   
             + "    <div class=\"form-group col-md-6\">\n"
             + "      <label class=\"control-label\"></label>\n"
            + "     <img src=\"" + e.getImage()+ "\" id=\"avatarPreview\" alt=\"User cc\" width=\"100px\">"
             +"     <input type=\"file\" name=\"image\" id=\"newAvatar\" onchange=\"previewImage(this) ;\">"
                      +"      <input type=\"hidden\" name=\"oldimage\" value=\"" + e.getImage() + "\">\n"
             + "    </div>\n"    
             + "  </div>\n");
out.print(   "<script>\n" +

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
                   "</script>");

             
out.print("<button class=\"btn btn-save\" type=\"submit\">save</button>\n"
        + "  <a class=\"btn btn-cancel\" data-dismiss=\"modal\" href=\"#\">Hủy bỏ</a>\n"
        + "  <br>\n"
        + "</form>\n"
        + "<script>\n"
        + "   var currentemail = \"" + currentemail + "\";\n"
        + "   var currentphone = \"" + currentphone + "\";\n"
        + "   var emailArray = " + Arrays.toString(array).replace("[", "['").replace(", ", "', '").replace("]", "']") + ";\n"
        + "   var phoneArray = " + Arrays.toString(arrayy).replace("[", "['").replace(", ", "', '").replace("]", "']") + ";\n"
        + "   function validateForm() {\n"
        + "       var email = document.getElementById('email').value;\n"
       +"const currentDate = new Date();\n"
        +"const birthdayDate = new Date(birthday);\n"
        + "       var firstname = document.getElementById('firstname').value;\n"
        + "       var lastname = document.getElementById('lastname').value;\n"
        + "       var address = document.getElementById('address').value;\n"
        + "       var birthday = document.getElementById('birthday').value;\n"
        + "       var password = document.getElementById('password').value;\n"
        + "       var phone = document.getElementById('phone').value;\n"
        + "       var emailErrorElement = document.getElementById('emailError');\n"
        + "       var firstnameErrorElement = document.getElementById('firstnameError');\n"
       + "       var lastnameErrorElement = document.getElementById('lastnameError');\n"
       + "       var addressErrorElement = document.getElementById('addressError');\n"
                + "       var passwordErrorElement = document.getElementById('passwordError');\n"
       + "       var birthdayErrorElement = document.getElementById('birthdayError');\n"
        + "       var phoneErrorElement = document.getElementById('phoneError');\n"
        + "       var emailPattern = /^[a-zA-Z0-9._%+-]+@gmail.com$/; // Gmail email pattern\n"
        + "       var phonePattern = /^\\d{10,11}$/; // Phone number should have between 10 and 11 digits\n"
        + "       if (firstname.trim()=== \"\") {\n"
        + "           firstnameErrorElement.innerHTML = 'không được để trống.';\n"
        + "           firstnameErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
         + "       } else {\n"
        + "           firstnameErrorElement.innerHTML = ''; // Clear the error message if no duplicate email\n"
        + "       }\n"
        + "       if (lastname.trim()===  \"\") {\n"
        + "           lastnameErrorElement.innerHTML = 'không được để trống.';\n"
        + "           lastnameErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
        + "       } else {\n"
        + "           lastnameErrorElement.innerHTML = ''; // Clear the error message if no duplicate email\n"
        + "       }\n"
         + "       if (address.trim()=== \"\") {\n"
        + "           addressErrorElement.innerHTML = 'không được để trống.';\n"
        + "           addressErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
         + "       } else {\n"
        + "           addressErrorElement.innerHTML = ''; // Clear the error message if no duplicate email\n"
        + "       }\n"
        + "       if (birthday.trim()===  \"\") {\n"
        + "           birthdayErrorElement.innerHTML = 'không được để trống.';\n"
        + "           birthdayErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
         + "       }"
        + "        else {\n"
        + "           birthdayErrorElement.innerHTML = ''; // Clear the error message if no duplicate email\n"
        + "       }\n"
        + "       if (email === currentemail && phone === currentphone) {\n"
        + "           return true; // No change in email and phone, allow form submission\n"
        + "       }\n"
        + "       if (email.trim()===  \"\") {\n"
        + "           emailErrorElement.innerHTML = 'không được để trống.';\n"
        + "           emailErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
        + "       }\n"
        + "       if (!emailPattern.test(email)) {\n"
        + "           emailErrorElement.innerHTML = 'Invalid Gmail email address.';\n"
        + "           emailErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
        + "       }\n"
      + "       for (var i = 0; i < emailArray.length; i++) {\n"
        + "           if (email === emailArray[i]) {\n"
        + "               emailErrorElement.innerHTML = 'Email already exists.';\n"
        + "               emailErrorElement.style.color = 'red';\n"
        + "               return false;\n"
        + "           }\n"
        +"else{"
        +"emailErrorElement.innerHTML = '';"
        + "       }\n"
                + "       }\n"
          + "       if (password.trim()===  \"\") {\n"
        + "           passwordErrorElement.innerHTML = 'Không được để trống.';\n"
        + "           passwordErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is a duplicate, prevent form submission\n"
        + "       } else {\n"
        + "           passwordErrorElement.innerHTML = ''; // Clear the error message if no duplicate email\n"
        + "       }\n"
        + "       if (phone.trim()=== \"\") {\n"
        + "           phoneErrorElement.innerHTML = 'không được để trống.';\n"
        + "           phoneErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Email is invalid, prevent form submission\n"
        + "       }\n"
        + "       if (!phonePattern.test(phone)) {\n"
        + "           phoneErrorElement.innerHTML = 'Phone number should have between 10 and 11 digits.';\n"
        + "           phoneErrorElement.style.color = 'red'; // Set the color to red\n"
        + "           return false; // Phone number is too short or too long, prevent form submission\n"
        + "       }\n"
          + "       for (var i = 0; i < phoneArray.length; i++) {\n"
        + "           if (phone === phoneArray[i]) {\n"
        + "               phoneErrorElement.innerHTML = 'Email already exists.';\n"
        + "               phoneErrorElement.style.color = 'red';\n"
        + "               return false;\n"
        + "           }\n"
        +"else{"
        +"phoneErrorElement.innerHTML = '';"
        + "       }\n"
                + "       }\n"
      
        + "       return true; // No duplicate email or phone and valid Gmail format, allow form submission\n"
        + "   }\n"
        + "</script>");

        }}
   
           
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListUserbyID.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ListUserbyID.class.getName()).log(Level.SEVERE, null, ex);
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

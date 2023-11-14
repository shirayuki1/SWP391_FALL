/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import Dao.AccountDAO;
import Dao.OrderDAO;
import Dao.UserDAO;
import Model.Account;
import Model.Order1;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.CreateError;

/**
 *
 * @author DELL
 */
public class UserServlet extends HttpServlet {
      private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

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
        String action = request.getParameter("action");
     try {

            if (action == null) {
                action = "list";
            }
            switch (action) {
                case "showSignupForm":
                    showSignupForm(request, response);
                    break ; 
                case "adminPage":
                    adminPage(request, response);
                    break;
                case "showLoginForm":
                    showLoginForm(request, response);
                    break;
                case "ShowStaffPage":
                    ShowStaffPage(request, response);
                    break;
                case "ShowCustomerPage":
                    ShowCustomerPage(request, response);
                    break;
                case "login":
                    login(request, response);
                    break;
                case "showHomepage":
                    showHomepage(request, response);
                    break;
                case "ShowManagerPage":
                    ShowManagerPage(request, response);
                    break;
                
                case "signup":
                    createUser(request, response);
                    break;
                 case "profile":
                    userProfile(request, response);
                    break;
                    case "editProfile":
                    editProfile(request, response);
                    break;
                      case "profile1":
                    userProfile1(request, response);
                    break;
                     case "changepassword":
                    changepassword(request, response);
                    break;
            }
        } catch (SQLException e) {
            log("UserServlet _ SQL _ " + e.getMessage());
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
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
              Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
     private void ShowManagerPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Chuyển hướng đến servlet ManagerAllAccount
    response.sendRedirect(request.getContextPath() + "/ManagerProductController");
}
     private void ShowStaffPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        OrderDAO or = new OrderDAO();
        List<Order1> accList = or.getAll();
       
            request.setAttribute("listAcc", accList);
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
    }
       private void ShowCustomerPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("customerPage.jsp");
//          RequestDispatcher rd = request.getRequestDispatcher("dateTotal.jsp");
        rd.forward(request, response);

    }
   private void adminPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Chuyển hướng đến servlet ManagerAllAccount
    response.sendRedirect(request.getContextPath() + "/ManagerAllAccount");
}
     private void showHomepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
//          RequestDispatcher rd = request.getRequestDispatcher("dateTotal.jsp");
        rd.forward(request, response);

    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url = "login.jsp";
        boolean foundError = false;
        CreateError errors = new CreateError();
        HttpSession session = request.getSession();
        try {

            if (email.trim().equals("")) {
                foundError = true;
                errors.setUsernameBlankError("Email không được để trống");
            }
            if (password.trim().equals("")) {
                foundError = true;
                errors.setPasswordBlankError("Mật khẩu không được để trống");
            }
            if (foundError) {
                request.setAttribute("CREATE_ERROR", errors);
            } else {
               
                 Account loginUser = userDAO.checkLogin(email,password);
                if (loginUser != null) {
                    String  name = loginUser.getRoleid().getName();
                    session.setAttribute("userlogin", loginUser);
                    if ("admin".equals(name)) {
                        url = "UserServlet?action=adminPage";
                    }else  if ("manager".equals(name)) {
                      url = "UserServlet?action=ShowManagerPage";
                    }
                    else  if ("staff".equals(name)) {
                      url = "UserServlet?action=ShowStaffPage";
                    } else  if ("customer".equals(name)) {
                      url = "UserServlet?action=showHomepage";
                    } else  if ("customer".equals(name)) {
                      url = "UserServlet?action=ShowCustomerPage";}
                    
                } else {
                    // Invalid username or password
                    foundError = true;
                    errors.setWrongPasswordError("Email hoặc mật khẩu không hợp lệ");
                    request.setAttribute("CREATE_ERROR", errors);
                }
            }

        }catch(Exception e){
            log("Error at LoginController"+e.toString());} 
        finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }

    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    
    
    private void showSignupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

     private void createUser(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, ServletException, IOException {
          String url = "signup.jsp";
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday");
        String gender = request.getParameter("gender");
         String phone = request.getParameter("phone");
           String role = "4";
            String filename = request.getParameter("filename");
      UserDAO dao = new UserDAO();
        boolean foundError = false;
         boolean foundErrorr = false;
        CreateError errors = new CreateError();
        ArrayList<Account> list = userDAO.getAllUsers();
        try {
            if (firstname.trim().equals("")) {
                foundError = true;
                errors.setFullnameBlankError("Không được để trống.");
            }
            if (lastname.trim().equals("")) {
                foundError = true;
                errors.setFullnameBlankError("Không được để trống.");
            }
             if (password.trim().equals("")) {
                foundError = true;
                errors.setPasswordBlankError("Mật khẩu không được để trống.");
            } if (email.trim() == null || email.trim().isEmpty()) {
                foundError = true;
                errors.setEmailBlankError("Email không được để trống.");
            } else if (!isValidEmail(email)) {
                foundError = true;
                errors.setEmailFormatError("Email không hợp lệ.");
            }if (phone.trim() == null || phone.trim().isEmpty()) {
                foundError = true;
                errors.setPhoneBlankError("Số điện thoại không được để trống.");
            } else if (!isValidPhoneNumber(phone)) {
                foundError = true;
                errors.setPhoneFormatError("Số điện thoại không hợp lệ.");
            } if (address.trim() == null || address.trim().isEmpty()) {
                foundError = true;
                errors.setAddressBlankError("Địa chỉ không được để trống.");
            }if (birthday.trim() == null || birthday.trim().isEmpty()) {
                foundError = true;
                errors.setBirthdayBlankError("Ngày sinh không được để trống.");
            }else if (!isValidBirthday(birthday)) {
                foundError = true;
           errors.setBirthdayBlankError("Ngày sinh không hợp lệ hoặc trùng với ngày hôm nay hoặc ở tương lai.");
                }
            for (Account a : list) {
                if (a.getEmail().equals(email)) {
                    foundError = true;
                    errors.setEmailIsExisted("Email đã được đăng kí.");
                }
                if (a.getPhone().equals(phone)) {
                    foundError = true;
                    errors.setPhoneNumberIsExisted("Số điện thoại đã được đăng kí.");
               }
                 
            }
            if (foundError) {
                     request.setAttribute("CREATE_ERROR", errors);
                }
            else{
                dao.insertstaff(firstname,lastname,filename, address, birthday, gender, role, email, password,phone);
                 url = "UserServlet?action=showLoginForm";
            }

        }finally {
             RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
         }
        

//To change body of generated methods, choose Tools | Templates.
    

//To change body of generated methods, choose Tools | Templates.
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(emailRegex);
    } private boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^[0-9]{10}$";
        return phoneNumber.matches(phoneRegex);
    }  public static boolean isValidBirthday(String birthday) {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            // Parse the birthday string
            Date parsedDate = dateFormat.parse(birthday);

            // Get the current date
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);

            // Set the parsedDate to the same time as today to compare only the dates
            Calendar parsedCalendar = Calendar.getInstance();
            parsedCalendar.setTime(parsedDate);
            parsedCalendar.set(Calendar.HOUR_OF_DAY, 0);
            parsedCalendar.set(Calendar.MINUTE, 0);
            parsedCalendar.set(Calendar.SECOND, 0);
            parsedCalendar.set(Calendar.MILLISECOND, 0);

            // Compare the parsed birthday date with today's date and the future
            if (today.equals(parsedCalendar)) {
                return false; // The birthday is the same as today
            } else if (parsedCalendar.after(today)) {
                return false; // The birthday is in the future
            }

            return true; // The birthday is valid and not in the future
        } catch (Exception e) {
            // Parsing failed, so the birthday is not in a valid format
            return false;
        }
    }

    private void userProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       HttpSession session = request.getSession();
        Account dto = (Account) session.getAttribute("userlogin");
        Account user = null;
        if (dto != null) {
            int Id = dto.getId();
            AccountDAO userDAO = new AccountDAO();
            user = userDAO.getAccount(Id);
            request.setAttribute("user", user);
              request.getRequestDispatcher("profile.jsp").forward(request, response);
            
        }
    }

    private void editProfile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String url = "UserServlet?action=profile1";
        String id = request.getParameter("id");
        String firstname = request.getParameter("firstname");
       String lastname = request.getParameter("lastname");
       String email = request.getParameter("email");
       String address = request.getParameter("address");
       String birthday = request.getParameter("birthday");
       String phone  = request.getParameter("phone");
       String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
       String confirm = request.getParameter("confirm");
       String image = request.getParameter("image");
        String gender = request.getParameter("gender");
      String oldimg = request.getParameter("oldimage");
           boolean foundError = false;
        CreateError errors = new CreateError();
         UserDAO dao = new UserDAO();
    
//     try {
        if(image.equals("")){
              dao.UpdateProfile(id, firstname, lastname, email, birthday, address, gender, password,phone,oldimg);
        }
        else {
             dao.UpdateProfile(id, firstname, lastname, email, birthday, address, gender, password,phone,image);
        }
//          } else {
//                foundError = true;
//                errors.setNotMatchPassword("Password does not match or incorrect old password");
//            }
//          if (foundError) {
//                request.setAttribute("EDIT_ERROR", errors);
//            }else{
//                request.setAttribute("EDIT_SUCCESS", "User information updated successfully");
//          }
//          
//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
//        }
    }

    private void userProfile1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();
        Account list = dao.getUserByIDD(id);
        session.setAttribute("userlogin", list);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

    private void changepassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     PrintWriter out = response.getWriter();
     String url = "changepassword.jsp";
        String password = request.getParameter("password");
      String id = request.getParameter("id");   
     String newPassword = request.getParameter("newPassword");
       String confirm = request.getParameter("confirm");
           boolean foundError = false;
        CreateError errors = new CreateError();
         UserDAO dao = new UserDAO();
         try {
               if (password.equals(userDAO.getUserByID(id).getPassword()) && newPassword.equals(confirm)) {
             dao.changpassword(newPassword,id);
             
             }else {
                foundError = true;
                errors.setNotMatchPassword("Password does not match or incorrect old password");
            }
          if (foundError) {
                request.setAttribute("EDIT_ERROR", errors);
            }else{
                request.setAttribute("EDIT_SUCCESS", "User information updated successfully");
          }
          
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
     RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();       
    }
    }
    

}

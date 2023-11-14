/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Model.Account;
import Model.Role;
import Ultis.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class UserDAO {
    
    public Account checkLogin(String email, String password) throws SQLException{
        ResultSet rs = null;
        Account result = null;
        Connection conn= null;
        PreparedStatement stm = null; 
        try{
            conn= DBContext.getConnection();
               String sql = "SELECT e.* ,r.name FROM Account e JOIN Role r ON e.roleid = r.roleid WHERE email =? "+" and password = ? and isActive='true' ";
            stm = conn.prepareStatement(sql);
               stm.setString(1, email);
            stm.setString(2, password);
             
            rs = stm.executeQuery();
               while (rs.next()) {
                String dbEmail = rs.getString("Email");
                String dbPassword = rs.getString("Password");
                 if (dbEmail.equals(email) && dbPassword.equals(password)){
                     int id = rs.getInt("Id");
                     String firstname = rs.getString("firstname");
                     String lastname = rs.getString("lastname");
                     String address = rs.getString("address");
                     Date birthday = rs.getDate("birthday");
                     boolean isActive = rs.getBoolean("isActive");
                     boolean gender = rs.getBoolean("gender");
                     String image = rs.getString("image");
                     String name = rs.getString("name");
                     String phone = rs.getString("phone");
                     Role role = new Role() ; 
                     role.setName(name);
//                     role.setRoleid(rs.getInt(11));
                     result = new Account(id, firstname,lastname, dbPassword, dbEmail, birthday, role, address,isActive,gender,image,phone);
                     return result;
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(rs!= null) rs.close();
            if(stm!= null) stm.close();
            if(conn!= null) conn.close();
        
        }
        
        
       return result;
    }

    public List<Account> getListStaff() {
       List<Account> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            
            conn = DBContext.getConnection();
            
            String SHOW= "SELECT e.id,e.firstname,e.lastname,e.address,e.birthday,e.isActive,e.gender,e.email,e.password,e.image,r.name,e.phone FROM Account e JOIN Role r ON e.roleid = r.roleid WHERE r.name = 'staff' ";
        
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Account p = new Account();
//                     int id = rs.getInt("id");
//                     String firstname = rs.getString("firstname");
//                     String lastname = rs.getString("lastname");
//                     String address = rs.getString("address");
//                     Date birthday = rs.getDate("birthday");
//                     boolean isActive = rs.getBoolean("isActive");
//                     boolean gender = rs.getBoolean("gender");
//                     String email = rs.getString("email");
//                     String password = rs.getString("password");
//                    String image = rs.getString("image");
//                     String name = rs.getString("name");
//                     Role role = new Role() ; 
//                   
                        p.setId(rs.getInt(1));
                        p.setFirstname(rs.getString(2));
                        p.setLastname(rs.getString(3));
                        p.setAddress(rs.getString(4));
                        p.setBirthday(rs.getDate(5));
                        p.setIsActive(rs.getBoolean(6));
                        p.setGender(rs.getBoolean(7));
                        p.setEmail(rs.getString(8));
                        p.setPassword(rs.getString(9));
                        p.setImage(rs.getString(10));
                        p.setRoleid(new Role(-1,rs.getString(11)));
                         p.setPhone(rs.getString(12));
                        if (!p.getImage().contains("http")) {
                    p.setImage("./img/" + p.getImage());
                }
                    list.add(p);    
                        
                        
                        
                        
                     
                }
            }
            return list;
        } catch (Exception e) {
           Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        } finally {
           if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ptm != null) {
                try {
                    ptm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
//To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(String email) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false; 
           String sql = "UPDATE Account SET isActive = 'false'  WHERE email = ? and isActive ='true'";
          try {
            conn = DBContext.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, email);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;//To change body of generated methods, choose Tools | Templates.
    }

    public boolean addstaff(String email) throws SQLException {
       Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
              con = DBContext.getConnection();
              if (con != null) {
               String sql = "UPDATE Account\n"
                        + "SET isActive = 'true'\n"
                        + "WHERE email = ? AND isActive = 'false';";
                //3. Create Statement Object
                stm = con.prepareStatement(sql); //Nạp tham số 1 lần cho Statement
                stm.setString(1, email);

                //4. Execute Statement Object to get result
                int effectRow = stm.executeUpdate();
                //Nạp tham số 1 lần cho Statement
                //5. Process result
                if (effectRow > 0) {
                    result = true;
                } else {
                    result = false;
                }
        }
    
    }catch(Exception e){
        
    }finally{
              if (stm != null) {
                stm.close();  // tạo sau nên đóng trước
            }
            if (con != null) {
                con.close();
            }
        }
         return result;

   
  
    
    }

    public void UpdateStaff(String id, String firstname, String lastname, String email, String birthday, String address, String gender, String password,String role,String phone,String image)  {
        String sql = "UPDATE [dbo].[Account]  SET [FIRSTNAME] = ?, [LASTNAME] = ? ,[Birthday] = ? ,[Address] = ? ,[GENDER] = ? ,[PASSWORD] = ?,[EMAIL] = ?,[PHONE] = ?,[IMAGE] = ?,[RoleId] = ? WHERE ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
    
     try {
            try {
                conn = DBContext.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setDate(3, Date.valueOf(birthday));
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, password );
            ps.setString(7, email);
             ps.setString(8, phone);
              ps.setString(9, image);
            ps.setInt(10, Integer.parseInt(role));
            ps.setInt(11, Integer.parseInt(id));
            ps.executeUpdate();
             return;
             
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
     }
    }

    public Account getUserByID(String id) throws ClassNotFoundException {
        String sql = "SELECT e.id,e.firstname,e.lastname,e.address,e.birthday,e.isActive,e.gender,e.email,e.password,e.image,r.name,e.phone FROM Account e left JOIN Role r ON e.roleid = r.roleid WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; 
         try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account p = new Account();
                  p.setId(rs.getInt(1));
                  p.setFirstname(rs.getString(2));
                  p.setLastname(rs.getString(3));
                  p.setAddress(rs.getString(4));
                  p.setBirthday(rs.getDate(5));
                  p.setIsActive(rs.getBoolean(6));
                  p.setGender(rs.getBoolean(7));
                  p.setEmail(rs.getString(8));
                  p.setPassword(rs.getString(9));
                  p.setImage(rs.getString(10));
                  p.setRoleid(new Role(-1,rs.getString(11)));
               p.setPhone(rs.getString(12));
//                if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
         
                return p;
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;//To change body of generated methods, choose Tools | Templates.
    }
     public Account getUserByIDD(String id) throws ClassNotFoundException {
        String sql = "SELECT e.id,e.firstname,e.lastname,e.address,e.birthday,e.isActive,e.gender,e.email,e.password,e.image,r.name,e.phone FROM Account e left JOIN Role r ON e.roleid = r.roleid WHERE id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; 
         try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account p = new Account();
                  p.setId(rs.getInt(1));
                  p.setFirstname(rs.getString(2));
                  p.setLastname(rs.getString(3));
                  p.setAddress(rs.getString(4));
                  p.setBirthday(rs.getDate(5));
                  p.setIsActive(rs.getBoolean(6));
                  p.setGender(rs.getBoolean(7));
                  p.setEmail(rs.getString(8));
                  p.setPassword(rs.getString(9));
                  p.setImage(rs.getString(10));
                  p.setRoleid(new Role(-1,rs.getString(11)));
               p.setPhone(rs.getString(12));
               
         
                return p;
            }
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;//To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getEmail() throws ClassNotFoundException {
       List<String> list = new ArrayList<>();
        String sql = "SELECT email FROM Account";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; 
           try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("Email");
                list.add(email);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;//To change body of generated methods, choose Tools | Templates.
    }
      public List<String> getPhone() throws ClassNotFoundException {
       List<String> list = new ArrayList<>();
        String sql = "SELECT phone FROM Account";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; 
           try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String phone = rs.getString("Phone");
                list.add(phone);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;//To change body of generated methods, choose Tools | Templates.
    }

    public void insertstaff(String firstname, String lastname, String image, String address, String birthday, String gender, String role, String email, String password,String phone) throws ClassNotFoundException {
      String sql = "INSERT INTO Account"
                + "(firstname,lastname, email, password, gender, birthday, RoleId, address,image,phone,isActive )\n"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,'true')";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setBoolean(5, Boolean.parseBoolean(gender));
            ps.setDate(6, Date.valueOf(birthday));
            ps.setInt(7, Integer.parseInt(role));
            ps.setString(8, address);
            ps.setString(9, image);
            ps.setString(10, phone);
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
//To change body of generated methods, choose Tools | Templates.
    }
    
    }

    public void insertuser(String firstname, String lastname, String address, String birthday, String gender,  String email, String password,String phone) throws ClassNotFoundException {
       String sql = "INSERT INTO Account"
                + "(firstname,lastname, email, password, gender, birthday, RoleId, address,isActive,phone )\n"
                + "VALUES(?, ?, ?, ?, ?, ?, '1', ?, ? ,'true',?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setBoolean(5, Boolean.parseBoolean(gender));
            ps.setDate(6, Date.valueOf(birthday));
            ps.setString(7, address);
            ps.setString(8, phone);
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

//To change body of generated methods, choose Tools | Templates.
    }
    }

    public List<Account> getListAll() {
        List<Account> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();            
            String SHOW= "SELECT e.id,e.firstname,e.lastname,e.address,e.birthday,e.isActive,e.gender,e.email,e.password,e.image,r.name,e.phone FROM Account e JOIN Role r ON e.roleid = r.roleid WHERE r.name = 'staff' or r.name ='manager' ";
        
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Account p = new Account();
//                     int id = rs.getInt("id");
//                     String firstname = rs.getString("firstname");
//                     String lastname = rs.getString("lastname");
//                     String address = rs.getString("address");
//                     Date birthday = rs.getDate("birthday");
//                     boolean isActive = rs.getBoolean("isActive");
//                     boolean gender = rs.getBoolean("gender");
//                     String email = rs.getString("email");
//                     String password = rs.getString("password");
//                    String image = rs.getString("image");
//                     String name = rs.getString("name");
//                     Role role = new Role() ; 
//                   
                        p.setId(rs.getInt(1));
                        p.setFirstname(rs.getString(2));
                        p.setLastname(rs.getString(3));
                        p.setAddress(rs.getString(4));
                        p.setBirthday(rs.getDate(5));
                        p.setIsActive(rs.getBoolean(6));
                        p.setGender(rs.getBoolean(7));
                        p.setEmail(rs.getString(8));
                        p.setPassword(rs.getString(9));
                        p.setImage(rs.getString(10));
                        
                        p.setRoleid(new Role(-1,rs.getString(11)));
                         p.setPhone(rs.getString(12));
                        if (!p.getImage().contains("http")) {
                    p.setImage("./img/" + p.getImage());
                }
                    list.add(p);    
      
                     
                }
            }
            return list;
        } catch (Exception e) {
           Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        } finally {
           if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ptm != null) {
                try {
                    ptm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
//To change body of generated methods, choose Tools | Templates.
     //To change body of generated methods, choose Tools | Templates.
    }
        public List<Account> getListCustomer() {
       List<Account> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();            
            String SHOW= "SELECT e.id,e.firstname,e.lastname,e.address,e.birthday,e.isActive,e.gender,e.email,e.password,e.image,r.name,e.phone FROM Account e JOIN Role r ON e.roleid = r.roleid WHERE r.name = 'customer' ";
        
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    Account p = new Account();
//                     int id = rs.getInt("id");
//                     String firstname = rs.getString("firstname");
//                     String lastname = rs.getString("lastname");
//                     String address = rs.getString("address");
//                     Date birthday = rs.getDate("birthday");
//                     boolean isActive = rs.getBoolean("isActive");
//                     boolean gender = rs.getBoolean("gender");
//                     String email = rs.getString("email");
//                     String password = rs.getString("password");
//                    String image = rs.getString("image");
//                     String name = rs.getString("name");
//                     Role role = new Role() ; 
//                   
                        p.setId(rs.getInt(1));
                        p.setFirstname(rs.getString(2));
                        p.setLastname(rs.getString(3));
                        p.setAddress(rs.getString(4));
                        p.setBirthday(rs.getDate(5));
                        p.setIsActive(rs.getBoolean(6));
                        p.setGender(rs.getBoolean(7));
                        p.setEmail(rs.getString(8));
                        p.setPassword(rs.getString(9));
                        p.setImage(rs.getString(10));
                        p.setRoleid(new Role(-1,rs.getString(11)));
                        p.setPhone(rs.getString(12));
                        if (!p.getImage().contains("http")) {
                    p.setImage("./img/" + p.getImage());
                }
                    list.add(p);    
      
                     
                }
            }
            return list;
        } catch (Exception e) {
           Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        } finally {
           if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ptm != null) {
                try {
                    ptm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
//To change body of generated methods, choose Tools | Templates.
     //To change body of generated methods, choose Tools | Templates.
    }

//    public List<String> getPhone() throws ClassNotFoundException {
//       List<String> list = new ArrayList<>();
//        String sql = "SELECT phone FROM Account";
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null; 
//           try {
//            conn = DBContext.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                String phone = rs.getString("phone");
//                list.add(phone);
//            }
//        } catch (SQLException e) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, e);
//        } finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (ps != null) {
//                try {
//                    ps.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//        return list;//To change body of generated methods, choose Tools | Templates.
//    }

     public ArrayList<Account> getAllUsers() {
       ArrayList<Account> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            
            conn = DBContext.getConnection();
            
            String SHOW= "SELECT email ,phone FROM Account  ";
        
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    Account lists = new Account(email,phone);
                     list.add(lists);
                }
            }
            return list;
        } catch (Exception e) {
           Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, e);
        } finally {
           if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ptm != null) {
                try {
                    ptm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
//To change body of generated methods, choose Tools | Templates.
    }

    public void UpdateProfile(String id, String firstname, String lastname, String email, String birthday, String address, String gender, String newPassword, String phone, String image) {
          String sql = "UPDATE [dbo].[Account]  SET [FIRSTNAME] = ?, [LASTNAME] = ? ,[Birthday] = ? ,[Address] = ? ,[GENDER] = ? ,[PASSWORD] = ?,[EMAIL] = ?,[PHONE] = ?,[IMAGE] = ? WHERE ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
    
     try {
            try {
                conn = DBContext.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setDate(3, Date.valueOf(birthday));
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, newPassword );
            ps.setString(7, email);
             ps.setString(8, phone);
              ps.setString(9, image);
       
            ps.setInt(10, Integer.parseInt(id));
            ps.executeUpdate();
             return;
             
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
     }
    }

    public void changpassword(String newPassword, String id) {
       String sql = "UPDATE [dbo].[Account]  SET [PASSWORD] = ? WHERE ID = ?";
        Connection conn = null;
        PreparedStatement ps = null;
    
     try {
            try {
                conn = DBContext.getConnection();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps = conn.prepareStatement(sql);
            ps.setString(1, newPassword );
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();
             return;
             
        } catch (SQLException e) {
            // Handle the exception or log it
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
     
    }
    }

    public boolean addcustomer(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}







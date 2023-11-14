/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Account;
import Model.Category;
import Model.Material;
import Model.Product;
import Model.Role;
import Ultis.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AccountDAO {

    public Account getAccountByID(String id) {
        String sql = "Select a.*,r.name from Account a left join Role r on a.roleid = r.roleid where a.id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFirstname(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setLastname(rs.getString(5));
                a.setBirthday(rs.getDate(6));
                a.setRoleid(new Role(rs.getInt(7), rs.getString(12)));
                a.setAddress(rs.getString(8));
                a.setIsActive(rs.getBoolean(9));
                a.setGender(rs.getBoolean(10));
                a.setImage(rs.getString(11));

                if (!a.getImage().contains("http")) {
                    a.setImage("./img/" + a.getImage());
                }
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "Select a.*,r.name from Account a left join Role r on a.roleid = r.roleid";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstname = rs.getString(2);
                String password = rs.getString(3);
                String email = rs.getString(4);
                String lastname = rs.getString(5);
                Date date = rs.getDate(6);
                Role role = new Role(rs.getInt(7), rs.getString(12));
                String address = rs.getString(8);
                boolean isActive = rs.getBoolean(9);
                boolean gender = rs.getBoolean(10);
                String image = rs.getString(11);
                String phone = rs.getString(12);
                Account a = new Account(id, firstname, lastname, password, email, date, role, address, isActive, gender, image,phone);
                if (!a.getImage().contains("http")) {
                    a.setImage("./img/" + a.getImage());
                }
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
        } catch (ClassNotFoundException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public int insertAccount(String email, String lastname, String phone, String address) {
        String sql = "INSERT INTO [dbo].[Account]\n"
                + "           (\n"
                + "            [email]\n"
                + "           ,[lastname]\n"
                + "           ,[roleid]\n"
                + "           ,[address]\n"
                + "           ,[isActive]\n"
                + "           ,[phone])\n"
                + "     VALUES\n"
                + "           (?,?,5,?,1,?)";
        Connection conn;
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, lastname);
            ps.setString(4, phone);
            ps.setString(3, address);
            ps.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getLastID();
    }

    public int getLastID() {
        String sql = "SELECT top 1 id\n"
                + "  FROM [dbo].[Account] order by id desc";
        Connection conn;
        try {
            conn = DBContext.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        Account a = dao.getAccountByID("1");
        System.out.println(a.toString());

    }

    public Account getAccount(int Id) {
      String sql = "Select a.*,r.name from Account a left join Role r on a.roleid = r.roleid where a.id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt(1));
                a.setFirstname(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setLastname(rs.getString(5));
                a.setBirthday(rs.getDate(6));
                a.setRoleid(new Role(rs.getInt(7), rs.getString(12)));
                a.setAddress(rs.getString(8));
                a.setIsActive(rs.getBoolean(9));
                a.setGender(rs.getBoolean(10));
                a.setImage(rs.getString(11));

                if (!a.getImage().contains("http")) {
                    a.setImage("./img/" + a.getImage());
                }
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}

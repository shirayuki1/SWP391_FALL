/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Account;
import Model.Category;
import Model.Comment;
import Model.Product;
import Ultis.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CommentDAO {
    public int countProductComment(String productID) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS NumberOfRecords\n" +
                    "FROM Comment\n" +
                    "WHERE productID = ? and status = 1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(productID));
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return count;
    }
    
    public List<Comment> getAllProductComment(String productID) {
        List<Comment> list = new ArrayList<>();
        String sql = "Select * from Comment where productID = ? and status = 1";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(productID));
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt(1));
                
                AccountDAO ado = new AccountDAO();
                Account acc = ado.getAccountByID(String.valueOf(rs.getInt(2)));
                c.setAccount(acc);
                
                ProductDAO pdo = new ProductDAO();
                Product p = pdo.getProductByID(String.valueOf(rs.getInt(3)));
                c.setProduct(p);
                
                c.setContent(rs.getString(4));
                c.setCommentDate(rs.getDate(5));
                c.setRate(rs.getInt(6));
                c.setStatus(rs.getBoolean(7));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }
    
    public void insert(String userID, String productID, String content, String rate) {
        String sql = "insert into Comment values(?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(userID));
            ps.setInt(2, Integer.parseInt(productID));
            ps.setString(3, content);
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = currentDateTime.format(formatter);
            ps.setTimestamp(4, Timestamp.valueOf(formattedDateTime));
            ps.setInt(5, Integer.parseInt(rate));
            ps.setBoolean(6, true);
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void updateCommentStatus(String commentID){
        String sql = "update Comment set status = 0 where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(commentID));
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public static void main(String[] args){
        CommentDAO dao = new CommentDAO();
        dao.updateCommentStatus("4");
        List<Comment> list = new ArrayList<>();
        list = dao.getAllProductComment("1");
       for (Comment comment : list) {
            System.out.println("UserID: " + comment.getId());
            System.out.println("ProductID: " + comment.getProduct());
            System.out.println("Content: " + comment.getContent());
            System.out.println("CommentDate: " + comment.getCommentDate());
            System.out.println("Rate: " + comment.getRate());
            System.out.println("Status: " + comment.isStatus());
            System.out.println("-----------------------------");
        }
    }
}

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
import Model.Request;
import Ultis.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class RequestDAO {
    public List<Request> getAll() {
        List<Request> list = new ArrayList<>();
        String sql = "Select r.*,s.createdDate, s.status, m.original, c.categoryName from Request r left join RequestStatus s on r.id = s.requestID  \n" +
                    "left join Material m on r.material = m.id left join Category c on r.CategoryID = c.id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request req = new Request();
                req.setId(rs.getInt(1));
                req.setUserID(rs.getInt(2));
                req.setName(rs.getString(3));
                req.setSize(rs.getString(4));
                req.setMaterial(new Material(rs.getInt(5), rs.getString(12)));
                req.setPrice(rs.getFloat(6));
                req.setStaffID(rs.getInt(7));
                req.setQuantity(rs.getInt(8));
                req.setCategory(new Category(rs.getInt(9), rs.getString(13)));
                req.setCreateDate(rs.getDate(10));
                req.setStatus(rs.getInt(11));
                
               
//               if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
                list.add(req);
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
    
    public List<Request> getRequestByCustomerID(String id) {
        List<Request> list = new ArrayList<>();
        String sql = "Select r.*,s.createdDate, s.status, m.original, c.categoryName from Request r left join RequestStatus s on r.id = s.requestID\n" +
                    "left join Material m on r.material = m.id left join Category c on r.CategoryID = c.id where r.customerId = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Request req = new Request();
                req.setId(rs.getInt(1));
                req.setUserID(rs.getInt(2));
                req.setName(rs.getString(3));
                req.setSize(rs.getString(4));
                req.setMaterial(new Material(rs.getInt(5), rs.getString(12)));
                req.setPrice(rs.getFloat(6));
                req.setStaffID(rs.getInt(7));
                req.setQuantity(rs.getInt(8));
                req.setCategory(new Category(rs.getInt(9), rs.getString(13)));
                req.setCreateDate(rs.getDate(10));
                req.setStatus(rs.getInt(11));
                
               
//               if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
                list.add(req);
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
    
    public Request getRequestByID(String id) {
        String sql = "Select r.*,s.createdDate, s.status, m.original, c.categoryName from Request r left join RequestStatus s on r.id = s.requestID  \n" +
                    "  left join Material m on r.material = m.id left join Category c on r.CategoryID = c.id where r.id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Request req = new Request();
                req.setId(rs.getInt(1));
                req.setUserID(rs.getInt(2));
                req.setName(rs.getString(3));
                req.setSize(rs.getString(4));
                req.setMaterial(new Material(rs.getInt(5), rs.getString(12)));
                req.setPrice(rs.getFloat(6));
                req.setStaffID(rs.getInt(7));
                req.setQuantity(rs.getInt(8));
                req.setCategory(new Category(rs.getInt(9), rs.getString(13)));
                req.setCreateDate(rs.getDate(10));
                req.setStatus(rs.getInt(11));
                
                return req;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
        }  finally {
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
    
    
    
    public void insert(String customerID, String name, String size, String material, String price, String staffID, String quantity, String categoryID) {
        String RequestSQL = "insert into Request(customerId, name, size, material, price, staffID, Quantity, CategoryID) values(?, ?, ?, ?, ?, ?, ?, ?)";
        String RequeststatusSQL = "insert into RequestStatus(requestID, createdDate, status) values(?, ?, ?)";
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;        
        PreparedStatement reqStatement = null;        
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(RequestSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, Integer.parseInt(customerID));
            ps.setString(2, name);
            ps.setString(3, size);
            ps.setInt(4, Integer.parseInt(material));
            if (price != null) {
                try {
                    int parsedPrice = Integer.parseInt(price);
                    ps.setInt(5, parsedPrice);
                } catch (NumberFormatException e) {
                    // Handle the case where the string is not a valid integer
                    System.err.println("Invalid integer format for price: " + price);
                }
            } else {
                // Set the column to NULL in the database
                ps.setNull(5, java.sql.Types.INTEGER);
            }
            
            if (!"null".equals(staffID) && staffID != null) {
                try {
                    int parsedPrice = Integer.parseInt(staffID);
                    ps.setInt(6, parsedPrice);
                } catch (NumberFormatException e) {
                    // Handle the case where the string is not a valid integer
                    System.err.println("Invalid integer format for staffID: " + staffID);
                }
            } else {
                // Set the column to NULL in the database
                ps.setNull(6, java.sql.Types.INTEGER);
            }
            ps.setInt(7, Integer.parseInt(quantity));       
            ps.setInt(8, Integer.parseInt(categoryID));
             // Execute the insert statement
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                // Insert failed
                throw new SQLException("Creating request failed, means no rows affected.");
            }
            // Retrieve the auto-generated keys (in this case, the ID of the newly inserted request)
            rs = ps.getGeneratedKeys();
            
             if (rs.next()) {
                int requestID = rs.getInt(1);

                // Insert into RequestStatus table
                reqStatement = conn.prepareStatement(RequeststatusSQL);
                reqStatement.setInt(1, requestID);

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);
                reqStatement.setTimestamp(2, Timestamp.valueOf(formattedDateTime));
                reqStatement.setBoolean(3, false);

                // Execute the insert into RequestStatus table
                reqStatement.executeUpdate();
            } else {
                // Insert failed
                throw new SQLException("Creating request failed, no ID obtained.");
            }
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
    }
    
    public void updateRequest(int ID, float price, int staffID, int status) {
         Connection conn;
        PreparedStatement stmt;
        PreparedStatement statusStatement = null;        
        ResultSet rs = null;
        try {
            String sql = "Update Request set Price = ?, staffID = ? where id = ?";
            String statusSQL = "update RequestStatus set status = ? where requestID = ?";
            conn = DBContext.getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setFloat(1, price);
            stmt.setInt(2, staffID);
            stmt.setInt(3, ID);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                // Insert failed
                throw new SQLException("Updating request failed, means no rows affected.");
            }
            // Retrieve the auto-generated keys (in this case, the ID of the newly inserted request)
            rs = stmt.getGeneratedKeys();
           

                // Insert into RequestStatus table
                statusStatement = conn.prepareStatement(statusSQL);
                statusStatement.setInt(1, status); // 1 for true, 0 for false;
                statusStatement.setInt(2, ID);

                // Execute the insert into RequestStatus table
                statusStatement.executeUpdate();
            
            return;
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException cl){
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, cl);
        }
    }
    
    public void denyRequest(String ID, int status) {
         Connection conn;
        PreparedStatement stmt;
        PreparedStatement statusStatement = null;        
        ResultSet rs = null;
        try {
            String statusSQL = "update RequestStatus set status = ? where requestID = ?";
            conn = DBContext.getConnection();
           
                // Insert into RequestStatus table
                statusStatement = conn.prepareStatement(statusSQL);
                statusStatement.setInt(1, status); // 1 for true, 0 for false;
                statusStatement.setInt(2, Integer.parseInt(ID));

                // Execute the insert into RequestStatus table
                statusStatement.executeUpdate();
            
            return;
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException cl){
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, cl);
        }
    }
    
    
    public void deleteRequest(String id) {
        String sql = "DELETE FROM RequestStatus WHERE requestID = ?";
        String sql2 = "DELETE FROM Request WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
            
            ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, id);
            ps2.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
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
            if (ps2 != null) {
                try {
                    ps2.close();
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
    }
    
    public void insertIntoProduct(String name, String code, String material, String size, String price, String cateID, String quantity) {
        String sql = "insert into Product(name, code, material, size, price, discount, category, stock, isAvailable, quantitySold) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, code);
            ps.setInt(3, Integer.parseInt(material));
            ps.setString(4, size);
            ps.setFloat(5, Float.parseFloat(price.trim()));            
            ps.setDouble(6, 0.1);
            ps.setInt(7, Integer.parseInt(cateID));
            ps.setInt(8, Integer.parseInt(quantity));         
            if(Integer.parseInt(quantity) > 0){
                ps.setInt(9, 1);
            }else ps.setInt(9, 0);
            ps.setInt(10, Integer.parseInt(quantity));
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
    }
    
    public static void main(String[] args){
        RequestDAO req = new RequestDAO();
        ProductDAO pdo = new ProductDAO();
        pdo.deleteSP("17");
        req.insertIntoProduct("Lồng chim chào mào tooooo", "LCD000", "2", "cao 22cm", "211.0", "1", "8");
    }
}

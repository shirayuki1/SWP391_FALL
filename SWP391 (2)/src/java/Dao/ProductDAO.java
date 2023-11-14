/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Model.Category;
import Model.Product ;

import Ultis.DBContext;
import Ultis.Ultis;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductDAO {


     public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where p.code NOT LIKE 'LCD%'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
//               if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
                list.add(p);
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
     
     public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "Select p.*,c.categoryName from Product p left join Category c on p.category = c.id";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
//               if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
                list.add(p);
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

     public Product getProductByID(String id) {
        String sql = "  Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where p.id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
                
                
//                 if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());
//                }
                return p;
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
     public List<Product> getProductByCategory(String id) {
        String sql = "  Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where c.id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         List<Product> list = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
                list.add(p);
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
        return list;
    }

    public void updateSP(String id, String size, String name, String price, String discount, String cateID, String quantity, int isAvailable, String material) {
        String sql = " UPDATE Product SET name = ?, size = ?, price = ?, discount = ?, stock = ?, category = ?, isAvailable = ?, material = ?\n" +
"	WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, size);
            ps.setDouble(3, Double.parseDouble(price));
            ps.setDouble(4, Double.parseDouble(discount));
            ps.setInt(5, Integer.parseInt(quantity));
            ps.setInt(6, Integer.parseInt(cateID));
            ps.setInt(7, isAvailable);
            ps.setInt(8, Integer.parseInt(material));
            ps.setInt(9, Integer.parseInt(id));
            
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void updateProduct(String id, String size, String name, String price, String discount, String cateID, String quantity, int isAvailable, String material, String img) {
        String sql = " UPDATE Product SET name = ?, size = ?, price = ?, discount = ?, stock = ?, category = ?, isAvailable = ?, material = ?, Image=?\n" +
"	WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, size);
            ps.setDouble(3, Double.parseDouble(price));
            ps.setDouble(4, Double.parseDouble(discount));
            ps.setInt(5, Integer.parseInt(quantity));
            ps.setInt(6, Integer.parseInt(cateID));
            ps.setInt(7, isAvailable);
            ps.setInt(8, Integer.parseInt(material));
            ps.setString(9, img);
            ps.setInt(10, Integer.parseInt(id));
            ps.executeUpdate();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
   

    public void deleteSP(String id) {
        String sql = "DELETE from Product where id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

   

    public int getTotal() throws ClassNotFoundException {
        String sql = "Select count(*) as total from Product";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
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
        return -1;
    }

    public void insert(String name, String code, String material, String size, String price, String discount, String cateID, String quantity, String fileName) {
        String sql = "insert into Product(name, code, material, size, price, discount, category, stock, image, isAvailable, quantitySold) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            ps.setInt(5, Integer.parseInt(price));            
            ps.setDouble(6, Float.parseFloat(discount));
            ps.setInt(7, Integer.parseInt(cateID));
            ps.setInt(8, Integer.parseInt(quantity));         
            ps.setString(9, fileName);
            if(Integer.parseInt(quantity) > 0){
                ps.setInt(10, 1);
            }else ps.setInt(10, 0);
            ps.setInt(11, 0);
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
    

//    public ArrayList<Product> getAllProducts() throws ClassNotFoundException, SQLException {
//       ArrayList<Product> products = new ArrayList<>();
//       Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String sql = "select c.id,c.name,c.price,c.image,c.isAvailable from Product c" ; 
//       try{
//            conn = DBContext.getConnection();
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                 Product p = new Product();
//                 p.setProductID(rs.getInt("id"));
//                 p.setName(rs.getString("name"));
//                 p.setPrice(rs.getInt("price"));
//                 p.setImage(rs.getString("image"));
//                 p.setIsAvailable(rs.getInt("isAvailable"));
//                   if (!p.getImage().contains("http")) {
//                    p.setImage("./img/" + p.getImage());}
//                 products.add(p);
//                 
//            } 
//       }catch(SQLException ex){
//            ex.printStackTrace();
//       } 
// return products;
////To change body of generated methods, choose Tools | Templates.
//    }

    public List<Product> searchProductsByName(String productName) {
        ArrayList<Product> list = new ArrayList<>(); 
       String sql = "Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where p.name like ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
           
             conn = DBContext.getConnection();
              if (conn != null) {
               ps = conn.prepareStatement(sql);
               ps.setString(1, "%" + productName + "%");
               
               rs = ps.executeQuery();
                while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
//            if (!p.getImage().contains("http")) {
//                   p.setImage("./img/" + p.getImage());              }
                list.add(p);
                }
              }
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

    public List<Product> searchProductsByPriceRange(List<Product> searchResults, int minPrice, int maxPrice) {
         List<Product> products = new ArrayList<>();
        for (Product product : searchResults) {
            int price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> searchProductsByPriceRange(int minPrice, int maxPrice) {
        ArrayList<Product> list = new ArrayList<>(); 
       String sql = "Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where p.price >= ? and p.price <= ?  ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    try{
        conn = DBContext.getConnection();
              if (conn != null) {
               ps = conn.prepareStatement(sql);
               ps.setInt(1, minPrice);
               ps.setInt(2, maxPrice);
               rs = ps.executeQuery();
              }
              while(rs.next()){
                   Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
            if (!p.getImage().contains("http")) {
                   p.setImage("./img/" + p.getImage());              }
                list.add(p);
                
              }
//To change body of generated methods, choose Tools | Templates.
    }catch(Exception e){
        e.printStackTrace();
    }finally{
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

    public List<Product> searchProductsByCategory(String categoryName) {
      ArrayList<Product> list = new ArrayList<>(); 
       String sql = "Select p.*,c.categoryName from Product p left join Category c on p.category = c.id where c.categoryName = ?   ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    try{
        conn = DBContext.getConnection();
              if (conn != null) {
               ps = conn.prepareStatement(sql);
               ps.setString(1, categoryName);
              
               rs = ps.executeQuery();
              }
              while(rs.next()){
                   Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
//            if (!p.getImage().contains("http")) {
//                   p.setImage("./img/" + p.getImage());              }
                list.add(p);
                
              }
//To change body of generated methods, choose Tools | Templates.
    }catch(Exception e){
        e.printStackTrace();
    }finally{
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
    
    public static void main(String[] args){
        ProductDAO pdo = new ProductDAO();       
        
    }

    public List<Product> searchProductsByMaterial(String materialName) {
      ArrayList<Product> list = new ArrayList<>(); 
       String sql = "Select p.*,c.original from Product p left join Material c on p.material = c.id where c.original = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    try{
        conn = DBContext.getConnection();
              if (conn != null) {
               ps = conn.prepareStatement(sql);
               ps.setString(1, materialName);
              
               rs = ps.executeQuery();
              }
              while(rs.next()){
                   Product p = new Product();
                p.setProductID(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setCode(rs.getString(3));
                p.setMaterial(rs.getInt(4));
                p.setSize(rs.getString(5));
                p.setPrice(rs.getInt(6));
                p.setDiscount(rs.getFloat(7));
                p.setCategory(new Category(rs.getInt(8), rs.getString(15)));
                p.setStock(rs.getInt(9));
                p.setIsAvailable(rs.getInt(10));
                p.setQuantitySold(rs.getInt(11));
                p.setRate_sum(rs.getDouble(12));
                p.setRate_count(rs.getDouble(13));
                p.setImage(rs.getString(14));
               
            if (!p.getImage().contains("http")) {
                   p.setImage("./img/" + p.getImage());              }
                list.add(p);
                
              }
//To change body of generated methods, choose Tools | Templates.
    }catch(Exception e){
        e.printStackTrace();
    }finally{
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
}
            
    

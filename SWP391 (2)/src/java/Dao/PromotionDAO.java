/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Account;
import Model.Promotion;
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
 * @author Admin
 */
public class PromotionDAO {

    public List<Promotion> getAllPromotions() {
        AccountDAO dao = new AccountDAO();
        List<Promotion> listPromotions = new ArrayList<>();
        Date currentDate = Date.valueOf(LocalDate.now());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            String sql = "select v.ID, v.Name, v.DateStart, v.DateEnd, v.status, v.Value, v.AccountID,\n" +
//                        "from Promotion v left join Account a on v.AccountID = a.id";
            String sql = "select * from Promotion ";
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int promotionID = rs.getInt("ID");
                String promotionName = rs.getString("Name");
                Date dateStart = rs.getDate("DateStart");
                Date dateEnd = rs.getDate("DateEnd");

                boolean promotionStatus = rs.getBoolean("status");
                if (dateEnd.before(currentDate)) {
                    promotionStatus = false;
                }
                int value = rs.getInt("Value");
                int accountID = rs.getInt("AccountID");
                int condition = rs.getInt("Condition");
                Account acc = dao.getAccountByID(String.valueOf(accountID));
                Promotion promotion = new Promotion(promotionID, promotionName, dateStart, dateEnd, promotionStatus, value, acc, condition);
                listPromotions.add(promotion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        }

        return listPromotions;
    }

    public void addPromotion(String promotionName, Date dateStart, Date dateEnd, boolean promotionStatus, int value, int accountID, int condition) {

        Connection conn;
        PreparedStatement stmt;
        try {
            String sql = "insert into Promotion values(?, ?, ?, ?, ?, ?, ?)";
            conn = DBContext.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, promotionName);
            stmt.setDate(2, dateStart);
            stmt.setDate(3, dateEnd);
            stmt.setBoolean(4, promotionStatus);
            stmt.setInt(5, value);
            stmt.setInt(6, accountID);
            stmt.setInt(7, condition);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException cl) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, cl);
        }
    }

    public boolean deletePromotion(int promotionID) {
        boolean check = false;
        Connection conn;
        PreparedStatement stmt;
        try {
            String sql = "delete from Promotion where ID = ?";
            conn = DBContext.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, promotionID);
            stmt.executeUpdate();
            check = true;

        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException cl) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, cl);
        }
        return check;
    }

    public void updatePromotion(int promotionID, String promotionName, Date dateStart, Date dateEnd, boolean promotionStatus, int value, int accID) {
        Connection conn;
        PreparedStatement stmt;
        try {
            String sql;
            sql = "update Promotion set Name = ?, DateStart =?, DateEnd = ?, Status = ?, Value = ?, AccountID = ?\n"
                    + "  where ID = ?";

            conn = DBContext.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, promotionName);
            stmt.setDate(2, dateStart);
            stmt.setDate(3, dateEnd);
            stmt.setBoolean(4, promotionStatus);
            stmt.setInt(5, value);
            stmt.setInt(6, accID);
            stmt.setInt(7, promotionID);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException cl) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, cl);
        }
    }

    public Promotion getPromotion(int totalProduct) {
        AccountDAO dao = new AccountDAO();
        Date currentDate = Date.valueOf(LocalDate.now());
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "  Select * from Promotion where DateStart <= GetDate() and GETDATE() <= DateEnd and Condition <= ?";
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, totalProduct);
            rs = ps.executeQuery();
            while (rs.next()) {
                int promotionID = rs.getInt("ID");
                String promotionName = rs.getString("Name");
                Date dateStart = rs.getDate("DateStart");
                Date dateEnd = rs.getDate("DateEnd");

                boolean promotionStatus = rs.getBoolean("Status");
                if (dateEnd.before(currentDate)) {
                    promotionStatus = false;
                }
                int value = rs.getInt("Value");
                int accountID = rs.getInt("AccountID");
                int condition = rs.getInt("Condition");
                Account acc = dao.getAccountByID(String.valueOf(accountID));
                Promotion promotion = new Promotion(promotionID, promotionName, dateStart, dateEnd, promotionStatus, value, acc, condition);
                return promotion;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException se) {
            se.printStackTrace();
        }

        return null;
    }

}

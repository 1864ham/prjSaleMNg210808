package dao;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoProduct extends DaoSet {
    private String cate;
    private String getProdList;

    public Object[] getCate() {
        Object[] result = null;
        String sql = "select distinct category from DEMO_PRODUCT_INFO";
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            result = list.toArray();
        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return result;
    }
    public Object[] getProdList(String cate) {
        Object[] result = null;
        ArrayList list = new ArrayList();
        String sql = "select PRODUCT_ID, PRODUCT_NAME " +
                "from DEMO_PRODUCT_INFO where category = ? ";
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,cate);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1) +"/"+ rs.getString(2));
            }
            result = list.toArray();
        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return result;
    }

    public String getProdPrice(String pId) {
        String result = "";
        String sql = "Select list_price from demo_product_info " +
                "where product_id = ? ";
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(pId));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }
    public String tot(String pId) {
        String result = "";
        String sql = "Select list_price from demo_product_info " +
                "where product_id = ? ";
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(pId));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (SQLException e) {e.printStackTrace();}
        return result;
    }

    public ImageIcon getProdImg(String pId) {
        ImageIcon result = null;
        String sql = "Select product_image from demo_product_info " +
                "where product_id = ? ";
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(pId));
            rs = pstmt.executeQuery();
            if (rs.next()) result =
                    new ImageIcon(ImageIO.read(rs.getBinaryStream(1)));
        } catch (Exception e) {e.printStackTrace();}
        return result;
    }
}

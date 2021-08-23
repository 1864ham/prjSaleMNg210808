package dao;

import java.sql.SQLException;
import java.util.ArrayList;

public class DaoCustomer extends DaoSet {

    public Object[] getCustAll() {
        Object[] result = null;
        String sql = "select CUSTOMER_ID, CUST_FIRST_NAME, CUST_LAST_NAME " +
                "from demo_customers order by cust_first_name";
        ArrayList list = new ArrayList();
        try {
            conn = connDB();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1)+"/"+rs.getString(2)+" "+rs.getString(3));
            }
            result = list.toArray();
        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return result;
    }
}
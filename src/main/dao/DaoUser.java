package dao;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import controller.MainController;
import vo.UserVO;

import java.sql.SQLException;

public class DaoUser extends DaoSet{
    public UserVO checkLogin(String id, String pw) {
        UserVO user = null;
        try {
            conn = connDB();
            String query = "select * from demo_users where id=? and password=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new UserVO(rs.getInt(1), rs.getString(2),
                        rs.getString(3),rs.getDate(4),rs.getInt(5),
                        rs.getString(6), rs.getDate(7),rs.getString(8),
                        rs.getString(9)
                );
                MainController.getInstance().setSession(user);
            }
        } catch (SQLException throwables) { throwables.printStackTrace(); }
        return user;
    }

    public boolean registerUser(UserVO vo) {
        boolean result = false;
        try {
            conn = connDB();
            String sql = "insert into demo_users(user_id, user_name," +
                    "password, created_on, quota, products, expires_on," +
                    "admin_user, id values(demo_users_seq.nextval,?,?," +
                    "sysdate, null,'Y',null,'N',?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, vo.getUserId());
            pstmt.setString(2, vo.getUserName());
            pstmt.setString(3, vo.getId());
            int cnt = pstmt.executeUpdate();
            result = cnt>0?true:false;
        } catch (SQLException throwables) {throwables.printStackTrace();}
        return result;
    }
    public boolean duplicateId(String id){
        boolean result = false;
        String sql = "select * from demo_users where id=?";
        try {
            conn = connDB();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
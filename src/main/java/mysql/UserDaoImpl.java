package mysql;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    public ResultSet findOne(String username) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://39.106.206.139:3408/chero_rents_test", "root", "123456");
            pst = conn.prepareStatement("select * from user where user_name = ?");
            pst.setString(1,username);
            resultSet = pst.executeQuery();

            return resultSet;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pst != null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean insertOne(String username, String passwd,String userid) throws ClassNotFoundException, SQLException {
        ResultSet resultSet = null;
        PreparedStatement pst = null;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://39.106.206.139:3408/chero_rents_test", "root", "123456");
            pst = conn.prepareStatement("insert into user(user_name,passwd,user_id) values(?,?,?)");
            pst.setString(1,username);
            pst.setString(2,passwd);
            pst.setString(3,userid);
            resultSet = pst.executeQuery();

            if(resultSet.getFetchSize() > 0){
                return true;
            }else{
                return false;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(pst != null){
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

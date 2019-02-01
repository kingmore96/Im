package mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * user表
 */
public interface UserDao {

    /**
     * 查询用户名是否存在
     * @param username
     * @return 存在返回true，不存在返回false
     */
    ResultSet findOne(String username) throws ClassNotFoundException, SQLException;

    /**
     * 插入用户名、加密后的密码和用户id
     * @param username
     * @param passwd
     * @return 成功返回true，失败返回false
     */
    boolean insertOne(String username,String passwd,String userid) throws ClassNotFoundException, SQLException;
}

package server.service;

import mysql.UserDao;
import mysql.UserDaoImpl;
import pojo.ServerResponse;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServiceImpl implements LoginService {

    private UserDao userDao = new UserDaoImpl();
    private static Object lock = new Object();

    public ServerResponse loginOrRegister(String username, String passwd) {
        synchronized (lock) {
            try {
                //去数据库查询是否存在
                ResultSet b = userDao.findOne(username);
                //不存在，md5密码，生成user_id，插入记录
                if (b.getFetchSize() == 0) {

                } else if (b.getFetchSize() == 1) {
                    //存在，对比数据库与传入的md5密码
                    if (b.next()) {
                        String passwd1 = b.getString("passwd");
                        //一致，构造成功response
                        //不一致，构造失败response
                    }
                } else {

                }
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return new ServerResponse("LI", "400", "未知错误");
            } catch (SQLException e) {
                e.printStackTrace();
                return new ServerResponse("LI", "400", "未知错误");
            } catch (Exception e) {
                e.printStackTrace();
                return new ServerResponse("LI", "400", "未知错误");
            }
        }
    }
}

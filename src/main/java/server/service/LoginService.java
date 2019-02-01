package server.service;

import pojo.ServerResponse;

public interface LoginService {
    /**
     * 登录校验或注册，返回响应对象
     * @param username
     * @param passwd
     * @return
     */
    ServerResponse loginOrRegister(String username, String passwd);
}

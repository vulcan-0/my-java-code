package org.example.java.proxy.base;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class UserServiceImpl implements UserService {

    @Override
    public boolean register(String username, String password) {
        System.out.println("用户注册成功 => username: " + username + ", password: " + password);
        return true;
    }

}

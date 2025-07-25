package org.example.java.proxy;

import org.example.java.proxy.base.UserService;
import org.example.java.proxy.base.UserServiceImpl;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class JdkDynamicProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        UserService proxy = (UserService) myInvocationHandler.getProxy(userService);
        boolean result = proxy.register("张三", "123456");
        System.out.println("注册结果 => " + result);
    }

}

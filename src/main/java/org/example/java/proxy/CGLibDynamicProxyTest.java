package org.example.java.proxy;

import org.example.java.proxy.base.UserController;
import org.example.java.proxy.base.UserService;
import org.example.java.proxy.base.UserServiceImpl;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class CGLibDynamicProxyTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyMethodInterceptor myMethodInterceptor1 = new MyMethodInterceptor();
        UserService proxy1 = (UserService) myMethodInterceptor1.getProxy(userService);
        boolean result1 = proxy1.register("张三", "123456");
        System.out.println("注册结果1 => " + result1);

        UserController userController = new UserController(userService);
        MyMethodInterceptor myMethodInterceptor2 = new MyMethodInterceptor();
        UserController proxy2 = (UserController) myMethodInterceptor2.getProxy(
                userController, new Class[]{UserService.class}, new Object[]{userService}
        );
        boolean result2 = proxy2.register("张三", "123456");
        System.out.println("注册结果2 => " + result2);
    }

}

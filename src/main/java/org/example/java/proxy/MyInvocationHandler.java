package org.example.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public Object getProxy(Object target) {
        this.target = target;

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doBefore();
        Object result = method.invoke(target, args);
        doAfter();
        return result;
    }

    private void doBefore() {
        System.out.println("do before");
    }

    private void doAfter() {
        System.out.println("do after");
    }

}

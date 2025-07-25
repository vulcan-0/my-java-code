package org.example.java.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lxc
 * @date 2025/7/21
 */
public class MyMethodInterceptor implements MethodInterceptor {

    private Object target;

    public Object getProxy(Object target) {
        this.target = target;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    public Object getProxy(Object target, Class<?>[] argumentTypes, Object[] arguments) {
        this.target = target;

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create(argumentTypes, arguments);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
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

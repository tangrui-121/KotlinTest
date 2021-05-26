package com.person.kotlintest.agent.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @anthor tr
 * @date 2021/5/25
 * @desc
 */
public class ProxyCompany implements InvocationHandler {

    /**
     * 依旧持有真实对象
     */
    private Object mFactory;

    public void setFactory(Object mFactory) {
        this.mFactory = mFactory;
    }

    public Object getDynamicProxy() {
        /**     
         * 拿到动态代理对象         
         * ClassLoader loader ：真实对象的ClassLoader         
         * Class<?>[] interfaces : 真实对象实现的接口         
         * InvocationHandler h  : InvocationHandler对象         
         */
        return Proxy.newProxyInstance(mFactory.getClass().getClassLoader(), mFactory.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理公司 --- 方案指定");
        //调用真实方法
        Object result = method.invoke(mFactory,args);
        System.out.println("代理公司 --- 收集反馈");
        return result;
    }
}

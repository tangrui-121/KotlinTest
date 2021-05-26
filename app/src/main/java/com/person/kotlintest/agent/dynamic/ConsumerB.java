package com.person.kotlintest.agent.dynamic;

import com.person.kotlintest.agent.FactoryA;
import com.person.kotlintest.agent.IFactoryA;

/**
 * @anthor tr
 * @date 2021/5/25
 * @desc
 */
public class ConsumerB {
    /**
     * 整个过程并没有创建具体的代理类
     * @param args
     */
    public static void main(String[] args) {
        //公司A
        IFactoryA factoryA = new FactoryA();
        //动态代理对象
        ProxyCompany proxyCompany = new ProxyCompany();
        proxyCompany.setFactory(factoryA);
        //动态的创建代理类
        IFactoryA proxyA = (IFactoryA) proxyCompany.getDynamicProxy();
        //执行方法
        proxyA.saleManTools("0000");

        //公司B
        IFactoryA factoryB = new FactoryA();
        proxyCompany.setFactory(factoryB);
        //执行方法
        proxyA.saleManTools("00001111");
    }
}

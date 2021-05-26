package com.person.kotlintest.agent;

/**
 * @anthor tr
 * @date 2021/5/25
 * @desc 代理C，实现IFactory,并持有真实对象FactoryA引用
 */
public class ProxyC implements IFactoryA {

    private IFactoryA iFactoryA;

    /*代理C，实现IFactory,并持有真实对象FactoryA引用*/
    public ProxyC(IFactoryA factoryA) {
        this.iFactoryA = factoryA;
    }

    /**
     * 对真实方法进行补强
     * @param size
     */
    @Override
    public void saleManTools(String size) {
        System.out.println("代理C --- 预售");
        iFactoryA.saleManTools(size);
        System.out.println("代理C --- 售后");
    }
}

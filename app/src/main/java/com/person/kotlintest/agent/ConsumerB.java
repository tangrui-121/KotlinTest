package com.person.kotlintest.agent;

/**
 * @anthor tr
 * @date 2021/5/25
 * @desc 消费者B
 */
public class ConsumerB {
    /* 分析下优劣，这样是方便了对公司A的功能增强还不需要去动公司A，
    但是假如多个公司，公司销售多个商品时则需要改公共接口，或者增加接口这样就会牵一发而动全身，
    所以可以引入动态代理，优势就是无需自己创建代理类*/
    public static void main(String[] args) {
        IFactoryA factoryA = new FactoryA();
        ProxyC proxyC = new ProxyC(factoryA);
        proxyC.saleManTools("8");
    }
}

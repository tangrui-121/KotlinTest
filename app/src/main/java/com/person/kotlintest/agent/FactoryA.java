package com.person.kotlintest.agent;

/**
 * @anthor tr
 * @date 2021/5/25
 * @desc 具体公司实现
 */
public class FactoryA implements IFactoryA {
    @Override
    public void saleManTools(String size) {
        System.out.println("公司A --- 我销售的size = "+size);
    }
}

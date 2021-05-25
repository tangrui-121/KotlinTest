package com.person.kotlintest.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @anthor tr
 * @date 2021/5/20
 * @desc
 */
public class Test {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Class manClass = Man.class;

        Class manClass1 = null;
        try {
            manClass1 = Class.forName("com.person.kotlintest.reflex.Man");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<? extends Man> manClass2 = new Man().getClass();

        System.out.println(manClass.getName());
        System.out.println(manClass1.getName());
        System.out.println(manClass2.getName());


        Man man = (Man) manClass.newInstance();
        man.setAge(23333);
        int photoNum = man.getPhoneNum();
        System.out.println("photoNum = " + photoNum);


        //getConstructors public
        //getDeclaredConstructors all
        for (Constructor c : manClass.getDeclaredConstructors()) {
            System.out.println("getConstructors -- " + c);
        }

        Constructor constructorPublicDeclared = null;
        try {
            constructorPublicDeclared = manClass.getConstructor(String.class);
            Constructor constructorPrivateDeclared2 = manClass.getDeclaredConstructor(String.class, int.class);
            System.out.println(constructorPublicDeclared);
            System.out.println(constructorPrivateDeclared2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        try {
            Man man1 = (Man) constructorPublicDeclared.newInstance("Test");
            String address = man1.getAddress();
            System.out.println("constructorPublicDeclared address = " + address);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //getMethods 本身和父类所有的public
        //getDeclaredMethods 本身所有的方法
        Method[] methods = manClass.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }


        try {
            Method setAge = manClass.getMethod("setAge",int.class);
            Method getphotonum = manClass.getMethod("getPhoneNum");
            System.out.println(setAge);
            System.out.println(getphotonum);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        try {
            Method setPhoneNum = manClass.getDeclaredMethod("setPhoneNum",int.class);
            setPhoneNum.setAccessible(true);
            Man man_setPhoneNum = (Man) manClass.newInstance();
            setPhoneNum.invoke(man_setPhoneNum,2345);
            System.out.println(man_setPhoneNum.getPhoneNum());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //getFields 本身和父类所有public成员变量
        //getDeclaredFields 本身所有成员变量
        Field[] fields = manClass.getFields();
        for (Field field : fields){
            System.out.println("field = "+ field);
        }

        try {
            Field address = manClass.getDeclaredField("address");
            Field age = manClass.getField("age");
            System.out.println(address);
            System.out.println(age);
            Man man_age = (Man) manClass.newInstance();
            address.setAccessible(true);
            address.set(man_age,"asdasdasd");
            System.out.println(man_age.getAddress());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }
}

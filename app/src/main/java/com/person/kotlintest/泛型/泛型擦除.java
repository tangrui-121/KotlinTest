package com.person.kotlintest.泛型;

import java.util.ArrayList;
import java.util.Date;

/**
 * @anthor tr
 * @date 2021/7/23
 * @desc
 */
public class 泛型擦除 {
    //擦除后是一样的
//    public static void method(ArrayList<String> list) {
//        System.out.println("Arraylist<String> list");
//    }

    public static void method(ArrayList<Date> list) {
        System.out.println("Arraylist<Date> list");
    }

    //这样会报错 即使Wangxiaoer是Wanger的子集
//    ArrayList<Wanger> list = new ArrayList<Wangxiaoer>();
    ArrayList<? extends Wanger> list1 = new ArrayList<Wangxiaoer>();

    private void aaa() {
        Arraylist<? extends Wanger> list2 = new Arraylist<>(4);
        list2.add(null);
        //哪怕是list2使用了通配符还是无法添加wanger或者wanger的子类wangxiaoer
//        list2.add(new Wanger());
//        list2.add(new Wangxiaoer());

        //那么list2有什么用？
        //语句把 list 的值赋予了 list2，此时 list2 == list。
        //由于 list2 不允许往其添加其他元素，所以此时它是安全的——我们可以从容地对 list2 进行 get()、indexOf() 和 contains()。
        //想一想，如果可以向 list2 添加元素的话，这 3 个方法反而变得不太安全，它们的值可能就会变。
        Arraylist<Wanger> list = new Arraylist<>(4);
        Wanger wanger = new Wanger();
        list.add(wanger);
        Wangxiaoer wangxiaoer = new Wangxiaoer();
        list.add(wangxiaoer);

        list2 = list;
        Wanger w2 = list2.get(1);
        System.out.println(w2);
        System.out.println(list2.indexOf(wanger));
        System.out.println(list2.contains(new Wangxiaoer()));

        //利用 <? super Wanger> 形式的通配符，可以向 Arraylist 中存入父类是 Wanger 的元素，来看例子
        //需要注意的是，无法从 Arraylist<? super Wanger> 这样类型的 list3 中取出数据
        Arraylist<? super Wanger> list3 = new Arraylist<>(4);
        list3.add(new Wanger());
        list3.add(new Wangxiaoer());

//        Wanger w3 = list3.get(0);
    }
}

class Wanglaoer {
    public String toString() {
        return "王老二";
    }
}

class Wanger extends Wanglaoer {
    public String toString() {
        return "王二";
    }
}

class Wangxiaoer extends Wanger {
    public String toString() {
        return "王小二";
    }
}

class Arraylist<E> {
    private Object[] elementData;
    private int size = 0;

    public Arraylist(int initialCapacity) {
        this.elementData = new Object[initialCapacity];
    }

    public boolean add(E e) {
        elementData[size++] = e;
        return true;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Object o : elementData) {
            if (o != null) {
                E e = (E) o;
                sb.append(e.toString());
                sb.append(',').append(' ');
            }
        }
        return sb.toString();
    }

    public int size() {
        return size;
    }

    public E set(int index, E element) {
        E oldValue = (E) elementData[index];
        elementData[index] = element;
        return oldValue;
    }
}



package com.person.kotlintest.interface_abstract


//吃睡是天生有的  必须有  这个可以写抽象
//火圈是后天的  可以写接口

//继承是一个 "是不是"的关系，而 接口 实现则是 "有没有"的关系。
//如果一个类继承了某个抽象类，则子类必定是抽象类的种类，而接口实现则是有没有、具备不具备的关系，比如狗是否能钻火圈，能则可以实现这个接口，不能就不实现这个接口。
class Check{

    abstract class Dog {
        abstract fun eat()
        abstract fun sleep()
    }

    //通过接口定义
    interface DrillFireCircle {
        fun drillFireCircle()
    }
}

class MyDog : Check.Dog(), Check.DrillFireCircle {
    override fun eat() {
        TODO("Not yet implemented")
    }

    override fun sleep() {
        TODO("Not yet implemented")
    }

    override fun drillFireCircle() {
        TODO("Not yet implemented")
    }

}
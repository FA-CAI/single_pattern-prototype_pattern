package com.shangxue.singleton_pattern.lazy;

/**
 * @description:内部类单例
 **/
//未使用 synchronized 关键字，效率较高,使用的是（利用到的是）jvm的类加载的属性
//内部类的加载性质：在外部类被调用的时候（被使用、被new）内部类才会（刚刚开始）被加载，否则是不加载的。如此实现了懒，又实际new对象时用到的方式是饿，属实扬长避短取长补短（完美地既屏蔽了饿时可能内存浪费，又屏蔽了懒时得加锁影响性能），优秀！完美！巧妙！优雅！高智商！史上最牛逼的单例模式，花小钱小力气办大事，物美价廉，优雅，浑然天造。
//内部类一定是要在该类的对象调用getInstance()以前加载且初始化完毕，这个特点巧妙的避免了线程安全问题（因为是在被用（多个线程调用了getInstance()，run它了）之前就产生了实例，线程并发后不存在new该实例的可能，也就只针对单例这个东西而言，它是安全的它是线程安全的，即使此时多线程并发调用getInstance()方法这玩意并没有线程安全，哇，多美妙的设计，饿汉式赛高！内部类赛高！）

//通过反射获取对象时，可能会破坏了单例，怎么解决？在构造方法里写逻辑
public class LazyInnerClassSingleton {

    private static boolean initialized = false;

    private LazyInnerClassSingleton() {
        //防止放射入侵
        synchronized (LazyInnerClassSingleton.class) {
            if (initialized == false) {
                initialized = !initialized;
            } else {
                throw new RuntimeException("单例已被侵犯/不允许构建多个实例");
            }
        }
    }


    //等到外部方法调用时才执行，应用了内部类的特征
    ////每一个关键字都不是多余的,精简完美，精华，字斟句酌的，有如名作家的自信老练老到的文字一般,属实名架构师！
    //static 是为了使单例的空间共享
    //final 保证这个方法不会被重写(首怕)或重载(次怕)
    public static final LazyInnerClassSingleton getInstance() {

        //先不论内部类的特征，看代码可知设计为在返回结果以前，一定会先加载内部类，然后才return。完美契合了内部类的特点，所以说，设计得真巧妙，不能赞一词！很巧妙，很契合，浑然天造！
        return LazyHold.LAZY;
    }

    //默认是先不加载，除非外部类被使用了，那刚开始加载
    private static class LazyHold {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }

}

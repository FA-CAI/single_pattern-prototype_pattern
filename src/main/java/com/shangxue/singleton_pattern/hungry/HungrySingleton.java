package com.shangxue.singleton_pattern.hungry;

/**
 *
 * 单例模式的意义（/产生背景）：初衷就是为了使资源能够共享，只需要赋值或者初始化一次（节省内存空间？提高性能？），大家都能够重复利用  ，比如 Listener、ioc(为什么要有容易？咱把单例全部往一个地方存，也就往一个地方取，那就方便了我们的使用，有点"统一管理"的味道)、日历Calender、配置Config
 *
 * Ps.目前听说影响性能的事情拉长时间的事情：new对象是个很消耗的事情，开闭空间，给对象初始化、返回对象在堆上的引用...。普通加锁。io访问硬盘。访问数据库。

 **/

// 单例模式实现方式之一：饿汉式单例
public class HungrySingleton {


    //static 是为了使单例的空间共享。静态的先加载并且一直都是这一个（属于该类的共享属性了而不能被具体某个对象私有了，共享而不是私有），保证单例
    // final 不允许覆盖
    private static final HungrySingleton hungrySingleton ;
    static{
        hungrySingleton =new HungrySingleton();
    }

    //构造器私有化   不允许别人造实例，只有我自己造   //这也是种约定（不管你怎么产生单例的，那个类里把构造器给我先私有化了）
    private HungrySingleton(){}

    //全局访问点
    public  static HungrySingleton getInstance(){
        //

        return hungrySingleton;
    }
}

//缺点：类加载的时候就要创建对象。浪费内存空间


//Ps.JAVA中静态块、静态变量加载顺序详解 https://www.cnblogs.com/leiqiannian/p/7922824.html

//教程中用了100个线程CountDownLauch，然后在单例加上时间，可以看到有相同时间访问到实例的，也就是并发了（测试类是线程不安全的），但是实例地址永远都是一个，也就是单例且对于"对象访问"而言是线程安全的！也就是，仅对这个单例模式的单例而言是线程安全的（所有线程每次访问的都是同一个对象）！
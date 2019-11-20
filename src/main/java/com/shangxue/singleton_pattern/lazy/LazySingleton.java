package com.shangxue.singleton_pattern.lazy;

/**
 * 懒汉式/延时加载
 **/
public class LazySingleton {
    private static LazySingleton lazySingleton=null;
    private LazySingleton(){}

    //不是类加载时产生实例，而是类运行、被使用（new对象）时产生实例。因为使用时才new单例，所以，若外面线程并发时，这里容易同时有两次new，那么，单例就被破坏了
    //单例的线程安全是对单例对象而言，至于外部方法是否线程安全，尚不在讨论范围，仍不顾及。也就是，单例模式的线程安全就是指多线程时"保证单例"
    //所以这里要加锁(但是，这个加锁方式，会极大的影响性能！)
    public static synchronized LazySingleton getInstance(){
        if(lazySingleton==null){
            lazySingleton =new LazySingleton();
        }
        System.out.println(lazySingleton);

        return lazySingleton;
    }




}



// 加锁影响性能：
//解法一：用内部类（它的特性+饿汉式，使得大家都能扬长避短）。因为内部类在外面的类没实例化前（被用到，也就是new Out ），自己是不会实例化的
//解法二： 不对整个方法加锁，只对new对象那行加锁。但是就会又成了并发时多例了（这个锁只是让一次只有一个线程进去），需要再补充一个非空验证（这第二道闸门就可以防止又new一次开辟新空间（即对象会有一个新的地址的(应该是：会有一个你从未体验过的初始化的全新对象)）而又赋值给句柄覆盖掉了），是谓【双重检测】
         //但JAVA的无序写入可能会使得先给句柄返回了引用，而还没初始化呢（当然我这里例子里的对象本身就没加啥初始化的东西-_-||光秃秃的。但如果有呢？返回没初始化的，光的，那不就残缺了吗，bug了吗）
         //这种情况下对应到就是singleton已经不是null，而是指向了堆上的一个对象，但是该对象却还没有完成初始化动作。当后续的线程发现singleton不是null而直接使用的时候，就会出现意料之外的问题。
         //JDK1.5之后，可以使用volatile关键字修饰变量来解决无序写入产生的问题，因为volatile关键字的一个重要作用是禁止指令重排序，即保证不会出现内存分配、返回对象引用、初始化这样的顺序，从而使得双重检测真正发挥作用。
//Ps.防反射：在构造方法里写逻辑。
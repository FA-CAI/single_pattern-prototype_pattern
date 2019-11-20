package com.shangxue.singleton_pattern.lazy;

/**

 * @description:双重检查锁

 **/
public class LazyDoubleCheckSingleton {
    //volatile 解决懒汉式时，jdk1.5之前java无序写入而指令重排序的问题（然后又碰上高并发，还没初始化，会其他线程迫不及待取走了，哈哈，果真极其恶劣天气恶劣环境般的感受哈） 参考资料：https://www.cnblogs.com/NaLanZiYi-LinEr/p/7492571.html
    private volatile static LazyDoubleCheckSingleton lazyDoubleCheckSingleton=null;
    private LazyDoubleCheckSingleton(){}

    public static LazyDoubleCheckSingleton getInstance(){
        if(lazyDoubleCheckSingleton==null){
            synchronized (LazyDoubleCheckSingleton.class){
                if(lazyDoubleCheckSingleton==null){ //若不加此行代码，则仍可能出现多次new对象的情况，后一次会覆盖之前一次
                    lazyDoubleCheckSingleton =new LazyDoubleCheckSingleton();
                }
            }
        }
        System.out.println(lazyDoubleCheckSingleton);
        return lazyDoubleCheckSingleton;
    }

}


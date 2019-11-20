package com.shangxue.singleton_pattern.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */

//Spring中的做法，就是用这种注册式单例  ioc
public class ContainerSingleton {

    private ContainerSingleton(){}   //惯例，约定，凡实现单例模式，都先给我把构造器私有了，别让在外部能创建该对象

    //ConcurrentHashMap线程安全,然后又name不能重名，所以线程并发也无所谓，创建时，两个以上线程同时进去"同时"（应该有少许先后）都new的话，因为name不能重名，会有后个替换先个！无所谓谁替换了谁（对创建单例地址具体确定是哪个，用户肯定不会对此（地址）有特别定制要求吧），反正结果还是单例
    private static Map<String,Object> ioc = new ConcurrentHashMap<String,Object>();
    public static Object getInstance(String className){
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object obj = null;
                try {
                    obj = Class.forName(className).newInstance();   //通过反射创建实例（用反射会性能差，但Spring用的就是这），以k,v形式存放在Map容器
                    ioc.put(className, obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
            } else {
                return ioc.get(className);
            }
        }
    }
}

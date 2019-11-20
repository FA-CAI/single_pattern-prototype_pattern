package com.shangxue.prototype_pattern.simple;

import lombok.Data;

import java.util.List;

/**
 * 原型模式有点跟单例模式反着来的感觉，我就是不想要单例~不仅外壳不要，内壳内部对象也不要（拒绝内部属性是对象时只是复制地址的这种浅拷贝——深拷贝如何实现？io流序列化）
 **/
@Data   //使用@data标签会在编译的时候自动生成get/set方法，会实现接口的方法
public class ConcretePrototypeA implements Prototype {
    private int age;
    private String name;
    private List hobbies;
    public Prototype clone() {
        ConcretePrototypeA concretePrototypeA=new ConcretePrototypeA();
        concretePrototypeA.setAge(this.age);
        concretePrototypeA.setHobbies(this.hobbies);
        concretePrototypeA.setName(this.name);
        return concretePrototypeA;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobbies() {
        return hobbies;
    }

    public void setHobbies(List hobbies) {
        this.hobbies = hobbies;
    }
}

/**
 *原型模式https://www.cnblogs.com/NaLanZiYi-LinEr/p/11795843.html
 *原型模式的作用和意义 https://blog.csdn.net/huangyimo/article/details/80390001 基本就是你需要从A的实例得到一份与A内容相同，但是又互不干扰的实例的话，就需要使用原型模式。
 * 有点ThreadLocal的感觉啊
 */
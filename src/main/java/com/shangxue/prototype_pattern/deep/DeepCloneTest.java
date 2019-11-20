package com.shangxue.prototype_pattern.deep;


public class DeepCloneTest {

    public static void main(String[] args) {

        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try {
            QiTianDaSheng clone = (QiTianDaSheng)qiTianDaSheng.clone();
            System.out.println("深克隆：" + (qiTianDaSheng.jinGuBang == clone.jinGuBang));
        } catch (Exception e) {
            e.printStackTrace();
        }

        QiTianDaSheng q = new QiTianDaSheng();
        QiTianDaSheng n = q.shallowClone(q);
        System.out.println("浅克隆：" + (q.jinGuBang == n.jinGuBang));  //仍是指向同一个地址（对象的引用），这个拷贝太敷衍了，咱希望"属性组装到新的"这种感觉(所以是针对DTO、VO、pojo、entity)


    }
}

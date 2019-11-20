package com.shangxue.prototype_pattern.deep;


import java.io.*;
import java.util.Date;

/**
 * Created by Tom.
 */
//深克隆与单例相反的逻辑（它就是不想单例，要人手一个不同的，不要有属性是对象类型的竟是浅拷贝（just拷了份地址，指向同一个引用），不行，它要忍受一个）
public class QiTianDaSheng extends Monkey implements Cloneable,Serializable {

    public JinGuBang jinGuBang;

    public  QiTianDaSheng(){
        //只是初始化
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }


    public Object deepClone(){
        try{
           //它就是想让内部对象属性也人手一个不同的，不想单例。这儿通过序列化io流去实现，new出了新的（也就有开辟了空间，从而是有新的地址）
            //那么，io流序列化时俺也想保证单例（单例之序列化时如何保证？Ps.单例之集群时如何保证?[分布式锁、缓存]），就要同一个地址，如何变：在要序列化的类配置readResolve()方法，交给架构师设计好的方法去运行判断（有点面向接口编程味道嗷）
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);

            QiTianDaSheng copy = (QiTianDaSheng)ois.readObject();
            copy.birthday = new Date();
            return copy;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    public QiTianDaSheng shallowClone(QiTianDaSheng target){

        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.height = target.height;
        qiTianDaSheng.weight = target.height;

        qiTianDaSheng.jinGuBang = target.jinGuBang;
        qiTianDaSheng.birthday = new Date();
        return  qiTianDaSheng;
    }

    public static void main(String[] args) {
        String gitUrl="asda/hhhh/git.git";
        int k=gitUrl.lastIndexOf("/");
        int l=gitUrl.indexOf(".");
        String str=gitUrl.substring(k+1,l);
        System.out.println(str);
    }


}

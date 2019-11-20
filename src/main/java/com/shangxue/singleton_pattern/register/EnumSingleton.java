package com.shangxue.singleton_pattern.register;

/**
 * 常量中去使用。通过枚举实现单例（因为"枚举"只会加载一次嘛~）。常量不就是用来大家都能够共用吗？
 *"常量"也要单例，共享，如此减少内存消耗，提高性能。
 **/
public enum EnumSingleton {


    INSTANCE;
    private Object data;

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {

        return data;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }



}

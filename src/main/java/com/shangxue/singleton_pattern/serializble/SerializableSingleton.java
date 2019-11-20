package com.shangxue.singleton_pattern.serializble;

import java.io.Serializable;

/**
 * 实现序列化
 */
public class SerializableSingleton implements Serializable {
    private static final SerializableSingleton serializableSingleton = new SerializableSingleton();
    private SerializableSingleton(){}
    public static SerializableSingleton getInstance(){
        return serializableSingleton;
    }

    /**
     * 在ObjectInputSteam中有下面一段代碼確定是否需要新建實例
     * if (obj != null &&
     *             handles.lookupException(passHandle) == null &&
     *             desc.hasReadResolveMethod())
     * 實際上是創建了兩個實例，只不過由readResolve判斷是否已經有實例，有則覆蓋反序列化
     * 生成的對象實例  。
     * @return
     */
//    private Object readResolve(){
//        return serializableSingleton;
//    }
}

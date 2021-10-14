package com.qiucheni.factory.impl;


import com.qiucheni.factory.ObjectFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 基本数据类型创建工厂
 * 基本数据类型创建包装类,利用JDK1.5提供的自动拆装箱
 */
public class BasicTypeFactory extends AbstractObjectFactory implements ObjectFactory {

    public BasicTypeFactory() {
    }

    private static Map<String,Class<?>> basicType;
    static {
        basicType = new HashMap<>();
        basicType.put("int",Integer.class);
        basicType.put("byte",Byte.class);
        basicType.put("short",Short.class);
        basicType.put("long",Long.class);
        basicType.put("boolean",Boolean.class);
        basicType.put("char",Character.class);
        basicType.put("float",Float.class);
        basicType.put("double",Double.class);
        basicType.put("java.lang.Integer",Integer.class);
        basicType.put("java.lang.Byte",Byte.class);
        basicType.put("java.lang.Short",Short.class);
        basicType.put("java.lang.Long",Long.class);
        basicType.put("java.lang.Boolean",Boolean.class);
        basicType.put("java.lang.Character",Character.class);
        basicType.put("java.lang.Float",Float.class);
        basicType.put("java.lang.Double",Double.class);
        basicType.put("java.lang.String",String.class);
    }

    /**
     * 反射创建对象，基本数据类型创建包装类对象
     * @param clazz 创建对象类型
     * @return
     */
    public Object getBasicInstance(Class<?> clazz,String value) {
        Class<?> basic = basicType.get(clazz.getName());
        Object obj = null;
        try {
            Constructor<?> declaredConstructor = basic.getDeclaredConstructor(String.class);
            obj = declaredConstructor.newInstance(value);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 判断类型是否是字面量类型
     * @param clazz
     * @return 是否是字面量类型
     */
    public boolean isBasicType(Class<?> clazz) {
        return basicType.get(clazz.getName()) != null;
    }
}

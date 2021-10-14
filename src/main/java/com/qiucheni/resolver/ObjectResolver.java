package com.qiucheni.resolver;

import com.qiucheni.factory.ObjectFactory;
import com.qiucheni.factory.impl.BasicTypeFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象解析器
 */
public class ObjectResolver {

    //单例
    private static ObjectFactory objectFactory;
    static {
        objectFactory = new BasicTypeFactory();
    }

    //存储Bean关系映射   根部Bean叫@
    private Map<String,Object> mapObject = new HashMap<>();


    public <T> T resolverBean(Map<String, String> map,Class<T> clazz,String basic) {
        Object res = null;
        res = objectFactory.getInstance(clazz);
        try {
            assignment(map,clazz,res,basic);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T)res;
    }

    /**
     * 反射给对象赋值
     * @param map
     * @param clazz
     * @param obj
     */
    public void assignment(Map<String, String> map,Class<?> clazz,Object obj,String basic) throws IllegalAccessException {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();//字段名
            Class<?> type = declaredField.getType();//字段类型
            boolean basicType = objectFactory.isBasicType(type);//判断类型是否为字面量类型
            Object instance = null;
            if (basicType) {
                instance = objectFactory.getBasicInstance(type,map.get(basic+name));
            } else {
                instance = resolverBean(map, type, name + ".");
            }
            declaredField.setAccessible(true);//放权
            declaredField.set(obj,instance);//给外部对象设置值
        }
    }


}

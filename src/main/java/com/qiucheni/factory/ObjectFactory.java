package com.qiucheni.factory;

public interface ObjectFactory {

    public Object getInstance(Class<?> clazz);

    public Object getBasicInstance(Class<?> clazz,String value);

    public boolean isBasicType(Class<?> clazz);
}

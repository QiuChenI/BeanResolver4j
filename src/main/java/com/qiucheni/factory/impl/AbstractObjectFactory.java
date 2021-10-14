package com.qiucheni.factory.impl;

import com.qiucheni.factory.ObjectFactory;

public abstract class AbstractObjectFactory implements ObjectFactory {

    public Object getInstance(Class<?> clazz) {
        Object res = null;
        try {
            res = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }
}

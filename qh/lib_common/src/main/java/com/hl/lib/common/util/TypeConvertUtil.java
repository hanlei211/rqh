package com.hl.lib.common.util;

import java.lang.reflect.ParameterizedType;

/**
 * 类转换器
 */
public class TypeConvertUtil {

    public static <T> T getT(Object b,int i){
       try {
       return ((Class<T>) ((ParameterizedType) (b.getClass()
               .getGenericSuperclass())).getActualTypeArguments()[i])
               .newInstance();
       }catch (InstantiationException e) {
       } catch (IllegalAccessException e) {
       } catch (ClassCastException e) {
       }
        return null;
    }
}

package com.sbarrasa.bank.utils.attribute;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AttributeAccessor {
  private final Object object;

  public AttributeAccessor(Object object) {
    this.object = object;
  }

  public Object get(String name) throws ReflectiveOperationException {
    return get(new AttributeInfo(name));
  }

  public Object get(AttributeInfo info) throws ReflectiveOperationException {
    switch(info.getAccessType()){
      case FIELD:
        Field field = object.getClass().getDeclaredField(info.getName());
        field.setAccessible(true);
        return field.get(object);
      case METHOD:
        Method method = object.getClass().getDeclaredMethod(info.getName());
        method.setAccessible(true);
        return method.invoke(object);
    }
    return null;
  }

  public static Object get(Object object, String methodName) throws ReflectiveOperationException {
    return new AttributeAccessor(object).get(methodName);
  }

  public static Object get(Object object, AttributeInfo info) throws ReflectiveOperationException {
    return new AttributeAccessor(object).get(info);
  }
}
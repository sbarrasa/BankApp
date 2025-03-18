package com.sbarrasa.bank.utils.matcher;

import com.sbarrasa.bank.utils.attribute.AttributeAccessor;
import com.sbarrasa.bank.utils.attribute.AttributesBuilder;

import java.util.Objects;
import java.util.List;

public class ObjectMatcher<T> {

  private final List<AttributePairInfo> attributes;

  public ObjectMatcher(String... attributes) {
    this.attributes = AttributesBuilder.build(attributes);
  }

  public ObjectMatcher(List<AttributePairInfo> attributes) {
    this.attributes = attributes;
  }

  public boolean anyMatch(T object1, T object2) {
    if (object1 == null || object2 == null) return false;

    return attributes.stream().anyMatch(attr -> attributeMatch(attr, object1, object2));
  }

  public boolean allMatch(T object1, T object2) {
    if (object1 == null || object2 == null) return false;

    return attributes.stream().allMatch(attr -> attributeMatch(attr, object1, object2));
  }

  private boolean attributeMatch(AttributePairInfo attr, Object object1, Object object2) {
    Object value1;
    Object value2;
    try {
      value1 = AttributeAccessor.get(object1, attr.getAttribute1());
      value2 = AttributeAccessor.get(object2, attr.getAttribute2());
    } catch (ReflectiveOperationException e) {
      return false;
    }
    return Objects.equals(value1, value2);

  }
}

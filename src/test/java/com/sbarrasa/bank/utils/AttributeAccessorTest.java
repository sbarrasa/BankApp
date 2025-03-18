package com.sbarrasa.bank.utils;

import com.sbarrasa.bank.utils.attribute.AccessType;
import com.sbarrasa.bank.utils.attribute.AttributeAccessor;
import com.sbarrasa.bank.utils.attribute.AttributeInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributeAccessorTest {
  private String value;

  private String getValue(){
    if(value == null)
      value = "test";

    return value;
  }

  @Test
  void getError() {
    assertThrows(ReflectiveOperationException.class,
      () -> AttributeAccessor.get(this, "pipo"));

  }

  @Test
  void getFromString() throws ReflectiveOperationException {
    var attributeAccessor = new AttributeAccessor(this);

    assertEquals("test", attributeAccessor.get("getValue"));

  }

  @Test
  void getFromAttribute() throws ReflectiveOperationException {
    var attributeAccessor = new AttributeAccessor(this);

    assertNull(attributeAccessor.get(new AttributeInfo("value", AccessType.FIELD)));
    assertEquals("test", attributeAccessor.get(new AttributeInfo("getValue", AccessType.METHOD)));

  }
}
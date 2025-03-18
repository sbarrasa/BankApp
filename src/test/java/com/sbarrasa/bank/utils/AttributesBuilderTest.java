package com.sbarrasa.bank.utils;

import com.sbarrasa.bank.utils.attribute.AccessType;
import com.sbarrasa.bank.utils.attribute.AttributeInfo;
import com.sbarrasa.bank.utils.attribute.AttributesBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttributesBuilderTest {

  @Test
  void build() {
    var attributeInfoList = new AttributesBuilder()
      .add("attr0")
      .add("attr1")
      .add("attr2")
      .add(new AttributeInfo("attr3"))
      .add(new AttributeInfo("attr4", AccessType.FIELD))
      .add(new AttributeInfo("attr5", AccessType.METHOD))
      .add(new AttributeInfo("attr6", AccessType.FIELD),new AttributeInfo("attr7"))
      .build();

    assertEquals(AccessType.METHOD, attributeInfoList.get(0).getAttribute1().getAccessType());
    assertEquals(AccessType.FIELD, attributeInfoList.get(4).getAttribute2().getAccessType());
    assertEquals(AccessType.METHOD, attributeInfoList.get(6).getAttribute2().getAccessType());
    assertEquals("attr3", attributeInfoList.get(3).getAttribute2().getName());


  }

  @Test
  void buildFormStrings() {
    var attributeInfoList = AttributesBuilder.build("cero", "uno", "dos", "tres");

    assertEquals("dos", attributeInfoList.get(2).getAttribute1().getName());
    assertEquals(AccessType.METHOD, attributeInfoList.get(3).getAttribute1().getAccessType());

  }


}
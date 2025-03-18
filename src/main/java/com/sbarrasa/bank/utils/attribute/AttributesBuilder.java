package com.sbarrasa.bank.utils.attribute;

import com.sbarrasa.bank.utils.matcher.AttributePairInfo;

import java.util.ArrayList;
import java.util.List;

public class AttributesBuilder {
  private final List<AttributePairInfo> list = new ArrayList<>();

  public AttributesBuilder add(String name) {
    add(new AttributeInfo(name));
    return this;
    
  }

  public AttributesBuilder add(AttributeInfo info) {
    list.add(new AttributePairInfo(info));
    return this;
  }

  public AttributesBuilder add(AttributeInfo attr1, AttributeInfo attr2) {
    list.add(new AttributePairInfo(attr1, attr2));
    return this;
  }

  public AttributesBuilder add(AttributePairInfo info) {
    list.add(info);
    return this;
  }

  public List<AttributePairInfo> build() {
    return list;
  }

  public static List<AttributePairInfo> build(String... names) {
    AttributesBuilder builder = new AttributesBuilder();
    for (String name : names)
      builder.add(name);
    return builder.build();
  }

  public static List<AttributePairInfo> build(AttributeInfo... infos) {
    AttributesBuilder builder = new AttributesBuilder();
    for (AttributeInfo info : infos)
      builder.add(info);
    return builder.build();
  }

  public static List<AttributePairInfo> build(AttributePairInfo... infos) {
    AttributesBuilder builder = new AttributesBuilder();
    for (AttributePairInfo info : infos)
      builder.add(info);
    return builder.build();
  }
}
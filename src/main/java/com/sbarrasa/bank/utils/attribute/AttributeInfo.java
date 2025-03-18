package com.sbarrasa.bank.utils.attribute;

import lombok.Getter;

@Getter
public class AttributeInfo {
  private final String name;
  private final AccessType accessType;

  public AttributeInfo(String name) {
    this(name, AccessType.METHOD);
  }

  public AttributeInfo(String name, AccessType accessType) {
    this.name = name;
    this.accessType = accessType;
  }

}
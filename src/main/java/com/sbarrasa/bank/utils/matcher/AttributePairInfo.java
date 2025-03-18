package com.sbarrasa.bank.utils.matcher;

import com.sbarrasa.bank.utils.attribute.AttributeInfo;
import lombok.Getter;

@Getter
public class AttributePairInfo {
  private final AttributeInfo attribute1;
  private final AttributeInfo attribute2;

  public AttributePairInfo(AttributeInfo attributeInfo) {
    this.attribute1 = attributeInfo;
    this.attribute2 = attributeInfo;
  }

  public AttributePairInfo(String attribute1, String attribute2) {
    this(new AttributeInfo(attribute1), new AttributeInfo(attribute2));
  }

  public AttributePairInfo(AttributeInfo attribute1, AttributeInfo attribute2) {
    this.attribute1 = attribute1;
    this.attribute2 = attribute2 != null
      ? attribute2
      : attribute1;
  }

}
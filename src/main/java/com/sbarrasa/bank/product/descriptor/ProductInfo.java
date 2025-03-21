package com.sbarrasa.bank.product.descriptor;

import com.sbarrasa.util.id.Desc;

public interface ProductInfo extends Desc {
  String getDescription();
  String getLargeDescription();
  void validate();
}

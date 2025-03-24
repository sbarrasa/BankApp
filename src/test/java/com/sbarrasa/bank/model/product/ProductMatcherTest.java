package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.controller.dto.ProductDTO;
import com.sbarrasa.bank.model.product.types.*;
import com.sbarrasa.bank.service.ProductMatcher;
import com.sbarrasa.util.matcher.MatchType;
import org.junit.jupiter.api.Test;

import static com.sbarrasa.bank.model.product.ProductEntityTest.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductMatcherTest {
  static ProductMatcher matcher = new ProductMatcher();

  @Test
  void anyMatch() {
    var sample = new ProductDTO()
      .setBranch(Branch.VISA)
      .setProductType(CreditCard.PRODUCT_TYPE);

    assertTrue(matcher.match(productTC_VISA, sample, MatchType.ANY));
    assertTrue(matcher.match(productTD, sample, MatchType.ANY));
    assertTrue(matcher.match(productTC_AMEX, sample, MatchType.ANY));
    assertFalse(matcher.match(productCC, sample, MatchType.ANY));
  }

  @Test
  void matchBranch() {
    var sample = new ProductDTO()
      .setBranch(Branch.VISA);

    assertTrue(matcher.match(productTC_VISA, sample, MatchType.ALL));
    assertTrue(matcher.match(productTD, sample, MatchType.ALL));
    assertFalse(matcher.match(productTC_AMEX, sample, MatchType.ALL));

  }

  @Test
  void matchCredit() {
    var sample = new ProductDTO()
      .setIsCredit(true);

    assertTrue(matcher.match(productCC, sample, MatchType.ALL));
    assertTrue(matcher.match(productTC_VISA, sample, MatchType.ALL));
    assertFalse(matcher.match(productCA_USD, sample, MatchType.ALL));

  }


}

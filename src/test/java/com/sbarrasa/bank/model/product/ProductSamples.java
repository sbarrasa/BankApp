package com.sbarrasa.bank.model.product;

import com.sbarrasa.bank.model.product.types.CheckingAccount;
import com.sbarrasa.bank.model.product.types.CreditCard;
import com.sbarrasa.bank.model.product.types.DebitCard;
import com.sbarrasa.bank.model.product.types.SavingAccount;

public class ProductSamples {
  public static CreditCard productTC_VISA = new CreditCard();
  static {
    productTC_VISA
      .setCreditLimit(6000000.00)
      .setTier("GOLD")
      .setBranch(Branch.VISA);
  }

  public static CreditCard productTC_AMEX = new CreditCard();
  static {
    productTC_AMEX
      .setCreditLimit(5000000.00)
      .setBranch(Branch.AMEX);
  }

  public static DebitCard productTD = new DebitCard();
  static {
    productTD
      .setBranch(Branch.VISA);
  }

  public static CheckingAccount productCC = new CheckingAccount();
  static {
    productCC
      .setCreditLimit(1000000.00)
      .setCbu("1234")
      .setCurrency(Currency.ARS);

  }

  public static SavingAccount productCA_USD = new SavingAccount();
  static {
    productCA_USD
      .setCbu("4321")
      .setCurrency(Currency.USD);
  }


}

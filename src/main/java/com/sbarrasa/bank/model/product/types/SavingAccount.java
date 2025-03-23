package com.sbarrasa.bank.model.product.types;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue("CA")
@Accessors(chain = true)
public class SavingAccount extends Account {

}

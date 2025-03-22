package com.sbarrasa.bank.product.types;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.Accessors;

@Entity
@DiscriminatorValue("TD")
@Accessors(chain = true)
public class DebitCard extends Card {
}

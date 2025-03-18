package com.sbarrasa.bank.util.descriptible;

public record EnumDescription<E extends Enum<E> & Describable>(E id, String description){

}

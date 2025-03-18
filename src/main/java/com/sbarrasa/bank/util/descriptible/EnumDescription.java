package com.sbarrasa.bank.util.descriptible;

public record EnumDescription<E extends Enum<E> & Descriptible>(E id, String description){

}

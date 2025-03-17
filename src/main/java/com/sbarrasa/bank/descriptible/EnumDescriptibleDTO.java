package com.sbarrasa.bank.descriptible;

public record EnumDescriptibleDTO<E extends Enum<E> & Descriptible>(E id, String description){

}

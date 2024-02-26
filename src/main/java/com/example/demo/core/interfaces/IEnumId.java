package com.example.demo.core.interfaces;

public interface IEnumId<TEnum>{
    int getTypeId();
    TEnum fromId(int id);
}

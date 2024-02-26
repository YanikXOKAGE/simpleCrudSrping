package com.example.demo.core.enums;

import com.example.demo.core.interfaces.IEnumId;

import java.util.HashMap;
import java.util.Map;

public enum EErrorType implements IEnumId<EErrorType> {


    UNKNOWN_ERROR(-1),
    SUCCESS(0),
    ITEM_ID_NOT_FOUND(1),
    CATALOG_ID_NOT_FOUND(2);


    private static final Map<Integer, EErrorType> map = new HashMap<>();

    private final Integer errorId;

    EErrorType(Integer errorId) {
        this.errorId = errorId;
    }

    static {
        for (EErrorType type : EErrorType.values()) {
            map.put(type.errorId, type);
        }
    }

    @Override
    public int getTypeId() {
        return errorId;
    }

    @Override
    public EErrorType fromId(int id) {
        return map.get(id);
    }
}

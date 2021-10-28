package com.b0ve.solucionintegraciongenerica.utils.condiciones;

public class FilterConditionNotEquals extends FilterCondition{
    private final String value;

    public FilterConditionNotEquals(String xpath, String value) {
        super(xpath);
        this.value = value;
    }

    @Override
    protected boolean testValue(String text) {
        return !text.equals(value);
    }
}

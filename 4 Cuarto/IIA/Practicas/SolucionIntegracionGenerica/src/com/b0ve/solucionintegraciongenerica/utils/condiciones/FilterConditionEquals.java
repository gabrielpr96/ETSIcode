package com.b0ve.solucionintegraciongenerica.utils.condiciones;

public class FilterConditionEquals extends FilterCondition{
    private final String value;

    public FilterConditionEquals(String xpath, String value) {
        super(xpath);
        this.value = value;
    }

    @Override
    protected boolean testValue(String text) {
        return text.equals(value);
    }
}

package com.techelevator;

import java.math.BigDecimal;

public enum Money {
    NICKEL(.05),
    DIME(.10),
    QUARTER(.25),
    DOLLAR(1.00),
    FIVE(5.00),
    TEN(10.00),
    TWENTY(20.00);

    BigDecimal value;

    Money(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}


package com.techelevator.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Money {
   private static final BigDecimal NICKEL = BigDecimal.valueOf(.05);
   private static final BigDecimal DIME = BigDecimal.valueOf(.1);
   private static final BigDecimal QUARTER = BigDecimal.valueOf(.25);
   private static final BigDecimal DOLLAR = BigDecimal.valueOf(1.00);
   private static final BigDecimal FIVE = BigDecimal.valueOf(5.00);
   private static final BigDecimal TEN = BigDecimal.valueOf(10.00);
   private static final BigDecimal TWENTY = BigDecimal.valueOf(20.00);
    final List<BigDecimal> moneyList = new ArrayList<>(Arrays.asList(NICKEL, DIME, QUARTER, DOLLAR, FIVE, TEN, TWENTY));

    public BigDecimal getNICKEL() {
        return NICKEL;
    }

    public BigDecimal getDIME() {
        return DIME;
    }

    public BigDecimal getQUARTER() {
        return QUARTER;
    }

    public BigDecimal getDOLLAR() {
        return DOLLAR;
    }

    public BigDecimal getFIVE() {
        return FIVE;
    }

    public BigDecimal getTEN() {
        return TEN;
    }

    public BigDecimal getTWENTY() {
        return TWENTY;
    }

    public List<BigDecimal> getMoneyList() {
        return moneyList;
    }
}


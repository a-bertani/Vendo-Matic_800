package com.techelevator.Services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Money class was created to be used for all the transactions in the Service class
public class Money {
   private final BigDecimal NICKEL = BigDecimal.valueOf(.05);
   private final BigDecimal DIME = BigDecimal.valueOf(.1);
   private final BigDecimal QUARTER = BigDecimal.valueOf(.25);
   private final BigDecimal DOLLAR = BigDecimal.valueOf(1.00);
   private final BigDecimal FIVE = BigDecimal.valueOf(5.00);
   private final BigDecimal TEN = BigDecimal.valueOf(10.00);
   private final BigDecimal TWENTY = BigDecimal.valueOf(20.00);
   private final List<BigDecimal> moneyList = new ArrayList<>(Arrays.asList(NICKEL, DIME, QUARTER, DOLLAR, FIVE, TEN, TWENTY));

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


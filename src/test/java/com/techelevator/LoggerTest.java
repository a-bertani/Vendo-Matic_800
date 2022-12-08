package com.techelevator;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerTest extends TestCase {
    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
    String formattedDate = localDateTime.format(dateTimeFormatter);

    @Test
    public void testFeedMoneyPrintLog() {
        Logger log = new Logger();
        assertEquals(formattedDate + " FEED MONEY: " + "$5.00 $5.00",
                    log.printLog("FEED MONEY:", BigDecimal.valueOf(5.00), BigDecimal.valueOf(5.00)));
    }
    @Test
    public void testGiveChangePrintLog() {
        Logger log = new Logger();
        assertEquals(formattedDate + " GIVE CHANGE: " + "$5.00 $0.00",
                log.printLog("GIVE CHANGE:", BigDecimal.valueOf(5.00), BigDecimal.valueOf(0.00)));
    }

    @Test
    public void testBuyItemPrintLog(){
        Logger log = new Logger();
        assertEquals(formattedDate + " Crunchie " + "$1.75 $8.25",
                log.printLog("Crunchie", BigDecimal.valueOf(1.75), BigDecimal.valueOf(8.25)));
    }

    @Test
    public void testNullTransactionTypePrintLog() {
        Logger log = new Logger();
        String errorMessage = null;
        try{
            log.printLog(null, BigDecimal.valueOf(1.75), BigDecimal.valueOf(8.25));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assert("Input values cannot be null".equals(errorMessage));
    }

    @Test
    public void testNullTransactionAmountPrintLog() {
        Logger log = new Logger();
        String errorMessage = null;
        try{
            log.printLog("Crunchie", null, BigDecimal.valueOf(8.25));
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assert("Input values cannot be null".equals(errorMessage));
    }

    @Test
    public void testNullCurrentMoneyProvidedPrintLog() {
        Logger log = new Logger();
        String errorMessage = null;
        try{
            log.printLog("Crunchie", BigDecimal.valueOf(1.75), null);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assert("Input values cannot be null".equals(errorMessage));
    }



}
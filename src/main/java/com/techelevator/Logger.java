package com.techelevator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Logger class is responsible for create the log text String that will be put in the Log.txt file
public class Logger {
    LocalDateTime localDateTime;
    DateTimeFormatter dateTimeFormatter;


    public String printLog(String transactionType, BigDecimal transactionAmount, BigDecimal currentMoneyProvided) throws IllegalArgumentException {
        if(transactionType == null || transactionAmount == null || currentMoneyProvided == null) {
            throw new IllegalArgumentException("Input values cannot be null");
        }
        localDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        String formattedDate = localDateTime.format(dateTimeFormatter);
        
        return String.format("%s %s $%.2f $%.2f", formattedDate,transactionType, transactionAmount, currentMoneyProvided);
    }
}

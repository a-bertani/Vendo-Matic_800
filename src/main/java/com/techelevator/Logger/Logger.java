package com.techelevator.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Logger class is responsible for create the log text String that will be put in the Log.txt file
public class Logger {
    private PrintWriter printWriter;

    public void printLog(String transactionType, BigDecimal transactionAmount, BigDecimal currentMoneyProvided) throws IllegalArgumentException {
        if(transactionType == null || transactionAmount == null || currentMoneyProvided == null) {
            throw new IllegalArgumentException("Input values cannot be null");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
        String formattedDate = localDateTime.format(dateTimeFormatter);
        File logFile = new File("Log.txt");

        try {
            printWriter = new PrintWriter(new FileOutputStream(logFile, true));
            printWriter.print(String.format("%s %-21s $%-6.2f $%-6.2f\n", formattedDate,transactionType, transactionAmount, currentMoneyProvided));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            printWriter.close();
        }
    }

}

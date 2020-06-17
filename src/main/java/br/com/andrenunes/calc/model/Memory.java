package br.com.andrenunes.calc.model;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private enum CommandType {
        RESET, NUMBER, DIV, MULT, SOM, SUB, EQ, COM, PC, CH
    }

    private static final Memory instance = new Memory();
    private final List<MemoryObserver> observers = new ArrayList<>();

    private CommandType lastOperation = null;
    private boolean replace = false;
    private String currentText = "";
    private String bufferText = "";

    public Memory() {
    }

    public static Memory getInstance() {
        return instance;
    }

    public void addObserver(MemoryObserver o) {
        observers.add(o);
    }

    public String getCurrentText() {
        return currentText.isEmpty() ? "0" : currentText;
    }

    public void processComand(String text) {
        CommandType commandType = commandTypeDetect(text);
        if (commandType == null) {
            return;
        } else if (commandType == CommandType.RESET) {
            currentText = "";
            bufferText = "";
            replace = false;
            lastOperation = null;
        } else if (commandType == CommandType.CH && currentText.contains("-")) {
            currentText = currentText.substring(1);
        } else if (commandType == CommandType.CH && !currentText.contains("-")) {
            currentText = "-" + currentText;
        } else if (commandType == CommandType.NUMBER || commandType == CommandType.COM) {
            currentText = replace ? text : currentText + text;
            replace = false;
        } else {
            replace = true;
            currentText = resultOp();
            bufferText = currentText;
            lastOperation = commandType;
        }


        observers.forEach(o -> o.alternateValue(getCurrentText()));

    }

    private String resultOp() {
        if (lastOperation == null || lastOperation == CommandType.EQ) {
            return currentText;
        }
        double bufferNumber = Double.parseDouble(bufferText.replace(",", "."));
        double currentNumber = Double.parseDouble(currentText.replace(",", "."));
        double result = 0;

        if (lastOperation == CommandType.SOM) {
            result = bufferNumber + currentNumber;
        } else if (lastOperation == CommandType.SUB) {
            result = bufferNumber - currentNumber;
        } else if (lastOperation == CommandType.DIV) {
            result = bufferNumber / currentNumber;
        } else if (lastOperation == CommandType.MULT) {
            result = bufferNumber * currentNumber;
        } else if (lastOperation == CommandType.PC) {
            result = currentNumber * (bufferNumber / 100);
        }
        String resultString = Double.toString(result).replace(".", ",");
        boolean dontDouble = resultString.endsWith(",0");
        return dontDouble ? resultString.replace(",0", "") : resultString;
    }


    private CommandType commandTypeDetect(String text) {
        if (currentText.isEmpty() && currentText == "0") {
            return null;
        }

        try {
            Integer.parseInt(text);
            return CommandType.NUMBER;
        } catch (NumberFormatException e) {
            if ("AC".equals(text)) {
                return CommandType.RESET;
            } else if ("/".equals(text)) {
                return CommandType.DIV;
            } else if ("*".equals(text)) {
                return CommandType.MULT;
            } else if ("-".equals(text)) {
                return CommandType.SUB;
            } else if ("+".equals(text)) {
                return CommandType.SOM;
            } else if ("=".equals(text)) {
                return CommandType.EQ;
            } else if (",".equals(text) && !currentText.contains(",")) {
                return CommandType.COM;
            } else if ("%".equals(text)) {
                return CommandType.PC;
            } else if ("±".equals(text)) {
                return CommandType.CH;
            }
        }


        return null;
    }
}

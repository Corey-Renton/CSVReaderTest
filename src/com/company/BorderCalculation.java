package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorderCalculation {

    public static Map borderReturn() {
        Map<String, Integer> borderMap = new HashMap<>();
        borderMap.put("MONGOLIA", 2); // China & Russia
        borderMap.put("CHANNEL ISLANDS", 2); // France & UK
        borderMap.put("POLAND", 6); // Germany, Czechia, Slovakia, Ukraine, Belarus, Lithuania
        borderMap.put("UNITED KINGDOM", 5); // Ireland, Channel Islands, France, Belgium, Netherlands
        borderMap.put("WEST GERMANY", 9); // Netherlands, Belgium, Luxembourg, France, Switzerland, Austria, Czechia, Poland, Denmark

        return borderMap;
    }


    public static int potentialTradingPartners(List Companies, String Country) {
        int i = 0;
        int value = 0;
        int amountOfPossibleTrades = 0;

        while (i < Companies.size()) {
            if(Companies.get(i).toString().contains(Country)){
                amountOfPossibleTrades++;
            }
            if(Companies.get(i).toString().contains(Country) && (Companies.get(i).toString().toUpperCase().contains("LIMITED") || Companies.get(i).toString().toUpperCase().contains("LTD"))){
                value = (int) borderReturn().get(Country.toUpperCase());
                amountOfPossibleTrades += value;
            }
            i++;
        }
        return amountOfPossibleTrades -1;
    }

}


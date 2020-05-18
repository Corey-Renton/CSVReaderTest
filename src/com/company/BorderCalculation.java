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
        int amountOfBorders = 0;
        int amountOfPossibleSameCountryTrades = 0;

        for (Object Company : Companies) {
            if (Company.toString().contains(Country)) {
                amountOfPossibleSameCountryTrades++;
            }
            if (Company.toString().contains(Country) && (Company.toString().toUpperCase().contains("LIMITED") || Company.toString().toUpperCase().contains("LTD"))) {
                amountOfBorders = (int) borderReturn().get(Country.toUpperCase());
            }
        }
        return (amountOfPossibleSameCountryTrades -1) + amountOfBorders; // Remove 1 as company cannot trade with itself.
    }
}


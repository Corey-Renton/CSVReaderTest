package com.company;

import java.util.HashMap;
import java.util.Map;

public class BorderCalculation {
    public static Map<String, Integer> borderMap = new HashMap<>();

    public static Map<String, Integer> getBorderMap() {
        if (!borderMap.isEmpty()) {
            return borderMap;
        } else {

            borderMap.put("MONGOLIA", 2); // China & Russia
            borderMap.put("CHANNEL ISLANDS", 2); // France & UK
            borderMap.put("POLAND", 6); // Germany, Czechia, Slovakia, Ukraine, Belarus, Lithuania
            borderMap.put("UNITED KINGDOM", 5); // Ireland, Channel Islands, France, Belgium, Netherlands
            borderMap.put("WEST GERMANY", 9); // Netherlands, Belgium, Luxembourg, France, Switzerland, Austria, Czechia, Poland, Denmark
            return borderMap;
        }
    }


    public static int getTradingPartnerCount(Company company, String country) {
            int amountOfPossibleSameCountryTrades = 0;
            int amountOfBorders = 0;
            if (company.getCompanyCountry().equals(country)) {
                for (int i = 0; i < Main.COMPANIES.size(); i++) {
                    if(Main.COMPANIES.get(i).getCompanyCountry().equals(company.getCompanyCountry()))
                    amountOfPossibleSameCountryTrades++;
                }
            }

            if (company.getCompanyCountry().equals(country) && company.isLimited()) {
                amountOfBorders = getBorderMap().get(country);
            }

        return (amountOfPossibleSameCountryTrades -1) + amountOfBorders; // Remove 1 as company cannot trade with itself.
    }

}


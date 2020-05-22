package com.company;

public class Company {

    private final String companyName;
    private final String companyCountry;
    private final boolean isLimited;

    public Company(String companyName, String companyCountry) {
        this.companyName = companyName;
        this.companyCountry = cleanCompanyCountry(companyCountry);
        this.isLimited = companyName.toUpperCase().matches(".*LIMITED.*|.*LTD.*");
    }


    public String getCompanyCountry() { return companyCountry; }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isLimited(){
        return isLimited;
    }


    @Override
    public String toString() {
        return this.companyName + Main.DELIMITER + this.companyCountry;
    }


    private static String cleanCompanyCountry(String country){
        return country.toUpperCase().replace("\"", "");
    }

}

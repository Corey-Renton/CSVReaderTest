package com.company;

public class Company {

    private final String companyName;
    private final String companyCountry;

    public Company(String companyName, String companyCountry) {
        this.companyName = companyName;
        this.companyCountry = companyCountry;
    }

    public String getCompanyCountry() {
        return companyCountry;
    }

    @Override
    public String toString() {
        return this.companyName + Main.DELIMITER + this.companyCountry;
    }

}

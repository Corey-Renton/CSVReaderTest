package com.company;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


class Main {
    public static final List<Company> COMPANIES = new ArrayList<>();
    private static final String CSV_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final int COMPANY_NAME_INDEX = 0;
    private static final int COMPANY_COUNTRY_INDEX = 12;
    private static final int COLUMNS_NEEDED = 2;
    public static final String DELIMITER = ",";
    private static final Path INPUT_FILE_PATH =   Paths.get("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv");
    private static final Path OUTPUT_FILE_PATH = Paths.get("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");

    public static void main(String[] args) throws IOException {
        BufferedReader br_input = new BufferedReader(new FileReader(INPUT_FILE_PATH.toFile()));
        FileUtil.ensureFileEnvironmentIsSetup(OUTPUT_FILE_PATH.toFile());

        String line;
        String[] column;
        while ((line = br_input.readLine()) != null) {
            column = line.split(CSV_DELIMITER);
            Company company = new Company(column[COMPANY_NAME_INDEX], column[COMPANY_COUNTRY_INDEX]);
            COMPANIES.add(company);
        }

        COMPANIES.remove(0); // To get rid of the column headers

        final Comparator<Company> comp = Comparator.comparing(Company::getCompanyCountry);
        COMPANIES.sort(comp);

        COMPANIES.stream().filter(company -> company.getCompanyCountry().length() > 10).collect(Collectors.toSet());

        for(Company company : COMPANIES){
            try (FileWriter fw = new FileWriter(OUTPUT_FILE_PATH.toFile(), true);
                 BufferedWriter writer = new BufferedWriter(fw)
            ) {

                writer.write(company.getCompanyName() + DELIMITER + company.getCompanyCountry()
                        + DELIMITER + BorderCalculation.getTradingPartnerCount(company, company.getCompanyCountry()));
                writer.newLine();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }
    }
}










package com.company;
import java.io.*;
import java.util.*;

import static java.util.Comparator.comparing;

class Main {
    private static final List<Company> COMPANIES = new ArrayList<>();
    private static final String CSV_DELIMITER = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
    private static final int COMPANY_NAME_INDEX = 0;
    private static final int COMPANY_COUNTRY_INDEX = 12;
    private static final int COLUMNS_NEEDED = 2;
    private static final String DELIMITER = ",";
    private static final String INPUT_FILE_PATH = "C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv";
    private static final File OUTPUT_FILE_PATH = new File("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");

    public static void main(String[] args) throws IOException {
        BufferedReader br_input = new BufferedReader(new FileReader(INPUT_FILE_PATH));
        FileUtil.fileCheck(OUTPUT_FILE_PATH);

        String line;
        String[] column;
        while ((line = br_input.readLine()) != null) {

            column = line.split(CSV_DELIMITER);
            Company company = new Company(column[COMPANY_NAME_INDEX], column[COMPANY_COUNTRY_INDEX]);
            COMPANIES.add(company);
        }

        final Comparator<Company> comp = (comparing(Company::getCompanyCountry));
        COMPANIES.sort(comp);
        COMPANIES.remove(COMPANIES.size() - 1); // To get rid of the column headers

        int i = 0;
        while (i < COMPANIES.size()) {
            try (FileWriter fw = new FileWriter(OUTPUT_FILE_PATH, true); BufferedWriter writer = new BufferedWriter(fw)) {
                String str = COMPANIES.get(i).toString();
                String[] arrOfStr = str.split(CSV_DELIMITER, COLUMNS_NEEDED);
                arrOfStr[1] = arrOfStr[1].replace("\"" , "");
                writer.write(arrOfStr[0] + DELIMITER + arrOfStr[1] + DELIMITER + BorderCalculation.potentialTradingPartners(COMPANIES, arrOfStr[1]));
                writer.newLine();
                i++;
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }

        }


    }
}










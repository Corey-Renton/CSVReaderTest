package com.company;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {

        String line;
        String[] column;

        String Input_File = "C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv";
        BufferedReader br_input = new BufferedReader(new FileReader(Input_File));
        File Output_File = new File("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");

        if (Output_File.exists()) {
            Output_File.delete();
        } else {
            Output_File.createNewFile();
        }

        while ((line = br_input.readLine()) != null) {

            String Split_String = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // Had issues when initially splitting with "," due to the fact some columns included a , in addresses etc.
            column = line.split(Split_String);

            try (FileWriter fw = new FileWriter(Output_File, true);
                 BufferedWriter br_output = new BufferedWriter(fw)) {

                br_output.write(column[0] + "," + column[12] + ""); // Writes the two needed columns to file.
                br_output.newLine(); // New Tuple

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }

            System.out.println("Company Name: " + column[0] + " Company Country: " + column[12]); // Just for testing
        }
    }
}

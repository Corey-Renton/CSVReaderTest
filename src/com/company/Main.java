package com.company;
import java.io.*;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {




    public static void main(String[] args) throws IOException {



        Methods methods = new Methods();
        String line;
        String[] column;
        TreeMap<String,String> company_data = new TreeMap<>();
        SortedMap m = Collections.synchronizedSortedMap(new TreeMap(company_data));
        int i = 0;
        int y = 0;

        String Input_File = "C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv";
        BufferedReader br_input = new BufferedReader(new FileReader(Input_File));
        File Output_File = new File("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");


        methods.File_Check(Output_File);

        while ((line = br_input.readLine()) != null) {

            String Split_String = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // Had issues when initially splitting with "," due to the fact some columns included a , in addresses etc.
            column = line.split(Split_String);

            try (FileWriter fw = new FileWriter(Output_File, true);
                 BufferedWriter br_output = new BufferedWriter(fw)) {

                br_output.write(column[0] + "," + column[12] + ""); // Writes the two needed columns to file.
                br_output.newLine(); // New Tuple

                m.put(column[12], column[0]);

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }

            System.out.println("Company Name: " + column[12] + " Company Country: " + column[0]); // Just for testing

            i++;
        }



//        i = 0;

//        while (i < company_names.size()) {
//
//            Map.Entry<String, String> entry = company_names.entrySet().iterator().next();
//            String key = entry.getKey();
//            String value = entry.getValue();
//            //  br_output.write(key + "," + value + ""); // Writes the two needed columns to file.
//            // br_output.newLine();
//            System.out.println(key + value);
//            i++;
//
//        }

        System.out.println(m);
        System.out.println(m.size());


    }
}

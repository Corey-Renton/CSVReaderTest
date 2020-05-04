package com.company;
import javafx.util.Pair;

import java.io.*;
import java.text.Collator;
import java.util.*;

import static java.util.Comparator.comparing;

public class Main {


    static final List<Pair<String, String>> words = new ArrayList<>();

    public static String getValue(String value) {
        for (Pair<String, String> p : words)
            if (p.getKey().equals(value))
                return p.getValue();
        return null;
    }


    public static void main(String[] args) throws IOException {


        Methods methods = new Methods();
        String line;
        String[] column;
        String[] dataHold = new String[200];
        TreeMap<String, String> company_data = new TreeMap<>();
        int Amount_Of_Tuples = 0;

        String value;


        String Input_File = "C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv";
        File Output_File = new File("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");
        BufferedReader br_input = new BufferedReader(new FileReader(Input_File));


        methods.File_Check(Output_File);


        while ((line = br_input.readLine()) != null) {

            String Split_String = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // Had issues when initially splitting with "," due to the fact some columns included a , in addresses etc.
            column = line.split(Split_String);


            Pair<String, String> paird = new Pair<>(column[0], column[12]);
            words.add(paird);


            System.out.println("Company Name: " + column[12] + " Company Country: " + column[0]); // Just for testing

            Amount_Of_Tuples++;
        }

        final Comparator<Pair<String, String>> c = (comparing(Pair::getValue));
        Collections.sort(words, c);

//        words.stream()
//                .sorted(c)
//                .map(Pair::getValue)
//                .forEach(System.out::println);

        int y = 0;
        int w = 0;
        while(y < Amount_Of_Tuples) {
            String str = words.get(y).toString();
            String[] arrOfStr = str.split("=", 2);
            for (String a : arrOfStr) {
                dataHold[w] = a;
                w++;
            }
            y++;
        }


        int i = 0;
        while(i < Amount_Of_Tuples * 2 - 2) {
            try (FileWriter fw = new FileWriter(Output_File, true);
                 BufferedWriter br_output = new BufferedWriter(fw)) {


                br_output.write(dataHold[i] + "," + dataHold[i+1]); // Writes the two needed columns to file.
                br_output.newLine(); // New Tuple


            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
            i = i +2;
        }

    }
}











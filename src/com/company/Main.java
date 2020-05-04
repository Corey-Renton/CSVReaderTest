package com.company;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

class Main {

    private static final List<Pair<String, String>> PAIR_LIST = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Methods methods = new Methods();
        int i = 0;
        int y = 0;
        int w = 0;
        int Split_Amount = 2;
        String line;
        String[] column;
        int Amount_Of_Tuples = 0;

        String Input_File = "C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\data.csv";
        File Output_File = new File("C:\\Users\\Yh\\Desktop\\CSVReader\\CSVReader\\src\\com\\company\\results.csv");
        BufferedReader br_input = new BufferedReader(new FileReader(Input_File));

        methods.File_Check(Output_File);

            while ((line = br_input.readLine()) != null) {

                String Split_String = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // Had issues when initially splitting with "," due to the fact some columns included a , in addresses etc.
                column = line.split(Split_String); // Split the 2 barrel string into 2 strings via the CSV ","

                Pair<String, String> pair_input_tuples = new Pair<>(column[0], column[12]); // Pair value to load all tuples into the PAIR_LIST
                PAIR_LIST.add(pair_input_tuples);

                Amount_Of_Tuples++; // Have a counter here to provide accurate non hard-coded index's for our arrays.
            }

        String[] dataHold = new String[Amount_Of_Tuples * Split_Amount]; // Index should be the size of the Amount of Tuples needed times the amount of splits per string.

        final Comparator<Pair<String, String>> comp = (comparing(Pair::getValue)); // Comparator to compare the Value key (Second String Value) in the PairList.
        PAIR_LIST.sort(comp); // Sort PAIR_LIST by comparator we made this will sort our Key's aka our Company Country strings by Alphabetical Order.

        while (y < Amount_Of_Tuples) {
            String str = PAIR_LIST.get(y).toString();
            String[] arrOfStr = str.split("=", Split_Amount); // Splits the PairList into two strings whilst keeping their bond (Value will always be +1 from the Key in the Array allowing us to keep their relation).
            for (String a : arrOfStr) { //While there's a string in the array of strings add them to the DataHold array
                dataHold[w] = a;
                w++;
            }
            y++;
        }

        while (i < Amount_Of_Tuples * 2 - 2) { // Minus 2 as we have two unnecessary tuples at the end. If have time
            try (FileWriter fw = new FileWriter(Output_File, true);
                 BufferedWriter br_output = new BufferedWriter(fw)) {

                br_output.write(dataHold[i] + "," + dataHold[i + 1]); // Writes the two needed columns to file.
                br_output.newLine(); // New Tuple

            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
            i += Split_Amount; // Had to add two because adding one would mean 0 - 1 - 1 - 2  during the write syntax but adding 2 addresses this perfectly.
        }

    }
}











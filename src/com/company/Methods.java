package com.company;

import java.io.File;
import java.io.IOException;

public class Methods {


    public void File_Check(File Output_File) throws IOException {
        if (Output_File.exists()) {
            Output_File.delete();
        } else {
            Output_File.createNewFile();
        }
    }
}

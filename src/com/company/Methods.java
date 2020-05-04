package com.company;

import java.io.File;
import java.io.IOException;

class Methods {


    public void File_Check(File Output_File) throws IOException { // If File exists then delete else make new file
        if (Output_File.exists()) {
            //(noinspection ResultOfMethodCallIgnored) <---- Please explain wtf this means IntelliJ put it here.
            Output_File.delete();
        } else {
            Output_File.createNewFile();
        }



    }
}

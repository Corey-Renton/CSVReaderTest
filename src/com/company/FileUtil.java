package com.company;
import java.io.File;
import java.io.IOException;

class FileUtil {

    public static void ensureFileEnvironmentIsSetup(File outputFile) throws IOException {
        if (outputFile.exists()) {
            outputFile.delete();
        } else {
            outputFile.createNewFile();
        }
    }
}

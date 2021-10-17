package it.unimol.com.utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectoryReader {


    public List<File> read(String directoryName, List<String> pathsToAvoid) {
        List<File> resultList = new ArrayList<File>();
        File directory = new File(directoryName);
        if (directory.isDirectory()) {
            List<File> fList = Arrays.asList(directory.listFiles());
            for (File file : fList) {
                if (!pathsToAvoid.contains(file.getAbsolutePath())) {
                    System.out.println(file.getAbsolutePath());
                    resultList.add(file);
                }
            }
        }


        return resultList;
    }
}

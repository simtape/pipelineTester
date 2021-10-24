package it.unimol.com.all_plugins.row_destroyer;

import com.google.auto.service.AutoService;

import it.unimol.com.Plugin;
import it.unimol.com.utilities.DirectoryReader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AutoService(Plugin.class)
public class RowDestroyer implements Plugin {

    final String directoryName = "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\temp";
    List<Project> filesToChange;

    @Override
    public void run() throws NotAJavaProject {
        filesToChange = DirectoryReader.getDirectoryReader().exploreDirectories(directoryName);
        for (Project project : filesToChange) {
            System.out.println("******************************************");
            System.out.println("PROJECT NAME: " + project.getName());
            System.out.println("PROJECT TYPE: " + project.getProjectType());
            System.out.println("                     ");
            if (project.getProjectType() == ProjectType.NOT_JAVA) {
                //throw new NotAJavaProject(project.getName() + " is not a Java project");
            } else {
                JavaFiles javaFiles = project.getJavaFiles();
                File fileToMutate = javaFiles.getFiles()
                        .get((int) (Math.random() * javaFiles.getFiles().size()))
                        .toFile();
                String newPathForFile = fileToMutate.getAbsolutePath().replace(".java", "2.java");
                File newFile = new File(newPathForFile);

                try {
                    BufferedReader br = new BufferedReader(new FileReader(fileToMutate));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
                    List<String> lines = new ArrayList<>();

                    String line = br.readLine();
                    System.out.println(" ");
                    System.out.println("OLD FILE: ");
                    System.out.println(" ");
                    while (line != null) {
                        if (!(line.isEmpty() || line.contains("System.out.println") || line.contains("//"))) {
                            lines.add(line);
                        }
                        System.out.println(line);
                        line = br.readLine();
                    }
                    int index = (int) (Math.random() * lines.size());
                    System.out.println("    ");
                    System.out.println("line about to be mutate: " + lines.get(index));
                    System.out.println("    ");
                    if (lines.get(index).contains(";")) {
                        String newline = lines.get(index).replace(";", "");
                        lines.set(index, newline);

                    } else {
                        String newline = lines.get(index).replace(lines.get(index), "");
                        lines.set(index, newline);

                    }


                    System.out.println(" ");
                    System.out.println("NEW FILE: ");
                    System.out.println(" ");
                    for (String string : lines) {
                        bw.write(string);
                        bw.newLine();
                        System.out.println(string);
                    }
                    br.close();
                    bw.close();
                    fileToMutate.delete();
                    newFile.renameTo(fileToMutate);
                } catch (IOException e) {


                }


            }
        }
    }



}

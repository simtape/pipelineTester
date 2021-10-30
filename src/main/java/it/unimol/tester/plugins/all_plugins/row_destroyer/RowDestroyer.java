package it.unimol.tester.plugins.all_plugins.row_destroyer;

import com.google.auto.service.AutoService;
import it.unimol.tester.plugins.Plugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@AutoService(Plugin.class)
public class RowDestroyer implements Plugin {

    List<Project> files, repositories = new ArrayList<>();

    @Override
    public void run(List<Project>projects) throws NotAJavaProject {
        this.files = projects;
        for (Project p : files) {
            if (p.getName().contains("clone_"))
                repositories.add(p);
        }
        System.out.println("SIZE OF ALL DIR: " + files.size());
        System.out.println("SIZE OF REPOS CLONED: " + repositories.size());
        for (Project project : repositories) {
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

package it.unimol.tester.utilities;

import it.unimol.tester.parser.app.PipeLineParser;
import it.unimol.tester.parser.app.pipeline_utilities.PipeLine;
import it.unimol.tester.plugins.all_plugins.row_destroyer.JavaFiles;
import it.unimol.tester.plugins.all_plugins.row_destroyer.Project;
import it.unimol.tester.plugins.all_plugins.row_destroyer.ProjectType;

import java.io.File;
import java.io.IOException;
import java.nio.channels.Pipe;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryReader {
    //TODO creare modulo a parte per questo directory reader forse è una cosa migliore
    public static DirectoryReader directoryReader = new DirectoryReader();

    private DirectoryReader() {

    }

    public static DirectoryReader getDirectoryReader() {
        return directoryReader;
    }

    //lista tutti i files all'interno di una directory
    public List<File> read(String directoryName) {
        List<File> resultList = new ArrayList<File>();
        File directory = new File(directoryName);
        if (directory.isDirectory()) {
            List<File> fList = Arrays.asList(directory.listFiles());
            for (File file : fList) {

                resultList.add(file);

            }
        }


        return resultList;
    }


    //usata per esplorare tutte le cartelle e files all'interno dei progetti
    //controlla anche se il progetto è scritto in java
    public List<Project> exploreDirectories(String directoryName) {
        List<File> directories = this.read(directoryName);
        List<Project> projects = new ArrayList<>();

        for (File file : directories) {
            String nameOfProject = file.getAbsolutePath().replace(directoryName + "\\", "");


            Project project = new Project();
            project.setName(nameOfProject);
            JavaFiles javaFiles1 = new JavaFiles();
            List<Path> pathsJavaFiles = new ArrayList<>();

            boolean isJava = false;
            Path path = Paths.get(file.getAbsolutePath());
            List<Path> result = new ArrayList<>();
            try {
                Stream<Path> walk = Files.walk(path);
                result = walk.filter(Files::isRegularFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {

            }
            for (Path p : result) {
                if (p.toFile().isFile() && p.toFile().getAbsolutePath().endsWith(".java")) {
                    isJava = true;
                    pathsJavaFiles.add(p);

                    javaFiles1.setFiles(pathsJavaFiles);
                }


            }
            if (isJava)
                project.setProjectType(ProjectType.JAVA);
            else
                project.setProjectType(ProjectType.NOT_JAVA);

            project.setJavaFiles(javaFiles1);
            projects.add(project);

        }

        return projects;
    }

    public List<PipeLine> findPipeline(String directoryName) {
        List<File> directories = this.read(directoryName);
        List<String> pathPipelines = new ArrayList<>();
        List<PipeLine> pipeLines = new ArrayList<>();

        for (File f : directories) {
            Path path = Paths.get(f.getAbsolutePath());
            List<Path> result = new ArrayList<>();
            try {
                Stream<Path> walk = Files.walk(path);
                result = walk.filter(Files::isRegularFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {

            }
            for (Path p : result) {
                PipeLineParser pipeLineParser = new PipeLineParser();
                //TODO modificare parser, fare in modo tale che mi restituisce una pipeline parsata


            }


        }
        return pipeLines;
    }
}

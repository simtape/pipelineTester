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
    DirectoryReader directoryReader = new DirectoryReader();
    final List<String> pathsToAvoid = Arrays.asList(
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\.git",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\.gitignore",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\.idea",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\utilities\n",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\target",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\src",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\repo_cloner",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\pom.xml",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\plugins",
            "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\parser"
    );
    final String directoryName = "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester";
    List<Project> filesToChange;

    @Override
    public void run() throws NotAJavaProject {
        filesToChange = exploreDirectories();
        for (Project project : filesToChange) {
            System.out.println("PROJECTS LENGTH: " + filesToChange.size());
            System.out.println("PROJECT NAME: " + project.getName());
            System.out.println("PROJECT TYPE: " + project.getProjectType());
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
                    while (line != null) {
                        if (!(line.isEmpty() || line.contains("System.out.println") || line.contains("//"))) {
                            lines.add(line);
                        }
                        System.out.println(line);
                        line = br.readLine();
                    }
                    int index = (int)(Math.random() * lines.size());
                    System.out.println("line about to be mutate: " + lines.get(index));
                    if(lines.get(index).contains(";")){
                        String newline = lines.get(index).replace(";", "");
                        lines.set(index, newline);
                    }else{
                        String newline = lines.get(index).replace(lines.get(index), "");
                        lines.set(index, newline);
                    }

                    for(String string: lines){
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

    List<Project> exploreDirectories() {
        List<File> directories = directoryReader.read(directoryName, pathsToAvoid);
        List<Project> projects = new ArrayList<>();

        for (File file : directories) {
            String nameOfProject = file.getAbsolutePath().replace(directoryName + "\\", "");
            System.out.println("name of project: " + nameOfProject);

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

}

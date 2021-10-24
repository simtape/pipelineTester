package it.unimol.com;

import it.unimol.com.utilities.DirectoryReader;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GitCloner {

    private static GitCloner gitCloner = new GitCloner();

    public static GitCloner getGitCloner() {
        return gitCloner;
    }

    private String name_dir;

    private GitCloner() {
    }

    private void downloadAndClone() throws IOException {

        File file = new File("repo_cloner/src/main/resources/list_repositories.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String uri = br.readLine();
        new File("temp").mkdirs();

        while (uri != null) {
            System.out.println(uri);
            String command = "cmd /c cd temp && git clone "+ uri;
            CommandLine cmdLine = CommandLine.parse( command);
            DefaultExecutor executor = new DefaultExecutor();
            int exitValue = executor.execute(cmdLine);
            uri = br.readLine();
        }

        List<File>repositories = DirectoryReader.directoryReader.read("C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\temp");
        for(File repo : repositories){
            String destination = "temp/clone_" + repo.getName();
            File destDir = new File(destination);

            try {
                FileUtils.copyDirectory(repo, destDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {


        try {
            downloadAndClone();
        } catch (Exception e) {

        }
    }
}

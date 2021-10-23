package it.unimol.com;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
    }

    public void run() {


        try {
            downloadAndClone();
        } catch (Exception e) {

        }
    }
}

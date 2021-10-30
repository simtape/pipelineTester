package it.unimol.tester.repo_cloner;

import it.unimol.tester.utilities.DirectoryReader;
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

        File file = new File("src/main/resources/list_repositories.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
        String uri = br.readLine();
        new File("temp").mkdirs();
        //TODO controllare se la cartella temp gia esiste, se esiste eliminarla prima
        //TODO GESTIRE ECCEZIONE SE MANCA INTERNET
        //TODO GESTIRE ECCEZIONE SE FILE PASSATO E' VUOTO
        //TODO gestire possibilità di avere due repo con autore diverso e nome uguale
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

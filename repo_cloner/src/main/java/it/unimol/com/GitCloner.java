package it.unimol.com;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GitCloner {

    private static GitCloner gitCloner = new GitCloner();

    public static GitCloner getGitCloner() {
        return gitCloner;
    }

    private String name_dir;

    private GitCloner() {
    }

    private void downloadAndClone() throws IOException, GitAPIException {
        File file = new File("C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\repo_cloner\\src\\main\\resources\\list_repositories.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String uri = br.readLine();
        while (uri != null) {

            Git.cloneRepository()
                    .setURI(uri)
                    .call();
            System.out.println(uri);
            uri = br.readLine();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);


        try {
            downloadAndClone();
        } catch (Exception e) {

        }
    }
}

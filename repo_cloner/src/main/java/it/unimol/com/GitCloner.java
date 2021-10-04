package it.unimol.com;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;

import java.io.File;
import java.util.Scanner;

public class GitCloner {

    private static GitCloner gitCloner = new GitCloner();

    public static GitCloner getGitCloner() {
        return gitCloner;
    }

    private String name_dir;

    private GitCloner() {
    }

    private void downloadAndClone(String uri/*, String directory*/) throws InvalidRemoteException, TransportException, GitAPIException {
/*        Git.cloneRepository()
                .setURI(uri)
                .setDirectory(new File(directory))
                .call();*/
        Git.cloneRepository()
                .setURI(uri)
                .call();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert uri of repository to download: ");
        String uri = sc.nextLine();

   /*     System.out.println("Insert name of the directory: ");
        name_dir =sc.nextLine();

*/
        try {
            downloadAndClone(uri);
        } catch (Exception e) {

        }
    }
}

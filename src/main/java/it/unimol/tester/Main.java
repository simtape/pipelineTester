package it.unimol.tester;


import it.unimol.tester.plugins.all_plugins.row_destroyer.NotAJavaProject;
import it.unimol.tester.plugins.all_plugins.row_destroyer.Project;
import it.unimol.tester.plugins.all_plugins.row_destroyer.RowDestroyer;
import it.unimol.tester.utilities.DirectoryReader;
import it.unimol.tester.repo_cloner.GitCloner;

import java.util.List;

public class Main {
    public static void main(String args[]) {
        final String directoryName = "C:\\Users\\scimo\\IdeaProjects\\pipeline_tester\\temp";
        GitCloner.getGitCloner().run();

        RowDestroyer rowDestroyer = new RowDestroyer();
        List<Project> projects = DirectoryReader
                .getDirectoryReader()
                .exploreDirectories(directoryName);


        try {
            rowDestroyer.run(projects);
        } catch (NotAJavaProject e) {
            System.out.println(e.getMessage());
        }
        for (Project project : projects){

            //TODO eseguire pipeline in locale
        }


    }
}

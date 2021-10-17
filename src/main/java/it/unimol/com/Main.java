package it.unimol.com;

import it.unimol.com.all_plugins.row_destroyer.NotAJavaProject;
import it.unimol.com.all_plugins.row_destroyer.RowDestroyer;

public class Main {
    public static void main(String args[]) {
        RowDestroyer rowDestroyer = new RowDestroyer();

        try {
            rowDestroyer.run();
        } catch (NotAJavaProject e) {
            System.out.println(e.getMessage());
        }


    }
}
